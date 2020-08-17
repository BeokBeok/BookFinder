package com.beok.bookfinder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.beok.bookfinder.datasource.BookSearchDataSourceFactory
import com.beok.bookfinder.model.BookItem
import com.beok.common.event.ViewModelToViewEvent
import com.beok.common.ext.safeLaunch
import com.beok.domain.BooksRepository
import kotlinx.coroutines.CoroutineExceptionHandler

class BookSearchViewModel @ViewModelInject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    var book: LiveData<PagedList<BookItem>>? = null
        private set

    private val _isShowResult = MutableLiveData<Boolean>()
    val isShowResult: LiveData<Boolean> get() = _isShowResult

    private val _resultCount = MutableLiveData(-1)
    val resultCount: LiveData<Int> get() = _resultCount

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errMessage = MutableLiveData<String>()
    val errMessage: LiveData<String> get() = _errMessage

    private val _selectedBuyLink = MutableLiveData<String>()
    val selectedBuyLink: LiveData<String> get() = _selectedBuyLink

    val searchBook = fun(keyword: String) {
        requestBookItems(keyword)
        requestResultCount(keyword)
    }

    fun onClick(item: BookItem) {
        if (item.buyLink.isEmpty()) _errMessage.value = "구매 링크가 존재하지 않습니다."
        _selectedBuyLink.value = item.buyLink
    }

    private val viewModelToViewEvent = ViewModelToViewEvent(
        showLoading = { _isLoading.postValue(it) },
        showErrorMessage = { _errMessage.postValue(it) }
    )

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errMessage.value = throwable.message ?: ""
    }

    private fun requestBookItems(keyword: String) =
        viewModelScope.safeLaunch(coroutineExceptionHandler) {
            book = BookSearchDataSourceFactory(
                keyword,
                booksRepository,
                viewModelToViewEvent
            ).toLiveData(config = Config(pageSize = PER_PAGE))
            _isShowResult.value = true
        }

    private fun requestResultCount(keyword: String) =
        viewModelScope.safeLaunch(coroutineExceptionHandler) {
            val result = booksRepository.searchBook(keyword = keyword, perPage = 1)
            if (result.isSuccess) {
                _resultCount.value = result.getOrNull()?.totalItems
            }
        }

    companion object {
        private const val PER_PAGE = 40
    }
}

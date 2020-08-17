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
import com.beok.common.ext.safeLaunch
import com.beok.domain.BooksRepository

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

    val searchBook = fun(keyword: String) {
        showLoading()
        requestBookItems(keyword)
        requestResultCount(keyword)
        hideLoading()
    }

    private fun requestBookItems(keyword: String) {
        book = BookSearchDataSourceFactory(keyword, booksRepository)
            .toLiveData(config = Config(pageSize = PER_PAGE))
        _isShowResult.value = true
    }

    private fun requestResultCount(keyword: String) = viewModelScope.safeLaunch {
        val result = booksRepository.searchBook(keyword = keyword, perPage = 1)
        if (result.isSuccess) {
            _resultCount.value = result.getOrNull()?.totalItems
        }
    }

    private fun showLoading() {
        _isLoading.value = true
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    companion object {
        private const val PER_PAGE = 40
    }
}

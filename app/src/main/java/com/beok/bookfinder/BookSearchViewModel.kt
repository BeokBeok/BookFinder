package com.beok.bookfinder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.bookfinder.model.BookItem
import com.beok.bookfinder.model.mapToPresenter
import com.beok.common.ext.safeLaunch
import com.beok.domain.BooksRepository
import com.beok.domain.entity.BooksResponse

class BookSearchViewModel @ViewModelInject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _book = MutableLiveData<List<BookItem>>()
    val book: LiveData<List<BookItem>> get() = _book

    private val _resultCount = MutableLiveData(0)
    val resultCount: LiveData<Int> get() = _resultCount

    private val _errMessage = MutableLiveData<String>()
    val errMessage: LiveData<String> get() = _errMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var keyword: String = ""
    private var currentPage: Int = START_PAGE

    private val bookItems = mutableListOf<BookItem>()

    val searchBook = fun(bookName: String) {
        setupKeyword(bookName)
        searchBook()
    }

    fun searchBook(isNext: Boolean = false) = viewModelScope.safeLaunch {
        setupCurrentPage(isNext)
        if (isLastPage()) return@safeLaunch

        showLoading()
        val result = booksRepository.searchBook(keyword, currentPage, PER_PAGE)
        if (result.isFailure) {
            _errMessage.value = result.exceptionOrNull()?.message ?: ""
            hideLoading()
            return@safeLaunch
        }
        setupBookSearchItems(result, isNext)
    }

    private fun setupBookSearchItems(result: Result<BooksResponse>, isNext: Boolean) {
        result.map { it.mapToPresenter() }.getOrNull()?.let {
            setupResultCount(it.totalItems, isNext)
            setupBookItems(it.items)
        }
        hideLoading()
    }

    private fun isLastPage(): Boolean {
        val isStartPage = currentPage == START_PAGE
        val isLastPage = currentPage == _resultCount.value
        return !isStartPage && isLastPage
    }

    private fun showLoading() {
        _isLoading.value = true
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun setupBookItems(items: List<BookItem>) {
        bookItems.addAll(items)
        _book.value = bookItems
    }

    private fun setupResultCount(totalItems: Int, isNext: Boolean = false) {
        if (isNext) return
        _resultCount.value = totalItems
    }

    private fun setupCurrentPage(isNext: Boolean) {
        if (!isNext) {
            currentPage = START_PAGE
            bookItems.clear()
            return
        }
        if (currentPage + PER_PAGE >= _resultCount.value ?: START_PAGE) {
            currentPage = _resultCount.value ?: START_PAGE
        } else {
            currentPage += PER_PAGE
        }
    }

    private fun setupKeyword(bookName: String) {
        keyword = bookName
    }

    companion object {
        private const val START_PAGE = 0
        private const val PER_PAGE = 40
    }
}

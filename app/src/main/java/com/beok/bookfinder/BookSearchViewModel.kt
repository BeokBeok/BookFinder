package com.beok.bookfinder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.bookfinder.model.BookItem
import com.beok.bookfinder.model.mapToPresenter
import com.beok.common.safeLaunch
import com.beok.domain.BooksRepository

class BookSearchViewModel @ViewModelInject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _book = MutableLiveData<List<BookItem>>()
    val book: LiveData<List<BookItem>> get() = _book

    private val _resultCount = MutableLiveData(0)
    val resultCount: LiveData<Int> get() = _resultCount

    private val _errMessage = MutableLiveData<String>()
    val errMessage: LiveData<String> get() = _errMessage

    val searchBook = fun(bookName: String) {
        searchBook(bookName)
    }

    private fun searchBook(bookName: String) = viewModelScope.safeLaunch {
        val result = booksRepository.searchBook(bookName)
        if (result.isFailure) {
            _errMessage.value = result.exceptionOrNull()?.message ?: ""
            return@safeLaunch
        }
        result.map { it.mapToPresenter() }.getOrNull()?.let {
            _resultCount.value = it.totalItems
            _book.value = it.items
        }

    }
}

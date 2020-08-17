package com.beok.bookfinder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookBuyInfoViewModel @ViewModelInject constructor() : ViewModel() {

    private val _buyLink = MutableLiveData<String>()
    val buyLink: LiveData<String> get() = _buyLink

    fun setupBuyLink(buyLink: String) {
        _buyLink.value = buyLink
    }
}

package com.beok.common

import android.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("bind_setOnQueryTextListener")
fun setOnQueryTextListener(searchView: SearchView, block: (String) -> Unit) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            block(query ?: "")
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean = false
    })
}

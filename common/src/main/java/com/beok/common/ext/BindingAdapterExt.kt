package com.beok.common.ext

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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

@BindingAdapter("bind_imageUrlWithGlide")
fun imageUrlWithGlide(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView)
        .load(imageUrl)
        .into(imageView)
}

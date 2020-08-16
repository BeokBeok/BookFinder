package com.beok.bookfinder

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.beok.bookfinder.databinding.ActivityBookSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookSearchActivity : AppCompatActivity() {

    private val viewModel by viewModels<BookSearchViewModel>()
    private lateinit var binding: ActivityBookSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_search)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}

package com.beok.bookfinder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.beok.bookfinder.databinding.ActivityBookSearchBinding
import com.beok.bookfinder.model.BookItem
import com.beok.common.base.BaseAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookSearchActivity : AppCompatActivity() {

    private val viewModel by viewModels<BookSearchViewModel>()
    private lateinit var binding: ActivityBookSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupRecyclerView()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.errMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupRecyclerView() {
        binding.rvBookSearchContents.run {
            adapter = BaseAdapter<BookItem>(
                layoutRes = R.layout.item_book,
                bindingId = BR.item
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1))
                        viewModel.searchBook(isNext = true)
                }
            })
        }
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_search)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}

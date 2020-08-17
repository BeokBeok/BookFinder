package com.beok.bookfinder

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
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
        viewModel.isShowResult.observe(this, Observer { isShowResult ->
            if (!isShowResult) return@Observer
            showContents()
        })
    }

    private fun showContents() {
        viewModel.book?.observe(this, Observer {
            @Suppress("UNCHECKED_CAST")
            (binding.rvBookSearchContents.adapter as BaseAdapter<BookItem>).submitList(it)
        })
    }

    private fun setupRecyclerView() {
        binding.rvBookSearchContents.run {
            adapter = BaseAdapter(
                layoutRes = R.layout.item_book,
                bindingId = BR.item,
                diffUtil = object : DiffUtil.ItemCallback<BookItem>() {
                    override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean =
                        oldItem == newItem

                    override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean =
                        oldItem.id == newItem.id
                }
            )
        }
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_search)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}

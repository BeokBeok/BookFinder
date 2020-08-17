package com.beok.bookfinder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.beok.bookfinder.databinding.ActivityBookSearchBinding
import com.beok.bookfinder.model.BookItem
import com.beok.common.base.BaseAdapter
import com.beok.common.ext.startActivity
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
        val owner = this
        viewModel.run {
            errMessage.observe(owner, Observer {
                Toast.makeText(owner, it, Toast.LENGTH_SHORT).show()
            })
            errResMessage.observe(owner, Observer {
                Toast.makeText(owner, getString(it), Toast.LENGTH_SHORT).show()
            })
            isShowResult.observe(owner, Observer { isShowResult ->
                if (!isShowResult) return@Observer
                showContents()
            })
            selectedBuyLink.observe(owner, Observer {
                startActivity<BookBuyInfoActivity>(bundleOf("buyLink" to it))
            })
        }
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
                viewModel = mapOf(BR.vm to viewModel),
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

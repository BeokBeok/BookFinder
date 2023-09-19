package com.beok.bookfinder.search

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.beok.bookfinder.BR
import com.beok.bookfinder.R
import com.beok.bookfinder.buyInfo.BookBuyInfoActivity
import com.beok.bookfinder.databinding.ActivityBookSearchBinding
import com.beok.bookfinder.model.BookItem
import com.beok.common.base.BaseActivity
import com.beok.common.base.BaseAdapter
import com.beok.common.ext.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookSearchActivity : BaseActivity<ActivityBookSearchBinding>(
    R.layout.activity_book_search
) {
    private val viewModel by viewModels<BookSearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()
        setupObserve()
    }

    override fun onResume() {
        super.onResume()
        binding.svBookSearch.clearFocus()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val gridLayoutManager = (binding.rvBookSearchContents.layoutManager as GridLayoutManager)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager.spanCount = LANDSCAPE_SPAN_COUNT
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager.spanCount = PORTRAIT_SPAN_COUNT
        }
    }

    override fun setupBinding() {
        super.setupBinding()
        binding.vm = viewModel
    }

    private fun setupObserve() {
        val owner = this
        viewModel.run {
            errMessage.observe(owner, Observer {
                Toast.makeText(owner, it, Toast.LENGTH_SHORT).show()
            })
            isShowResult.observe(owner, Observer { isShowResult ->
                if (!isShowResult) return@Observer
                showContents()
            })
            selectedBuyLink.observe(owner, Observer {
                if (it.isEmpty()) {
                    Toast.makeText(
                        owner,
                        getString(R.string.err_not_exist_buy_link),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Observer
                }
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
                diffUtil = setupBookSearchDiffUtil()
            )
        }
    }

    private fun setupBookSearchDiffUtil(): DiffUtil.ItemCallback<BookItem> =
        object : DiffUtil.ItemCallback<BookItem>() {
            override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean =
                oldItem.id == newItem.id
        }

    companion object {
        private const val PORTRAIT_SPAN_COUNT = 2
        private const val LANDSCAPE_SPAN_COUNT = 4
    }

}

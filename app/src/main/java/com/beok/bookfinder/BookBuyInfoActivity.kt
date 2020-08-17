package com.beok.bookfinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.beok.bookfinder.databinding.ActivityBookBuyInfoBinding
import com.beok.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookBuyInfoActivity : BaseActivity<ActivityBookBuyInfoBinding>(
    R.layout.activity_book_buy_info
) {

    private val viewModel by viewModels<BookBuyInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWebView()
        setupViewModel()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.buyLink.observe(this, Observer {
            binding.wvBookPreview.run {
                post { loadUrl(it) }
            }
        })
    }

    private fun setupViewModel() {
        viewModel.setupBuyLink((intent.extras?.get("buyLink") as? String) ?: "")
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setupWebView() {
        binding.wvBookPreview.run {
            webViewClient = setupWebViewClient()
            webChromeClient = setupWebChromeClient()
            settings.javaScriptEnabled = true
        }
    }

    private fun setupWebChromeClient(): WebChromeClient {
        return object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.pbBookPreview.run {
                    if (newProgress == 100) {
                        visibility = View.GONE
                    }
                    progress = newProgress
                }
            }
        }
    }

    private fun WebView.setupWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                loadUrl(request?.url.toString())
                return true
            }
        }
    }
}

package com.nyt.views;

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.dastanapps.dastanlib.utils.WindowUtils
import com.nyt.mostpopular.R
import kotlinx.android.synthetic.main.nytwebview.*

/**
 * Created by dastaniqbal on 28/02/2018.
 * dastanIqbal@marvelmedia.com
 * 28/02/2018 5:45
 */
open class NytWebView : AppCompatActivity() {
    companion object {
        val NYTWEBVIEW_URL = "nytwebviewurl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nytwebview)

        WindowUtils.setStatusIconColor(this, false)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent != null) {
            val loadUrl = intent.getStringExtra(NYTWEBVIEW_URL)
            webview.settings.javaScriptEnabled = true
            webview.settings.setSupportZoom(true)
            webview.loadUrl(loadUrl)
            webview.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true;
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressbar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressbar.visibility = View.GONE
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                    progressbar.visibility = View.GONE
                }

                override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                    super.onReceivedHttpError(view, request, errorResponse)
                    progressbar.visibility = View.GONE
                }

                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    super.onReceivedSslError(view, handler, error)
                    progressbar.visibility = View.GONE
                }

                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    progressbar.visibility = View.GONE
                }
            }
            webview.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    progressbar.progress = newProgress
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
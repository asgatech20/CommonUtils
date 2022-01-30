package com.asgatech.commonutils

import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import android.webkit.WebViewClient


/**
 * WebView Helpers to handle showing of content inside WebView in different languages
 */
class WebViewHelper {
    private val SCROLL_DELAY = 50L  // the delay time before scrolling the webView

    /**
     * Build the WebView with specifying if its rtl or ltr language
     */
    fun setWebViewHtml(webView: WebView, htmlString: String?, isRtlLang: Boolean) {
        if (htmlString == null) return
        handleOnPageLoaded(webView, isRtlLang)
        webView.loadData(
            getHtmlString(htmlString, isRtlLang),
            "text/html; charset=utf-8",
            "UTF-8"
        )
    }

    /**
     * Build the HTML String
     */
    private fun getHtmlString(htmlString: String, isRtlLang: Boolean): String {
        return "<html" + (if (isRtlLang) " dir='rtl'" else " ") + "><head></head><body> " + htmlString + "</body></html>"
    }

    private fun handleOnPageLoaded(webView: WebView, htmlIsArabic: Boolean) {
        if (htmlIsArabic) webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                handleScrollToStart(view)
            }
        }
    }

    /**
     * as webView scrolls to most left even in RTL
     */
    private fun handleScrollToStart(view: WebView) {
        Handler(Looper.getMainLooper()).postDelayed({ scrollToStart(view) }, SCROLL_DELAY)
    }

    /**
     * Scroll the WebView to start of screen
     */
    private fun scrollToStart(view: WebView) {
        view.scrollTo(view.right + 50, 0)
    }
}
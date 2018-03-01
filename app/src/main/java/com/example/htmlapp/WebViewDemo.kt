package com.example.htmlapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewDemo : Activity() {
    private lateinit var wSettings: WebSettings

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this)
        webView.isClickable = true
        wSettings = webView.settings
        wSettings.javaScriptEnabled = true

        // <b> Support Classes For WebView </b>
        val webViewClient = WebClientClass()
        webView.setWebViewClient(webViewClient)
        val webChromeClient = WebChromeClient()
        webView.setWebChromeClient(webChromeClient)

        // Now Added Java Interface Class
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        // Load Our Custom JS Inside WebView
        webView.loadUrl("file:///android_asset/demo.html")
        setContentView(webView)
    }

    inner class WebClientClass : WebViewClient() {
        internal var pd: ProgressDialog? = null

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            super.onPageStarted(view, url, favicon)
            pd = ProgressDialog(this@WebViewDemo)
            pd!!.setTitle("Please wait")
            pd!!.setMessage("Page is loading..")
            pd!!.show()
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            pd!!.dismiss()
        }
    }

    inner class WebChromeClass : WebChromeClient()

}
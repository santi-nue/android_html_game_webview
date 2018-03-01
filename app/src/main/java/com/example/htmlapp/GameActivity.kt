package com.example.htmlapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class GameActivity : Activity() {
    private lateinit var wSettings: WebSettings

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gamePath = intent.getStringExtra("game_path")
        Log.d("GameActivity", "gamePath====" + gamePath)

        val webView = WebView(this)
        webView.isClickable = true
        wSettings = webView.settings
        wSettings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.setSupportMultipleWindows(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.isHorizontalScrollBarEnabled = false
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.settings.allowFileAccessFromFileURLs = true
        webView.settings.allowUniversalAccessFromFileURLs = true

        // <b> Support Classes For WebView </b>
        val webViewClient = WebViewClient()
        webView.setWebViewClient(webViewClient)
        val webChromeClient = WebChromeClient()
        webView.setWebChromeClient(webChromeClient)

        // Now Added Java Interface Class
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        // Load Our Custom JS Inside WebView
        webView.loadUrl(gamePath)
        //        webView.loadUrl("file:///android_asset/pop.html");
        setContentView(webView)
    }

}

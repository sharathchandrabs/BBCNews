package com.example.shara.bbcnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class displayNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);
        String url = getIntent().getExtras().getString("TO_DISPLAYWEB");
        WebView webView = (WebView) findViewById(R.id.displayNewsWebView);
        webView.loadUrl(url);
    }
}

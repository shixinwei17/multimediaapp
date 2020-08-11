package com.neusoft.shixinwei.multimediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView=findViewById(R.id.url);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);//控制网页放大缩小

        webView.loadUrl("http://www.musictool.top/");//加载默认网页
    }
    public void onBackBtnClick(View v){
        if (webView.canGoBack()){
            webView.goBack();
        }
    }
    public void onForwardBtnClick(View v){
        if (webView.canGoForward()){
            webView.goForward();
        }
    }
}

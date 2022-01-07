package com.bccrishra.in;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView m_webview;
    private long back_pressed_time;
    private Toast back_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_webview=(WebView)findViewById(R.id.my_wv);
        WebSettings webSettings =m_webview.getSettings();
        m_webview.getSettings().setJavaScriptEnabled(true);
        m_webview.requestFocusFromTouch();
        m_webview.setWebViewClient(new WebViewClient());
        m_webview.setWebChromeClient(new WebChromeClient());
        m_webview.loadUrl("https://www.bccrishra.in/");
        m_webview.getSettings().setSupportZoom(true);
        m_webview.getSettings().setBuiltInZoomControls(true);
        m_webview.getSettings().setDisplayZoomControls(false);
        m_webview.getSettings().setUseWideViewPort(true);
        m_webview.setInitialScale(1);





            //download file
        m_webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                //pdf opener
                Intent intent;
                if (url.endsWith(".pdf")){
                    intent = new Intent("android.intent.action.VIEW",Uri.parse(url));
                }else{
                    intent = new Intent("android.intent.action.VIEW",Uri.parse(url));
                }
                startActivity(intent);

            }
        });
    }


    @Override
    public void onBackPressed() {

        if (m_webview.canGoBack()){
            m_webview.goBack();
        }else {
            if (back_pressed_time+2000>System.currentTimeMillis()){
                super.onBackPressed();
                return;
            }else {
                back_toast=Toast.makeText(getBaseContext(),"Press again to exit",Toast.LENGTH_SHORT);
                back_toast.show();
            }back_pressed_time = System.currentTimeMillis();
        }

    }

}

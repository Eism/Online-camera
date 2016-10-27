package com.example.onlinecamera.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.onlinecamera.R;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        WebView webView=(WebView) findViewById(R.id.webViewPlayer);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://ru.webcams.travel/webcam/stream/1437608431?autoplay=1");

        //String frameVideo = "<html><body><iframe hight=\""+webView.getHeight()+"\" wight=\""+webView.getWidth()+"\" src=\"http://ru.webcams.travel/webcam/stream/1182089417?autoplay=1\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe></body></html>";
        //webView.loadData(frameVideo, "text/html", "utf-8");
    }
}

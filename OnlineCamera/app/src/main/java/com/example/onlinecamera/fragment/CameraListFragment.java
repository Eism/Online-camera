package com.example.onlinecamera.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinecamera.R;
import com.example.onlinecamera.activity.BaseActivity;
import com.example.onlinecamera.activity.PlayerActivity;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by ELNUR on 22.10.2016.
 */

public class CameraListFragment extends ListFragment {

    private final String[] catnames = new String[]{"Рыжик", "Барсик",
            "Мурзик", "Мурка", "Васька", "Томасина", "Бобик", "Кристина",
            "Пушок", "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.camera_list, container, false);


        ListAdapter myListAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.item_camera_list, R.id.titleCamera, catnames);
        setListAdapter(myListAdapter);
        setRetainInstance(true);


        //new CallMashapeAsync().execute();

        return root;
    }

    public void onCklikPlay() {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
/*
        Long ID=id;

        Toast.makeText(getActivity(),
                ID.toString(),
                Toast.LENGTH_LONG).show();

        String frameVideo = "<html><body><iframe src=\"http://ru.webcams.travel/webcam/stream/1182089417?autoplay=1\" frameborder=\"0\" allowfullscreen></iframe></body></html>";


        ViewGroup viewGroup =(ViewGroup) v;

        final WebView webView=(WebView) viewGroup.findViewById(R.id.webView1);
        final FrameLayout container = (FrameLayout) viewGroup.findViewById(R.id.fullscreen_container);
        final WebView webViewFullscrean = (WebView) viewGroup.findViewById(R.id.web_view);

        webView.bringToFront();
/*
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadData(frameVideo, "text/html", "utf-8");
        webView.setVerticalScrollBarEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://ru.webcams.travel/webcam/stream/1182089417");
        */
    }
}

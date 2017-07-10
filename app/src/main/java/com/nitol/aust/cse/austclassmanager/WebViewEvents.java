package com.nitol.aust.cse.austclassmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewEvents  extends Fragment{

    View v;
    WebView wb4;
    SwipeRefreshLayout mySwipeRefreshLayout;

    private String currentUrl = "http://aust.edu/news_events.htm";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.webview_event, container,false);

        mySwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        wb4 = (WebView) v.findViewById(R.id.webView_event);

        wb4.setInitialScale(1);
        wb4.getSettings().setJavaScriptEnabled(true);
        wb4.getSettings().setLoadWithOverviewMode(true);
        wb4.getSettings().setUseWideViewPort(true);
        wb4.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wb4.setScrollbarFadingEnabled(false);
        wb4.loadUrl(currentUrl);
        wb4.setWebViewClient(new WebViewClient());
        wb4.getSettings().setBuiltInZoomControls(true);
        wb4.getSettings().setUseWideViewPort(true);
        wb4.getSettings().setLoadWithOverviewMode(true);
        wb4.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);



        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        wb4.loadUrl(currentUrl);
                        wb4.setWebViewClient(new MyWebViewClient());
                    }
                }
        );


        return v;

    }

    public class MyWebViewClient extends WebViewClient{

        @Override
        public void onPageFinished(WebView view, String url) {
            mySwipeRefreshLayout.setRefreshing(false);
            currentUrl = url;
            super.onPageFinished(view, url);
        }
    }

}

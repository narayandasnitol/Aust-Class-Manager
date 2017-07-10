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


public class WebViewAcademic extends Fragment {

    View v;
    WebView wb0;
    SwipeRefreshLayout mySwipeRefreshLayout;

    private String currentUrl = "http://aust.edu/academic_cal.htm";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.webview_academic, container,false);

        wb0 = (WebView) v.findViewById(R.id.webView_academic);
        mySwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);

        wb0.setInitialScale(1);
        wb0.getSettings().setJavaScriptEnabled(true);
        wb0.getSettings().setLoadWithOverviewMode(true);
        wb0.getSettings().setUseWideViewPort(true);
        wb0.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wb0.setScrollbarFadingEnabled(false);
        wb0.loadUrl(currentUrl);
        wb0.setWebViewClient(new WebViewClient());
        wb0.getSettings().setBuiltInZoomControls(true);
        wb0.getSettings().setUseWideViewPort(true);
        wb0.getSettings().setLoadWithOverviewMode(true);
        wb0.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        wb0.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        wb0.loadUrl(currentUrl);
                        wb0.setWebViewClient(new MyWebViewClient());
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

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains(currentUrl)){
                //do something
            }
            return true;
        }
    }

}

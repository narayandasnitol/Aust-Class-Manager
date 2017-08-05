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

public class WebViewTeacher extends Fragment {

    View v;
    WebView wb6;
    SwipeRefreshLayout mySwipeRefreshLayout;

    String currentUrl = "http://aust.edu/cse/fm_cse_ft.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.webview_teacher, container,false);

        wb6 = (WebView) v.findViewById(R.id.webView_teacher);
        mySwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);

        wb6.setInitialScale(1);
        wb6.getSettings().setJavaScriptEnabled(true);
        wb6.getSettings().setLoadWithOverviewMode(true);
        wb6.getSettings().setUseWideViewPort(true);
        wb6.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wb6.setScrollbarFadingEnabled(false);
        wb6.loadUrl(currentUrl);
        wb6.setWebViewClient(new WebViewClient());
        wb6.getSettings().setBuiltInZoomControls(true);
        wb6.getSettings().setUseWideViewPort(true);
        wb6.getSettings().setLoadWithOverviewMode(true);
        wb6.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        wb6.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        wb6.loadUrl(currentUrl);
                        wb6.setWebViewClient(new MyWebViewClient());
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

            }
            return true;
        }
    }

}

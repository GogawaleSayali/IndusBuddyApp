package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.apphomeactivity.AppHomeActivity;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

public class IndusSitesURLActivity extends BaseActivity {
    private WebView webViewIndus;
    private String url = "http://www.indusites.com/member-login-indusite.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indus_sites_url);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"indusites.com");
        webViewIndus = findViewById(R.id.webViewIndus);
        webViewIndus.setWebViewClient(new WebViewClient());
        if (AppHomeActivity.ROLE.equalsIgnoreCase("M")){
            url = ApiUrl.CD_USER_URL;
        }else if (AppHomeActivity.ROLE .equalsIgnoreCase("P") || AppHomeActivity.ROLE.equalsIgnoreCase("T")){
            url = ApiUrl.PHARMA_AND_TELESALES_USER_URL;
        }
        //webViewIndus.loadUrl(url);
        if (NetworkUtility.isNetworkAvailable(IndusSitesURLActivity.this)) {
            openURLinBrowser();
        }else {
            snackInternet();
        }
    }

    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(webViewIndus, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    public void openURLinBrowser(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

}

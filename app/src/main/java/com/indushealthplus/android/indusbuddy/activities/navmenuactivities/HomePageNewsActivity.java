package com.indushealthplus.android.indusbuddy.activities.navmenuactivities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.PorterDuff;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivityNoMenu;
import com.indushealthplus.android.indusbuddy.adapters.ArchiveYearAdapter;
import com.indushealthplus.android.indusbuddy.adapters.HomePageNewsAdapter;
import com.indushealthplus.android.indusbuddy.models.ModelHomePageNews;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageNewsActivity extends BaseActivityNoMenu implements View.OnClickListener{
    private RecyclerView rvHomePageNews;
    private FloatingActionButton fabDates;
    private HomePageNewsAdapter adapter;
    private ImageView ivNewsOne,ivNewsTwo,ivNewsThree;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_news);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Indus Updates");
        initialize();
    }

    /**
     * Initialize Ui widgets.
     */
    private void initialize() {
        rvHomePageNews = findViewById(R.id.rvHomePageNews);
        fabDates       = findViewById(R.id.fab);
        ivNewsOne      = findViewById(R.id.ivNewsOne);
        ivNewsTwo      = findViewById(R.id.ivNewsTwo);
        ivNewsThree    = findViewById(R.id.ivNewsThree);
        changeImageColor("white",ivNewsOne);
        changeImageColor("white",ivNewsTwo);
        changeImageColor("white",ivNewsThree);
        setListeners();
        getHomePageNews("Home page news");
    }

    private void showYears() {

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.archives_year_layout);
        ArrayList<String> years = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = 2014;
        years.add("Home page news");
        int lastYear = calendar.get(Calendar.YEAR);
        while(year<=lastYear-1){
            years.add(""+year);
            Log.v("YEARS",""+year);
            year++;
        }
        RecyclerView rvYears = dialog.findViewById(R.id.rvYears);
        rvYears.setLayoutManager(new LinearLayoutManager(HomePageNewsActivity.this));
        ArchiveYearAdapter yearAdapter = new ArchiveYearAdapter(HomePageNewsActivity.this,years);
        rvYears.setAdapter(yearAdapter);
        dialog.show();

    }

    public void SelectYear(String year){
        if (dialog!=null){
            dialog.dismiss();
        }
        getHomePageNews(year);
    }

    /************************************
     * Fill image with color.           *
     * @param color : color tb be filled*
     * @param imageView : ImageView     *
     ************************************/
    private void changeImageColor(String color,ImageView imageView){
        if (color.equalsIgnoreCase("primary")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("white")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        }

    }

    private void getHomePageNews(String year) {
        if (NetworkUtility.isNetworkAvailable(HomePageNewsActivity.this)){
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_ICONNECT)
                    .create(ApiInterfaceGet.class);
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HomePageNewsActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            Callback<ArrayList<ModelHomePageNews>> call = new Callback<ArrayList<ModelHomePageNews>>() {
                @Override
                public void onResponse(Call<ArrayList<ModelHomePageNews>> call, Response<ArrayList<ModelHomePageNews>> response) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            ArrayList<ModelHomePageNews> ModelCentre = response.body();
                            if (ModelCentre != null) {
                                if (ModelCentre.size() > 0) {
                                    if (adapter == null) {
                                        adapter = new HomePageNewsAdapter(HomePageNewsActivity.this, ModelCentre);
                                        rvHomePageNews.setLayoutManager(new LinearLayoutManager(HomePageNewsActivity.this));
                                        rvHomePageNews.setAdapter(adapter);
                                    } else {
                                        adapter.updateList(ModelCentre);
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ModelHomePageNews>> call, Throwable t) {

                }
            };
            try {
                if (year.equalsIgnoreCase("Home page news")){
                    alertDialog.show();
                     interfaceGet.getHomePageNews().enqueue(call);
                }else {
                    alertDialog.show();
                     interfaceGet.getHomePageNews(year).enqueue(call);
                }
/*
                interfaceGet.getHomePageNews().enqueue(new Callback<ArrayList<ModelHomePageNews>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelHomePageNews>> call, Response<ArrayList<ModelHomePageNews>> response) {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                ArrayList<ModelHomePageNews> ModelCentre = response.body();
                                if (ModelCentre != null) {
                                    if (ModelCentre.size() > 0) {
                                        if (adapter == null) {
                                            adapter = new HomePageNewsAdapter(HomePageNewsActivity.this, ModelCentre);
                                            rvHomePageNews.setLayoutManager(new LinearLayoutManager(HomePageNewsActivity.this));
                                            rvHomePageNews.setAdapter(adapter);
                                        } else {
                                            adapter.updateList(ModelCentre);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelHomePageNews>> call, Throwable t) {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                    }
                });
*/
            }catch (Exception e){
                if (alertDialog.isShowing()) {
                    alertDialog.hide();
                }
                e.printStackTrace();
            }
        }else {
            snackInternet();
        }
    }
    /**
     * Set listeners to the ui widgets like "onClick".
     */
    private void setListeners() {
        fabDates.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                showYears();
                break;
        }
    }

    /**********************************
     * Show snake bor for no internet.*
     **********************************/
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(fabDates, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("Warning", new View.OnClickListener() {
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
}

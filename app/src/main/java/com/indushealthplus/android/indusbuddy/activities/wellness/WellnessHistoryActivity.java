package com.indushealthplus.android.indusbuddy.activities.wellness;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.adapters.PendingEventAdapter;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.HistoryEventResponse;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WellnessHistoryActivity extends BaseActivity {
    private RecyclerView rvHistory;
    private ProgressBar progressBar;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_willness_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initializeToolBar(toolbar, "History");
        initialize();
    }

    private void initialize() {
        rvHistory = findViewById(R.id.rvHistory);
        progressBar = findViewById(R.id.progressBar);
        mLayoutManager = new LinearLayoutManager(WellnessHistoryActivity.this);
        rvHistory.setLayoutManager(mLayoutManager);
        rvHistory.setItemAnimator(new DefaultItemAnimator());
        getHistory();
    }

    private void getHistory() {
        if (NetworkUtility.isNetworkAvailable(WellnessHistoryActivity.this)) {
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_WELLNESS).create(ApiInterfaceGet.class);
            progressBar.setVisibility(View.VISIBLE);
            SharedPrefsManager prefsManager = SharedPrefsManager
                    .getSharedInstance(WellnessHistoryActivity.this);
            String userId = prefsManager.getData(getString(R.string.shars_userid));
            //userId = "179997";
            String url = ApiUrl.Base_URL_WELLNESS + ApiUrl.eventHistory + userId;
            interfaceGet.getHistory(url).enqueue(new Callback<HistoryEventResponse>() {
                @Override
                public void onResponse(Call<HistoryEventResponse> call, Response<HistoryEventResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.body() != null) {
                        HistoryEventResponse eventResponse = response.body();
                        if (eventResponse.getStatusCode() == Constatnts.S_CODE_1) {
                            PendingEventAdapter adapter = new PendingEventAdapter(WellnessHistoryActivity.this, eventResponse.getHistoryModel());
                            rvHistory.setAdapter(adapter);
                        } else {
                            Toast.makeText(WellnessHistoryActivity.this, "" + eventResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<HistoryEventResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
    }else{
        snackInternet();
    }

}
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(rvHistory, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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

}

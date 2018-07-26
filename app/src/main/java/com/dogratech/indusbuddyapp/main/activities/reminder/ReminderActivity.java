package com.dogratech.indusbuddyapp.main.activities.reminder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.adapters.ReminderAdapter;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.ModelItemReminder;
import com.dogratech.indusbuddyapp.main.models.Model_Response_ReminderList;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReminderActivity extends BaseActivity {
    private String TAG = ReminderActivity.class.getName();
    private RecyclerView rv_reminder ;
    private ReminderAdapter adapter;
    private List<ModelItemReminder> reminderList;
    private RelativeLayout rlProgress;
    private SharedPrefsManager prefsManager;
    private String userId ;
    private TextView tvNoData;
    private ApiInterfaceGet interface_get ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        initialise();
        intialiseClass();
        setCommentData();
        requestGetReminderData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReminderActivity.this,SetReminderActivity.class);
              startActivityForResult(intent,2);
            }
        });
    }

    private void requestGetReminderData() {
        String url      = ApiUrl.Base_URL_INDUS + ApiUrl.getReminderByEHRId + userId;
        if(NetworkUtility.isNetworkAvailable(getApplicationContext())){
            rlProgress.setVisibility(View.VISIBLE);
            interface_get.getAllReminders(url).enqueue(new Callback<Model_Response_ReminderList>() {
                @Override
                public void onResponse(Call<Model_Response_ReminderList> call, Response<Model_Response_ReminderList> response) {
                    if (rlProgress.getVisibility() == View.VISIBLE){
                        rlProgress.setVisibility(View.GONE);
                    }
                    try{
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                reminderList = response.body().getReminderList().getItemReminders();
                                if (reminderList.size()>0) {
                                    tvNoData.setVisibility(View.GONE);
                                    adapter = new ReminderAdapter(ReminderActivity.this, reminderList);
                                    rv_reminder.setAdapter(adapter);
                                }else {
                                    tvNoData.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_Response_ReminderList> call, Throwable t) {
                    if (rlProgress.getVisibility() == View.VISIBLE){
                        rlProgress.setVisibility(View.GONE);
                    }
                    Log.d(TAG,t.toString());
                }
            });
        }else{
            snackInternet();
        }
    }

    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(rv_reminder, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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

    public void showReminder(int position) {
        ModelItemReminder itemReminder = reminderList.get(position);
        Gson gson = new Gson();
        String data = gson.toJson(itemReminder);
        Intent intent = new Intent(ReminderActivity.this,ReminderDetailsActivity.class);
        intent.putExtra("data",data);
        startActivity(intent);
    }

    private void setCommentData() {
        reminderList      = new ArrayList<>();
        RecyclerView      . LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_reminder       . setLayoutManager(mLayoutManager);
        rv_reminder       . setItemAnimator(new DefaultItemAnimator());
    }

    private void intialiseClass() {
        prefsManager    = SharedPrefsManager.getSharedInstance(getApplicationContext());
        userId          = prefsManager.getData(getString(R.string.shars_userid));
        interface_get   = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
    }

    private void initialise() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Reminder");
        rv_reminder     = findViewById(R.id.rv_reminder);
        rlProgress      = findViewById(R.id.rlProgress);
        tvNoData        = findViewById(R.id.tvNoData);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
         if (requestCode == 2) {
             requestGetReminderData();
         }
        }
    }
}

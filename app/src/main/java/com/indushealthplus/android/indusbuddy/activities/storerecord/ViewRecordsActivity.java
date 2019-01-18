package com.indushealthplus.android.indusbuddy.activities.storerecord;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.adapters.RecordsAdapter;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.helper.ErrorCodesAndMessagesManager;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.Model_Item_Report;
import com.indushealthplus.android.indusbuddy.models.ModelSelfUploadReports;
import com.indushealthplus.android.indusbuddy.models.ModelResGetAllReport;
import com.indushealthplus.android.indusbuddy.models.ViewRecordSupportModel;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import java.util.ArrayList;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**************************************************
 * This class shows the list of all stored records.*
 * @auther : AmolR.
 **************************************************/
public class ViewRecordsActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = getClass().getName();
    private RecyclerView rvViewTypes;
    private FloatingActionButton fabUpload;
    private ArrayList<ViewRecordSupportModel> modelRecordsList = new ArrayList<>();
    ;
    private ApiInterfaceGet interface_get;
    private TextView tvNoReportsMsg;
    private RecordsAdapter recordsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar, "View Records");
        initialize();
    }

    /****************************
     * Initialize all Ui Widgets*
     ****************************/
    private void initialize() {
        fabUpload = findViewById(R.id.fabUpload);
        rvViewTypes = findViewById(R.id.rvViewTypes);
        tvNoReportsMsg = findViewById(R.id.tvNoReportsMsg);
        setListeners();
        setData();
    }

    /**********************************************
     * Set listeners to the Widgets like "OnClick"*
     **********************************************/
    private void setListeners() {
        fabUpload.setOnClickListener(this);
    }


    /**********************************************************
     * Get Store record data from "getSelfUploadReportByEHRId"*
     * webservice and show in a recycler view.                *
     **********************************************************/
    private void setData() {
        if (NetworkUtility.isNetworkAvailable(ViewRecordsActivity.this)) {
            String userId = SharedPrefsManager.getSharedInstance(ViewRecordsActivity.this)
                    .getData(getString(R.string.shars_userid));

            String url = ApiUrl.Base_URL_INDUS + ApiUrl.getSelfUploadReportByEHRId + userId;
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ViewRecordsActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            try {
                alertDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            interface_get = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
            interface_get.getAllReport(url).enqueue(new Callback<ModelResGetAllReport>() {
                @Override
                public void onResponse(Call<ModelResGetAllReport> call, Response<ModelResGetAllReport> response) {
                    try {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                ModelResGetAllReport allReport = response.body();
                                if (allReport.getStatus_code() == Constatnts.S_CODE_0) {
                                    ModelSelfUploadReports selfUploadReports = allReport.getUploadReports();
                                    ArrayList<Model_Item_Report> RecordsList = selfUploadReports.getSelfUploadReports();
                                    ArrayList<String> ducplicateList = new ArrayList<>();

                                  for(int k=0;k<RecordsList.size();k++){
                                      ducplicateList.add(RecordsList.get(k).getComment());
                                  }


                                    HashSet<String> hashSet = new HashSet<String>();
                                    hashSet.addAll(ducplicateList);
                                    ducplicateList.clear();
                                    ducplicateList.addAll(hashSet);
                                    ArrayList<Model_Item_Report> RecordsListduplicates = selfUploadReports.getSelfUploadReports();
                                    if (ducplicateList.size() > 0) {
                                        for (int i = 0; i < ducplicateList.size(); i++) {
                                            ArrayList<Model_Item_Report> List =new ArrayList<>();

                                            for (int j = 0; j < RecordsListduplicates.size(); j++) {
                                                if (ducplicateList.get(i).equals(RecordsListduplicates.get(j).getComment())) {
                                                    List.add(RecordsListduplicates.get(j));
                                                }
                                                }
                                            if(List.size()>0){
                                                ViewRecordSupportModel item_report =new ViewRecordSupportModel();
                                                item_report.setCount(String.valueOf(List.size()));
                                                item_report.setName(ducplicateList.get(i));
                                                item_report.setRecordsList(List);
                                                modelRecordsList.add(item_report);

                                            }


                                        }

                                        recordsAdapter = new RecordsAdapter(ViewRecordsActivity.this, modelRecordsList);
                                        mLayoutManager = new LinearLayoutManager(ViewRecordsActivity.this);
                                        rvViewTypes.setLayoutManager(mLayoutManager);
                                        rvViewTypes.setAdapter(recordsAdapter);
                                    } else {
                                        tvNoReportsMsg.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    int error_code = allReport.getError_code();
                                    ErrorCodesAndMessagesManager errCodeMsg = ErrorCodesAndMessagesManager.getInstance();
                                    Log.e(TAG, errCodeMsg.getErrorMessage(error_code));
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ModelResGetAllReport> call, Throwable t) {
                    Log.d(TAG, t.toString());
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                }
            });
        } else {
            snackInternet();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabUpload:
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    /**********************************
     * Show snake bor for no internet.*
     **********************************/
    public void snackInternet() {
        Snackbar snackbar = Snackbar
                .make(fabUpload, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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

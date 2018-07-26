package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.uitility.DeviceUtility;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class FeedbackActivity extends BaseActivity implements View.OnClickListener{
    private MaterialRatingBar ratingbar;
    private LinearLayout llRating;
    private TextView tvVideoUpload;
    protected RelativeLayout rlGeneralInfo,rlServiceRelated,rlAdditionalInfo;
    protected LinearLayout   llGeneralInfo,llServiceRelatedInfo,llAdditionalInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initToolBar();
        initialize();
        setListeners();
    }

    private void setListeners() {
        rlGeneralInfo       .setOnClickListener(this);
        rlServiceRelated    .setOnClickListener(this);
        rlAdditionalInfo    .setOnClickListener(this);
        llGeneralInfo       .setOnClickListener(this);
        llServiceRelatedInfo.setOnClickListener(this);
        llAdditionalInfo    .setOnClickListener(this);

        ratingbar . setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if (rating < 5.0f){
                    llRating.setVisibility(View.VISIBLE);
                    tvVideoUpload.setVisibility(View.GONE);
                }else {
                    llRating.setVisibility(View.GONE);
                    tvVideoUpload.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initialize() {
        ratingbar     = findViewById(R.id.ratingbar);
        llRating      = findViewById(R.id.llRating);
        tvVideoUpload = findViewById(R.id.tvVideoUpload);
        rlGeneralInfo        = findViewById(R.id.rlGeneralInfo);
        rlServiceRelated     = findViewById(R.id.rlServiceRelated);
        rlAdditionalInfo     = findViewById(R.id.rlAdditionalInfo);
        llGeneralInfo        = findViewById(R.id.llGeneralInfo);
        llServiceRelatedInfo = findViewById(R.id.llServiceRelatedInfo);
        llAdditionalInfo     = findViewById(R.id.llAdditionalInfo);
        DeviceUtility.hideKeyBord(FeedbackActivity.this);
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Feedback");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlGeneralInfo:
                llGeneralInfo       .setVisibility(View.VISIBLE);
                llAdditionalInfo    .setVisibility(View.GONE);
                llServiceRelatedInfo.setVisibility(View.GONE);
                rlGeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                rlAdditionalInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlServiceRelated.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                break;
            case R.id.rlServiceRelated:
                llGeneralInfo       .setVisibility(View.GONE);
                llAdditionalInfo    .setVisibility(View.GONE);
                llServiceRelatedInfo.setVisibility(View.VISIBLE);
                rlGeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlAdditionalInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlServiceRelated.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.rlAdditionalInfo:
                llGeneralInfo       .setVisibility(View.GONE);
                llAdditionalInfo    .setVisibility(View.VISIBLE);
                llServiceRelatedInfo.setVisibility(View.GONE);
                rlGeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlAdditionalInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                rlServiceRelated.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

}

package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;

public class MainFeedBackActivity extends BaseActivity implements View.OnClickListener {
    protected RelativeLayout rlGeneralInfo,rlServiceRelated,rlAdditionalInfo;
    protected LinearLayout   llGeneralInfo,llServiceRelatedInfo,llAdditionalInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed_back);
        initToolBar();
        initialize();
        setListeners();
    }

    private void setListeners() {
        rlGeneralInfo   .setOnClickListener(this);
        rlServiceRelated.setOnClickListener(this);
        rlAdditionalInfo.setOnClickListener(this);

        llGeneralInfo       .setOnClickListener(this);
        llServiceRelatedInfo.setOnClickListener(this);
        llAdditionalInfo    .setOnClickListener(this);
    }

    private void initialize() {
        rlGeneralInfo        = findViewById(R.id.rlGeneralInfo);
        rlServiceRelated     = findViewById(R.id.rlServiceRelated);
        rlAdditionalInfo     = findViewById(R.id.rlAdditionalInfo);
        llGeneralInfo        = findViewById(R.id.llGeneralInfo);
        llServiceRelatedInfo = findViewById(R.id.llServiceRelatedInfo);
        llAdditionalInfo     = findViewById(R.id.llAdditionalInfo);
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Client feedback");
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

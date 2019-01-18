package com.indushealthplus.android.indusbuddy.activities.storerecord;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;

public class NamingReportActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivTestOne,ivTestTwo,ivSuitcase,ivPrescription;
    private TextView tvBackBtn,tvFinishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Fill in few details");
        initialize();
    }
    private void initialize() {
        ivTestOne = findViewById(R.id.ivTestOne);
        ivTestTwo = findViewById(R.id.ivTestTwo);
        ivSuitcase = findViewById(R.id.ivSuitcase);
        ivPrescription = findViewById(R.id.ivPrescription);
        tvBackBtn = findViewById(R.id.tvBackBtn);
        tvFinishBtn = findViewById(R.id.tvFinishBtn);

        changeImageColor("white",ivPrescription);
        changeImageColor("white",ivTestOne);
        changeImageColor("white",ivTestTwo);
        changeImageColor("white",ivSuitcase);

        setListeners();
    }

    private void setListeners() {
        tvBackBtn.setOnClickListener(this);
        tvFinishBtn.setOnClickListener(this);
    }

    /************************************
     * Fill image with color.           *
     * @param color : color tb be filled*
     * @param imageView : ImageView     *
     ************************************/
    private void changeImageColor(String color,ImageView imageView){
        if (color.equalsIgnoreCase("orange")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorOrange), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("white")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("brown")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorBrownDark), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("blue")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("green")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorGreenDark), PorterDuff.Mode.SRC_IN);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvBackBtn:
                onBackPressed();
                break;
            case R.id.tvFinishBtn:
                //TODO - Upload document with other info.
                break;
        }
    }
}

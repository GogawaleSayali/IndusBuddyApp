package com.indushealthplus.android.indusbuddy.activities.storerecord;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;

public class StoreRecordsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivcloudOne, ivMainCloud, ivThree, ivTwo, ivOne, ivMobile, ivUploadIcon,
            ivViewIcon, ivShareIcon;
    private RelativeLayout rlUploadRecords,rlViewRecords,rlDocumentType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_records);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Store Record");
        initialize();
        setListeners();
    }

    private void setListeners() {
        rlUploadRecords.setOnClickListener(this);
        rlViewRecords.setOnClickListener(this);
    }

    private void initialize() {
        ivcloudOne  = findViewById(R.id.ivcloudOne);
        ivMainCloud = findViewById(R.id.ivMainCloud);
        ivThree     = findViewById(R.id.ivThree);
        ivTwo       = findViewById(R.id.ivTwo);
        ivOne       = findViewById(R.id.ivOne);
        ivMobile    = findViewById(R.id.ivMobile);
        ivUploadIcon= findViewById(R.id.ivUploadIcon);
        ivViewIcon  = findViewById(R.id.ivViewIcon);
        ivShareIcon = findViewById(R.id.ivShareIcon);
        rlUploadRecords = findViewById(R.id.rlUploadRecords);
        rlViewRecords   = findViewById(R.id.rlViewRecords);
        rlDocumentType  = findViewById(R.id.rlDocumentType);

        changeImageColor("white",ivcloudOne);
        changeImageColor("white",ivMainCloud);
        changeImageColor("brown",ivThree);
        changeImageColor("brown",ivTwo);
        changeImageColor("brown",ivOne);
        changeImageColor("white",ivMobile);

        changeImageColor("blue",ivUploadIcon);
        changeImageColor("orange",ivViewIcon);
        changeImageColor("green",ivShareIcon);
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
            case R.id.rlUploadRecords:
                    startActivity(new Intent(StoreRecordsActivity.this,UploadMenuActivity.class));
                break;
            case R.id.rlViewRecords:
                startActivityForResult(new Intent(StoreRecordsActivity.this,ViewRecordsActivity.class),100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            if (resultCode == Activity.RESULT_OK){
                startActivity(new Intent(StoreRecordsActivity.this,UploadMenuActivity.class));
            }
        }
    }
}

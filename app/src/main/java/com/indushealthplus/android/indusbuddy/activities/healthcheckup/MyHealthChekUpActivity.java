package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.os.Bundle;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.helper.CheckUpSubMenuManager;
import com.indushealthplus.android.indusbuddy.uitility.DeviceUtility;

public class MyHealthChekUpActivity extends CheckUpSubMenuManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_health_chek_up);
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Health Checkup");
        DeviceUtility.hideKeyBord(MyHealthChekUpActivity.this);
        initialize();
        setListeners();
    }

    private void initialize() {
        tabs     = findViewById(R.id.EasyTabs);
        tvError  = findViewById(R.id.tvError);
        showTabNemu();
    }

    private void setListeners() {
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/
/*
    *//*****************************************************************
     * This method shows <- Arrow on top left corner of the activity.*
     *****************************************************************//*
    protected void initializeToolBar(Toolbar toolbar,String title){
        this.toolbar = toolbar;
        setSupportActionBar(this.toolbar);
        actionBar = getSupportActionBar();
        actionBar . setHomeButtonEnabled(true); // disable the button
        actionBar . setDisplayHomeAsUpEnabled(true); // remove the left caret
        actionBar . setDisplayShowHomeEnabled(true);
        actionBar.setTitle(title);
    }*/

}

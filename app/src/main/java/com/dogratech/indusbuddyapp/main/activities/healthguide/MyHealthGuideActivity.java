package com.dogratech.indusbuddyapp.main.activities.healthguide;

import android.os.Bundle;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.helper.GuideSubmenuManager;

public class MyHealthGuideActivity extends GuideSubmenuManager {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_health_guide);
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Health Guide");
        initialise();
        setListeners();
    }

    private void setListeners() {
    }

    private void initialise() {
        eTabHealthGuide = findViewById(R.id.eTabHealthGuide);
        tvError         = findViewById(R.id.tvError);
        setSubMenuTabs();
    }
}

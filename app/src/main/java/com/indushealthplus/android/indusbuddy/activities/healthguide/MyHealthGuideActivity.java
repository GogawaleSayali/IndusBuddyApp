package com.indushealthplus.android.indusbuddy.activities.healthguide;

import android.os.Bundle;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.helper.GuideSubmenuManager;

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

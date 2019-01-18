package com.indushealthplus.android.indusbuddy.activities.navmenuactivities;

import android.os.Bundle;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"About Us");
    }
}

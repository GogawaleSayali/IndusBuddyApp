package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;

public class CenterLocatorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_locator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Centre Locator");
    }

}

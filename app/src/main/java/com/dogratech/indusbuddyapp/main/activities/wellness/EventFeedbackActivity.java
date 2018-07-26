package com.dogratech.indusbuddyapp.main.activities.wellness;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;

public class EventFeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feedback);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Events Feedback");
    }



}

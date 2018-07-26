package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;

public class TestAVActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_av);

        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Test AV");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

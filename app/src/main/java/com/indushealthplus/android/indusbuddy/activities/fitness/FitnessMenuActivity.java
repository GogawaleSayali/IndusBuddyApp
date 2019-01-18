package com.indushealthplus.android.indusbuddy.activities.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;

public class FitnessMenuActivity extends BaseActivity {
    private TextView tvConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Fitness Tracker");
        initialize();

    }

    private void initialize() {
        tvConnect = findViewById(R.id.tvConnect);
        settListeners();
    }

    private void settListeners() {
        tvConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FitnessMenuActivity.this,MyFitnessActivity.class));
            }
        });
    }

}

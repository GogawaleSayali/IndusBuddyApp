package com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.fragments.WeekTimelineFragment;

public class FitnessTimeLineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_time_line);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Timeline");
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,new WeekTimelineFragment()).commit();
    }

}

package com.indushealthplus.android.indusbuddy.activities.fitness;

import android.os.Bundle;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.fragments.ActivitiesFragment;

/*******************************
 * Created by amolr            *
 *******************************/
public class MyFitnessHomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fitness_home);
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Fitness Tracker");
        initialize();

    }

    private void initialize() {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager!=null){
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new ActivitiesFragment()).commit();
        }
    }

}

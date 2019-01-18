package com.indushealthplus.android.indusbuddy.activities.fitness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.GoalFragment;

public class SetGoalActivity extends BaseActivity implements View.OnClickListener{
    private FrameLayout fragmentContainer;
    private RelativeLayout rlDialodSetGoal,rlProgressView;
    private TextView tvCancel,tvSave,tvEight,tvTen,tvTwelve,tvMore;
    private FloatingActionButton fab;
    public static String stepesNumber= "";
    private EditText etSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Your Goal");
        initialize();
        setListeners();
    }


    private void initialize() {
        fab               = findViewById(R.id.fab);
        rlProgressView    =  findViewById(R.id.rlProgressView);
        rlDialodSetGoal   =  findViewById(R.id.rlDialodSetGoal);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        tvCancel =  findViewById(R.id.tvCancel);
        tvSave   =  findViewById(R.id.tvSave);
        tvEight  =  findViewById(R.id.tvEight);
        tvTen    =  findViewById(R.id.tvTen);
        tvTwelve =  findViewById(R.id.tvTwelve);
        tvMore   =  findViewById(R.id.tvMore);
        etSteps  =  findViewById(R.id.etSteps);
    }

    private void setListeners() {
        fab  .setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvEight.setOnClickListener(this);
        tvTen.setOnClickListener(this);
        tvTwelve.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvEight:
                stepesNumber = "8000";
                updateGoal();
                break;
            case R.id.tvTen:
                stepesNumber = "10000";
                updateGoal();
                break;
            case R.id.tvTwelve:
                stepesNumber = "12000";
                updateGoal();
                break;
            case R.id.fab:
            case R.id.tvMore:
                rlDialodSetGoal.setVisibility(View.VISIBLE);
                break;
            case R.id.tvCancel:
                rlDialodSetGoal.setVisibility(View.GONE);
                break;
            case R.id.tvSave:
                rlDialodSetGoal.setVisibility(View.GONE);
                if (!etSteps.getText().toString().isEmpty()){
                    stepesNumber = etSteps.getText().toString().trim();
                    updateGoal();
                }
                break;
        }
    }

    private void updateGoal() {
        rlProgressView.setVisibility(View.VISIBLE);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager!=null){
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new GoalFragment()).commit();
        }
    }


}

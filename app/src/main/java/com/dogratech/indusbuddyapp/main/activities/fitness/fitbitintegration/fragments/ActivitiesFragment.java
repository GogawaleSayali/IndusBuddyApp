package com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.fragments;

import android.content.Loader;
import android.os.Bundle;
import android.view.View;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.ActivityService;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.ResourceLoaderResult;
import com.dogratech.indusbuddyapp.main.models.DailyActivitySummary;
import com.dogratech.indusbuddyapp.main.models.Goals;
import com.dogratech.indusbuddyapp.main.models.Summary;

import java.util.Date;
import java.util.Locale;

public class ActivitiesFragment extends InfoFragment<DailyActivitySummary> {

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public int getTitleResourceId() {
        return R.string.activity_info;
    }

    @Override
    protected int getLoaderId() {
        return DAILY_STEPS_LOADER_ID;
    }


    @Override
    public Loader<ResourceLoaderResult<DailyActivitySummary>> onCreateLoader(int id, Bundle args) {
        rlProgressView.setVisibility(View.VISIBLE);
        if(id == DAILY_STEPS_LOADER_ID) {
            return ActivityService.getDailyActivitySummaryLoader(getActivity(), new Date());
        }else if (id == DAILY_HEART_RATE_LOADER_ID){
            return  ActivityService.getHeartRate(getActivity());
        }else if (id == DAILY_SLEEP_LOADER_ID){
            return  ActivityService.getSleep(getActivity(),new Date());
        }else if (id == DAILY_WEIGHT_LOADER_ID){
            return  ActivityService.getWeight(getActivity(),new Date());
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<DailyActivitySummary>> loader,
                               ResourceLoaderResult<DailyActivitySummary> data) {
        super.onLoadFinished(loader, data);
        rlProgressView.setVisibility(View.GONE);
        if (data.isSuccessful()) {
            if (loader.getId() == DAILY_STEPS_LOADER_ID){
                showActivitySummary(data.getResult());
            }else if (loader.getId() == DAILY_HEART_RATE_LOADER_ID){
                showHeartRate(data.getResult());
            }else if (loader.getId() == DAILY_SLEEP_LOADER_ID){
                showSleep(data.getResult());
            }else if (loader.getId() == DAILY_WEIGHT_LOADER_ID){
                showWeight(data.getResult());
            }
        }
    }


    /**
     * Show resultant weight value on UI.
     * @param result : result of body weight.
     */
    private void showWeight(DailyActivitySummary result) {
        try {
            String resultWeight = result.getWeightGoal().getStartWeight()+
                    "/"+result.getWeightGoal().getWeight()+" Kgs";
           tvWeightKgs.setText(resultWeight);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Show resultant sleep value on UI.
     * @param result : result of sleep (total time in bed).
     */
    private void showSleep(DailyActivitySummary result) {
        if (result!=null){
            String resultSleep = ""+ result.getSummary().getGetTotalSleepBed();
            tvSleepHrs.setText(resultSleep);
        }
    }

    /**
     * Show resultant heart rate value on UI.
     * @param result : result of Heart Rate.
     */
    public void showHeartRate(DailyActivitySummary result){
        if (result.getActivitiesHeart()!=null){
            if (result.getActivitiesHeart().size()>0) {
                String resultHRate=""+result.getActivitiesHeart().get(0).getValue();
                tvHeartRate.setText(resultHRate);
            }
        }
    }

    /**
     * Show resultant daily steps summary value on UI.
     * @param result : result of Daily summary steps.
     */
    public void showActivitySummary(DailyActivitySummary result) {
        Summary summary  = result.getSummary();
        Goals goals      = result.getGoals();
        float progress   = getPercentage(goals.getSteps(), summary.getSteps());
        progress = 80f;
        progressBarGoal  .setProgress(progress);
        tvStepsCount     .setText(String.valueOf(summary.getSteps()));
        tvCaloriesBurn   .setText(String.valueOf(summary.getCaloriesOut()));
        String str       = "%d %s";
               str       = String.format(Locale.getDefault(),str, ((int) (progress)), "%");
        tvTodaysGoal     .setText(str);
        tvDistanceGoal   .setText(String.valueOf(goals.getDistance()));
        tvStepsGoal      .setText(String.valueOf(goals.getSteps()));
        tvCaloriesOutGoal.setText(String.valueOf(goals.getCaloriesOut()));
        tvFloorsGoal     .setText(String.valueOf(goals.getFloors()));
        tvFairlyActiveMin.setText(String.valueOf(summary.getFairlyActiveMinutes()));
        tvMarginalCal    .setText(String.valueOf(summary.getMarginalCalories()));
        tvSedentaryMin   .setText(String.valueOf(summary.getSedentaryMinutes()));
        tvLightActiveMin .setText(String.valueOf(summary.getLightlyActiveMinutes()));
        tvActivityCal    .setText(String.valueOf(summary.getActivityCalories()));
        tvVeryActiveMin  .setText(String.valueOf(summary.getVeryActiveMinutes()));
        tvCalOut         .setText(String.valueOf(summary.getCaloriesOut()));
        tvActiveScore    .setText(String.valueOf(summary.getActiveScore()));
        tvDistance       .setText(String.valueOf(summary.getDistances().get(2).getDistance()));
        tvFloors         .setText(String.valueOf(summary.getFloors()));
        tvCaloriesBMR    .setText(String.valueOf(summary.getCaloriesBMR()));
        tvElevation      .setText(String.valueOf(summary.getElevation()));
    }
}

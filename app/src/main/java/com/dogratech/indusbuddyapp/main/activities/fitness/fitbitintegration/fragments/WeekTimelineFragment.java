package com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.fragments;

import android.content.Loader;
import android.os.Bundle;
import android.view.View;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.ActivityService;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.ResourceLoaderResult;
import com.dogratech.indusbuddyapp.main.models.DailyActivitySummary;
import com.dogratech.indusbuddyapp.main.models.WeekHistoryModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class WeekTimelineFragment extends TimeLineinfoFragment<DailyActivitySummary> {
    public WeekTimelineFragment(){

    }


    @Override
    public int getTitleResourceId() {
        return R.string.activity_info;
    }

    @Override
    protected int getLoaderId() {
        return WEEK_STEPS_LOADER_ID;
    }


    @Override
    public Loader<ResourceLoaderResult<DailyActivitySummary>> onCreateLoader(int id, Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        if(id == WEEK_STEPS_LOADER_ID) {

            return ActivityService.getWeekSteps(getActivity(), dateStart,dateEnd);
        }else if (id == WEEK_CALORIES_LOADER_ID){
            return  ActivityService.getWeekCalories(getActivity(),dateStart,dateEnd);
        }
        return null;
    }



    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<DailyActivitySummary>> loader,
                               ResourceLoaderResult<DailyActivitySummary> data) {
        super.onLoadFinished(loader, data);
        if (data.isSuccessful()) {
            if (loader.getId() == WEEK_STEPS_LOADER_ID){
                showWeekSteps(data.getResult());
            }else if (loader.getId() == WEEK_CALORIES_LOADER_ID){
                showWeekCalories(data.getResult());
            }
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showWeekCalories(DailyActivitySummary result) {
        ArrayList<WeekHistoryModel> weekCalories = result.getWeekCalories();
        Collections.reverse(weekCalories);
        for (int i = 0 ; i<weekCalories.size();i++) {
            caloriesNumber.get(i).setText("Calories : "+weekCalories.get(i).getValue());
        }
        progressBar.setVisibility(View.GONE);
    }

    private void showWeekSteps(DailyActivitySummary result) {
        ArrayList<WeekHistoryModel> weekSteps = result.getWeekSteps();
        Collections.reverse(weekSteps);
        for (int i = 0 ; i<weekSteps.size();i++) {
            String[] dayss = getFormatedTo(weekSteps.get(i).getDateTime()).split(" ");
            dayNames.get(i).setText(dayss[0]);
            daysNumber.get(i).setText(dayss[1]);
            stepsNumber.get(i).setText("Steps : "+weekSteps.get(i).getValue());
        }
    }

    public String getFormatedTo(String date){
        DateFormat inFormat = new SimpleDateFormat( "yyyy-MM-dd");
        DateFormat outFormat = new SimpleDateFormat( "EE dd");
        String dddd = "";
        try {
            Date dateStr = inFormat.parse(date);
            dddd = outFormat.format(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!dddd.equalsIgnoreCase("")){
            return dddd;
        }
        return date;
    }

}

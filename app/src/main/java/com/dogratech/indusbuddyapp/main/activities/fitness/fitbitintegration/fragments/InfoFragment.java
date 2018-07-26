package com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.fitness.SetGoalActivity;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.FitnessTimeLineActivity;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.ResourceLoaderResult;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by amolr on 24/4/18.
 */

public abstract class InfoFragment<T> extends Fragment implements LoaderManager
        .LoaderCallbacks<ResourceLoaderResult<T>>, SwipeRefreshLayout.OnRefreshListener {
    private   View rootView;
    protected final String TAG = getClass().getSimpleName();
    protected final int DAILY_STEPS_LOADER_ID = 3;
    protected final int DAILY_HEART_RATE_LOADER_ID = 5;
    protected final int DAILY_SLEEP_LOADER_ID = 10;
    protected final int DAILY_WEIGHT_LOADER_ID = 20;
    protected RelativeLayout rlProgressView;
    protected ImageView ivHeart, ivSleep, ivWeight, ivSteps, ivBurn;
    protected RoundCornerProgressBar progressBarGoal;
    protected TextView tvStepsCount, tvCaloriesBurn, tvTodaysGoal, tvActiveMinGoal, tvDistanceGoal,
                       tvStepsGoal, tvCaloriesOutGoal,tvFloorsGoal, tvFairlyActiveMin,tvMarginalCal,
                       tvSedentaryMin, tvLightActiveMin, tvActivityCal, tvVeryActiveMin, tvCalOut,
                       tvActiveScore,tvCaloriesBMR, tvFloors, tvDistance, tvElevation, tvToday,
                       tvGoal, tvSetGoal, tvTimeLine, tvHeartRate, tvSleepHrs, tvWeightKgs;
    private LinearLayout llToday, llGoal;
    private WebView webview;
    private String whichResponse = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activities, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        changeImageColor("white",ivBurn);
        changeImageColor("white",ivSteps);
        changeImageColor("white",ivHeart);
        changeImageColor("white",ivSleep);
        changeImageColor("white",ivWeight);
        setListeners();
    }

    private void initialize() {
        whichResponse     = "";
        webview           = rootView.findViewById(R.id.webview);
        tvStepsCount      = rootView .findViewById(R.id.tvStepsCount);
        tvTodaysGoal      = rootView .findViewById(R.id.tvTodaysGoal);
        tvCaloriesBurn    = rootView .findViewById(R.id.tvCaloriesBurn);
        rlProgressView    = rootView.findViewById(R.id.rlProgressView);
        progressBarGoal   = rootView .findViewById(R.id.progressBarGoal);
        ivBurn            = rootView .findViewById(R.id.ivBurn);
        ivSteps           = rootView .findViewById(R.id.ivSteps);

        tvToday           = rootView .findViewById(R.id.tvToday);
        tvGoal            = rootView .findViewById(R.id.tvGoal);
        tvActiveMinGoal   = rootView .findViewById(R.id.tvActiveMinGoal);
        tvDistanceGoal    = rootView .findViewById(R.id.tvDistanceGoal);
        tvStepsGoal       = rootView .findViewById(R.id.tvStepsGoal);
        tvCaloriesOutGoal = rootView .findViewById(R.id.tvCaloriesOutGoal);
        tvFloorsGoal      = rootView .findViewById(R.id.tvFloorsGoal);

        tvFairlyActiveMin = rootView .findViewById(R.id.tvFairlyActiveMin);
        tvMarginalCal     = rootView .findViewById(R.id.tvMarginalCal);
        tvSedentaryMin    = rootView .findViewById(R.id.tvSedentaryMin);
        tvLightActiveMin  = rootView .findViewById(R.id.tvLightActiveMin);
        tvActivityCal     = rootView .findViewById(R.id.tvActivityCal);
        tvVeryActiveMin = rootView .findViewById(R.id.tvVeryActivMin);
        tvCalOut          = rootView .findViewById(R.id.tvCalOut);
        tvActiveScore     = rootView .findViewById(R.id.tvActiveScore);
        tvHeartRate       = rootView .findViewById(R.id.tvHeartRate);
        tvSleepHrs        = rootView .findViewById(R.id.tvSleepHrs);
        tvWeightKgs       = rootView .findViewById(R.id.tvWeightKgs);

        tvCaloriesBMR     = rootView .findViewById(R.id.tvCaloriesBMR);
        tvFloors          = rootView .findViewById(R.id.tvFloors);
        tvDistance        = rootView .findViewById(R.id.tvDistance);
        tvElevation       = rootView .findViewById(R.id.tvElevation);
        llToday           = rootView .findViewById(R.id.llToday);
        llGoal            = rootView .findViewById(R.id.llGoal);
        ivHeart           = rootView .findViewById(R.id.ivHeart);
        ivSleep           = rootView .findViewById(R.id.ivSleep);
        ivWeight           = rootView .findViewById(R.id.ivWeight);
        tvSetGoal         = rootView .findViewById(R.id.tvSetGoal);
        tvTimeLine        = rootView .findViewById(R.id.tvTimeLine);
        callFitBit();
    }

    private void callFitBit() {
       /* ActivitySummaryCaller activitySummaryCaller = new ActivitySummaryCaller();
        activitySummaryCaller.onCreateLoader(3,null);*/
    }

    private void setListeners() {
        tvToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvToday.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_left_colored));
                tvGoal.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_right_white));
                tvToday.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                tvGoal.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
                llToday.setVisibility(View.VISIBLE);
                llGoal.setVisibility(View.GONE);
            }
        });

        tvGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvGoal.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_right_colored));
                tvToday.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_left_white));
                tvGoal.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                tvToday.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
                llToday.setVisibility(View.GONE);
                llGoal.setVisibility(View.VISIBLE);
            }
        });
        tvTimeLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FitnessTimeLineActivity.class));
            }
        });
        tvSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetGoalActivity.class));
            }
        });
    }

    protected void changeImageColor(String color, ImageView imageView){
        if (color.equalsIgnoreCase("primary"))
            imageView.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        else if (color.equalsIgnoreCase("white"))
            imageView.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        else if (color.equalsIgnoreCase("orange"))
            imageView.setColorFilter(getResources().getColor(R.color.colorOrange), PorterDuff.Mode.SRC_IN);

    }


    protected float getPercentage(int goal,int completed){
        float pecentage = 0 ;
        pecentage = (100 * completed)/ goal ;
        return pecentage;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (whichResponse.isEmpty())
        getLoaderManager().initLoader(DAILY_STEPS_LOADER_ID, null, this).forceLoad();
        else if (whichResponse.equalsIgnoreCase("first"))
        getLoaderManager().initLoader(DAILY_HEART_RATE_LOADER_ID, null, this).forceLoad();
        else if (whichResponse.equalsIgnoreCase("second"))
        getLoaderManager().initLoader(DAILY_SLEEP_LOADER_ID, null, this).forceLoad();
        else if (whichResponse.equalsIgnoreCase("third"))
        getLoaderManager().initLoader(DAILY_WEIGHT_LOADER_ID, null, this).forceLoad();
        else if (whichResponse.equalsIgnoreCase("fifth")){
            whichResponse = "";
        }
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<T>> loader, ResourceLoaderResult<T> data) {
        if (loader.getId() == DAILY_STEPS_LOADER_ID) {
            whichResponse = "first";
        } else if (loader.getId() == DAILY_HEART_RATE_LOADER_ID) {
            whichResponse = "second";
        }else if (loader.getId() == DAILY_SLEEP_LOADER_ID) {
            whichResponse = "third";
        }else if (loader.getId() == DAILY_WEIGHT_LOADER_ID){
            whichResponse = "fifth";
        }
        onResume();
        rlProgressView.setVisibility(View.GONE);
        switch (data.getResultType()) {
            case ERROR:
                Log.e(TAG, "Error loading data" +data.getErrorMessage());
              //  Toast.makeText(getActivity(), R.string.error_loading_data+data.getErrorMessage(), Toast.LENGTH_LONG).show();
                break;
            case EXCEPTION:
                Log.e(TAG, "Error loading data" + data.getException().getMessage());
               // Toast.makeText(getActivity(), R.string.error_loading_data+data.getException().getMessage(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    public abstract int getTitleResourceId();

    protected abstract int getLoaderId();

    @Override
    public void onLoaderReset(Loader<ResourceLoaderResult<T>> loader) {
        //no-op
    }


    @Override
    public void onRefresh() {
        getLoaderManager().getLoader(getLoaderId()).forceLoad();
    }


    private String formatNumber(Number number) {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(number);
    }

    private boolean isImageUrl(String string) {
        return (string.startsWith("http") &&
                (string.endsWith("jpg")
                        || string.endsWith("gif")
                        || string.endsWith("png")));
    }

    protected void printKeys(StringBuilder stringBuilder, Object object) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new Gson().toJson(object));
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = jsonObject.get(key);
                if (!(value instanceof JSONObject)
                        && !(value instanceof JSONArray)) {
                    stringBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;<b>");
                    stringBuilder.append(key);
                    stringBuilder.append(":</b>&nbsp;");
                    if (value instanceof Number) {
                        stringBuilder.append(formatNumber((Number) value));
                    } else if (isImageUrl(value.toString())) {
                        stringBuilder.append("<br/>");
                        stringBuilder.append("<center><img src=\"");
                        stringBuilder.append(value.toString());
                        stringBuilder.append("\" width=\"150\" height=\"150\"></center>");
                    } else {
                        stringBuilder.append(value.toString());
                    }
                    stringBuilder.append("<br/>");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void setMainText(String text) {
        webview.loadData(text, "text/html", "UTF-8");

    }

}

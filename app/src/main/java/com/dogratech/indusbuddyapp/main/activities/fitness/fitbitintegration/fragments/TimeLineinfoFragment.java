package com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.ResourceLoaderResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public abstract class TimeLineinfoFragment<T> extends Fragment implements LoaderManager
        .LoaderCallbacks<ResourceLoaderResult<T>>, SwipeRefreshLayout.OnRefreshListener{
   private View rootView;
   protected final String TAG = getClass().getSimpleName();
   protected final int WEEK_STEPS_LOADER_ID = 501;
   protected final int WEEK_CALORIES_LOADER_ID = 502;
   private static String whichResponse = "";
   protected ProgressBar progressBar;
   protected Date dateStart;
   protected Date dateEnd;
   private TextView tvDateNumberOne, tvDayNameOne, tvDateNumberTwo, tvDayNameTwo, tvDateNumberThree,
           tvDayNameThree, tvDateNumberFour, tvDayNameFour, tvDateNumberFive, tvDayNameFive,
           tvDateNumberSix, tvDayNameSix,tvDayNameSeven, tvDateNumberSeven;
   private TextView tvStepesOne, tvCaloriesOne, tvStepesTwo, tvCaloriesTwo, tvStepesThree,
           tvCaloriesThree, tvStepesFour, tvCaloriesFour, tvStepsFive, tvCaloriesFive,tvStepsSix, tvCaloriesSix, tvStepsSeven,
           tvCaloriesSeven;
   protected ArrayList<TextView> dayNames = new ArrayList<TextView>();
   protected ArrayList<TextView> daysNumber = new ArrayList<TextView>();
   protected ArrayList<TextView> stepsNumber = new ArrayList<TextView>();
   protected ArrayList<TextView> caloriesNumber = new ArrayList<TextView>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateStart = new Date();
        dateEnd = subtractDays(dateStart,6);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_time_lineinfo, container, false);
        return rootView;
    }

    public static Date subtractDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, - days);
        return cal.getTime();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = rootView.findViewById(R.id.progressBar);
        tvDateNumberOne = rootView .findViewById(R.id.tvDateNumberOne);
        tvDayNameOne = rootView .findViewById(R.id.tvDayNameOne);
        tvDateNumberTwo = rootView .findViewById(R.id.tvDateNumberTwo);
        tvDayNameTwo = rootView .findViewById(R.id.tvDayNameTwo);
        tvDateNumberThree = rootView .findViewById(R.id.tvDateNumberThree);
        tvDayNameThree = rootView .findViewById(R.id.tvDayNameThree);
        tvDateNumberFour = rootView .findViewById(R.id.tvDateNumberFour);
        tvDayNameFour = rootView .findViewById(R.id.tvDayNameFour);
        tvDateNumberFive = rootView .findViewById(R.id.tvDateNumberFive);
        tvDayNameFive = rootView .findViewById(R.id.tvDayNameFive);
        tvDateNumberSix = rootView .findViewById(R.id.tvDateNumberSix);
        tvDayNameSix = rootView .findViewById(R.id.tvDayNameSix);
        tvDayNameSeven = rootView .findViewById(R.id.tvDayNameSeven);
        tvDateNumberSeven = rootView .findViewById(R.id.tvDateNumberSeven);

        tvStepesOne   = rootView .findViewById(R.id.tvStepesOne);
        tvCaloriesOne = rootView .findViewById(R.id.tvCaloriesOne);
        tvStepesTwo   = rootView .findViewById(R.id.tvStepesTwo);
        tvCaloriesTwo = rootView .findViewById(R.id.tvCaloriesTwo);
        tvStepesThree = rootView .findViewById(R.id.tvStepesThree);
        tvCaloriesThree = rootView .findViewById(R.id.tvCaloriesThree);
        tvStepesFour  = rootView .findViewById(R.id.tvStepesFour);
        tvCaloriesFour= rootView .findViewById(R.id.tvCaloriesFour);
        tvStepsFive    = rootView .findViewById(R.id.tvStepsFive);
        tvCaloriesFive = rootView .findViewById(R.id.tvCaloriesFive);
        tvStepsSix   = rootView .findViewById(R.id.tvStepsSix);
        tvCaloriesSix= rootView .findViewById(R.id.tvCaloriesSix);
        tvStepsSeven   = rootView .findViewById(R.id.tvStepsSeven);
        tvCaloriesSeven= rootView .findViewById(R.id.tvCaloriesSeven);
        dayNames.add(tvDayNameOne);
        dayNames.add(tvDayNameTwo);
        dayNames.add(tvDayNameThree);
        dayNames.add(tvDayNameFour);
        dayNames.add(tvDayNameFive);
        dayNames.add(tvDayNameSix);
        dayNames.add(tvDayNameSeven);
        daysNumber.add(tvDateNumberOne);
        daysNumber.add(tvDateNumberTwo);
        daysNumber.add(tvDateNumberThree);
        daysNumber.add(tvDateNumberFour);
        daysNumber.add(tvDateNumberFive);
        daysNumber.add(tvDateNumberSix);
        daysNumber.add(tvDateNumberSeven);
        stepsNumber.add(tvStepesOne);
        stepsNumber.add(tvStepesTwo);
        stepsNumber.add(tvStepesThree);
        stepsNumber.add(tvStepesFour);
        stepsNumber.add(tvStepsFive);
        stepsNumber.add(tvStepsSix);
        stepsNumber.add(tvStepsSeven);
        caloriesNumber.add(tvCaloriesOne);
        caloriesNumber.add(tvCaloriesTwo);
        caloriesNumber.add(tvCaloriesThree);
        caloriesNumber.add(tvCaloriesFour);
        caloriesNumber.add(tvCaloriesFive);
        caloriesNumber.add(tvCaloriesSix);
        caloriesNumber.add(tvCaloriesSeven);

    }

    @Override
    public Loader<ResourceLoaderResult<T>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<T>> loader, ResourceLoaderResult<T> data) {
        if (loader.getId() == WEEK_STEPS_LOADER_ID) {
            whichResponse = "first";
        } else if (loader.getId() == WEEK_CALORIES_LOADER_ID) {
            whichResponse = "second";
        }
        onResume();
        switch (data.getResultType()) {
            case ERROR:
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "Error loading data" +data.getErrorMessage());
                //  Toast.makeText(getActivity(), R.string.error_loading_data+data.getErrorMessage(), Toast.LENGTH_LONG).show();
                break;
            case EXCEPTION:
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "Error loading data" + data.getException().getMessage());
                // Toast.makeText(getActivity(), R.string.error_loading_data+data.getException().getMessage(), Toast.LENGTH_LONG).show();
                break;
                default: //progressBar.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<ResourceLoaderResult<T>> loader) {

    }

    public abstract int getTitleResourceId();

    protected abstract int getLoaderId();

    @Override
    public void onRefresh() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (whichResponse.isEmpty()){
            getLoaderManager().initLoader(WEEK_STEPS_LOADER_ID, null, this).forceLoad();
        }else if (whichResponse.equalsIgnoreCase("first")){
            getLoaderManager().initLoader(WEEK_CALORIES_LOADER_ID, null, this).forceLoad();
        }if (whichResponse.equalsIgnoreCase("second")){
            whichResponse = "";
        }
    }
}

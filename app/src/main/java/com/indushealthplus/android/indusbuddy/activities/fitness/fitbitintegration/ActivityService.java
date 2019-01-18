package com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration;

import android.app.Activity;
import android.content.Loader;

import com.indushealthplus.android.indusbuddy.models.DailyActivitySummary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by amolr on 24/4/18.
 */

public class ActivityService {
    private final static String ACTIVITIES_URL = "https://api.fitbit.com/1/user/-/activities/date/%s.json";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_ACTIVITIES_LOADER_FACTORY = new ResourceLoaderFactory<>(ACTIVITIES_URL, DailyActivitySummary.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>> getDailyActivitySummaryLoader(Activity activityContext, Date date) throws MissingScopesException, TokenExpiredException {
        return USER_ACTIVITIES_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.activity},"GET", dateFormat.format(date));
    }

    private final static String HEARTRATE_URL = "https://api.fitbit.com/1/user/-/activities/heart/date/%s/%s/%s/time/%s/%s.json";
    private static String day = "today",endDate = "1d",detail_level = "1sec",startTime = "00:00",endTime = "00:01";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_HEARTRATE_LOADER_FACTORY = new ResourceLoaderFactory<>(HEARTRATE_URL, DailyActivitySummary.class);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>> getHeartRate(Activity activityContext) throws MissingScopesException, TokenExpiredException {
        return USER_HEARTRATE_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.activity},"GET",day,endDate,detail_level,startTime,endTime);
    }

    private final static String SLEEP_URL = "https://api.fitbit.com/1.2/user/-/sleep/date/%s.json";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_SLEEP_LOADER_FACTORY = new ResourceLoaderFactory<>(SLEEP_URL, DailyActivitySummary.class);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>> getSleep(Activity activityContext,Date date) throws MissingScopesException, TokenExpiredException {
        return USER_SLEEP_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.activity},"GET",dateFormat.format(date));
    }

    private final static String Weight_URL = "https://api.fitbit.com/1/user/-/body/log/weight/goal.json";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_WEIGHT_LOADER_FACTORY = new ResourceLoaderFactory<>(Weight_URL, DailyActivitySummary.class);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>> getWeight(Activity activityContext,Date date) throws MissingScopesException, TokenExpiredException {
        return USER_WEIGHT_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.activity},"GET");
    }


    private final static String STEPS_URL = "https://api.fitbit.com/1/user/-/activities/tracker/steps/date/%s/%s.json";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_WEEK__STEPS_LOADER_FACTORY = new ResourceLoaderFactory<>(STEPS_URL, DailyActivitySummary.class);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>>
    getWeekSteps(Activity activityContext,Date startDate,Date endDate)
            throws MissingScopesException, TokenExpiredException {
        return USER_WEEK__STEPS_LOADER_FACTORY.newResourceLoader(activityContext,
                new Scope[]{Scope.activity},"GET",dateFormat.format(startDate),dateFormat.format(endDate));
    }


    private final static String CALORIES_URL = "https://api.fitbit.com/1/user/-/activities/tracker/calories/date/%s/%s.json";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_WEEK_CALORIES_LOADER_FACTORY
            = new ResourceLoaderFactory<>(CALORIES_URL, DailyActivitySummary.class);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>>
    getWeekCalories(Activity activityContext,Date startDate,Date endDate)
            throws MissingScopesException, TokenExpiredException {
        return USER_WEEK_CALORIES_LOADER_FACTORY.newResourceLoader(activityContext,
                new Scope[]{Scope.activity},"GET",dateFormat.format(startDate),dateFormat.format(endDate));
    }
}

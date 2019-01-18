package com.indushealthplus.android.indusbuddy.helper;

import android.app.Activity;
import android.content.Loader;

import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.MissingScopesException;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.ResourceLoaderFactory;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.ResourceLoaderResult;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.Scope;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.TokenExpiredException;
import com.indushealthplus.android.indusbuddy.models.Goals;

/*******************************
 * Created by amolr on 26/4/18.*
 *******************************/

public class UpdateGoalHelper {
    private static final String ACTIVITIES_URL = "https://api.fitbit.com/1/user/-/activities/goals/%s.json?type=%s&value=%s";
    private static final ResourceLoaderFactory<Goals> USER_STEP_GOAL_UPDATE_LOADER_FACTORY = new ResourceLoaderFactory<>(ACTIVITIES_URL, Goals.class);
    private static final String period = "daily";
    private static final String type = "steps";


    public static Loader<ResourceLoaderResult<Goals>> updateStepsGoal(Activity activityContext, String value) throws MissingScopesException, TokenExpiredException {
        return USER_STEP_GOAL_UPDATE_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.activity},"POST" ,period,type,value);
    }



    /*private static final String period = "daily";
    private static final String type = "steps";


    public static Loader<ResourceLoaderResult<Goals>> updateStepsGoal(Activity activityContext, String value) throws MissingScopesException, TokenExpiredException {
        String ACTIVITIES_URL = "https://api.fitbit.com/1/user/-/activities/goals/%s.json?type="+type+"&value="+value;
        ResourceLoaderFactory<Goals> USER_STEP_GOAL_UPDATE_LOADER_FACTORY = new ResourceLoaderFactory<>(ACTIVITIES_URL, Goals.class);
        return USER_STEP_GOAL_UPDATE_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.activity}, period);
    }*/
}

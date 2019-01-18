package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by amolr on 30/4/18.
 */

public class HeartRateItraDayModel {
    @SerializedName("activities-heart")
    private ArrayList<ActivitiesHeart> activitiesHeart;

    public ArrayList<ActivitiesHeart> getActivitiesHeart() {
        return activitiesHeart;
    }

    public void setActivitiesHeart(ArrayList<ActivitiesHeart> activitiesHeart) {
        this.activitiesHeart = activitiesHeart;
    }
}

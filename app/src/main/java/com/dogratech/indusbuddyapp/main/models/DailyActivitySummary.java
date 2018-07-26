package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolr on 24/4/18.
 */

public class DailyActivitySummary {

    @SerializedName("goal")
    private GoalWeight weightGoal;

    @SerializedName("weight")
    private ArrayList<String > weight;

    @SerializedName("activities-heart")
    private ArrayList<ActivitiesHeart> activitiesHeart;

    @SerializedName("activities")
    @Expose
    private List<Object> activities = new ArrayList<Object>();
    @SerializedName("goals")
    @Expose
    private Goals goals;
    @SerializedName("summary")
    @Expose
    private Summary summary;

    @SerializedName("activities-tracker-calories")
    private ArrayList<WeekHistoryModel> weekCalories;

    @SerializedName("activities-tracker-steps")
    private ArrayList<WeekHistoryModel> weekSteps;

    public ArrayList<WeekHistoryModel> getWeekCalories() {
        return weekCalories;
    }

    public void setWeekCalories(ArrayList<WeekHistoryModel> weekCalories) {
        this.weekCalories = weekCalories;
    }

    public ArrayList<WeekHistoryModel> getWeekSteps() {
        return weekSteps;
    }

    public void setWeekSteps(ArrayList<WeekHistoryModel> weekSteps) {
        this.weekSteps = weekSteps;
    }

    public GoalWeight getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(GoalWeight weightGoal) {
        this.weightGoal = weightGoal;
    }

    public ArrayList<String> getWeight() {
        return weight;
    }

    public void setWeight(ArrayList<String> weight) {
        this.weight = weight;
    }

    public ArrayList<ActivitiesHeart> getActivitiesHeart() {
        return activitiesHeart;
    }

    public void setActivitiesHeart(ArrayList<ActivitiesHeart> activitiesHeart) {
        this.activitiesHeart = activitiesHeart;
    }
    /**
     * @return The activities
     */
    public List<Object> getActivities() {
        return activities;
    }

    /**
     * @param activities The activities
     */
    public void setActivities(List<Object> activities) {
        this.activities = activities;
    }

    /**
     * @return The goals
     */
    public Goals getGoals() {
        return goals;
    }

    /**
     * @param goals The goals
     */
    public void setGoals(Goals goals) {
        this.goals = goals;
    }

    /**
     * @return The summary
     */
    public Summary getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}

package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolr on 24/4/18.
 */

public class Summary {

    @SerializedName("totalMinutesAsleep")
    private Integer totalSleep = 0;
    @SerializedName("totalSleepRecords")
    private Integer getTotalSleepRec = 0;
    @SerializedName("totalTimeInBed")
    private Integer getTotalSleepBed = 0;
    @SerializedName("activeScore")
    @Expose
    private Integer activeScore = 0;
    @SerializedName("activityCalories")
    @Expose
    private Integer activityCalories = 0;
    @SerializedName("caloriesBMR")
    @Expose
    private Integer caloriesBMR = 0;
    @SerializedName("caloriesOut")
    @Expose
    private Integer caloriesOut = 0;
    @SerializedName("distances")
    @Expose
    private List<Distance> distances = new ArrayList<Distance>();
    @SerializedName("elevation")
    @Expose
    private Double elevation = 0.0;
    @SerializedName("fairlyActiveMinutes")
    @Expose
    private Integer fairlyActiveMinutes = 0;
    @SerializedName("floors")
    @Expose
    private Integer floors = 0;
    @SerializedName("lightlyActiveMinutes")
    @Expose
    private Integer lightlyActiveMinutes = 0;
    @SerializedName("marginalCalories")
    @Expose
    private Integer marginalCalories = 0;
    @SerializedName("sedentaryMinutes")
    @Expose
    private Integer sedentaryMinutes = 0;
    @SerializedName("steps")
    @Expose
    private Integer steps = 0;
    @SerializedName("veryActiveMinutes")
    @Expose
    private Integer veryActiveMinutes = 0;

    /**
     * @return The activeScore
     */
    public Integer getActiveScore() {
        return activeScore;
    }

    /**
     * @param activeScore The activeScore
     */
    public void setActiveScore(Integer activeScore) {
        this.activeScore = activeScore;
    }

    /**
     * @return The activityCalories
     */
    public Integer getActivityCalories() {
        return activityCalories;
    }

    /**
     * @param activityCalories The activityCalories
     */
    public void setActivityCalories(Integer activityCalories) {
        this.activityCalories = activityCalories;
    }

    /**
     * @return The caloriesBMR
     */
    public Integer getCaloriesBMR() {
        return caloriesBMR;
    }

    /**
     * @param caloriesBMR The caloriesBMR
     */
    public void setCaloriesBMR(Integer caloriesBMR) {
        this.caloriesBMR = caloriesBMR;
    }

    /**
     * @return The caloriesOut
     */
    public Integer getCaloriesOut() {
        return caloriesOut;
    }

    /**
     * @param caloriesOut The caloriesOut
     */
    public void setCaloriesOut(Integer caloriesOut) {
        this.caloriesOut = caloriesOut;
    }

    /**
     * @return The distances
     */
    public List<Distance> getDistances() {
        return distances;
    }

    /**
     * @param distances The distances
     */
    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    /**
     * @return The elevation
     */
    public Double getElevation() {
        return elevation;
    }

    /**
     * @param elevation The elevation
     */
    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    /**
     * @return The fairlyActiveMinutes
     */
    public Integer getFairlyActiveMinutes() {
        return fairlyActiveMinutes;
    }

    /**
     * @param fairlyActiveMinutes The fairlyActiveMinutes
     */
    public void setFairlyActiveMinutes(Integer fairlyActiveMinutes) {
        this.fairlyActiveMinutes = fairlyActiveMinutes;
    }

    /**
     * @return The floors
     */
    public Integer getFloors() {
        return floors;
    }

    /**
     * @param floors The floors
     */
    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    /**
     * @return The lightlyActiveMinutes
     */
    public Integer getLightlyActiveMinutes() {
        return lightlyActiveMinutes;
    }

    /**
     * @param lightlyActiveMinutes The lightlyActiveMinutes
     */
    public void setLightlyActiveMinutes(Integer lightlyActiveMinutes) {
        this.lightlyActiveMinutes = lightlyActiveMinutes;
    }

    /**
     * @return The marginalCalories
     */
    public Integer getMarginalCalories() {
        return marginalCalories;
    }

    /**
     * @param marginalCalories The marginalCalories
     */
    public void setMarginalCalories(Integer marginalCalories) {
        this.marginalCalories = marginalCalories;
    }

    /**
     * @return The sedentaryMinutes
     */
    public Integer getSedentaryMinutes() {
        return sedentaryMinutes;
    }

    /**
     * @param sedentaryMinutes The sedentaryMinutes
     */
    public void setSedentaryMinutes(Integer sedentaryMinutes) {
        this.sedentaryMinutes = sedentaryMinutes;
    }

    /**
     * @return The steps
     */
    public Integer getSteps() {
        return steps;
    }

    /**
     * @param steps The steps
     */
    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    /**
     * @return The veryActiveMinutes
     */
    public Integer getVeryActiveMinutes() {
        return veryActiveMinutes;
    }

    /**
     * @param veryActiveMinutes The veryActiveMinutes
     */
    public void setVeryActiveMinutes(Integer veryActiveMinutes) {
        this.veryActiveMinutes = veryActiveMinutes;
    }
    public int getTotalSleep() {
        return totalSleep;
    }

    public void setTotalSleep(int totalSleep) {
        this.totalSleep = totalSleep;
    }

    public int getGetTotalSleepRec() {
        return getTotalSleepRec;
    }

    public void setGetTotalSleepRec(int getTotalSleepRec) {
        this.getTotalSleepRec = getTotalSleepRec;
    }

    public int getGetTotalSleepBed() {
        return getTotalSleepBed;
    }

    public void setGetTotalSleepBed(int getTotalSleepBed) {
        this.getTotalSleepBed = getTotalSleepBed;
    }
}

package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amolr on 24/4/18.
 */

public class Goals {
    @SerializedName("activeMinutes")
    @Expose
    private Integer activeMinutes = 0;
    @SerializedName("caloriesOut")
    @Expose
    private Integer caloriesOut = 0;
    @SerializedName("distance")
    @Expose
    private Double distance = 0.0;
    @SerializedName("floors")
    @Expose
    private Integer floors = 0;
    @SerializedName("steps")
    @Expose
    private Integer steps = 0;

    /**
     * @return The activeMinutes
     */
    public Integer getActiveMinutes() {
        return activeMinutes;
    }

    /**
     * @param activeMinutes The activeMinutes
     */
    public void setActiveMinutes(Integer activeMinutes) {
        this.activeMinutes = activeMinutes;
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
     * @return The distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
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
}

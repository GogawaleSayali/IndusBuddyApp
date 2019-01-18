package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amolr on 30/4/18.
 */

public class SleepSummaryModel {
    @SerializedName("totalMinutesAsleep")
    private int totalSleep;
    @SerializedName("totalSleepRecords")
    private int getTotalSleepRec;
    @SerializedName("totalTimeInBed")
    private int getTotalSleepBed;

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

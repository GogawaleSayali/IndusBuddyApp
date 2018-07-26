package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amolr on 30/4/18.
 */

public class DailySleepModel {
    /*{
  "sleep": [],
  "summary": {
    "totalMinutesAsleep": 0,
    "totalSleepRecords": 0,
    "totalTimeInBed": 0
  }
}*/

    @SerializedName("summary")
    private SleepSummaryModel sleepSummaryModel;

    public SleepSummaryModel getSleepSummaryModel() {
        return sleepSummaryModel;
    }

    public void setSleepSummaryModel(SleepSummaryModel sleepSummaryModel) {
        this.sleepSummaryModel = sleepSummaryModel;
    }
}

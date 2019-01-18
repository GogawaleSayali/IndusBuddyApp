package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

public class SlotsModel {
    @SerializedName("slotId")
    private String slotId;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String endTime;

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

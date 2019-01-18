package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

public class WeekHistoryModel {
    @SerializedName("dateTime")
    private String dateTime;
    @SerializedName("value")
    private String value;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

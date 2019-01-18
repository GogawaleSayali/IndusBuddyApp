package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

public class ReminderListItem
{
    @SerializedName("Date")
    private String duration;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

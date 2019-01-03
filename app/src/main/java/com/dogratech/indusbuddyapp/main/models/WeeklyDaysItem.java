package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

public class WeeklyDaysItem {
    @SerializedName("onText")
    private String onText;

    public String getOnText() {
        return onText;
    }

    public void setOnText(String onText) {
        this.onText = onText;
    }
}

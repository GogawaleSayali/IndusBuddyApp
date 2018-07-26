package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

public class ModelIsWellnessEvent {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("msg")
    private String  msg;
    @SerializedName("Event")
    private String isEvent;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsEvent() {
        return isEvent;
    }

    public void setIsEvent(String isEvent) {
        this.isEvent = isEvent;
    }
}

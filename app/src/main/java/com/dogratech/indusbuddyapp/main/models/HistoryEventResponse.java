package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HistoryEventResponse {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("msg")
    private String msg;
    @SerializedName("History")
    private ArrayList<EventItemModel> historyModel;

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

    public ArrayList<EventItemModel> getHistoryModel() {
        return historyModel;
    }

    public void setHistoryModel(ArrayList<EventItemModel> historyModel) {
        this.historyModel = historyModel;
    }
}

package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsMainModel {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("msg")
    private String msg;
    @SerializedName("groupEvent")
    private ArrayList<EventItemModel> grpEventItemModels;
    @SerializedName("individualEvent")
    private ArrayList<EventItemModel> individualEventItemModel;

    public EventsMainModel() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int status_code) {
        this.statusCode = status_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<EventItemModel> getGrpEventItemModels() {
        return grpEventItemModels;
    }

    public void setGrpEventItemModels(ArrayList<EventItemModel> grpEventItemModels) {
        this.grpEventItemModels = grpEventItemModels;
    }

    public ArrayList<EventItemModel> getIndividualEventItemModel() {
        return individualEventItemModel;
    }

    public void setIndividualEventItemModel(ArrayList<EventItemModel> individualEventItemModel) {
        this.individualEventItemModel = individualEventItemModel;
    }
}

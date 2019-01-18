package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelPostPoneReasonRes {
    @SerializedName("ListPostpondReason")
    private ArrayList<ModelReason> modelReason;
    @SerializedName("StatusCode")
    private int StatusCode;
    @SerializedName("ErrorCode")
    private int ErrorCode;

    public ArrayList<ModelReason> getModelReason() {
        return modelReason;
    }

    public void setModelReason(ArrayList<ModelReason> modelReason) {
        this.modelReason = modelReason;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }
}

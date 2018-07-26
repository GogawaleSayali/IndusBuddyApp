package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AvailedPackagesModel {
    @SerializedName("StatusCode")
    private int statusCode;

    @SerializedName("ErrorCode")
    private int errorCode;

    @SerializedName("PackageDetails")
    private ArrayList<PackageDetailsModel> packageDetailsModels;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<PackageDetailsModel> getPackageDetailsModels() {
        return packageDetailsModels;
    }

    public void setPackageDetailsModels(ArrayList<PackageDetailsModel> packageDetailsModels) {
        this.packageDetailsModels = packageDetailsModels;
    }
}

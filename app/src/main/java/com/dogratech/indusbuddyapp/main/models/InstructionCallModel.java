package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sayali.gogawale on 1/11/2019.
 */

public class InstructionCallModel {
    @SerializedName("StatusCode")
    private int statusCode;

    @SerializedName("ErrorCode")
    private int errorCode;

    @SerializedName("PackageDetails")
    private ArrayList<PckgwiseInstrctionCallModel> pckWise;

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

    public ArrayList<PckgwiseInstrctionCallModel> getPckWise() {
        return pckWise;
    }

    public void setPckWise(ArrayList<PckgwiseInstrctionCallModel> pckWise) {
        this.pckWise = pckWise;
    }
}

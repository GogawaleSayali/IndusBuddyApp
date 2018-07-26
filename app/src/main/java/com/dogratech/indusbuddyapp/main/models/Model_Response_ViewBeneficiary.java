package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Response_ViewBeneficiary {

    @SerializedName("StatusCode")
    private int StatusCode ;
    @SerializedName("ViewBeneficiary")
    private ArrayList<Model_Item_Beneficiary> beneficiaries;
    @SerializedName("ErrorCode")
    private int ErrorCode ;

    public Model_Response_ViewBeneficiary() {
    }

    public Model_Response_ViewBeneficiary(int statusCode,
                                          ArrayList<Model_Item_Beneficiary> beneficiaries,
                                          int errorCode) {
        StatusCode = statusCode;
        this.beneficiaries = beneficiaries;
        ErrorCode = errorCode;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public ArrayList<Model_Item_Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(ArrayList<Model_Item_Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }
}

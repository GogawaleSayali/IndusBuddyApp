package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberDetrailsModel {
    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("MemberDetails")
    private ArrayList<MemberDetails> memberDetails;
    @SerializedName("ErrorCode")
    private int errorCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<MemberDetails> getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(ArrayList<MemberDetails> memberDetails) {
        this.memberDetails = memberDetails;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

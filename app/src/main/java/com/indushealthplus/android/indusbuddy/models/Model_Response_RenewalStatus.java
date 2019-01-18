package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Response_RenewalStatus {
    /*
            *{
            "StatusCode": 0,
            "RenewalStatus": {
                "MemberId": 499573,
                "MemberName": "JADHAV AKSHAY PUNDLIK",
                "ExpiryDate": "02-DEC-2018"
            },
            "ErrorCode": 200
        }
    * */
    @SerializedName("StatusCode")
    private String StatusCode ;

    @SerializedName("RenewalStatus")
    private Model_Item_RenewalStatus itemRenewalStatus;

    @SerializedName("ErrorCode")
    private String ErrorCode ;

    public Model_Response_RenewalStatus() {
    }

    public Model_Response_RenewalStatus(String statusCode, Model_Item_RenewalStatus itemRenewalStatus, String errorCode) {
        StatusCode = statusCode;
        this.itemRenewalStatus = itemRenewalStatus;
        ErrorCode = errorCode;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public Model_Item_RenewalStatus getItemRenewalStatus() {
        return itemRenewalStatus;
    }

    public void setItemRenewalStatus(Model_Item_RenewalStatus itemRenewalStatus) {
        this.itemRenewalStatus = itemRenewalStatus;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }
}

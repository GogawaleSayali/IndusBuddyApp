package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by amolr on 20/3/18.
 */

public class ModelPaymentStatusResponse {
        @SerializedName("StatusCode")
        private int statusCode;
        @SerializedName("ErrorCode")
        private int errorCode;
        @SerializedName("PaymentStatus")
        private ArrayList<ModelPaymentStatus> PaymentStatus;

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

    public ArrayList<ModelPaymentStatus> getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(ArrayList<ModelPaymentStatus> paymentStatus) {
        PaymentStatus = paymentStatus;
    }
}

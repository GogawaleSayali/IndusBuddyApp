package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 20/3/18.
 */

public class Model_Response_AppReschedule {
    /*
            *{
                "StatusCode": 1,
                "ErrorCode": 700
            }
    * */

    @SerializedName("Message")
    private String message;

    @SerializedName("StatusCode")
    private int StatusCode ;

    @SerializedName("ErrorCode")
    private String ErrorCode ;

    public Model_Response_AppReschedule(int statusCode, String errorCode) {
        StatusCode = statusCode;
        ErrorCode = errorCode;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

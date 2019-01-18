package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 15/2/18.
 */

public class Model_Response {

    /*
    * Error respose
            * {
            "status_code": 1,
            "error_code": 404,
            "msg": "Client Not Found",
            "clientDetails": {}}
    * *
    /*
    * Success response
            *{
            "status_code": 0,
            "error_code": 0,
            "msg": "Client Found",
            "clientDetails": {
                "lastName": "Pawar",
                "otpSendAt": "2018-02-19 13:29:42",
                "emailId": "",
                "otp": "199741",
                "firstName": "Mr. Sakharam",
                "mobileNumber": "9403345344",
                "clientId": "15000"
            }
        }
    * */
    @SerializedName("status_code")
    private String status_code ;
    @SerializedName("error_code")
    private String error_code ;
    @SerializedName("msg")
    private String msg ;
    @SerializedName("clientDetails")
    private Model_Client_Details clientDetails ;

    public Model_Response() {
    }

    public Model_Response(String status_code, String error_code,
                          String msg, Model_Client_Details clientDetails) {
        this.status_code = status_code;
        this.error_code = error_code;
        this.msg = msg;
        this.clientDetails = clientDetails;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Model_Client_Details getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(Model_Client_Details clientDetails) {
        this.clientDetails = clientDetails;
    }
}

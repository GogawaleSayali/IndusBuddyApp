package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

public class Model_Response_setReminderDetails {
    /*"msg": "success",
    "status_code": 0,
    "error_code": 0 }*/

    @SerializedName("status_code")
    private String status_code;
    @SerializedName("error_code")
    private String error_code;
    @SerializedName("msg")
    private String msg;


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

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }





}

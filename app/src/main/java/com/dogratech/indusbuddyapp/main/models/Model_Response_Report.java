package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 21/3/18.
 */

public class Model_Response_Report {
    /*
    * {
            "status_code": 0,
            "error_code": 0,
            "msg": "success"
}
    * */

    @SerializedName("status_code")
    private int status_code ;

    @SerializedName("error_code")
    private int error_code ;

    @SerializedName("msg")
    private String msg ;

    public Model_Response_Report() {
    }

    public Model_Response_Report(int status_code, int error_code, String msg) {
        this.status_code = status_code;
        this.error_code = error_code;
        this.msg = msg;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

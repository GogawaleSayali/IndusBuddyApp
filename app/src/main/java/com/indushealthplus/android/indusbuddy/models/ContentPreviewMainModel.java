package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

public class ContentPreviewMainModel {
    @SerializedName("status_code")
    private int status_code;
    @SerializedName("data")
    private DataContent data;
    @SerializedName("error_code")
    private int error_code;
    @SerializedName("msg")
    private String msg;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public DataContent getData() {
        return data;
    }

    public void setData(DataContent data) {
        this.data = data;
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

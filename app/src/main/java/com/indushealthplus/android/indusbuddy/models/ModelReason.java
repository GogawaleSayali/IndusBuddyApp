package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

public class ModelReason {
    @SerializedName("Code")
    private String Code;
    @SerializedName("Reason")
    private String Reason;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
}

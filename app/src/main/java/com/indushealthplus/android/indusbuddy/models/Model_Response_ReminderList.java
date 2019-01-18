package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Response_ReminderList {
    /*
    *
    * */

    @SerializedName("msg")
    private String msg ;
    @SerializedName("status_code")
    private int status_code ;
    @SerializedName("data")
    private Model_ReminderList reminderList;
    @SerializedName("error_code")
    private int error_code ;

    public Model_Response_ReminderList()
    {
    }

    public Model_Response_ReminderList(String msg, int status_code,
                                       Model_ReminderList reminderList, int error_code) {
        this.msg = msg;
        this.status_code = status_code;
        this.reminderList = reminderList;
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public Model_ReminderList getReminderList() {
        return reminderList;
    }

    public void setReminderList(Model_ReminderList reminderList) {
        this.reminderList = reminderList;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}

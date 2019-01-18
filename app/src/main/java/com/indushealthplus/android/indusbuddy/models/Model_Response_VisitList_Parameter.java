package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Response_VisitList_Parameter {
    @SerializedName("status_code")
    private int status_code ;

    @SerializedName("data")
    private Model_Item_VisitList itemVisitList ;

    @SerializedName("error_code")
    private String error_code ;

    @SerializedName("msg")
    private String msg ;

    public Model_Response_VisitList_Parameter() {
    }

    public Model_Response_VisitList_Parameter(int status_code,
                                              Model_Item_VisitList itemVisitList,
                                              String error_code, String msg) {
        this.status_code = status_code;
        this.itemVisitList = itemVisitList;
        this.error_code = error_code;
        this.msg = msg;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public Model_Item_VisitList getItemVisitList() {
        return itemVisitList;
    }

    public void setItemVisitList(Model_Item_VisitList itemVisitList) {
        this.itemVisitList = itemVisitList;
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
}

package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Response_DoctorComment {
    /*
    * {
          "msg": "success",
          "status_code": 0,
          "data": {
            "analysisObject": {
              "clientId": "15000",
              "analysisCommentId": "27741",
              "addedBy": "amol",
              "analysisComment": "visit2",
              "classification": "Abnormal",
              "addedOn": "2018-03-01 12:00:01.0"
            }
          },
          "error_code": 0
}   * */


    @SerializedName("msg")
    private String msg ;
    @SerializedName("status_code")
    private int status_code ;
    @SerializedName("data")
    private Model_Item_AnalysisObject analysisObject ;
    @SerializedName("error_code")
    private int error_code ;

    public Model_Response_DoctorComment() {
    }

    public Model_Response_DoctorComment(String msg, int status_code,
                                        Model_Item_AnalysisObject analysisObject, int error_code) {
        this.msg = msg;
        this.status_code = status_code;
        this.analysisObject = analysisObject;
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

    public Model_Item_AnalysisObject getAnalysisObject() {
        return analysisObject;
    }

    public void setAnalysisObject(Model_Item_AnalysisObject analysisObject) {
        this.analysisObject = analysisObject;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}

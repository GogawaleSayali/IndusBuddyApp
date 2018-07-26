package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class ModelResGetAllReport {
    /*
    *{
          "msg": "success",
          "status_code": 0,
          "data": {
            "selfUploadReports": [
              {
                "addedBy": "amol",
                "filePath": "15000_03-02-2018_529-2712-1-PB.pdf",
                "comment": "c1",
                "ehrId": "15000",
                "clientSelfReportId": 86,
                "addedOn": "02-Mar-2018"
              },
            ],
            "filePath": "/home/amol/Documents/EHRLive/Reports/"
          },
          "error_code": 0
        }
    * */

    @SerializedName("msg")
    private String msg ;
    @SerializedName("status_code")
    private int status_code ;
    @SerializedName("data")
    private ModelSelfUploadReports uploadReports;
    @SerializedName("error_code")
    private int error_code ;

    public ModelResGetAllReport() {
    }

    public ModelResGetAllReport(String msg, int status_code,
                                ModelSelfUploadReports uploadReports, int error_code) {
        this.msg = msg;
        this.status_code = status_code;
        this.uploadReports = uploadReports;
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

    public ModelSelfUploadReports getUploadReports() {
        return uploadReports;
    }

    public void setUploadReports(ModelSelfUploadReports uploadReports) {
        this.uploadReports = uploadReports;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}

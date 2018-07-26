package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 14/3/18.
 */

public class ModelSelfUploadReports {
    /*
    *
    * */
    @SerializedName("selfUploadReports")
    private ArrayList<Model_Item_Report> selfUploadReports ;

    @SerializedName("filePath")
    private String filePath ;

    public ModelSelfUploadReports() {
    }

    public ModelSelfUploadReports(ArrayList<Model_Item_Report> selfUploadReports, String filePath) {
        this.selfUploadReports = selfUploadReports;
        this.filePath = filePath;
    }

    public ArrayList<Model_Item_Report> getSelfUploadReports() {
        return selfUploadReports;
    }

    public void setSelfUploadReports(ArrayList<Model_Item_Report> selfUploadReports) {
        this.selfUploadReports = selfUploadReports;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Item_AnalysisObject {
    @SerializedName("analysisComments")
    private ArrayList<Model_Item_doctor_comment> analysisComments ;

    public ArrayList<Model_Item_doctor_comment> getAnalysisComments() {
        return analysisComments;
    }

    public void setAnalysisComments(ArrayList<Model_Item_doctor_comment> analysisComments) {
        this.analysisComments = analysisComments;
    }
}

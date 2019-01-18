package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Item_Visit_ParameterList {
    /*
    *
    * */

    @SerializedName("parameterList")
    private ArrayList<ModelItemByVisit> parameterList ;

    @SerializedName("visitId")
    private String visitId ;

    @SerializedName("visitDate")
    private String visitDate ;

    public Model_Item_Visit_ParameterList() {
    }

    public Model_Item_Visit_ParameterList(ArrayList<ModelItemByVisit> parameterList, String visitId, String visitDate) {
        this.parameterList = parameterList;
        this.visitId = visitId;
        this.visitDate = visitDate;
    }

    public ArrayList<ModelItemByVisit> getParameterList() {
        return parameterList;
    }

    public void setParameterList(ArrayList<ModelItemByVisit> parameterList) {
        this.parameterList = parameterList;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
}

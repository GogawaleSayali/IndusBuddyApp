package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Item_VisitList {
    @SerializedName("visitList")
    private ArrayList<Model_Item_Visit_ParameterList>  visitList;

    public Model_Item_VisitList(ArrayList<Model_Item_Visit_ParameterList> visitList) {
        this.visitList = visitList;
    }

    public ArrayList<Model_Item_Visit_ParameterList> getParameterLists() {
        return visitList;
    }

    public void setParameterLists(ArrayList<Model_Item_Visit_ParameterList> visitList) {
        this.visitList = visitList;
    }
}

package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Item_hraTypes {
    /*
    *
    * */

    @SerializedName("hraTypeId")
    private int hraTypeId ;

    @SerializedName("questions")
    private ArrayList<Model_Item_Question> questions ;

    @SerializedName("hraTypeName")
    private String hraTypeName ;

    public Model_Item_hraTypes() {
    }

    public Model_Item_hraTypes(int hraTypeId, ArrayList<Model_Item_Question> questions,
                               String hraTypeName) {
        this.hraTypeId = hraTypeId;
        this.questions = questions;
        this.hraTypeName = hraTypeName;
    }

    public int getHraTypeId() {
        return hraTypeId;
    }

    public void setHraTypeId(int hraTypeId) {
        this.hraTypeId = hraTypeId;
    }

    public ArrayList<Model_Item_Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Model_Item_Question> questions) {
        this.questions = questions;
    }

    public String getHraTypeName() {
        return hraTypeName;
    }

    public void setHraTypeName(String hraTypeName) {
        this.hraTypeName = hraTypeName;
    }
}

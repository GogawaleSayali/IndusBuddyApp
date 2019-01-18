package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Item_hraTypes_answerArray {
    @SerializedName("hraTypes")
    private ArrayList<Model_Item_hraTypes> hraTypes ;

    @SerializedName("answerArray")
    private ArrayList<AnswersArrayModel> answerArray;

    public Model_Item_hraTypes_answerArray() {
    }

    public Model_Item_hraTypes_answerArray(ArrayList<Model_Item_hraTypes> hraTypes,
                                           ArrayList<AnswersArrayModel> answerArray) {
        this.hraTypes = hraTypes;
        this.answerArray = answerArray;
    }

    public ArrayList<Model_Item_hraTypes> getHraTypes() {
        return hraTypes;
    }

    public void setHraTypes(ArrayList<Model_Item_hraTypes> hraTypes) {
        this.hraTypes = hraTypes;
    }

    public ArrayList<AnswersArrayModel> getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(ArrayList<AnswersArrayModel> answerArray) {
        this.answerArray = answerArray;
    }
}

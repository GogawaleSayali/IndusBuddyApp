package com.indushealthplus.android.indusbuddy.models;

import java.util.ArrayList;

/**
 * Created by amolr on 10/4/18.
 */

public class HraTypeModel {
    private String hraType;
    private int numberOfQuestions;
    private int solvedQuestions;
    private ArrayList<Model_Item_Question> questionsOfHraType;

    public String getHraType() {
        return hraType;
    }

    public void setHraType(String hraType) {
        this.hraType = hraType;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getSolvedQuestions() {
        return solvedQuestions;
    }

    public void setSolvedQuestions(int solvedQuestions) {
        this.solvedQuestions = solvedQuestions;
    }

    public ArrayList<Model_Item_Question> getQuestionsOfHraType() {
        return questionsOfHraType;
    }

    public void setQuestionsOfHraType(ArrayList<Model_Item_Question> questionsOfHraType) {
        this.questionsOfHraType = questionsOfHraType;
    }
}

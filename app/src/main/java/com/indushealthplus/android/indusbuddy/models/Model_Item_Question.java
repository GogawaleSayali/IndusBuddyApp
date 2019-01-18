package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Item_Question {

    @SerializedName("questionId")
    private int questionId ;

    @SerializedName("questionType")
    private String questionType ;

    @SerializedName("question")
    private String question ;

    @SerializedName("options")
    private ArrayList<Model_Item_options> options ;

//    private String answString = "";
    @SerializedName("answString")
    private String answString;
    public Model_Item_Question() {
    }

    public Model_Item_Question(int questionId, String questionType, String question,
                               ArrayList<Model_Item_options> options) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.question = question;
        this.options = options;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Model_Item_options> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Model_Item_options> options) {
        this.options = options;
    }

    public String getAnswString() {
        return answString;
    }

    public void setAnswString(String answString) {
        this.answString = answString;
    }
}

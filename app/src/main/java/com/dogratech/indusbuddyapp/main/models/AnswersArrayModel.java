package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by amolr on 9/5/18.
 */

public class AnswersArrayModel {
    @SerializedName("optionSet")
    private ArrayList<OptionSetModel> optionSetModels;
    @SerializedName("hraTypeId")
    private int hraTypeId;
    @SerializedName("questionId")
    private int questionId;
    @SerializedName("textAnswer")
    private String textAnswer;
    @SerializedName("questionType")
    private String questionType;
    @SerializedName("question")
    private String question;
    @SerializedName("hraTypeName")
    private String hraTypeName;
    @SerializedName("clientId")
    private String clientId;

    public ArrayList<OptionSetModel> getOptionSetModels() {
        return optionSetModels;
    }

    public void setOptionSetModels(ArrayList<OptionSetModel> optionSetModels) {
        this.optionSetModels = optionSetModels;
    }

    public int getHraTypeId() {
        return hraTypeId;
    }

    public void setHraTypeId(int hraTypeId) {
        this.hraTypeId = hraTypeId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
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

    public String getHraTypeName() {
        return hraTypeName;
    }

    public void setHraTypeName(String hraTypeName) {
        this.hraTypeName = hraTypeName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}

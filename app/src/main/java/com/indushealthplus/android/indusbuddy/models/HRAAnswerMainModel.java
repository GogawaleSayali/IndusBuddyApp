package com.indushealthplus.android.indusbuddy.models;

/**
 * Created by amolr on 8/5/18.
 */

public class HRAAnswerMainModel {
    private int questionId;
    private int clientId;
    private String answer = "";

    public HRAAnswerMainModel() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

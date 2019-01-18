package com.indushealthplus.android.indusbuddy.models;

import java.util.ArrayList;

/**
 * Created by amolr on 9/3/18.
 */

public class ModelHra {
    private String type;
    private int headerId;
    private String header;
    private String question;
    private int questionId;
    private String answer;
    private ArrayList<Model_Item_options> optionsList;
    private ArrayList<String> answersList;

    public ModelHra(){
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<Model_Item_options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(ArrayList<Model_Item_options> optionsList) {
        this.optionsList = optionsList;
    }

    public ArrayList<String> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(ArrayList<String> answersList) {
        this.answersList = answersList;
    }
}

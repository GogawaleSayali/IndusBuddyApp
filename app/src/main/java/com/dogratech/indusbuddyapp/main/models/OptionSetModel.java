package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amolr on 9/5/18.
 */

public class OptionSetModel {

    @SerializedName("optionId")
    private int optionId;
    @SerializedName("option")
    private String option;
    @SerializedName("optionMasterDeleteFlag")
    private OptionMasterDeleteFlagModel optionMasterDeleteFlag;
    @SerializedName("questionReportDetail")
    private QuestionReportDetailModel questionReportDetail;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public OptionMasterDeleteFlagModel getOptionMasterDeleteFlag() {
        return optionMasterDeleteFlag;
    }

    public void setOptionMasterDeleteFlag(OptionMasterDeleteFlagModel optionMasterDeleteFlag) {
        this.optionMasterDeleteFlag = optionMasterDeleteFlag;
    }

    public QuestionReportDetailModel getQuestionReportDetail() {
        return questionReportDetail;
    }

    public void setQuestionReportDetail(QuestionReportDetailModel questionReportDetail) {
        this.questionReportDetail = questionReportDetail;
    }
}

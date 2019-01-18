package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 12/3/18.
 */

public class Model_Item_doctor_comment {

    /*
    * {
          "msg": "success",
          "status_code": 0,
          "data": {
            "analysisObject": {
              "clientId": "15000",
              "analysisCommentId": "27741",
              "addedBy": "amol",
              "analysisComment": "visit2",
              "classification": "Abnormal",
              "addedOn": "2018-03-01 12:00:01.0"
            }
          },
          "error_code": 0
}
    * */
    @SerializedName("visitId")
    private String visitId ;

    @SerializedName("commentTypeId")
    private String commentTypeId ;

    @SerializedName("analysisCommentId")
    private String analysisCommentId ;

    @SerializedName("addedBy")
    private String addedBy ;

    @SerializedName("analysisComment")
    private String analysisComment ;

    @SerializedName("comment")
    private String comment ;

    @SerializedName("classification")
    private String classification ;

    @SerializedName("addedOn")
    private String addedOn ;

    public Model_Item_doctor_comment() {

    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getCommentTypeId() {
        return commentTypeId;
    }

    public void setCommentTypeId(String commentTypeId) {
        this.commentTypeId = commentTypeId;
    }

    public String getAnalysisCommentId() {
        return analysisCommentId;
    }

    public void setAnalysisCommentId(String analysisCommentId) {
        this.analysisCommentId = analysisCommentId;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getAnalysisComment() {
        return analysisComment;
    }

    public void setAnalysisComment(String analysisComment) {
        this.analysisComment = analysisComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}

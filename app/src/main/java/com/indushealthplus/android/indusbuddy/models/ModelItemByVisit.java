package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 13/3/18.
 */

public class ModelItemByVisit {
    private String VisitDate;
    /*
    *                   "parameterValue": "93",
                        "upperValue": "96",
                        "testId": "21",
                        "analysisComment": "",
                        "addedOn": "05-Dec-2017 14:06:24",
                        "testDescription": "\tHaemogram\t",
                        "generalComment": "null",
                        "valueFor": "1,5",
                        "parameterId": "364",
                        "parameterName": "M.C.V.",
                        "reportId": "267674",
                        "lowerValue": "76",
                        "normalValue": "1",
                        "testResultStatus": "normal",
                        "unitName": "fl",
                        "centerId": "15",
                        "unitId": "6"
    * */

    @SerializedName("parameterValue")
    private String parameterValue;
    @SerializedName("upperValue")
    private String upperValue;
    @SerializedName("testId")
    private String testId;
    @SerializedName("analysisComment")
    private String analysisComment;
    @SerializedName("addedOn")
    private String addedOn;
    @SerializedName("testDescription")
    private String testDescription;
    @SerializedName("generalComment")
    private String generalComment;
    @SerializedName("valueFor")
    private String valueFor;
    @SerializedName("parameterId")
    private String parameterId;
    @SerializedName("parameterName")
    private String parameterName;
    @SerializedName("reportId")
    private String reportId;
    @SerializedName("lowerValue")
    private String lowerValue;
    @SerializedName("normalValue")
    private String normalValue;
    @SerializedName("testResultStatus")
    private String testResultStatus;
    @SerializedName("unitName")
    private String unitName;
    @SerializedName("centerId")
    private String centerId;
    @SerializedName("unitId")
    private String unitId;

    public ModelItemByVisit() {
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getUpperValue() {
        return upperValue;
    }

    public void setUpperValue(String upperValue) {
        this.upperValue = upperValue;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getAnalysisComment() {
        return analysisComment;
    }

    public void setAnalysisComment(String analysisComment) {
        this.analysisComment = analysisComment;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public String getGeneralComment() {
        return generalComment;
    }

    public void setGeneralComment(String generalComment) {
        this.generalComment = generalComment;
    }

    public String getValueFor() {
        return valueFor;
    }

    public void setValueFor(String valueFor) {
        this.valueFor = valueFor;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(String lowerValue) {
        this.lowerValue = lowerValue;
    }

    public String getNormalValue() {
        return normalValue;
    }

    public void setNormalValue(String normalValue) {
        this.normalValue = normalValue;
    }

    public String getTestResultStatus() {
        return testResultStatus;
    }

    public void setTestResultStatus(String testResultStatus) {
        this.testResultStatus = testResultStatus;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getVisitDate() {
        return VisitDate;
    }

    public void setVisitDate(String visitDate) {
        VisitDate = visitDate;
    }
}

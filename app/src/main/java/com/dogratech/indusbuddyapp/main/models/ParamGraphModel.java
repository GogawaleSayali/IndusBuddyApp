package com.dogratech.indusbuddyapp.main.models;

/**
 * Created by amolr on 2/5/18.
 */

public class ParamGraphModel{
    private String paramValue;
    private String lowerVal;
    private String upperVal;
    private String visitDate;
    private String testStatus;

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getLowerVal() {
        return lowerVal;
    }

    public void setLowerVal(String lowerVal) {
        this.lowerVal = lowerVal;
    }

    public String getUpperVal() {
        return upperVal;
    }

    public void setUpperVal(String upperVal) {
        this.upperVal = upperVal;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }
}

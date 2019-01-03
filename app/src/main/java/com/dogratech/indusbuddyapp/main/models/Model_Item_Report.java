package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Item_Report {
    /*
        * {
            "addedBy": "amol",
            "filePath": "15000_03-02-2018_529-2712-1-PB.pdf",
            "comment": "cmi1",
            "ehrId": "15000",
            "clientSelfReportId": 88,
            "addedOn": "02-Mar-2018"
          }
    * */

    @SerializedName("addedBy")
    private String addedBy ;
    @SerializedName("filePath")
    private String filePath ;
    @SerializedName("comment")
    private String comment ;
    @SerializedName("ehrId")
    private String ehrId ;
    @SerializedName("clientSelfReportId")
    private int  clientSelfReportId ;
    @SerializedName("addedOn")
    private String addedOn ;
    @SerializedName("count")
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Model_Item_Report() {
    }

    public Model_Item_Report(String addedBy, String filePath, String comment,
                             String ehrId, int clientSelfReportId, String addedOn) {
        this.addedBy = addedBy;
        this.filePath = filePath;
        this.comment = comment;
        this.ehrId = ehrId;
        this.clientSelfReportId = clientSelfReportId;
        this.addedOn = addedOn;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEhrId() {
        return ehrId;
    }

    public void setEhrId(String ehrId) {
        this.ehrId = ehrId;
    }

    public int getClientSelfReportId() {
        return clientSelfReportId;
    }

    public void setClientSelfReportId(int clientSelfReportId) {
        this.clientSelfReportId = clientSelfReportId;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}

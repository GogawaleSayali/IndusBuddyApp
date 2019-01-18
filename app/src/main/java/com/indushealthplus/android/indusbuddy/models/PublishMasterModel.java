package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

public class PublishMasterModel {
    @SerializedName("publishId")
    private int publishId;
    @SerializedName("publishDate")
    private long publishDate;
    @SerializedName("contentMaster")
    private String contentMaster;
    @SerializedName("publishDetail")
    private String publishDetail;
    @SerializedName("isActive")
    private boolean isActive;

    public int getPublishId() {
        return publishId;
    }

    public void setPublishId(int publishId) {
        this.publishId = publishId;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public String getContentMaster() {
        return contentMaster;
    }

    public void setContentMaster(String contentMaster) {
        this.contentMaster = contentMaster;
    }

    public String getPublishDetail() {
        return publishDetail;
    }

    public void setPublishDetail(String publishDetail) {
        this.publishDetail = publishDetail;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

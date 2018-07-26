package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FileObjectModel {
    @SerializedName("fileId")
    private int fileId;
    @SerializedName("fileName")
    private String fileName;
    @SerializedName("fileType")
    private String fileType;
    @SerializedName("isActive")
    private boolean isActive;
    @SerializedName("contentMaster")
    private String contentMaster;
    @SerializedName("categories")
    private String categories;
    @SerializedName("subCategories")
    private String subCategories;
    @SerializedName("publishMasters")
    private ArrayList<PublishMasterModel> publishMasters;
    @SerializedName("tagStatus")
    private String tagStatus;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getContentMaster() {
        return contentMaster;
    }

    public void setContentMaster(String contentMaster) {
        this.contentMaster = contentMaster;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(String subCategories) {
        this.subCategories = subCategories;
    }

    public ArrayList<PublishMasterModel> getPublishMasters() {
        return publishMasters;
    }

    public void setPublishMasters(ArrayList<PublishMasterModel> publishMasters) {
        this.publishMasters = publishMasters;
    }

    public String getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(String tagStatus) {
        this.tagStatus = tagStatus;
    }
}

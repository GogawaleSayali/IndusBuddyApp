package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContentsPreview {

    @SerializedName("contentId")
    private int contentId;
    @SerializedName("contentTitle")
    private String contentTitle;
    @SerializedName("contentDescription")
    private String contentDescription;
    @SerializedName("contentTags")
    private String contentTags;
    @SerializedName("isActive")
    private boolean isActive;
    @SerializedName("addedBy")
    private String addedBy;
    @SerializedName("addedOn")
    private long addedOn;
    @SerializedName("modifyBy")
    private  String modifyBy;
    @SerializedName("modifyOn")
    private long modifyOn;

    public String getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(String subCategories) {
        this.subCategories = subCategories;
    }

    @SerializedName("contentFiles")
    private ArrayList<FileObjectModel> contentFiles;
    @SerializedName("subCategories")
    private String subCategories;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getContentTags() {
        return contentTags;
    }

    public void setContentTags(String contentTags) {
        this.contentTags = contentTags;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public long getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(long addedOn) {
        this.addedOn = addedOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public long getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(long modifyOn) {
        this.modifyOn = modifyOn;
    }

    public ArrayList<FileObjectModel> getContentFiles() {
        return contentFiles;
    }

    public void setContentFiles(ArrayList<FileObjectModel> contentFiles) {
        this.contentFiles = contentFiles;
    }
}

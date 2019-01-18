package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataContent {
    @SerializedName("contents")
    private ArrayList<ContentsPreview> contents;

    public ArrayList<ContentsPreview> getContents() {
        return contents;
    }

    public void setContents(ArrayList<ContentsPreview> contents) {
        this.contents = contents;
    }
}

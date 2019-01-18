package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sayali.gogawale on 1/14/2019.
 */

public class AudioDetails {

    @SerializedName("Language")
    private String language;
    @SerializedName("audioLink")
    private String audioLink;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }
}

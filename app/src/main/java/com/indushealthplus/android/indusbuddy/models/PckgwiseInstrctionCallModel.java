package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sayali.gogawale on 1/11/2019.
 */

public class PckgwiseInstrctionCallModel {
    @SerializedName("Name")
    private String name;

    //InstructionCall Audio :
    @SerializedName("AudioDetail")
    private ArrayList<AudioDetails> audioDetails;

    public ArrayList<AudioDetails> getAudioDetails() {
        return audioDetails;
    }

    public void setAudioDetails(ArrayList<AudioDetails> audioDetails) {
        this.audioDetails = audioDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

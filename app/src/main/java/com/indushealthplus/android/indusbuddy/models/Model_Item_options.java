package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Item_options {

    @SerializedName("optionId")
    private int optionId ;
    @SerializedName("option")
    private String option;

    private boolean isChecked = false;

    public Model_Item_options() {
    }

    public Model_Item_options(int optionId, String option) {
        this.optionId = optionId;
        this.option = option;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

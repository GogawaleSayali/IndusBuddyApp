package com.indushealthplus.android.indusbuddy.models;

/**
 * Created by amolr on 21/3/18.
 */

public class ModelArticleSettings {
    private String name;
    private boolean isSelected;

    public ModelArticleSettings(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

package com.dogratech.indusbuddyapp.main.models;

/**
 * Created by akshaya on 23/2/18.
 */

public class Model_Package {

    private String name;
    private int numOfSongs;
    private int thumbnail;

    public Model_Package() {
    }

    public Model_Package(String name,  int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

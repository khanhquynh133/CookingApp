package com.finalexam.cookingapp.model.entity;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("prepare")
    private String prepare;

    @SerializedName("cover_image")
    private int imageID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public Food(int id, String name, String prepare, int imageID) {
        this.id = id;
        this.name = name;
        this.prepare = prepare;
        this.imageID = imageID;
    }
}

package com.finalexam.cookingapp;

import android.app.Application;

public class GlobalStorage extends Application {
    private String string;

    public GlobalStorage() {
        super();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}

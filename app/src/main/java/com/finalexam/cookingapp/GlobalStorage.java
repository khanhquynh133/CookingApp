package com.finalexam.cookingapp;

import android.app.Application;

import com.finalexam.cookingapp.model.global.storage.RecipeData;

public class GlobalStorage extends Application {
    private RecipeData recipeData;

    public GlobalStorage() {
        super();
        recipeData = new RecipeData();
    }

    public RecipeData getRecipeData() {
        return recipeData;
    }

    public void setRecipeData(RecipeData recipeData) {
        this.recipeData = recipeData;
    }
}

package com.finalexam.cookingapp.model.global.storage;

import com.finalexam.cookingapp.model.Ingredient;

public class DetailIngredient {
    private Ingredient ingredient;
    private String quantity;

    public DetailIngredient(Ingredient ingredient, String quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return quantity + " " + ingredient.getIngredientName().toLowerCase();
    }
}

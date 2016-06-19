package com.example.myapplication;

import java.io.Serializable;

/**
 * @author Piotr Makowski (<a href=\"mailto:Piotr.Makowski@allegrogroup.pl\">Piotr.Makowski@allegrogroup.pl</a>)
 *
 * Recipe model.
 *
 * Do not modify.
 */
public class Recipe implements Serializable {

    private String dishName;
    private String ingredients;
    private String recipeText;
    private String rating;

    public Recipe(String dishName, String ingredients, String recipeText, String rating) {
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.recipeText = recipeText;
        this.rating = rating;
    }

    public String getDishName() {
        return dishName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public String getRating() {
        return rating;
    }
}
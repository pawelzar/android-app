package com.example.myapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecipesProvider implements RecipesProvider {

    private final List<Recipe> recipes = new ArrayList<>();

    public SimpleRecipesProvider(Context context) {
        recipes.add(new Recipe(context.getString(R.string.recipe_title), context.getString(R.string.ingredients),
                context.getString(R.string.recipe), "5.4"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title2), context.getString(R.string.ingredients2),
                context.getString(R.string.recipe2), "5.8"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title3), context.getString(R.string.ingredients3),
                context.getString(R.string.recipe3), "1.4"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title4), context.getString(R.string.ingredients4),
                context.getString(R.string.recipe4), "9.4"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title5), context.getString(R.string.ingredients5),
                context.getString(R.string.recipe5), "4.3"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title6), context.getString(R.string.ingredients6),
                context.getString(R.string.recipe6), "2.6"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title7), context.getString(R.string.ingredients7),
                context.getString(R.string.recipe7), "7.3"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title8), context.getString(R.string.ingredients8),
                context.getString(R.string.recipe8), "1.1"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title9), context.getString(R.string.ingredients9),
                context.getString(R.string.recipe9), "2.8"));
        recipes.add(new Recipe(context.getString(R.string.recipe_title10), context.getString(R.string.ingredients10),
                context.getString(R.string.recipe10), "9.1"));
    }

    @Override
    public Recipe getRecipe(int position) {
        return recipes.get(position);
    }

    @Override
    public int getRecipesNumber() {
        return recipes.size();
    }

}

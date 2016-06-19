package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.database.RecipeDatabase;

/**
 * Exercise 2.
 *
 * Use RecipeDatabase instead of RecipesProvider.
 */
public class RecipeAdapter extends BaseAdapter {

    private RecipesProvider recipesProvider;
    private Context context;

    public RecipeAdapter(Context context) {
        this.context = context;
//        this.recipesProvider = new SimpleRecipesProvider(context);
        this.recipesProvider = new RecipeDatabase(context);
    }

    @Override
    public int getCount() {
        return recipesProvider.getRecipesNumber();
    }

    @Override
    public Recipe getItem(int position) {
        return recipesProvider.getRecipe(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View recipeView;

        if (convertView == null) {
            recipeView = LayoutInflater.from(context).inflate(R.layout.recipe_row, parent, false);
        } else {
            recipeView = convertView;
        }

        bindRecipeToView(getItem(position), recipeView, position);

        return recipeView;
    }

    private void bindRecipeToView(Recipe recipe, View recipeView, int position) {

        TextView recipeLabel = (TextView) recipeView.findViewById(R.id.recipe_label);
        recipeLabel.setText(recipe.getDishName());

        TextView recipeRating = (TextView) recipeView.findViewById(R.id.recipe_rating);
        String recipeRatingText = recipe.getRating() + "/10";
        recipeRating.setText(recipeRatingText);
    }

}
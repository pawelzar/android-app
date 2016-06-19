package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.RecipeDatabase;

public class AddRecipeActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_recipe);

        setupViews();
    }

    private void setupViews() {
        final EditText recipeNameEditText = (EditText) findViewById(R.id.recipe_name_edit_text);
        final EditText recipeEditText = (EditText) findViewById(R.id.recipe_edit_text);
        final EditText ingredientsEditText = (EditText) findViewById(R.id.ingredients_edit_text);
        final EditText ratingEditText = (EditText) findViewById(R.id.rating_edit_text);

        Button saveButton = (Button) findViewById(R.id.save_recipe_button);

        saveButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String recipeName = recipeNameEditText.getText().toString();
                String recipe = recipeEditText.getText().toString();
                String ingredients = ingredientsEditText.getText().toString();
                String rating = ratingEditText.getText().toString();

                saveRecipe(new Recipe(recipeName, ingredients, recipe, rating));
            }
        });
    }

    private void saveRecipe(Recipe recipe) {
        RecipeDatabase recipeDb = new RecipeDatabase(this);
        recipeDb.addRecipe(recipe);

        Toast.makeText(this, "Recipe " + recipe.getDishName() + " saved", Toast.LENGTH_SHORT).show();

        finish();
    }
}

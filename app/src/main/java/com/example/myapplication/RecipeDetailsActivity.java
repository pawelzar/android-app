package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class RecipeDetailsActivity extends ActionBarActivity {

    public static final String RECIPE_EXTRA_KEY = "recipe";

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);

        Button button = (Button) findViewById(R.id.button);
        final ScrollView sv = (ScrollView) findViewById(R.id.scroll);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                sv.scrollTo(0,0);
            }
        });

        Intent i = getIntent();
        recipe = (Recipe) i.getExtras().getSerializable(RECIPE_EXTRA_KEY);

        showRecipe(recipe);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_to_disc: {
                saveRecipeToDisk(recipe);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    private void showRecipe(Recipe recipe) {
        TextView title = (TextView) findViewById(R.id.title);
        TextView ingredients = (TextView) findViewById(R.id.ingredients);
        TextView recipeText = (TextView) findViewById(R.id.recipe);

        title.setText(recipe.getDishName());
        ingredients.setText(recipe.getIngredients());
        recipeText.setText(recipe.getRecipeText());
    }

    private void saveRecipeToDisk(Recipe recipe) {
        SaveRecipeOnDiskDelegate delegate = new SaveRecipeOnDiskDelegate();
        delegate.saveRecipe(this, recipe);
    }
}

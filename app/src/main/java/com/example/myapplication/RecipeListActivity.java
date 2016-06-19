package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


public class RecipeListActivity extends ActionBarActivity {

    private GridView grid;
    private RecipeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        initializeGrid();
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    private void initializeGrid() {

        grid = (GridView) findViewById(R.id.recipe_grid);

        adapter = new RecipeAdapter(this);

        grid.setNumColumns(1);

        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = adapter.getItem(position);
                showRecipe(recipe);
            }
        });
    }

    private void showRecipe(Recipe recipe) {
        Intent i = new Intent(this, RecipeDetailsActivity.class);

        i.putExtra(RecipeDetailsActivity.RECIPE_EXTRA_KEY, recipe);

        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_recipe: {
                startAddRecipeActivity();
                return true;
            }
            case R.id.delete_all: {
                deleteAllRecipes();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    private void startAddRecipeActivity() {
        Intent i = new Intent(this, AddRecipeActivity.class);
        startActivity(i);
    }

    private void deleteAllRecipes() {
        adapter.deleteAll();
        adapter.notifyDataSetChanged();
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.recipe_label);
        String task = String.valueOf(taskTextView.getText());

        adapter.deleteItem(task);
        adapter.notifyDataSetChanged();
    }
}

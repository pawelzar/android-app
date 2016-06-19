package com.example.myapplication;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Makowski (<a href=\"mailto:Piotr.Makowski@allegrogroup.pl\">Piotr.Makowski@allegrogroup.pl</a>)
 */
public class RecipeListActivity extends ActionBarActivity {

    private ColumnNumberPreferences columnNumberPreferences;

    private GridView grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        columnNumberPreferences = new ColumnNumberPreferences(this);

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

        grid.setNumColumns(columnNumberPreferences.getSelectedNumberOfColumns());

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
            case R.id.col_number: {
                showColNumberChooserDialog();
                return true;
            }
            case R.id.add_recipe: {
                startAddRecipeActivity();
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

    private void showColNumberChooserDialog() {
        AlertDialog.Builder builder = new Builder(this);

        List<CharSequence> options = new ArrayList<>();

        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        options.add("5");

        builder.setItems(options.toArray(new CharSequence[5]), new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setNumberOfGridColumns(which + 1);
            }
        });

        builder.setTitle("Select number of columns");

        builder.create().show();
    }

    private void setNumberOfGridColumns(int numberOfColumns) {
        grid.setNumColumns(numberOfColumns);
        columnNumberPreferences.saveSelectedNumberOfColumns(numberOfColumns);
    }

    private RecipeAdapter adapter;
}
package com.example.myapplication.database;

import static com.example.myapplication.database.DbConstants.COLUMN_INGREDIENTS;
import static com.example.myapplication.database.DbConstants.COLUMN_NAME;
import static com.example.myapplication.database.DbConstants.COLUMN_RATING;
import static com.example.myapplication.database.DbConstants.COLUMN_RECIPE;
import static com.example.myapplication.database.DbConstants.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Recipe;
import com.example.myapplication.RecipesProvider;

/**
 * Exercise 2.
 *
 * Implement this class' methods. Extend SQLiteOpenHelper to access database.
 */
public class RecipeDatabase implements RecipesProvider {

    private RecipeDbHelper recipeDbHelper;

    public RecipeDatabase(Context context) {
        this.recipeDbHelper = new RecipeDbHelper(context);
    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = recipeDbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, recipe.getDishName());
            values.put(COLUMN_RECIPE, recipe.getRecipeText());
            values.put(COLUMN_INGREDIENTS, recipe.getIngredients());
            values.put(COLUMN_RATING, recipe.getRating());

            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public Recipe getRecipe(int position) {
        SQLiteDatabase db = recipeDbHelper.getReadableDatabase();

        try {
            String[] projection = { COLUMN_NAME, COLUMN_RECIPE, COLUMN_INGREDIENTS, COLUMN_RATING };

            String sortOrder = COLUMN_RATING + " DESC";

            Cursor cursor = db.query(TABLE_NAME,  // The table to query
                    projection,                   // The columns to return
                    null,                         // The columns for the WHERE clause
                    null,                         // The values for the WHERE clause
                    null,                         // don't group the rows
                    null,                         // don't filter by row groups
                    sortOrder                     // The sort order
            );

            return getRecipeFromCursor(position, cursor);
        } finally {
            db.close();
        }
    }

    private Recipe getRecipeFromCursor(int position, Cursor cursor) {
        try {
            cursor.moveToPosition(position);

            String name = cursor.getString(0);
            String recipe = cursor.getString(1);
            String ingredients = cursor.getString(2);
            String rating = cursor.getString(3);

            cursor.close();

            return new Recipe(name, ingredients, recipe, rating);
        } finally {
            cursor.close();
        }
    }

    public int getRecipesNumber() {
        SQLiteDatabase db = recipeDbHelper.getReadableDatabase();

        try {
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, null, null);
        } finally {
            db.close();
        }
    }
}

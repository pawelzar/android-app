package com.example.myapplication;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Exercise 3.
 *
 * Implement saveRecipe() method. It should save recipe data in external storage.
 * Make sure to check if external storage is available.
 */
public class SaveRecipeOnDiskDelegate {

    private static final String FILENAME = "recipe.txt";

    public void saveRecipe(Context context, Recipe recipe) {
        if (!isExternalStorageWritable()) {
            Toast.makeText(context, "Could not save file - external storage is not writable", Toast.LENGTH_LONG).show();
            return;
        }

        File file = new File(context.getExternalFilesDir(null), FILENAME);

        file.getParentFile().mkdirs();

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buildRecipeText(context, recipe).getBytes());
            fos.close();
            Toast.makeText(context, "File saved at " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error during saving file", Toast.LENGTH_LONG).show();
        }
    }

    private String buildRecipeText(Context context, Recipe recipe) {
        return context.getString(R.string.recipe_name_label) + "\n" + recipe.getDishName() + "\n\n"
                + context.getString(R.string.recipe_label) + "\n" + recipe.getRecipeText() + "\n\n"
                + context.getString(R.string.ingredients_label) + "\n" + recipe.getIngredients() + "\n\n"
                + context.getString(R.string.rating_label) + "\n" + recipe.getRating();
    }

    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}

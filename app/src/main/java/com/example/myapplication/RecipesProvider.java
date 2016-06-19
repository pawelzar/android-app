package com.example.myapplication;

/**
 * @author Piotr Makowski (<a href=\"mailto:Piotr.Makowski@allegrogroup.pl\">Piotr.Makowski@allegrogroup.pl</a>)
 */
public interface RecipesProvider {

    Recipe getRecipe(int position);

    int getRecipesNumber();
}

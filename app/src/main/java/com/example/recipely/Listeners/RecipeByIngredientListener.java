package com.example.recipely.Listeners;

import com.example.recipely.Models.RecipeAPIResponse;

public interface RecipeByIngredientListener {
    void didFetch(RecipeAPIResponse response, String message);
    void didError(String message);
}

package com.example.recipely.Listeners;

import com.example.recipely.Models.RecipeIngredResponse;

import java.util.List;

public interface RecipeByIngredientListener {
    void didFetch(List<RecipeIngredResponse> response, String message);
    void didError(String message);
}

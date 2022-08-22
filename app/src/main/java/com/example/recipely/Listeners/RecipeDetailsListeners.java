package com.example.recipely.Listeners;

import com.example.recipely.Models.RecipeDetailsResponse;

public interface RecipeDetailsListeners {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}

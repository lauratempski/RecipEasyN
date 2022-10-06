package com.example.recipeasy.Listener;

import com.example.recipeasy.Models.RandomRecipeAPIResponse;

public interface RandomRecipeResponseListener {

    void didFetch(RandomRecipeAPIResponse response, String message);
    void didError(String message);
}

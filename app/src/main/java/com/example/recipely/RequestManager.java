package com.example.recipely;

import android.content.Context;

import com.example.recipely.Listeners.RecipeByIngredientListener;
import com.example.recipely.Models.RecipeAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context){
        this.context = context;
    }

    public void getRecipeByIngredient(RecipeByIngredientListener listener){
        CallRecipeByIngredient callRecipeByIngredient = retrofit.create(CallRecipeByIngredient.class);
        Call<RecipeAPIResponse> call = callRecipeByIngredient.callRecipe(context.getString(R.string.API) , "10");
        call.enqueue(new Callback<RecipeAPIResponse>() {
            @Override
            public void onResponse(Call<RecipeAPIResponse> call, Response<RecipeAPIResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    private interface CallRecipeByIngredient{
        @GET("recipes/findByIngredients")
        Call<RecipeAPIResponse> callRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );

    }


}

package com.example.recipely;

import android.content.Context;

import com.example.recipely.Listeners.RandomRecipeResponseListener;
import com.example.recipely.Listeners.RecipeDetailsListeners;
import com.example.recipely.Models.RandomRecipeAPIResponse;
import com.example.recipely.Models.RecipeDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    public void getRandomRecipe(RandomRecipeResponseListener listener){
        CallRandomRecipe callRandomRecipe = retrofit.create(CallRandomRecipe.class);
        Call<RandomRecipeAPIResponse> call = callRandomRecipe.callRecipe(context.getString(R.string.API) , "50");
        call.enqueue(new Callback<RandomRecipeAPIResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeAPIResponse> call, Response<RandomRecipeAPIResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListeners listeners, int id){
        callRecipeDetails callRecipeDetails = retrofit.create(RequestManager.callRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id , context.getString(R.string.API));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listeners.didError(response.message());
                    return;
                }
                listeners.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listeners.didError(t.getMessage());
            }
        });

    }

    private interface CallRandomRecipe{
        @GET("recipes/random")
        Call<RandomRecipeAPIResponse> callRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );

    }

    private interface callRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

}

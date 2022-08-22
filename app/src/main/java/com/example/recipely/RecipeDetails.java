package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipely.Adapters.recipeDetailsIngredientAdapter;
import com.example.recipely.Listeners.RecipeDetailsListeners;
import com.example.recipely.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetails extends AppCompatActivity {
    int id;
    ImageView recipeImage;
    TextView RecipeName, recipeSource, recipeServing, recipeTimePrepared, recipeScore, recipeSummary;
    RecyclerView IngredientListRecipeDetails;
    RequestManager manager;
    recipeDetailsIngredientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        findViewById();
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListeners, id);
    }

    private void findViewById(){
        recipeImage = (ImageView) findViewById(R.id.recipeImageDetails);
        RecipeName = (TextView) findViewById(R.id.RecipeNameDetails);
        recipeSource = (TextView) findViewById(R.id.recipeSourceDetails);
        recipeServing = (TextView) findViewById(R.id.recipeServingDetails);
        recipeTimePrepared = (TextView) findViewById(R.id.recipeTimePreparedDetails);
        recipeScore = (TextView) findViewById(R.id.recipeScoreDetails);
        recipeSummary = (TextView) findViewById(R.id.recipeSummaryDetails);
        IngredientListRecipeDetails = (RecyclerView) findViewById(R.id.IngredientListRecipeDetailsDetails);
    }

    private final RecipeDetailsListeners recipeDetailsListeners = new RecipeDetailsListeners() {
        @SuppressLint("SetTextI18n")
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            Picasso.get().load(response.image).into(recipeImage);
            RecipeName.setText(response.title);
            recipeSource.setText(response.sourceName);
            recipeServing.setText(response.servings + " Serving");
            recipeTimePrepared.setText(response.readyInMinutes + "Min");
            recipeScore.setText(String.valueOf(response.aggregateLikes));
            recipeSummary.setText(Html.fromHtml(response.summary, Html.FROM_HTML_MODE_COMPACT));

            IngredientListRecipeDetails.setHasFixedSize(true);
            IngredientListRecipeDetails.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false ));
            adapter = new recipeDetailsIngredientAdapter(getBaseContext(), response.extendedIngredients);
            IngredientListRecipeDetails.setAdapter(adapter);
        }
        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetails.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}
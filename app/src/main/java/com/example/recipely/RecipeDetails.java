package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipely.Adapters.InstructionAdapter;
import com.example.recipely.Adapters.recipeDetailsIngredientAdapter;
import com.example.recipely.Listeners.RecipeDetailsListeners;
import com.example.recipely.Listeners.instructionListener;
import com.example.recipely.Models.InstructionResponse;
import com.example.recipely.Models.RecipeDetailsResponse;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetails extends AppCompatActivity {
    int id;
    ImageView recipeImage;
    TextView RecipeName, recipeSource, recipeServing, recipeTimePrepared, recipeScore, recipeSummary;
    RecyclerView IngredientListRecipeDetails, recipeAnalyzeInstruction;
    RequestManager manager;
    recipeDetailsIngredientAdapter adapter;
    InstructionAdapter instructionAdapter;

    LinearLayout recipeDetailsLoaded;
    ShimmerFrameLayout recipeDetailsShimmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeDetailsLoaded = findViewById(R.id.recipeDetailsLoaded);
        recipeDetailsShimmer = findViewById(R.id.recipeDetailsShimmer);
        recipeDetailsShimmer.startShimmer();
        recipeDetailsLoaded.setVisibility(View.GONE);
        Handler handler = new Handler();
        handler.postDelayed(()->{
            recipeDetailsShimmer.stopShimmer();
            recipeDetailsShimmer.setVisibility(View.GONE);
            recipeDetailsLoaded.setVisibility(View.VISIBLE);
        },3000);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        findViewById();
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListeners, id);

        manager.getInstruction(instructionListener, id);

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
        recipeAnalyzeInstruction = (RecyclerView) findViewById(R.id.recipeAnalyzeInstruction);
    }

    private final RecipeDetailsListeners recipeDetailsListeners = new RecipeDetailsListeners() {
        @SuppressLint("SetTextI18n")
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            Picasso.get().load(response.image).into(recipeImage);
            RecipeName.setText(response.title);
            recipeSource.setText(response.sourceName);
            if (response.sourceName == null){
                recipeSource.setText("Unknown");
            }
            recipeServing.setText(response.servings + " Serving");
            recipeTimePrepared.setText(response.readyInMinutes + " Min");
            recipeScore.setText(String.valueOf(response.aggregateLikes) + " Likes");
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

    private final instructionListener instructionListener = new instructionListener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
            recipeAnalyzeInstruction.setHasFixedSize(true);
            recipeAnalyzeInstruction.setLayoutManager(new LinearLayoutManager(RecipeDetails.this, LinearLayoutManager.VERTICAL, false));
            instructionAdapter = new InstructionAdapter(RecipeDetails.this, response);
            recipeAnalyzeInstruction.setAdapter(instructionAdapter);

        }


        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetails.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}
package com.example.recipely.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipely.Models.Recipe;
import com.example.recipely.Models.RecipeAPIResponse;
import com.example.recipely.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeByIngredAdapter extends RecyclerView.Adapter<RecipeByIngredViewHolder>{
    Context context;
    List<RecipeAPIResponse> list;

    public RecipeByIngredAdapter(Context context, List<RecipeAPIResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecipeByIngredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeByIngredViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ingred_recipe, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeByIngredViewHolder holder, int position) {
        holder.recipeName.setText(list.get(position).title);
        holder.recipeIngredientMissed.setText(list.get(position).missedIngredientCount);
        // recipeIngredientMissed insert soon
        Picasso.get().load(list.get(position).image).into(holder.recipeImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecipeByIngredViewHolder extends RecyclerView.ViewHolder{
    CardView recipeCardContainer;
    TextView recipeName, recipeServing, recipeIngredientMissed, recipeTimePrepared;
    ImageView recipeImage;

    public RecipeByIngredViewHolder(@NonNull View itemView){
        super(itemView);

        recipeCardContainer = itemView.findViewById(R.id.recipeCardContainer);
        recipeName = itemView.findViewById(R.id.recipeName);
        recipeServing = itemView.findViewById(R.id.recipeServing);
        recipeIngredientMissed = itemView.findViewById(R.id.recipeIngredientMissed);
        recipeTimePrepared = itemView.findViewById(R.id.recipeTimePrepared);
        recipeImage = itemView.findViewById(R.id.recipeImage);
    }
}

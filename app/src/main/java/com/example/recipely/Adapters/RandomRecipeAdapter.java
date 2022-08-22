package com.example.recipely.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipely.Listeners.RecipeClickListener;
import com.example.recipely.Models.Recipe;
import com.example.recipely.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {

    //initialize object
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.listener = listener;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ingred_recipe, parent , false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.recipeName.setText(list.get(position).title);
        holder.recipeServing.setText(list.get(position).servings + " Serving");
        holder.recipeTimePrepared.setText(list.get(position).readyInMinutes + " Min");
        Picasso.get().load(list.get(position).image).into(holder.recipeImage);

        holder.recipeCardContainer.setOnClickListener(v ->{
            listener.onRecipeClick(String.valueOf(list.get(holder.getAdapterPosition()).id)); // might error getAdapterPosition
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder{

    //initialize item
    CardView recipeCardContainer;
    TextView recipeName, recipeServing, recipeTimePrepared;
    ImageView recipeImage;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        recipeCardContainer = itemView.findViewById(com.example.recipely.R.id.recipeCardContainer);
        recipeName = itemView.findViewById(com.example.recipely.R.id.recipeName);
        recipeServing = itemView.findViewById(com.example.recipely.R.id.recipeServing);
        recipeTimePrepared = itemView.findViewById(com.example.recipely.R.id.recipeTimePrepared);
        recipeImage = itemView.findViewById(com.example.recipely.R.id.recipeImage);
    }

}
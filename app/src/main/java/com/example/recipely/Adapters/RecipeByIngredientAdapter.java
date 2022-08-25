package com.example.recipely.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipely.Listeners.RecipeClickListener;
import com.example.recipely.Models.RecipeIngredResponse;
import com.example.recipely.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RecipeByIngredientAdapter extends RecyclerView.Adapter<RecipeByIngredientViewHolder> {

    Context context;
    List<RecipeIngredResponse> list;
    RecipeClickListener listener;

    public RecipeByIngredientAdapter(Context context, List<RecipeIngredResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeByIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeByIngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipebyingred , parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecipeByIngredientViewHolder holder, int position) {
        holder.missedIngredDetails.setVisibility(View.GONE);
        AtomicReference<Boolean> notView = new AtomicReference<>(false);

        holder.viewMissedIngredient.setOnClickListener(v->{
            if (!notView.get()){
                notView.set(true);
                holder.MissedIngredList.setHasFixedSize(true);
                holder.MissedIngredList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                missedIngredientAdapter missedIngredientAdapter = new missedIngredientAdapter(context, list.get(position).missedIngredients);
                holder.MissedIngredList.setAdapter(missedIngredientAdapter);
                holder.viewMissedIngredient.setText("Hide");
                holder.missedIngredDetails.setVisibility(View.VISIBLE);
            } else {
                notView.set(false);
                holder.missedIngredDetails.setVisibility(View.GONE);
                holder.viewMissedIngredient.setText("View");
            }
        });
        holder.recipeName.setText(list.get(position).title);
        holder.missedIngred.setText(list.get(position).missedIngredientCount + " Missed Ingredient");
        holder.likes.setText(list.get(position).likes + " Likes");
        Picasso.get().load(list.get(position).image).into(holder.recipeImage);
        holder.recipeCardContainerbyIngred.setOnClickListener(v ->{
            listener.onRecipeClick(String.valueOf(list.get(holder.getAdapterPosition()).id)); // might error getAdapterPosition
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecipeByIngredientViewHolder extends RecyclerView.ViewHolder{

    ImageView recipeImage;
    TextView recipeName, missedIngred, likes, viewMissedIngredient;
    CardView recipeCardContainerbyIngred;
    LinearLayout missedIngredDetails;
    RecyclerView MissedIngredList;

    public RecipeByIngredientViewHolder(@NonNull View itemView) {
        super(itemView);

        MissedIngredList = itemView.findViewById(R.id.MissedIngredList);
        viewMissedIngredient = itemView.findViewById(R.id.viewMissedIngredient);
        missedIngredDetails = itemView.findViewById(R.id.missedIngredDetails);
        recipeImage = itemView.findViewById(R.id.recipeImage);
        recipeName = itemView.findViewById(R.id.recipeName);
        missedIngred = itemView.findViewById(R.id.missedIngred);
        likes = itemView.findViewById(R.id.likes);
        recipeCardContainerbyIngred = itemView.findViewById(R.id.recipeCardContainerbyIngred);
    }
}
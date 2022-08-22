package com.example.recipely.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipely.Models.ExtendedIngredient;
import com.example.recipely.R;

import java.util.List;

public class recipeDetailsIngredientAdapter extends RecyclerView.Adapter<ingredientViewHolder> {

    Context context;
    List<ExtendedIngredient> list;

    public recipeDetailsIngredientAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ingredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ingredientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipedetails_ingred, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ingredientViewHolder holder, int position) {
        holder.units.setText(list.get(position).amount + " " + list.get(position).unit);
        holder.item.setText(list.get(position).originalName);
        holder.units.setSelected(true);
        holder.item.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ingredientViewHolder extends RecyclerView.ViewHolder{

    TextView units, item;
    public ingredientViewHolder(@NonNull View itemView) {
        super(itemView);

        units = itemView.findViewById(R.id.units);
        item = itemView.findViewById(R.id.item);
    }
}
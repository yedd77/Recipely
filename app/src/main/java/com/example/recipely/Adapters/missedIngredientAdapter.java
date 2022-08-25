package com.example.recipely.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipely.Models.MissedIngredient;
import com.example.recipely.R;

import java.util.List;


public class missedIngredientAdapter extends RecyclerView.Adapter<missedIngredientViewHolder> {

    Context context;
    List<MissedIngredient> list;

    public missedIngredientAdapter(Context context, List<MissedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public missedIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new missedIngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_missed_ingredient, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull missedIngredientViewHolder holder, int position) {
        holder.textView_Unit.setText(list.get(position).amount + " "+ list.get(position).unitLong);
        holder.textView_ingredName.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class missedIngredientViewHolder extends RecyclerView.ViewHolder{

    TextView textView_Unit, textView_ingredName;

    public missedIngredientViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_Unit = (TextView) itemView.findViewById(R.id.textView_Unit);
        textView_ingredName = (TextView) itemView.findViewById(R.id.textView_ingredName);
    }
}
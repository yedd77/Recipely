package com.example.recipely.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipely.Models.Step;
import com.example.recipely.R;

import java.util.List;

public class instructionstepadapter extends RecyclerView.Adapter<InstructionStepViewHolder> {

    Context context;
    List<Step> list;

    public instructionstepadapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps , parent , false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.textView_instruction_step_number.setText("Step "+ list.get(position).number);
        holder.textView_instruction_step_title.setText(list.get(position).step);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder{

    TextView textView_instruction_step_number, textView_instruction_step_title;

    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_instruction_step_number = itemView.findViewById(R.id.textView_instruction_step_number);
        textView_instruction_step_title = itemView.findViewById(R.id.textView_instruction_step_title);
    }
}

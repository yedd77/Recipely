package com.example.recipely.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipely.Models.InstructionResponse;
import com.example.recipely.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionViewHolder>{

    Context context;
    List<InstructionResponse> list;

    public InstructionAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {

        holder.textViewInstructionName.setText(list.get(position).name);
        holder.recyclerInstructionSteps.setHasFixedSize(true);
        holder.recyclerInstructionSteps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        instructionstepadapter instructionstepadapter = new instructionstepadapter(context, list.get(position).steps);
        holder.recyclerInstructionSteps.setAdapter(instructionstepadapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionViewHolder extends RecyclerView.ViewHolder{

    TextView textViewInstructionName;
    RecyclerView recyclerInstructionSteps;

    public InstructionViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewInstructionName = itemView.findViewById(R.id.textViewInstructionName);
        recyclerInstructionSteps = itemView.findViewById(R.id.recyclerInstructionSteps);
    }
}
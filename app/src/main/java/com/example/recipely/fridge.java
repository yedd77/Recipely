package com.example.recipely;

import static java.time.temporal.ChronoUnit.DAYS;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fridge#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fridge extends Fragment {

    private View ingredientView;
    private RecyclerView myIngredientList;
    private LinearLayout noFridge;
    private ScrollView HaveData;
    private DatabaseReference fridgeRef;
    private FirebaseAuth mAuth;
    private String currentUserID;
    private LinearLayout expiryNote;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fridge() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fridge.
     */
    // TODO: Rename and change types and number of parameters
    public static fridge newInstance(String param1, String param2) {
        fridge fragment = new fridge();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ingredientView = inflater.inflate(R.layout.fragment_fridge, container, false);

        expiryNote = (LinearLayout) ingredientView.findViewById(R.id.expiryNote);
        HaveData = (ScrollView) ingredientView.findViewById(R.id.HaveData);
        noFridge = (LinearLayout) ingredientView.findViewById(R.id.noFridge);
        expiryNote.setVisibility(View.GONE);
        HaveData.setVisibility(View.GONE);
        myIngredientList = (RecyclerView) ingredientView.findViewById(R.id.ingredientList);
        myIngredientList.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();

        fridgeRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);

        fridgeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    HaveData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return ingredientView;
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<fridgeItem> options =
                new FirebaseRecyclerOptions.Builder<fridgeItem>()
                        .setQuery(fridgeRef.orderByChild("Expiry") , fridgeItem.class)
                        .build();

        FirebaseRecyclerAdapter<fridgeItem, fridgeViewHolder> adapter
                = new FirebaseRecyclerAdapter<fridgeItem, fridgeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull fridgeViewHolder holder, int position, @NonNull fridgeItem model) {
                String itemName = getRef(position).getKey();

                LocalDate expiry = LocalDate.parse(model.getExpiry());
                LocalDate today = LocalDate.now();
                long dayDiff = DAYS.between(today, expiry);
                if(dayDiff <= 7){
                    holder.cardViewIngredient.setBackgroundColor(Color.parseColor("#FFD1D1"));
                    expiryNote.setVisibility(View.VISIBLE);
                }
                holder.ingredName.setText(itemName);
                holder.ingredExpiry.setText(dayDiff + " Days until expiry");

                holder.deleteBtn.setOnClickListener(v ->{
                    fridgeRef.child(itemName.toString()).removeValue();
                });
            }

            @NonNull
            @Override
            public fridgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredientrecycle, parent, false);
                fridgeViewHolder viewHolder = new fridgeViewHolder(view);
                return viewHolder;
            }
        };
        myIngredientList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class fridgeViewHolder extends RecyclerView.ViewHolder{

        TextView ingredName, ingredExpiry;
        LinearLayout deleteBtn, cardViewIngredient;

        public fridgeViewHolder(@NonNull View itemView) {
            super(itemView);


            ingredName = itemView.findViewById(R.id.itemName);
            ingredExpiry = itemView.findViewById(R.id.itemExpiry);

            cardViewIngredient = itemView.findViewById(R.id.cardViewIngredient);
            //set button to delete an item
            deleteBtn = itemView.findViewById(R.id.deleteItem);
        }
    }
}
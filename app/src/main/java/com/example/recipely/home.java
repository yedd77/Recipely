package com.example.recipely;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.recipely.Adapters.RandomRecipeAdapter;
import com.example.recipely.Adapters.RecipeByIngredientAdapter;
import com.example.recipely.Listeners.RandomRecipeResponseListener;
import com.example.recipely.Listeners.RecipeByIngredientListener;
import com.example.recipely.Listeners.RecipeClickListener;
import com.example.recipely.Models.RandomRecipeAPIResponse;
import com.example.recipely.Models.RecipeIngredResponse;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */

public class home extends Fragment {
    List<String> Ingredient =  new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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

    //set hooks
    ShimmerFrameLayout VS, HS;
    LinearLayout LL, Loaded;

    RandomRecipeAdapter randomRecipeAdapter;
    RequestManager manager;
    RecyclerView recyclerView, recyclerFromYourFridge;
    RecipeByIngredientAdapter recipeByIngredientAdapter;
    LinearLayout NoDataAPI;
    ScrollView havedataAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        //shimmer effect
        HS = v.findViewById(R.id.horizontal_shimmer);
        VS = v.findViewById(R.id.Vertical_shimmer);
        LL = v.findViewById(R.id.LinearLayout);
        Loaded = v.findViewById(R.id.loadedHome);

        HS.startShimmer();
        VS.startShimmer();
        Loaded.setVisibility(View.INVISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(()->{
            HS.stopShimmer();
            VS.stopShimmer();
            LL.setVisibility(View.GONE);
            Loaded.setVisibility(View.VISIBLE);
        },5000);

        NoDataAPI = (LinearLayout) v.findViewById(R.id.NoDataAPI);
        havedataAPI = (ScrollView) v.findViewById(R.id.havedataAPI);

        manager = new RequestManager(getContext());
        manager.getRandomRecipe(randomRecipeResponseListener);

        NoDataAPI.setVisibility(View.GONE);

        recyclerFromYourFridge = (RecyclerView) v.findViewById(R.id.recyclerFromYourFridge);
        //get ingredient from database
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (!(mAuth.getCurrentUser() == null)){
            String currentUser = mAuth.getCurrentUser().getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUser);
            reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for (DataSnapshot userSnapshot : task.getResult().getChildren()){
                            Ingredient.add(userSnapshot.getKey());
                        }
                    }
                    if (Ingredient.size() == 0){
                        NoDataAPI.setVisibility(View.VISIBLE);
                    }
                    manager.getRecipeByIngredient(recipeByIngredientListener , Ingredient, 20);
                }
            });
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.randomRecipeRecycler);
        return v;

    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeAPIResponse response, String message) {

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 20));
            randomRecipeAdapter = new RandomRecipeAdapter(getContext() , response.recipes, recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);

        }
        @Override
        public void didError(String message) {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeByIngredientListener recipeByIngredientListener = new RecipeByIngredientListener() {
        @Override
        public void didFetch(List<RecipeIngredResponse> response, String message) {

            recyclerFromYourFridge.setHasFixedSize(true);
            recyclerFromYourFridge.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recipeByIngredientAdapter = new RecipeByIngredientAdapter(getContext(), response, recipeClickListener);
            recyclerFromYourFridge.setAdapter(recipeByIngredientAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(String id) {
            startActivity(new Intent(getContext(), RecipeDetails.class)
                    .putExtra("id", id));

        }
    };
}

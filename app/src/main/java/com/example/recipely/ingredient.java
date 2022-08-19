package com.example.recipely;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ingredient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ingredient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ingredient() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ingredient newInstance(String param1, String param2) {
        ingredient fragment = new ingredient();
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
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);

        LinearLayout vegetableBtn = (LinearLayout) view.findViewById(R.id.ingredientVegetablesBtn);
        LinearLayout ingredientNutsBtn = (LinearLayout) view.findViewById(R.id.ingredientNutsBtn);
        LinearLayout ingredientSeafoodBtn = (LinearLayout) view.findViewById(R.id.ingredientSeafoodBtn);
        LinearLayout ingredientMushroomBtn = (LinearLayout) view.findViewById(R.id.ingredientMushroomBtn);
        LinearLayout ingredientMeatSubBtn = (LinearLayout) view.findViewById(R.id.ingredientMeatSubBtn);
        LinearLayout ingredientDairyBtn = (LinearLayout) view.findViewById(R.id.ingredientDairyBtn);

        //listener for vegetable ingredient category
        vegetableBtn.setOnClickListener(v -> {
            Intent VegetablePage = new Intent(getActivity(), ingred_veg.class);
            startActivity(VegetablePage);
        });

        ingredientNutsBtn.setOnClickListener(v ->{
            Intent nutsPage = new Intent(getActivity(), ingred_nuts.class);
            startActivity(nutsPage);
        });

        ingredientSeafoodBtn.setOnClickListener(v ->{
            Intent seafoodPage = new Intent(getActivity(), ingred_seafood.class);
            startActivity(seafoodPage);
        });

        ingredientMushroomBtn.setOnClickListener(v ->{
            Intent mushroomPage = new Intent(getActivity(), ingred_mushroom.class);
            startActivity(mushroomPage);
        });

        ingredientMeatSubBtn.setOnClickListener(v ->{
            Intent meatPage = new Intent(getActivity(), ingred_meat.class);
            startActivity(meatPage);
        });

        ingredientDairyBtn.setOnClickListener(v ->{
            Intent dairyPage = new Intent(getActivity(), ingred_dairy.class);
            startActivity(dairyPage);
        });

        return view;
    }
}
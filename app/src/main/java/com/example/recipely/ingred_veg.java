package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ingred_veg extends AppCompatActivity {

    TextView itemCount , Garlic, Onion;
    LinearLayout addBtn;
    int item = 0;
    Boolean GarlicIsClicked = false, OnionIsClicked = false;
    int garlicSelected, onionSelected;
    String onionDateExpiry, garlicDateExpiry;
    String[] Ingredient = {"Garlic" , "Onion"}; //TODO - Tambah item kat sini

    DatabaseReference dataRef;
    String currentUserID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_veg);

        itemCount = (TextView) findViewById(R.id.itemCount);
        Garlic = (TextView) findViewById(R.id.vegItemGarlic);
        Onion = (TextView) findViewById(R.id.vegItemOnion);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        dataRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);

        Onion.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!OnionIsClicked){

                addItemCount();
                setClicked(Onion);
                OnionIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Onion);
                OnionIsClicked = false;
            }
        });

        Garlic.setOnClickListener(view ->{

            if (!GarlicIsClicked) {

                addItemCount();
                setClicked(Garlic);
                GarlicIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Garlic);
                GarlicIsClicked = false;
            }
        });

        //listener for adding item into db
        addBtn.setOnClickListener(v ->{
            setExpiry();
            checkItem();

            for (int x=0; x<Ingredient.length; x++) {

                int getItemValue = 0;
                String getItemExpiry = "";

                //TODO - Tambah item baru kat sini jugak
                if (Objects.equals(Ingredient[x], "Garlic") && garlicSelected == 1) {
                    getItemValue = garlicSelected;
                    getItemExpiry = garlicDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                } else if (Objects.equals(Ingredient[x], "Onion") && onionSelected == 1) {
                    getItemValue = onionSelected;
                    getItemExpiry = onionDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
            }
        });
    }

    private void addItemByIngredient(String IngredientName , int item , String Expiry) {
        //Creating new hashmap for each item in the loop and add it into db
        HashMap hm = new HashMap();
        hm.put("Ingredient" , item);
        hm.put("Expiry", Expiry);

        dataRef.child(IngredientName).setValue(hm);
    }

    // method to set value of an item to boolean before sending it to database
    private void checkItem() {

        if (GarlicIsClicked) {
            garlicSelected = 1; // Button selected
        } else {
            garlicSelected = 0; //button not selected
        }

        if (OnionIsClicked) {
            onionSelected = 1;
        } else {
            onionSelected = 0;
        }

        //TODO - Tambah item baru kat sini
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places
        int onionExpiry = 90;
        int garlicExpiry = 90;
        //TODO - Tambah item baru kat sini

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime onionDate = LocalDateTime.now().plusDays(onionExpiry);
        LocalDateTime GarlicDate = LocalDateTime.now().plusDays(garlicExpiry);

        //set the date as string
        onionDateExpiry = String.valueOf(dtf.format(onionDate));
        garlicDateExpiry = String.valueOf(dtf.format(GarlicDate));
        //TODO - Tambah item baru kat sini
    }

    //update item counter when remove an item
    public void minusItemCount() {
        item--;
        itemCount.setText(String.format(item + " Item"));
    }

    //update item counter when adding an item
    public void addItemCount() {
        item++;
        itemCount.setText(String.format(item + " Item"));
    }

    // method to set properties for unchecked item
    private void setNotClicked(TextView item) {
        item.setBackground(getResources().getDrawable(R.drawable.ingredient_itembg));
        item.setTextColor(Color.parseColor("#707070"));
    }

    // method to set properties for checked item
    private void setClicked(TextView item) {
        item.setBackground(getResources().getDrawable(R.drawable.ingridient_itembg_clicked));
        item.setTextColor(getResources().getColor(R.color.white));
    }
}
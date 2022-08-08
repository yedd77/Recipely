package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ingred_veg extends AppCompatActivity {

    TextView itemCount , Garlic, Onion;
    LinearLayout addBtn;
    int item = 0;
    Boolean GarlicIsClicked = false, OnionIsClicked = false;
    int garlicSelected, onionSelected;
    String onionDateExpiry, garlicDateExpiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_veg);

        itemCount = (TextView) findViewById(R.id.itemCount);
        Garlic = (TextView) findViewById(R.id.vegItemGarlic);
        Onion = (TextView) findViewById(R.id.vegItemOnion);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);
        System.out.println("System set value of the item");

        Onion.setOnClickListener(view -> {
            //check if the item has been clicked or not
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

            //check if the item has been clicked or not
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

        //listener to add item to database
        addBtn.setOnClickListener(v ->{
            DAOvegetables daoVeg = new DAOvegetables();
            setExpiry();
            checkItem();
            vegetable veg = new vegetable(garlicSelected , garlicDateExpiry, onionSelected, onionDateExpiry);
            daoVeg.add(veg).addOnSuccessListener( suc ->{
                Toast.makeText(this, "Item has been recorded", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        });
    }

    // method to set value of an item to boolean before sending it to database
    private void checkItem() {

        if (GarlicIsClicked) {
            garlicSelected = 1;
        } else {
            garlicSelected = 0;
        }

        if (OnionIsClicked) {
            onionSelected = 1;
        } else {
            onionSelected = 0;
        }
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places
        int onionExpiry = 90;
        int garlicExpiry = 90;

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime onionDate = LocalDateTime.now().plusDays(onionExpiry);
        LocalDateTime GarlicDate = LocalDateTime.now().plusDays(garlicExpiry);

        //set the date as string
        onionDateExpiry = String.valueOf(dtf.format(onionDate));
        garlicDateExpiry = String.valueOf(dtf.format(GarlicDate));
    }

    //update item counter when remove an item
    public void minusItemCount() {
        System.out.println("y");
        item--;
        itemCount.setText(String.format(item + " Item"));
    }

    //update item counter when adding an item
    public void addItemCount() {
        System.out.println("x");
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
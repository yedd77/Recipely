package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ingred_meat extends AppCompatActivity {

    TextView itemCount , Beef, Chicken, Pork, Chorizo, Sausage, Goat,
            Meatball, Mutton, Lamb;
    LinearLayout addBtn;
    int item = 0;
    Boolean BeefIsClicked = false, ChickenIsClicked = false, PorkIsClicked = false, ChorizoIsClicked = false,
            SausageIsClicked = false, GoatIsClicked = false,  MeatballIsClicked = false,
            LambIsClicked = false, MuttonIsClicked = false;
    int beefSelected, chickenSelected, porkSelected, chorizoSelected, sausageSelected,
            goatSelected, meatballSelected, muttonSelected, lambSelected;
    String beefDateExpiry, chickenDateExpiry, porkDateExpiry, chorizoDateExpiry,
            sausagekDateExpiry, goatDateExpiry, meatballDateExpiry,
            lambDateExpiry, muttonDateExpiry;
    String[] Ingredient = {"Beef", "Chicken", "Pork", "Chorizo", "Sausage", "Goat", "" +
            "Meatball", "Mutton", "Lamb" }; //TODO - Tambah item kat sini
    DatabaseReference dataRef;
    String currentUserID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_meat);

        itemCount = (TextView) findViewById(R.id.itemCount);
        Beef = (TextView) findViewById(R.id.meatItemBeef);
        Chicken = (TextView) findViewById(R.id.meatItemChicken);
        Pork = (TextView) findViewById(R.id.meatItempork);
        Chorizo = (TextView) findViewById(R.id.meatItemChorizo);
        Sausage = (TextView) findViewById(R.id.meatItemSausage);
        Goat = (TextView) findViewById(R.id.meatItemGoat);
        Meatball = (TextView) findViewById(R.id.meatItemMeatball);
        Mutton = (TextView) findViewById(R.id.meatItemMutton);
        Lamb = (TextView) findViewById(R.id.meatItemLamb);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        dataRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);

        Beef.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!BeefIsClicked){

                addItemCount();
                setClicked(Beef);
                BeefIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Beef);
                BeefIsClicked = false;
            }
        });

        Chicken.setOnClickListener(view ->{

            if (!ChickenIsClicked) {

                addItemCount();
                setClicked(Chicken);
                ChickenIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Chicken);
                ChickenIsClicked = false;
            }
        });

        Pork.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PorkIsClicked){

                addItemCount();
                setClicked(Pork);
                PorkIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Pork);
                PorkIsClicked = false;
            }
        });

        Chorizo.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!ChorizoIsClicked){

                addItemCount();
                setClicked(Chorizo);
                ChorizoIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Chorizo);
                ChorizoIsClicked = false;
            }
        });

        Sausage.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!SausageIsClicked){

                addItemCount();
                setClicked(Sausage);
                SausageIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Sausage);
                SausageIsClicked = false;
            }
        });

        Goat.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!GoatIsClicked) {

                addItemCount();
                setClicked(Goat);
                GoatIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Goat);
                GoatIsClicked = false;
            }
        });

        Lamb.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!LambIsClicked){

                addItemCount();
                setClicked(Lamb);
                LambIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Lamb);
                LambIsClicked = false;
            }
        });

        Mutton.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!MuttonIsClicked){

                addItemCount();
                setClicked(Mutton);
                MuttonIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Mutton);
                MuttonIsClicked = false;
            }
        });

        Meatball.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!MeatballIsClicked){

                addItemCount();
                setClicked(Meatball);
                MeatballIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Meatball);
                MeatballIsClicked = false;
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
                if (Objects.equals(Ingredient[x], "Beef") && beefSelected == 1) {
                    getItemValue = beefSelected;
                    getItemExpiry = beefDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                } else if (Objects.equals(Ingredient[x], "Chicken") && chickenSelected == 1) {
                    getItemValue = chickenSelected;
                    getItemExpiry = chickenDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Pork") && porkSelected == 1) {
                    getItemValue = porkSelected;
                    getItemExpiry = porkDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Chorizo") && chorizoSelected == 1) {
                    getItemValue = chorizoSelected;
                    getItemExpiry = chorizoDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Sausage") && sausageSelected == 1) {
                    getItemValue = sausageSelected;
                    getItemExpiry = sausagekDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Goat") && goatSelected == 1) {
                    getItemValue = goatSelected;
                    getItemExpiry = goatDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Lamb") && lambSelected == 1) {
                    getItemValue = lambSelected;
                    getItemExpiry = lambDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Mutton") && muttonSelected == 1) {
                    getItemValue = muttonSelected;
                    getItemExpiry = muttonDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Meatball") && meatballSelected == 1) {
                    getItemValue = meatballSelected;
                    getItemExpiry = meatballDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }


            }
            Toast.makeText(this, "Item added into fridge", Toast.LENGTH_SHORT).show();
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

        if (BeefIsClicked) {
            beefSelected = 1; // Button selected
        } else {
            beefSelected = 0; //button not selected
        }

        if (ChickenIsClicked) {
            chickenSelected = 1;
        } else {
            chickenSelected = 0;
        }

        if (PorkIsClicked) {
            porkSelected = 1;
        } else {
            porkSelected = 0;
        }

        if (ChorizoIsClicked) {
            chorizoSelected = 1;
        } else {
            chorizoSelected = 0;
        }

        if (SausageIsClicked) {
            sausageSelected = 1;
        } else {
            sausageSelected = 0;
        }

        if (GoatIsClicked) {
            goatSelected = 1;
        } else {
            goatSelected = 0;
        }

        if (LambIsClicked) {
            lambSelected = 1;
        } else {
            lambSelected = 0;
        }

        if (MuttonIsClicked) {
            muttonSelected = 1;
        } else {
            muttonSelected = 0;
        }

        if (MeatballIsClicked) {
            meatballSelected = 1;
        } else {
            meatballSelected = 0;
        }





        //TODO - Tambah item baru kat sini
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places

        int beefExpiry = 90;
        int chickenExpiry = 60;
        int porkExpiry = 120;
        int chorizoExpiry = 300;
        int sausageExpiry = 60;
        int goatExpiry = 90;
        int lambExpiry = 120 ;
        int muttonExpiry = 150;
        int meatballExpiry = 90;


        //TODO - Tambah item baru kat sini

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime beefDate = LocalDateTime.now().plusDays(beefExpiry);
        LocalDateTime chickenDate = LocalDateTime.now().plusDays(chickenExpiry);
        LocalDateTime porkDate = LocalDateTime.now().plusDays(porkExpiry);
        LocalDateTime chorizoDate = LocalDateTime.now().plusDays(chorizoExpiry);
        LocalDateTime sausageDate = LocalDateTime.now().plusDays(sausageExpiry);
        LocalDateTime goatDate = LocalDateTime.now().plusDays(goatExpiry);
        LocalDateTime lambDate = LocalDateTime.now().plusDays(lambExpiry);
        LocalDateTime muttonDate = LocalDateTime.now().plusDays(muttonExpiry);
        LocalDateTime meatballDate = LocalDateTime.now().plusDays(meatballExpiry);


        //set the date as string
        beefDateExpiry = String.valueOf(dtf.format(beefDate));
        chickenDateExpiry = String.valueOf(dtf.format(chickenDate));
        porkDateExpiry = String.valueOf(dtf.format(porkDate));
        chorizoDateExpiry = String.valueOf(dtf.format(chorizoDate));
        sausagekDateExpiry = String.valueOf(dtf.format(sausageDate));
        goatDateExpiry = String.valueOf(dtf.format(goatDate));
        lambDateExpiry = String.valueOf(dtf.format(lambDate));
        muttonDateExpiry = String.valueOf(dtf.format(muttonDate));
        meatballDateExpiry = String.valueOf(dtf.format(meatballDate));

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
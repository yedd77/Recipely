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

public class ingred_nuts extends AppCompatActivity {

    TextView itemCount , Pistachios, Hazelnuts, Cashews, Walnuts, MarconaAlmonds,
            MacadamiaNuts, Peanuts, Almonds, BrazilNuts, PineNuts, Pecans ;
    LinearLayout addBtn;
    int item = 0;
    Boolean PistachiosIsClicked = false, HazelnutsIsClicked = false, CashewsIsClicked = false, WalnutsIsClicked = false, MarconaAlmondsIsClicked = false, MacadamiaNutsIsClicked = false,
            PeanutsIsClicked = false, AlmondsIsClicked = false, BrazilNutsIsClicked = false, PineNutsIsClicked = false, PecansIsClicked = false;
    int pistachiosSelected, hazelnutsSelected, cashewsSelected, walnutsSelected, marconaAlmondSelected, macadamiaNutsSelected,
            almondsSelected, peanutsSelected, brazilnutsSelected, pinenutsSelected, pecansSelected;
    String pistachiosDateExpiry, cashewsDateExpiry, walnutsDateExpiry, marconaalmondsDateExpiry, macadamianutsDateExpiry, pecansDateExpiry,
            peanutsDateExpiry, almondsDateExpiry, brazilnutsDateExpiry, hazelnutsDateExpiry, pinenutsDateExpiry;
    String[] Ingredient = {"Pistachios", "Hazelnuts", "Cashews", "Walnuts", "Marcona Almonds",
            "Macadamia Nuts", "Peanuts", "Almonds", "Brazil Nuts", "Pine Nuts", "Pecans" }; //TODO - Tambah item kat sini
    DatabaseReference dataRef;
    String currentUserID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_nuts);

        itemCount = (TextView) findViewById(R.id.itemCount);
        Pistachios = (TextView) findViewById(R.id.nutsItemPistachio);
        Hazelnuts = (TextView) findViewById(R.id.nutsItemHazelnut);
        Cashews = (TextView) findViewById(R.id.nutsItemCashew);
        Walnuts = (TextView) findViewById(R.id.nutsItemWalnut);
        MarconaAlmonds = (TextView) findViewById(R.id.nutsItemMarcona);
        MacadamiaNuts = (TextView) findViewById(R.id.nutsItemMacadamia);
        Peanuts = (TextView) findViewById(R.id.nutsItemPeanut);
        Almonds = (TextView) findViewById(R.id.nutsItemAlmond);
        BrazilNuts = (TextView) findViewById(R.id.nutsItemBrazil);
        PineNuts = (TextView) findViewById(R.id.nutsItemPine);
        Pecans = (TextView) findViewById(R.id.nutsItemPecan);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        dataRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);

        Pistachios.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!PistachiosIsClicked){

                addItemCount();
                setClicked(Pistachios);
                PistachiosIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Pistachios);
                PistachiosIsClicked = false;
            }
        });

        Hazelnuts.setOnClickListener(view ->{

            if (!HazelnutsIsClicked) {

                addItemCount();
                setClicked(Hazelnuts);
                HazelnutsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Hazelnuts);
                HazelnutsIsClicked = false;
            }
        });

        Cashews.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CashewsIsClicked){

                addItemCount();
                setClicked(Cashews);
                CashewsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cashews);
                CashewsIsClicked = false;
            }
        });

        Walnuts.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!WalnutsIsClicked){

                addItemCount();
                setClicked(Walnuts);
                WalnutsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Walnuts);
                WalnutsIsClicked = false;
            }
        });

        MarconaAlmonds.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!MarconaAlmondsIsClicked){

                addItemCount();
                setClicked(MarconaAlmonds);
                MarconaAlmondsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(MarconaAlmonds);
                MarconaAlmondsIsClicked = false;
            }
        });

        MacadamiaNuts.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!MacadamiaNutsIsClicked) {

                addItemCount();
                setClicked(MacadamiaNuts);
                MacadamiaNutsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(MacadamiaNuts);
                MacadamiaNutsIsClicked = false;
            }
        });

        Peanuts.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PeanutsIsClicked){

                addItemCount();
                setClicked(Peanuts);
                PeanutsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Peanuts);
                PeanutsIsClicked = false;
            }
        });

        Almonds.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!AlmondsIsClicked){

                addItemCount();
                setClicked(Almonds);
                AlmondsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Almonds);
                AlmondsIsClicked = false;
            }
        });

        BrazilNuts.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BrazilNutsIsClicked){

                addItemCount();
                setClicked(BrazilNuts);
                BrazilNutsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(BrazilNuts);
                BrazilNutsIsClicked = false;
            }
        });

        PineNuts.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PineNutsIsClicked){

                addItemCount();
                setClicked(PineNuts);
                PineNutsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(PineNuts);
                PineNutsIsClicked = false;
            }
        });

        Pecans.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PecansIsClicked){

                addItemCount();
                setClicked(Pecans);
                PecansIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Pecans);
                PecansIsClicked = false;
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
                if (Objects.equals(Ingredient[x], "Pistachios") && pistachiosSelected == 1) {
                    getItemValue = pistachiosSelected;
                    getItemExpiry = pistachiosDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                } else if (Objects.equals(Ingredient[x], "Hazelnuts") && hazelnutsSelected == 1) {
                    getItemValue = hazelnutsSelected;
                    getItemExpiry = hazelnutsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Cashews") && cashewsSelected == 1) {
                    getItemValue = cashewsSelected;
                    getItemExpiry = cashewsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Walnuts") && walnutsSelected == 1) {
                    getItemValue = walnutsSelected;
                    getItemExpiry = walnutsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Marcona Almonds") && marconaAlmondSelected == 1) {
                    getItemValue = marconaAlmondSelected;
                    getItemExpiry = marconaalmondsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Macadamia Nuts") && macadamiaNutsSelected == 1) {
                    getItemValue = macadamiaNutsSelected;
                    getItemExpiry = macadamianutsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Peanuts") && peanutsSelected == 1) {
                    getItemValue = peanutsSelected;
                    getItemExpiry = peanutsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Almonds") && almondsSelected == 1) {
                    getItemValue = almondsSelected;
                    getItemExpiry = almondsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Brazil Nuts") && brazilnutsSelected == 1) {
                    getItemValue = brazilnutsSelected;
                    getItemExpiry = brazilnutsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Pine Nuts") && pinenutsSelected == 1) {
                    getItemValue = pinenutsSelected;
                    getItemExpiry = pinenutsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Pecans") && pecansSelected == 1) {
                    getItemValue = pecansSelected;
                    getItemExpiry = pecansDateExpiry;
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

        if (PistachiosIsClicked) {
            pistachiosSelected = 1; // Button selected
        } else {
            pistachiosSelected = 0; //button not selected
        }

        if (HazelnutsIsClicked) {
            hazelnutsSelected = 1;
        } else {
            hazelnutsSelected = 0;
        }

        if (CashewsIsClicked) {
            cashewsSelected = 1;
        } else {
            cashewsSelected = 0;
        }

        if (WalnutsIsClicked) {
            walnutsSelected = 1;
        } else {
            walnutsSelected = 0;
        }

        if (MarconaAlmondsIsClicked) {
            marconaAlmondSelected = 1;
        } else {
            marconaAlmondSelected = 0;
        }

        if (MacadamiaNutsIsClicked) {
            macadamiaNutsSelected = 1;
        } else {
            macadamiaNutsSelected = 0;
        }

        if (PeanutsIsClicked) {
            peanutsSelected = 1;
        } else {
            peanutsSelected = 0;
        }

        if (AlmondsIsClicked) {
            almondsSelected = 1;
        } else {
            almondsSelected = 0;
        }

        if (BrazilNutsIsClicked) {
            brazilnutsSelected = 1;
        } else {
            brazilnutsSelected = 0;
        }

        if (PineNutsIsClicked) {
            pinenutsSelected = 1;
        } else {
            pinenutsSelected = 0;
        }

        if (PecansIsClicked) {
            pecansSelected = 1;
        } else {
            pecansSelected = 0;
        }

        //TODO - Tambah item baru kat sini
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places

        int pistachiosExpiry = 150;
        int hazelnutsExpiry = 90;
        int cashewsExpiry = 150;
        int walnutsExpiry = 90;
        int marconaalmondsExpiry = 150;
        int macadamianutsExpiry = 90;
        int peanutsExpiry = 180;
        int almondsExpiry = 150;
        int brazilnutsExpiry = 150;
        int pinenutsExpiry = 90;
        int pecansExpiry = 150;


        //TODO - Tambah item baru kat sini

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime pistachiosDate = LocalDateTime.now().plusDays(pistachiosExpiry);
        LocalDateTime hazelnutsDate = LocalDateTime.now().plusDays(hazelnutsExpiry);
        LocalDateTime cashewsDate = LocalDateTime.now().plusDays(cashewsExpiry);
        LocalDateTime walnutsDate = LocalDateTime.now().plusDays(walnutsExpiry);
        LocalDateTime marconaalmondsDate = LocalDateTime.now().plusDays(marconaalmondsExpiry);
        LocalDateTime macadamianutsDate = LocalDateTime.now().plusDays(macadamianutsExpiry);
        LocalDateTime peanutsDate = LocalDateTime.now().plusDays(peanutsExpiry);
        LocalDateTime almondsDate = LocalDateTime.now().plusDays(almondsExpiry);
        LocalDateTime brazilnutsDate = LocalDateTime.now().plusDays(brazilnutsExpiry);
        LocalDateTime pinenutsDate = LocalDateTime.now().plusDays(pinenutsExpiry);
        LocalDateTime pecansDate = LocalDateTime.now().plusDays(pecansExpiry);


        //set the date as string
        pistachiosDateExpiry = String.valueOf(dtf.format(pistachiosDate));
        hazelnutsDateExpiry = String.valueOf(dtf.format(hazelnutsDate));
        cashewsDateExpiry = String.valueOf(dtf.format(cashewsDate));
        walnutsDateExpiry = String.valueOf(dtf.format(walnutsDate));
        marconaalmondsDateExpiry = String.valueOf(dtf.format(marconaalmondsDate));
        macadamianutsDateExpiry = String.valueOf(dtf.format(macadamianutsDate));
        peanutsDateExpiry = String.valueOf(dtf.format(peanutsDate));
        almondsDateExpiry = String.valueOf(dtf.format(almondsDate));
        brazilnutsDateExpiry = String.valueOf(dtf.format(brazilnutsDate));
        pinenutsDateExpiry = String.valueOf(dtf.format(pinenutsDate));
        pecansDateExpiry = String.valueOf(dtf.format(pecansDate));

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
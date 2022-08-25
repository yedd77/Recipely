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

public class ingred_dairy extends AppCompatActivity {

    TextView itemCount , Cheese, FermentedMilk, Cream, Butter, LiquidMilk, Ghee,
            CondensedMilk, Casein, Yogurt, EvaporatedMilk;
    LinearLayout addBtn;
    int item = 0;
    Boolean ButterIsClicked = false, CheeseIsClicked = false, FermentedmilkIsClicked = false, CreamIsClicked = false,
            CaseinIsClicked = false, LiquidmilkIsClicked = false, GheeIsClicked = false, CondensedmilkIsClicked = false,
            YogurtIsClicked = false, EvaporatedmilkIsClicked = false;
    int butterSelected, cheeseSelected, fermentedmilkSelected, creamSelected, liquidmilkSelected,
            gheeSelected, condensedmilkSelected, caseinSelected, yogurtSelected, evaporatedmilkSelected;
    String butterDateExpiry, cheeseDateExpiry, fermentedmilkDateExpiry, creamDateExpiry,
            liquidmilkDateExpiry, gheeDateExpiry, condensedmilkDateExpiry, caseinDateExpiry,
            yogurtDateExpiry, evaporatedmilkDateExpiry;
    String[] Ingredient = {"Cheese", "Fermented Milk", "Cream", "Butter", "Liquid Milk", "Ghee",
            "Condensed Milk", "Casein", "Yogurt", "Evaporated Milk" }; //TODO - Tambah item kat sini
    DatabaseReference dataRef;
    String currentUserID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_dairy);

        itemCount = (TextView) findViewById(R.id.itemCount);
        Cheese = (TextView) findViewById(R.id.dairyItemCheese);
        FermentedMilk = (TextView) findViewById(R.id.dairyItemFermenMilk);
        Cream = (TextView) findViewById(R.id.dairyItemCream);
        Butter = (TextView) findViewById(R.id.dairyItemButter);
        LiquidMilk = (TextView) findViewById(R.id.dairyItemLiquMilk);
        Ghee = (TextView) findViewById(R.id.dairyItemGhee);
        CondensedMilk = (TextView) findViewById(R.id.dairyItemCondenMilk);
        Casein = (TextView) findViewById(R.id.dairyItemCasein);
        Yogurt = (TextView) findViewById(R.id.dairyItemYogurt);
        EvaporatedMilk = (TextView) findViewById(R.id.dairyItemEvapoMilk);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        dataRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);

        Butter.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!ButterIsClicked){

                addItemCount();
                setClicked(Butter);
                ButterIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Butter);
                ButterIsClicked = false;
            }
        });

        Cheese.setOnClickListener(view ->{

            if (!CheeseIsClicked) {

                addItemCount();
                setClicked(Cheese);
                CheeseIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cheese);
                CheeseIsClicked = false;
            }
        });

        FermentedMilk.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!FermentedmilkIsClicked){

                addItemCount();
                setClicked(FermentedMilk);
                FermentedmilkIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(FermentedMilk);
                FermentedmilkIsClicked = false;
            }
        });

        Cream.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CreamIsClicked){

                addItemCount();
                setClicked(Cream);
                CreamIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cream);
                CreamIsClicked = false;
            }
        });

        LiquidMilk.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!LiquidmilkIsClicked){

                addItemCount();
                setClicked(LiquidMilk);
                LiquidmilkIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(LiquidMilk);
                LiquidmilkIsClicked = false;
            }
        });

        Ghee.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!GheeIsClicked) {

                addItemCount();
                setClicked(Ghee);
                GheeIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Ghee);
                GheeIsClicked = false;
            }
        });

        CondensedMilk.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CondensedmilkIsClicked){

                addItemCount();
                setClicked(CondensedMilk);
                CondensedmilkIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(CondensedMilk);
                CondensedmilkIsClicked = false;
            }
        });

        Casein.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CaseinIsClicked){

                addItemCount();
                setClicked(Casein);
                CaseinIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Casein);
                CaseinIsClicked = false;
            }
        });

        Yogurt.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!YogurtIsClicked){

                addItemCount();
                setClicked(Yogurt);
                YogurtIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Yogurt);
                YogurtIsClicked = false;
            }
        });

        EvaporatedMilk.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!EvaporatedmilkIsClicked){

                addItemCount();
                setClicked(EvaporatedMilk);
                EvaporatedmilkIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(EvaporatedMilk);
                EvaporatedmilkIsClicked = false;
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
                if (Objects.equals(Ingredient[x], "Cheese") && cheeseSelected == 1) {
                    getItemValue = cheeseSelected;
                    getItemExpiry = cheeseDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                } else if (Objects.equals(Ingredient[x], "Cream") && creamSelected == 1) {
                    getItemValue = creamSelected;
                    getItemExpiry = creamDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Butter") && butterSelected == 1) {
                    getItemValue = butterSelected;
                    getItemExpiry = butterDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Fermented Milk") && fermentedmilkSelected == 1) {
                    getItemValue = fermentedmilkSelected;
                    getItemExpiry = fermentedmilkDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Liquid Milk") && liquidmilkSelected == 1) {
                    getItemValue = liquidmilkSelected;
                    getItemExpiry = liquidmilkDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Ghee") && gheeSelected == 1) {
                    getItemValue = gheeSelected;
                    getItemExpiry = gheeDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Condensed Milk") && condensedmilkSelected == 1) {
                    getItemValue = condensedmilkSelected;
                    getItemExpiry = condensedmilkDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Casein") && caseinSelected == 1) {
                    getItemValue = caseinSelected;
                    getItemExpiry = caseinDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Yogurt") && yogurtSelected == 1) {
                    getItemValue = yogurtSelected;
                    getItemExpiry = yogurtDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Evaporated Milk") && evaporatedmilkSelected == 1) {
                    getItemValue = evaporatedmilkSelected;
                    getItemExpiry = evaporatedmilkDateExpiry;
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

        if (ButterIsClicked) {
            butterSelected = 1; // Button selected
        } else {
            butterSelected = 0; //button not selected
        }

        if (CheeseIsClicked) {
            cheeseSelected = 1;
        } else {
            cheeseSelected = 0;
        }

        if (FermentedmilkIsClicked) {
            fermentedmilkSelected = 1;
        } else {
            fermentedmilkSelected = 0;
        }

        if (CreamIsClicked) {
            creamSelected = 1;
        } else {
            creamSelected = 0;
        }

        if (LiquidmilkIsClicked) {
            liquidmilkSelected = 1;
        } else {
            liquidmilkSelected = 0;
        }

        if (GheeIsClicked) {
            gheeSelected = 1;
        } else {
            gheeSelected = 0;
        }

        if (CondensedmilkIsClicked) {
            condensedmilkSelected = 1;
        } else {
            condensedmilkSelected = 0;
        }

        if (CaseinIsClicked) {
            caseinSelected = 1;
        } else {
            caseinSelected = 0;
        }

        if (YogurtIsClicked) {
            yogurtSelected = 1;
        } else {
            yogurtSelected = 0;
        }

        if (EvaporatedmilkIsClicked) {
            evaporatedmilkSelected = 1;
        } else {
            evaporatedmilkSelected = 0;
        }



        //TODO - Tambah item baru kat sini
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places

        int butterExpiry = 90;
        int cheeseExpiry = 60;
        int fermentedmilkExpiry = 7;
        int creamExpiry = 90;
        int liquidmilkExpiry = 7;
        int gheeExpiry = 150;
        int condensedmilkExpiry = 14 ;
        int caseinExpiry = 150;
        int yogurtExpiry = 60;
        int evaporatedExpiry = 90;


        //TODO - Tambah item baru kat sini

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime creamDate = LocalDateTime.now().plusDays(creamExpiry);
        LocalDateTime butterDate = LocalDateTime.now().plusDays(butterExpiry);
        LocalDateTime cheeseDate = LocalDateTime.now().plusDays(cheeseExpiry);
        LocalDateTime fermentedDate = LocalDateTime.now().plusDays(fermentedmilkExpiry);
        LocalDateTime liquidmilkDate = LocalDateTime.now().plusDays(liquidmilkExpiry);
        LocalDateTime gheeDate = LocalDateTime.now().plusDays(gheeExpiry);
        LocalDateTime condensedmilkDate = LocalDateTime.now().plusDays(condensedmilkExpiry);
        LocalDateTime caseinDate = LocalDateTime.now().plusDays(caseinExpiry);
        LocalDateTime yogurtDate = LocalDateTime.now().plusDays(yogurtExpiry);
        LocalDateTime evaporatedmilkDate = LocalDateTime.now().plusDays(evaporatedExpiry);


        //set the date as string
        creamDateExpiry = String.valueOf(dtf.format(creamDate));
        butterDateExpiry = String.valueOf(dtf.format(butterDate));
        cheeseDateExpiry = String.valueOf(dtf.format(cheeseDate));
        fermentedmilkDateExpiry = String.valueOf(dtf.format(fermentedDate));
        liquidmilkDateExpiry = String.valueOf(dtf.format(liquidmilkDate));
        gheeDateExpiry = String.valueOf(dtf.format(gheeDate));
        condensedmilkDateExpiry = String.valueOf(dtf.format(condensedmilkDate));
        caseinDateExpiry = String.valueOf(dtf.format(caseinDate));
        yogurtDateExpiry = String.valueOf(dtf.format(yogurtDate));
        evaporatedmilkDateExpiry = String.valueOf(dtf.format(evaporatedmilkDate));

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
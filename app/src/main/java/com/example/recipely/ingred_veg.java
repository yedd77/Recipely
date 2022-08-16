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

    TextView itemCount , Garlic, Onion, Lettuce, BellPepper, Tomato, Broccoli, CherryTomato, Chili, Carrot, BeetRoot, MangeTout, Radish, Parsley, Spinach, Basil, Potato,
            SpringOnion, CayennePepper, Asparagus, Avocado, Cabbage, CauliFlower, Celery, Cucumber, Corn, Parsnip, Fennel, Peas, Pumpkin, Swede, Turnip;
    LinearLayout addBtn;
    int item = 0;
    Boolean GarlicIsClicked = false, OnionIsClicked = false, LettuceIsClicked = false, BellPepperIsClicked = false, TomatoIsClicked = false, BroccoliIsClicked = false, CherryTomatoIsClicked = false, ChiliIsClicked = false, CarrotIsClicked = false,
            BeetRootIsClicked = false, MangeToutIsClicked = false, RadishIsClicked = false, ParsleyIsClicked = false, SpinachIsClicked = false, PotatoIsClicked = false, BasilIsClicked = false, SpringOnionIsClicked = false,
            CayennePepperIsClicked = false,
            AsparagusIsClicked = false, AvocadoIsClicked = false, CabbageIsClicked = false, CauliFlowerIsClicked = false, CeleryIsClicked = false, CucumberIsClicked = false,
            CornIsClicked = false, ParsnipIsClicked = false, FennelIsClicked = false, PeasIsClicked = false, PumpkinIsClicked = false, SwedeIsClicked = false, TurnipIsClicked = false;
    int garlicSelected, onionSelected, lettuceSelected, bellPepperSelected, tomatoSelected, broccoliSelected, cherryTomatoSelected, chiliSelected, carrotSelected,
            beetrootSelected, mangetoutSelected, radishSelected, parsleySelected, spinachSelected, potatoSelected, basilSelected, springOnionSelected, cayennePepperSelected,
            asparagusSelected, avocadoSelected, cabbageSelected, cauliflowerSelected, celerySelected, cucumberSelected,
            cornSelected, parsnipSelected, fennelSelected, peasSelected, pumpkinSelected, swedeSelected, turnipSelected;
    String onionDateExpiry, garlicDateExpiry, lettuceDateExpiry, bellPepperDateExpiry, tomatoDateExpiry, broccoliDateExpiry, cherryTomatoDateExpiry, chiliDateExpiry, carrotDateExpiry,
            beetrootDateExpiry, mangetoutDateExpiry, radishDateExpiry, parsleyDateExpiry, spinachDateExpiry, potatoDateExpiry, basilDateExpiry, springOnionDateExpiry, cayennePepperDateExpiry,
            asparagusDateExpiry, avocadoDateExpiry, cabbageDateExpiry, cauliflowerDateExpiry, celeryDateExpiry, cucumberDateExpiry,
            cornDateExpiry, parsnipDateExpiry, fennelDateExpiry, peasDateExpiry, pumpkinDateExpiry, swedeDateExpiry, turnipDateExpiry;
    String[] Ingredient = {"Garlic" , "Onion", "Lettuce", "BellPepper", "Tomato", "Broccoli", "CherryTomato", "Chili", "Carrot", "BeetRoot", "MangeTout", "Radish", "Parsley", "Spinach", "Basil", "Potato", "SpringOnion", "CayennePepper",
            "Asparagus", "Avocado", "Cabbage", "Cauliflower", "Celery", "Cucumber", "Corn", "Parsnip", "Fennel", "Peas", "Pumpkin", "Swede", "Turnip"}; //TODO - Tambah item kat sini
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
        Lettuce = (TextView) findViewById(R.id.vegItemLettuce);
        BellPepper = (TextView) findViewById(R.id.vegItemBellpepper);
        Tomato = (TextView) findViewById(R.id.vegItemTomato);
        Broccoli = (TextView) findViewById(R.id.vegItemBroccoli);
        CherryTomato = (TextView) findViewById(R.id.vegItemCherrytomato);
        Chili = (TextView) findViewById(R.id.vegItemChili);
        Carrot = (TextView) findViewById(R.id.vegItemCarrot);
        BeetRoot = (TextView) findViewById(R.id.vegItemBeetroot);
        MangeTout = (TextView) findViewById(R.id.vegItemMangeTout);
        Radish = (TextView) findViewById(R.id.vegItemRadish);
        Parsley = (TextView) findViewById(R.id.vegItemParsley);
        Spinach = (TextView) findViewById(R.id.vegItemSpinach);
        Basil = (TextView) findViewById(R.id.vegItemBasil);
        Potato = (TextView) findViewById(R.id.vegItemPotato);
        SpringOnion = (TextView) findViewById(R.id.vegItemSpringOnion);
        CayennePepper = (TextView) findViewById(R.id.vegItemCayennePepper);
        Asparagus = (TextView) findViewById(R.id.vegItemAsparagus);
        Avocado = (TextView) findViewById(R.id.vegItemAvocado);
        Cabbage = (TextView) findViewById(R.id.vegItemCabbage);
        CauliFlower = (TextView) findViewById(R.id.vegItemCauliflower);
        Celery = (TextView) findViewById(R.id.vegItemCelery);
        Cucumber = (TextView) findViewById(R.id.vegItemCucumber);
        Corn = (TextView) findViewById(R.id.vegItemCorn);
        Parsnip = (TextView) findViewById(R.id.vegItemParsnip);
        Fennel = (TextView) findViewById(R.id.vegItemFennel);
        Peas = (TextView) findViewById(R.id.vegItemPeas);
        Pumpkin = (TextView) findViewById(R.id.vegItemPumpkin);
        Swede = (TextView) findViewById(R.id.vegItemSwede);
        Turnip = (TextView) findViewById(R.id.vegItemTurnip);

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

        Lettuce.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!LettuceIsClicked){

                addItemCount();
                setClicked(Lettuce);
                LettuceIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Lettuce);
                LettuceIsClicked = false;
            }
        });

        BellPepper.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BellPepperIsClicked){

                addItemCount();
                setClicked(BellPepper);
                BellPepperIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(BellPepper);
                BellPepperIsClicked = false;
            }
        });

        Tomato.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!TomatoIsClicked){

                addItemCount();
                setClicked(Tomato);
                TomatoIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Tomato);
                TomatoIsClicked = false;
            }
        });

        Broccoli.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!BroccoliIsClicked) {

                addItemCount();
                setClicked(Broccoli);
                BroccoliIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Broccoli);
                BroccoliIsClicked = false;
            }
        });

        CherryTomato.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CherryTomatoIsClicked){

                addItemCount();
                setClicked(CherryTomato);
                CherryTomatoIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(CherryTomato);
                CherryTomatoIsClicked = false;
            }
        });

        Chili.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!ChiliIsClicked){

                addItemCount();
                setClicked(Chili);
                ChiliIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Chili);
                ChiliIsClicked = false;
            }
        });

        Carrot.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CarrotIsClicked){

                addItemCount();
                setClicked(Carrot);
                CarrotIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Carrot);
                CarrotIsClicked = false;
            }
        });

        MangeTout.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!MangeToutIsClicked){

                addItemCount();
                setClicked(MangeTout);
                MangeToutIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(MangeTout);
                MangeToutIsClicked = false;
            }
        });

        Radish.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!RadishIsClicked){

                addItemCount();
                setClicked(Radish);
                RadishIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Radish);
                RadishIsClicked = false;
            }
        });

        BeetRoot.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BeetRootIsClicked){

                addItemCount();
                setClicked(BeetRoot);
                BeetRootIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(BeetRoot);
                BeetRootIsClicked = false;
            }
        });

        Parsley.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!ParsleyIsClicked){

                addItemCount();
                setClicked(Parsley);
                ParsleyIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Parsley);
                ParsleyIsClicked = false;
            }
        });

        Spinach.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!SpinachIsClicked){

                addItemCount();
                setClicked(Spinach);
                SpinachIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Spinach);
                SpinachIsClicked = false;
            }
        });

        Potato.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PotatoIsClicked){

                addItemCount();
                setClicked(Potato);
                PotatoIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Potato);
                PotatoIsClicked = false;
            }
        });

        Basil.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BasilIsClicked){

                addItemCount();
                setClicked(Basil);
                BasilIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Basil);
                BasilIsClicked = false;
            }
        });

        SpringOnion.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!SpringOnionIsClicked){

                addItemCount();
                setClicked(SpringOnion);
                SpringOnionIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(SpringOnion);
                SpringOnionIsClicked = false;
            }
        });

        CayennePepper.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CayennePepperIsClicked){

                addItemCount();
                setClicked(CayennePepper);
                CayennePepperIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(CayennePepper);
                CayennePepperIsClicked = false;
            }
        });

        Asparagus.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!AsparagusIsClicked) {

                addItemCount();
                setClicked(Asparagus);
                AsparagusIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Asparagus);
                AsparagusIsClicked = false;
            }
        });

        Avocado.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!AvocadoIsClicked){

                addItemCount();
                setClicked(Avocado);
                AvocadoIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Avocado);
                AvocadoIsClicked = false;
            }
        });

        Cabbage.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CabbageIsClicked){

                addItemCount();
                setClicked(Cabbage);
                CabbageIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cabbage);
                CabbageIsClicked = false;
            }
        });

        CauliFlower.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CauliFlowerIsClicked){

                addItemCount();
                setClicked(CauliFlower);
                CauliFlowerIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(CauliFlower);
                CauliFlowerIsClicked = false;
            }
        });

        Celery.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CeleryIsClicked){

                addItemCount();
                setClicked(Celery);
                CeleryIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Celery);
                CeleryIsClicked = false;
            }
        });

        Cucumber.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CucumberIsClicked){

                addItemCount();
                setClicked(Cucumber);
                CucumberIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cucumber);
                CucumberIsClicked = false;
            }
        });

        Corn.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CornIsClicked){

                addItemCount();
                setClicked(Corn);
                CornIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Corn);
                CornIsClicked = false;
            }
        });

        Parsnip.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!ParsnipIsClicked){

                addItemCount();
                setClicked(Parsnip);
                ParsnipIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Parsnip);
                ParsnipIsClicked = false;
            }
        });

        Fennel.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!FennelIsClicked){

                addItemCount();
                setClicked(Fennel);
                FennelIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Fennel);
                FennelIsClicked = false;
            }
        });

        Peas.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PeasIsClicked){

                addItemCount();
                setClicked(Peas);
                PeasIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Peas);
                PeasIsClicked = false;
            }
        });

        Pumpkin.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PumpkinIsClicked){

                addItemCount();
                setClicked(Pumpkin);
                PumpkinIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Pumpkin);
                PumpkinIsClicked = false;
            }
        });

        Swede.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!SwedeIsClicked){

                addItemCount();
                setClicked(Swede);
                SwedeIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Swede);
                SwedeIsClicked = false;
            }
        });

        Turnip.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!TurnipIsClicked){

                addItemCount();
                setClicked(Turnip);
                TurnipIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Turnip);
                TurnipIsClicked = false;
            }
        });

        //listener for adding item into db
        addBtn.setOnClickListener(v ->{
            setExpiry();
            checkItem();

            for (int x=0; x<Ingredient.length; x++) {

                int getItemValue = 0;
                String getItemExpiry = "";

                if (Objects.equals(Ingredient[x], "Garlic") && garlicSelected == 1) {
                    getItemValue = garlicSelected;
                    getItemExpiry = garlicDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                } else if (Objects.equals(Ingredient[x], "Onion") && onionSelected == 1) {
                    getItemValue = onionSelected;
                    getItemExpiry = onionDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Lettuce") && lettuceSelected == 1) {
                    getItemValue = lettuceSelected;
                    getItemExpiry = lettuceDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                } else if (Objects.equals(Ingredient[x], "Tomato") && tomatoSelected == 1) {
                    getItemValue = tomatoSelected;
                    getItemExpiry = tomatoDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Broccoli") && broccoliSelected == 1) {
                    getItemValue = broccoliSelected;
                    getItemExpiry = broccoliDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "CherryTomato") && cherryTomatoSelected == 1) {
                    getItemValue = cherryTomatoSelected;
                    getItemExpiry = cherryTomatoDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Carrot") && carrotSelected == 1) {
                    getItemValue = carrotSelected;
                    getItemExpiry = carrotDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "BeetRoot") && beetrootSelected == 1) {
                    getItemValue = beetrootSelected;
                    getItemExpiry = beetrootDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "MangeTout") && mangetoutSelected == 1) {
                    getItemValue = mangetoutSelected;
                    getItemExpiry = mangetoutDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Radish") && radishSelected == 1) {
                    getItemValue = radishSelected;
                    getItemExpiry = radishDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Parsley") && parsleySelected == 1) {
                    getItemValue = parsleySelected;
                    getItemExpiry = parsleyDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Spinach") && spinachSelected == 1) {
                    getItemValue = spinachSelected;
                    getItemExpiry = spinachDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Basil") && basilSelected == 1) {
                    getItemValue = basilSelected;
                    getItemExpiry = basilDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Potato") && potatoSelected == 1) {
                    getItemValue = potatoSelected;
                    getItemExpiry = potatoDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "SpringOnion") && springOnionSelected == 1) {
                    getItemValue = springOnionSelected;
                    getItemExpiry = springOnionDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "CayennePepper") && cayennePepperSelected == 1) {
                    getItemValue = cayennePepperSelected;
                    getItemExpiry = cayennePepperDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Asparagus") && asparagusSelected == 1) {
                    getItemValue = asparagusSelected;
                    getItemExpiry = asparagusDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Avocado") && avocadoSelected == 1) {
                    getItemValue = avocadoSelected;
                    getItemExpiry = avocadoDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Cabbage") && cabbageSelected == 1) {
                    getItemValue = cabbageSelected;
                    getItemExpiry = cabbageDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Cauliflower") && cauliflowerSelected == 1) {
                    getItemValue = cauliflowerSelected;
                    getItemExpiry = cauliflowerDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Celery") && celerySelected == 1) {
                    getItemValue = celerySelected;
                    getItemExpiry = celeryDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Cucumber") && cucumberSelected == 1) {
                    getItemValue = cucumberSelected;
                    getItemExpiry = cucumberDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Corn") && cornSelected == 1) {
                    getItemValue = cornSelected;
                    getItemExpiry = cornDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Parsnip") && parsnipSelected == 1) {
                    getItemValue = parsnipSelected;
                    getItemExpiry = parsnipDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Peas") && peasSelected == 1) {
                    getItemValue = peasSelected;
                    getItemExpiry = peasDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Pumpkin") && pumpkinSelected == 1) {
                    getItemValue = pumpkinSelected;
                    getItemExpiry = pumpkinDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Swede") && swedeSelected == 1) {
                    getItemValue = swedeSelected;
                    getItemExpiry = swedeDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Turnip") && turnipSelected == 1) {
                    getItemValue = turnipSelected;
                    getItemExpiry = turnipDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Fennel") && fennelSelected == 1) {
                    getItemValue = fennelSelected;
                    getItemExpiry = fennelDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }

                Toast.makeText(this, "Item has been recorded", Toast.LENGTH_SHORT).show();
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

        if (LettuceIsClicked) {
            lettuceSelected = 1;
        } else {
            lettuceSelected = 0;
        }

        if (BellPepperIsClicked) {
            bellPepperSelected = 1;
        } else {
            bellPepperSelected = 0;
        }

        if (TomatoIsClicked) {
            tomatoSelected = 1;
        } else {
            tomatoSelected = 0;
        }

        if (BellPepperIsClicked) {
            broccoliSelected = 1;
        } else {
            broccoliSelected = 0;
        }

        if (CherryTomatoIsClicked) {
            cherryTomatoSelected = 1;
        } else {
            cherryTomatoSelected = 0;
        }

        if (ChiliIsClicked) {
            chiliSelected = 1;
        } else {
            chiliSelected = 0;
        }

        if (CarrotIsClicked) {
            carrotSelected = 1;
        } else {
            carrotSelected = 0;
        }

        if (BeetRootIsClicked) {
            beetrootSelected = 1;
        } else {
            beetrootSelected = 0;
        }

        if (MangeToutIsClicked) {
            mangetoutSelected = 1;
        } else {
            mangetoutSelected = 0;
        }

        if (RadishIsClicked) {
            radishSelected = 1;
        } else {
            radishSelected = 0;
        }

        if (ParsleyIsClicked) {
            parsleySelected = 1;
        } else {
            parsleySelected = 0;
        }

        if (SpinachIsClicked) {
            spinachSelected = 1;
        } else {
            spinachSelected = 0;
        }

        if (BasilIsClicked) {
            basilSelected = 1;
        } else {
            basilSelected = 0;
        }

        if (PotatoIsClicked) {
            potatoSelected = 1;
        } else {
            potatoSelected = 0;
        }

        if (SpringOnionIsClicked) {
            springOnionSelected = 1;
        } else {
            springOnionSelected = 0;
        }

        if (CayennePepperIsClicked) {
            cayennePepperSelected = 1;
        } else {
            cayennePepperSelected = 0;
        }

        if (AsparagusIsClicked) {
            asparagusSelected = 1;
        } else {
            asparagusSelected = 0;
        }

        if (AvocadoIsClicked) {
            avocadoSelected = 1;
        } else {
            avocadoSelected = 0;
        }

        if (CabbageIsClicked) {
            cabbageSelected = 1;
        } else {
            carrotSelected = 0;
        }

        if (CauliFlowerIsClicked) {
            cauliflowerSelected = 1;
        } else {
            cauliflowerSelected = 0;
        }

        if (CeleryIsClicked) {
            celerySelected = 1;
        } else {
            celerySelected = 0;
        }

        if (CucumberIsClicked) {
            cucumberSelected = 1;
        } else {
            cucumberSelected = 0;
        }

        if (CornIsClicked) {
            cornSelected = 1;
        } else {
            cornSelected = 0;
        }


        if (ParsnipIsClicked) {
            parsnipSelected = 1;
        } else {
            parsnipSelected = 0;
        }

        if (FennelIsClicked) {
            fennelSelected= 1;
        } else {
            fennelSelected = 0;
        }

        if (PeasIsClicked) {
            peasSelected = 1;
        } else {
            peasSelected = 0;
        }

        if (PumpkinIsClicked) {
            pumpkinSelected = 1;
        } else {
            pumpkinSelected = 0;
        }

        if (SwedeIsClicked) {
            swedeSelected = 1;
        } else {
            swedeSelected = 0;
        }

        if (TurnipIsClicked) {
            turnipSelected = 1;
        } else {
            turnipSelected = 0;
        }

        //TODO - Tambah item baru kat sini
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places

        int onionExpiry = 90;
        int garlicExpiry = 90;
        int lettuceExpiry = 20;
        int bellPepperExpiry = 5;
        int tomatoExpiry = 10;
        int broccoliExpiry = 10;
        int cherryTomatoExpiry = 10;
        int chiliExpiry = 5;
        int carrotExpiry = 30;
        int beetrootExpiry = 5;
        int mangetoutExpiry = 5;
        int radishExpiry = 15;
        int parsleyExpiry = 10;
        int spinachExpiry = 5;
        int basilExpiry = 10;
        int potatoExpiry = 30;
        int springOnionExpiry = 15;
        int cayennePepperExpiry = 5;
        int asparagusExpiry = 10;
        int avocadoExpiry = 10;
        int cabbageExpiry = 5;
        int cauliFlowerExpiry = 30;
        int celeryExpiry = 5;
        int cucumberExpiry = 5;
        int cornExpiry = 15;
        int parsnipExpiry = 10;
        int fennelExpiry = 5;
        int peasExpiry = 10;
        int pumpkinExpiry = 30;
        int swedeExpiry = 15;
        int turnipExpiry = 5;

        //TODO - Tambah item baru kat sini

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime onionDate = LocalDateTime.now().plusDays(onionExpiry);
        LocalDateTime GarlicDate = LocalDateTime.now().plusDays(garlicExpiry);
        LocalDateTime LettuceDate = LocalDateTime.now().plusDays(lettuceExpiry);
        LocalDateTime BellPepperDate = LocalDateTime.now().plusDays(bellPepperExpiry);
        LocalDateTime tomatoDate = LocalDateTime.now().plusDays(tomatoExpiry);
        LocalDateTime broccoliDate = LocalDateTime.now().plusDays(broccoliExpiry);
        LocalDateTime cherryTomatoDate = LocalDateTime.now().plusDays(cherryTomatoExpiry);
        LocalDateTime chiliDate = LocalDateTime.now().plusDays(chiliExpiry);
        LocalDateTime carrotDate = LocalDateTime.now().plusDays(carrotExpiry);
        LocalDateTime beetrootDate = LocalDateTime.now().plusDays(beetrootExpiry);
        LocalDateTime mangetoutDate = LocalDateTime.now().plusDays(mangetoutExpiry);
        LocalDateTime radishDate = LocalDateTime.now().plusDays(radishExpiry);
        LocalDateTime parsleyDate = LocalDateTime.now().plusDays(parsleyExpiry);
        LocalDateTime spinachDate = LocalDateTime.now().plusDays(spinachExpiry);
        LocalDateTime basilDate = LocalDateTime.now().plusDays(basilExpiry);
        LocalDateTime potatoDate = LocalDateTime.now().plusDays(potatoExpiry);
        LocalDateTime springOnionDate = LocalDateTime.now().plusDays(springOnionExpiry);
        LocalDateTime cayennePepperDate = LocalDateTime.now().plusDays(cayennePepperExpiry);
        LocalDateTime asparagusDate = LocalDateTime.now().plusDays(asparagusExpiry);
        LocalDateTime avocadoDate = LocalDateTime.now().plusDays(avocadoExpiry);
        LocalDateTime cabbageDate = LocalDateTime.now().plusDays(cabbageExpiry);
        LocalDateTime cauliFlowerDate = LocalDateTime.now().plusDays(cauliFlowerExpiry);
        LocalDateTime celeryDate = LocalDateTime.now().plusDays(celeryExpiry);
        LocalDateTime cucumberDate = LocalDateTime.now().plusDays(cucumberExpiry);
        LocalDateTime cornDate = LocalDateTime.now().plusDays(cornExpiry);
        LocalDateTime parsnipDate = LocalDateTime.now().plusDays(parsnipExpiry);
        LocalDateTime fennelDate = LocalDateTime.now().plusDays(fennelExpiry);
        LocalDateTime peasDate = LocalDateTime.now().plusDays(peasExpiry);
        LocalDateTime pumpkinDate = LocalDateTime.now().plusDays(pumpkinExpiry);
        LocalDateTime swedeDate = LocalDateTime.now().plusDays(swedeExpiry);
        LocalDateTime turnipDate = LocalDateTime.now().plusDays(turnipExpiry);

        //set the date as string
        onionDateExpiry = String.valueOf(dtf.format(onionDate));
        garlicDateExpiry = String.valueOf(dtf.format(GarlicDate));
        lettuceDateExpiry = String.valueOf(dtf.format(LettuceDate));
        bellPepperDateExpiry = String.valueOf(dtf.format(BellPepperDate));
        tomatoDateExpiry = String.valueOf(dtf.format(tomatoDate));
        broccoliDateExpiry = String.valueOf(dtf.format(broccoliDate));
        cherryTomatoDateExpiry = String.valueOf(dtf.format(cherryTomatoDate));
        chiliDateExpiry = String.valueOf(dtf.format(chiliDate));
        carrotDateExpiry = String.valueOf(dtf.format(carrotDate));
        beetrootDateExpiry = String.valueOf(dtf.format(beetrootDate));
        mangetoutDateExpiry = String.valueOf(dtf.format(mangetoutDate));
        radishDateExpiry = String.valueOf(dtf.format(radishDate));
        parsleyDateExpiry = String.valueOf(dtf.format(parsleyDate));
        spinachDateExpiry = String.valueOf(dtf.format(spinachDate));
        basilDateExpiry = String.valueOf(dtf.format(basilDate));
        potatoDateExpiry = String.valueOf(dtf.format(potatoDate));
        springOnionDateExpiry = String.valueOf(dtf.format(springOnionDate));
        cayennePepperDateExpiry = String.valueOf(dtf.format(cayennePepperDate));
        asparagusDateExpiry = String.valueOf(dtf.format(asparagusDate));
        avocadoDateExpiry = String.valueOf(dtf.format(avocadoDate));
        cabbageDateExpiry = String.valueOf(dtf.format(cabbageDate));
        cauliflowerDateExpiry = String.valueOf(dtf.format(cauliFlowerDate));
        celeryDateExpiry = String.valueOf(dtf.format(celeryDate));
        cucumberDateExpiry = String.valueOf(dtf.format(cucumberDate));
        cornDateExpiry = String.valueOf(dtf.format(cornDate));
        parsnipDateExpiry = String.valueOf(dtf.format(parsnipDate));
        fennelDateExpiry = String.valueOf(dtf.format(fennelDate));
        peasDateExpiry = String.valueOf(dtf.format(peasDate));
        pumpkinDateExpiry = String.valueOf(dtf.format(pumpkinDate));
        swedeDateExpiry = String.valueOf(dtf.format(swedeDate));
        turnipDateExpiry = String.valueOf(dtf.format(turnipDate));
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
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

public class ingred_seafood extends AppCompatActivity {

    TextView itemCount , ArticChar, Bream, Clams, Cod, Crab, Crayfish, Hake, Haddock, Halibut, Lobster, Mackerel, Mahi, Monkfish, Octopus, Oyster, Plaice, Pollack, Prawns, Salmon, Sardine, Scallop, Seaweed, Shrimp, Squid, Sturgeon, Tuna, Trout;
    LinearLayout addBtn;
    int item = 0;
    Boolean ArticCharIsClicked = false, BreamIsClicked = false, ClamsIsClicked = false, CodIsClicked = false, CrabIsClicked = false, CrayfishIsClicked = false, HakeIsClicked = false, HaddockIsClicked = false, HalibutIsClicked = false, LobsterIsClicked = false, MackerelIsClicked = false, MahiIsClicked = false, MonkfishIsClicked = false, OctopusIsClicked = false, OysterIsClicked = false, PlaiceIsClicked = false, PollackIsClicked = false, PrawnsIsClicked = false, SalmonIsClicked = false, SardineIsClicked = false, ScallopIsClicked = false, SeaweedIsClicked = false, ShrimpIsClicked = false, SquidIsClicked = false, SturgeonIsClicked = false, TunaIsClicked = false, TroutIsClicked = false;
    int ArticCharSelected, BreamSelected, ClamsSelected, CodSelected, CrabSelected, CrayfishSelected, HakeSelected, HaddockSelected, HalibutSelected, LobsterSelected, MackerelSelected, MahiSelected, MonkfishSelected, OctopusSelected, OysterSelected, PlaiceSelected, PollackSelected, PrawnsSelected, SalmonSelected, SardineSelected, ScallopSelected, SeaweedSelected, ShrimpSelected, SquidSelected, SturgeonSelected, TunaSelected, TroutSelected;
    String ArticCharDateExpiry, BreamDateExpiry, ClamsDateExpiry, CodDateExpiry, CrabDateExpiry, CrayfishDateExpiry, HakeDateExpiry, HaddockDateExpiry, HalibutDateExpiry, LobsterDateExpiry, MackerelDateExpiry, MahiDateExpiry, MonkfishDateExpiry, OctopusDateExpiry, OysterDateExpiry, PlaiceDateExpiry, PollackDateExpiry, PrawnsDateExpiry, SalmonDateExpiry, SardineDateExpiry, ScallopDateExpiry, SeaweedDateExpiry, ShrimpDateExpiry, SquidDateExpiry, SturgeonDateExpiry, TunaDateExpiry, TroutDateExpiry;
    String[] Ingredient = {"Artic Char", "Bream", "Clams", "Cod", "Crab", "Crayfish", "Hake", "Haddock", "Halibut", "Lobster", "Mackerel", "Mahi", "Monkfish", "Octopus", "Oyster", "Plaice", "Pollack", "Prawns", "Salmon", "Sardine", "Scallop", "Seaweed", "Shrimp", "Squid", "Sturgeon", "Tuna", "Trout"}; //TODO - Tambah item kat sini

    DatabaseReference dataRef;
    String currentUserID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_seafood);

        itemCount = (TextView) findViewById(R.id.itemCount);
        ArticChar = (TextView) findViewById(R.id.fishItemArticChar);
        Bream = (TextView) findViewById(R.id.fishItemBream);
        Clams = (TextView) findViewById(R.id.fishItemClams);
        Cod = (TextView) findViewById(R.id.fishItemCod);
        Crab = (TextView) findViewById(R.id.fishItemCrab);
        Crayfish = (TextView) findViewById(R.id.fishItemCrayfish);
        Hake = (TextView) findViewById(R.id.fishItemHake);
        Haddock = (TextView) findViewById(R.id.fishItemHaddock);
        Halibut = (TextView) findViewById(R.id.fishItemHalibut);
        Lobster = (TextView) findViewById(R.id.fishItemLobster);
        Mackerel = (TextView) findViewById(R.id.fishItemMackerel);
        Mahi = (TextView) findViewById(R.id.fishItemMahiMahi);
        Monkfish = (TextView) findViewById(R.id.fishItemMonkfish);
        Octopus = (TextView) findViewById(R.id.fishItemOctopus);
        Oyster = (TextView) findViewById(R.id.fishItemOyster);
        Plaice = (TextView) findViewById(R.id.fishItemPlaice);
        Pollack = (TextView) findViewById(R.id.fishItemPollack);
        Prawns = (TextView) findViewById(R.id.fishItemPrawns);
        Salmon = (TextView) findViewById(R.id.fishItemSalmon);
        Sardine = (TextView) findViewById(R.id.fishItemSardine);
        Scallop = (TextView) findViewById(R.id.fishItemScallop);
        Seaweed = (TextView) findViewById(R.id.fishItemSeaweed);
        Shrimp = (TextView) findViewById(R.id.fishItemShrimp);
        Squid = (TextView) findViewById(R.id.fishItemSquid);
        Sturgeon = (TextView) findViewById(R.id.fishItemSturgeon);
        Tuna = (TextView) findViewById(R.id.fishItemTuna);
        Trout = (TextView) findViewById(R.id.fishItemTrout);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        dataRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);


        ArticChar.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!ArticCharIsClicked){

                addItemCount();
                setClicked(ArticChar);
                ArticCharIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(ArticChar);
                ArticCharIsClicked = false;
            }
        });

        Bream.setOnClickListener(view ->{

            if (!BreamIsClicked) {

                addItemCount();
                setClicked(Bream);
                BreamIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Bream);
                BreamIsClicked = false;
            }
        });

        Clams.setOnClickListener(view -> {
            if (!ClamsIsClicked){

                addItemCount();
                setClicked(Clams);
                ClamsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Clams);
                ClamsIsClicked = false;
            }
        });

        Cod.setOnClickListener(view ->{

            if (!CodIsClicked) {

                addItemCount();
                setClicked(Cod);
                CodIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cod);
                CodIsClicked = false;
            }
        });

        Crab.setOnClickListener(view -> {
            if (!CrabIsClicked){

                addItemCount();
                setClicked(Crab);
                CrabIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Crab);
                CrabIsClicked = false;
            }
        });

        Crayfish.setOnClickListener(view ->{

            if (!CrayfishIsClicked) {

                addItemCount();
                setClicked(Crayfish);
                CrayfishIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Crayfish);
                CrayfishIsClicked = false;
            }
        });

        Hake.setOnClickListener(view -> {
            if (!HakeIsClicked){

                addItemCount();
                setClicked(Hake);
                HakeIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Hake);
                HakeIsClicked = false;
            }
        });

        Haddock.setOnClickListener(view ->{

            if (!HaddockIsClicked) {

                addItemCount();
                setClicked(Haddock);
                HaddockIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Haddock);
                HaddockIsClicked = false;
            }
        });

        Halibut.setOnClickListener(view -> {
            if (!HalibutIsClicked){

                addItemCount();
                setClicked(Halibut);
                HalibutIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Halibut);
                HalibutIsClicked = false;
            }
        });

        Lobster.setOnClickListener(view ->{

            if (!LobsterIsClicked) {

                addItemCount();
                setClicked(Lobster);
                LobsterIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Lobster);
                LobsterIsClicked = false;
            }
        });

        Mackerel.setOnClickListener(view -> {
            if (!MackerelIsClicked){

                addItemCount();
                setClicked(Mackerel);
                MackerelIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Mackerel);
                MackerelIsClicked = false;
            }
        });

        Mahi.setOnClickListener(view ->{

            if (!MahiIsClicked) {

                addItemCount();
                setClicked(Mahi);
                MahiIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Mahi);
                MahiIsClicked = false;
            }
        });

        Monkfish.setOnClickListener(view -> {
            if (!MonkfishIsClicked){

                addItemCount();
                setClicked(Monkfish);
                MonkfishIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Monkfish);
                MonkfishIsClicked = false;
            }
        });

        Octopus.setOnClickListener(view ->{

            if (!OctopusIsClicked) {

                addItemCount();
                setClicked(Octopus);
                OctopusIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Octopus);
                OctopusIsClicked = false;
            }
        });

        Oyster.setOnClickListener(view -> {
            if (!OysterIsClicked){

                addItemCount();
                setClicked(Oyster);
                OysterIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Oyster);
                OysterIsClicked = false;
            }
        });

        Plaice.setOnClickListener(view ->{

            if (!PlaiceIsClicked) {

                addItemCount();
                setClicked(Plaice);
                PlaiceIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Plaice);
                PlaiceIsClicked = false;
            }
        });

        Pollack.setOnClickListener(view -> {
            if (!PollackIsClicked){

                addItemCount();
                setClicked(Pollack);
                PollackIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Pollack);
                PollackIsClicked = false;
            }
        });

        Prawns.setOnClickListener(view ->{

            if (!PrawnsIsClicked) {

                addItemCount();
                setClicked(Prawns);
                PrawnsIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Prawns);
                PrawnsIsClicked = false;
            }
        });

        Salmon.setOnClickListener(view -> {
            if (!SalmonIsClicked){

                addItemCount();
                setClicked(Salmon);
                SalmonIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Salmon);
                SalmonIsClicked = false;
            }
        });

        Sardine.setOnClickListener(view ->{

            if (!SardineIsClicked) {

                addItemCount();
                setClicked(Sardine);
                SardineIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Sardine);
                SardineIsClicked = false;
            }
        });

        Scallop.setOnClickListener(view -> {
            if (!ScallopIsClicked){

                addItemCount();
                setClicked(Scallop);
                ScallopIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Scallop);
                ScallopIsClicked = false;
            }
        });

        Seaweed.setOnClickListener(view ->{

            if (!SeaweedIsClicked) {

                addItemCount();
                setClicked(Seaweed);
                SeaweedIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Seaweed);
                SeaweedIsClicked = false;
            }
        });

        Shrimp.setOnClickListener(view -> {
            if (!ShrimpIsClicked){

                addItemCount();
                setClicked(Shrimp);
                ShrimpIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Shrimp);
                ShrimpIsClicked = false;
            }
        });

        Squid.setOnClickListener(view ->{

            if (!SquidIsClicked) {

                addItemCount();
                setClicked(Squid);
                SquidIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Squid);
                SquidIsClicked = false;
            }
        });

        Sturgeon.setOnClickListener(view ->{

            if (!SturgeonIsClicked) {

                addItemCount();
                setClicked(Sturgeon);
                SturgeonIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Sturgeon);
                SturgeonIsClicked = false;
            }
        });

        Tuna.setOnClickListener(view -> {
            if (!TunaIsClicked){

                addItemCount();
                setClicked(Tuna);
                TunaIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Tuna);
                TunaIsClicked = false;
            }
        });

        Trout.setOnClickListener(view ->{

            if (!TroutIsClicked) {

                addItemCount();
                setClicked(Trout);
                TroutIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Trout);
                TroutIsClicked = false;
            }
        });


        //listener for adding item into db
        addBtn.setOnClickListener(v ->{
            setExpiry();
            checkItem();

            for (int x=0; x<Ingredient.length; x++) {

                int getItemValue = 0;
                String getItemExpiry = "";

                if (Objects.equals(Ingredient[x], "Artic Char") && ArticCharSelected == 1) {
                    getItemValue = ArticCharSelected;
                    getItemExpiry = ArticCharDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                }
                else if (Objects.equals(Ingredient[x], "Bream") && BreamSelected == 1) {
                    getItemValue = BreamSelected;
                    getItemExpiry = BreamDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Clams") && ClamsSelected == 1) {
                    getItemValue = ClamsSelected;
                    getItemExpiry = ClamsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Cod") && CodSelected == 1) {
                    getItemValue = CodSelected;
                    getItemExpiry = CodDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Crab") && CrabSelected == 1) {
                    getItemValue = CrabSelected;
                    getItemExpiry = CrabDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Crayfish") && CrayfishSelected == 1) {
                    getItemValue = CrayfishSelected;
                    getItemExpiry = CrayfishDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Hake") && HakeSelected == 1) {
                    getItemValue = HakeSelected;
                    getItemExpiry = HakeDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Haddock") && HaddockSelected == 1) {
                    getItemValue = HaddockSelected;
                    getItemExpiry = HaddockDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Halibut") && HalibutSelected == 1) {
                    getItemValue = HalibutSelected;
                    getItemExpiry = HalibutDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Lobster") && LobsterSelected == 1) {
                    getItemValue = LobsterSelected;
                    getItemExpiry = LobsterDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Mackerel") && MackerelSelected == 1) {
                    getItemValue = MackerelSelected;
                    getItemExpiry = MackerelDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Mahi") && MahiSelected == 1) {
                    getItemValue = MahiSelected;
                    getItemExpiry = MahiDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Monkfish") && MonkfishSelected == 1) {
                    getItemValue = MonkfishSelected;
                    getItemExpiry = MonkfishDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Octopus") && OctopusSelected == 1) {
                    getItemValue = OctopusSelected;
                    getItemExpiry = OctopusDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Oyster") && OysterSelected == 1) {
                    getItemValue = OysterSelected;
                    getItemExpiry = OysterDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Plaice") &PlaiceSelected == 1) {
                    getItemValue = PlaiceSelected;
                    getItemExpiry = PlaiceDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Pollack") && PollackSelected == 1) {
                    getItemValue = PollackSelected;
                    getItemExpiry = PollackDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Prawns") && PrawnsSelected == 1) {
                    getItemValue = PrawnsSelected;
                    getItemExpiry = PrawnsDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Salmon") && SalmonSelected == 1) {
                    getItemValue = SalmonSelected;
                    getItemExpiry = SalmonDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Sardine") && SardineSelected == 1) {
                    getItemValue = SardineSelected;
                    getItemExpiry = SardineDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Scallop") && ScallopSelected == 1) {
                    getItemValue = ScallopSelected;
                    getItemExpiry = ScallopDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Seaweed") &SeaweedSelected == 1) {
                    getItemValue = SeaweedSelected;
                    getItemExpiry = SeaweedDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Shrimp") && ShrimpSelected == 1) {
                    getItemValue = ShrimpSelected;
                    getItemExpiry = ShrimpDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Squid") && SquidSelected == 1) {
                    getItemValue = SquidSelected;
                    getItemExpiry = SquidDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Sturgeon") && SturgeonSelected == 1) {
                    getItemValue = SturgeonSelected;
                    getItemExpiry = SturgeonDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Tuna") &&TunaSelected == 1) {
                    getItemValue = TunaSelected;
                    getItemExpiry = TunaDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Trout") && TroutSelected == 1) {
                    getItemValue = TroutSelected;
                    getItemExpiry = TroutDateExpiry;
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
        if (ArticCharIsClicked) {
            ArticCharSelected = 1; // Button selected
        } else {
            ArticCharSelected = 0; //button not selected
        }

        if (BreamIsClicked) {
            BreamSelected = 1;
        } else {
            BreamSelected = 0;
        }
        if (ClamsIsClicked) {
            ClamsSelected = 1;
        } else {
            ClamsSelected = 0;
        }
        if (CodIsClicked) {
            CodSelected = 1;
        } else {
            CodSelected = 0;
        }
        if (CrabIsClicked) {
            CrabSelected = 1;
        } else {
            CrabSelected = 0;
        }
        if (CrayfishIsClicked) {
            CrayfishSelected = 1;
        } else {
            CrayfishSelected = 0;
        }
        if (HakeIsClicked) {
            HakeSelected = 1;
        } else {
            HakeSelected = 0;
        }
        if (HaddockIsClicked) {
            HaddockSelected = 1;
        } else {
            HaddockSelected = 0;
        }
        if (HalibutIsClicked) {
            HalibutSelected = 1;
        } else {
            HalibutSelected = 0;
        }
        if (LobsterIsClicked) {
            LobsterSelected = 1;
        } else {
            LobsterSelected = 0;
        }
        if (MackerelIsClicked) {
            MackerelSelected = 1;
        } else {
            MackerelSelected = 0;
        }
        if (MahiIsClicked) {
            MahiSelected = 1;
        } else {
            MahiSelected = 0;
        }
        if (MonkfishIsClicked) {
            MonkfishSelected = 1;
        } else {
            MonkfishSelected = 0;
        }
        if (OctopusIsClicked) {
            OctopusSelected = 1;
        } else {
            OctopusSelected = 0;
        }
        if (OysterIsClicked) {
            OysterSelected = 1;
        } else {
            OysterSelected = 0;
        }
        if (PlaiceIsClicked) {
            PlaiceSelected = 1;
        } else {
            PlaiceSelected = 0;
        }
        if (PollackIsClicked) {
            PollackSelected = 1;
        } else {
            PollackSelected = 0;
        }
        if (PrawnsIsClicked) {
            PrawnsSelected = 1;
        } else {
            PrawnsSelected = 0;
        }
        if (SalmonIsClicked) {
            SalmonSelected = 1;
        } else {
            SalmonSelected = 0;
        }
        if (SardineIsClicked) {
            SardineSelected = 1;
        } else {
            SardineSelected = 0;
        }
        if (ScallopIsClicked) {
            ScallopSelected = 1;
        } else {
            ScallopSelected = 0;
        }
        if (SeaweedIsClicked) {
            SeaweedSelected = 1;
        } else {
            SeaweedSelected = 0;
        }
        if (ShrimpIsClicked) {
            ShrimpSelected = 1;
        } else {
            ShrimpSelected = 0;
        }
        if (SquidIsClicked) {
            SquidSelected = 1;
        } else {
            SquidSelected = 0;
        }
        if (SturgeonIsClicked) {
            SturgeonSelected = 1;
        } else {
            SturgeonSelected = 0;
        }
        if (TunaIsClicked) {
            TunaSelected = 1;
        } else {
            TunaSelected = 0;
        }
        if (TroutIsClicked) {
            TroutSelected = 1;
        } else {
            TroutSelected = 0;
        }
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places
        int ArticCharExpiry = 90;
        int BreamExpiry = 90;
        int ClamsExpiry = 90;
        int CodExpiry = 90;
        int CrabExpiry = 90;
        int CrayfishExpiry = 90;
        int HakeExpiry = 90;
        int HaddockExpiry = 90;
        int HalibutExpiry = 90;
        int MackerelExpiry = 90;
        int LobsterExpiry = 90;
        int MahiExpiry = 90;
        int MonkfishExpiry = 90;
        int OctopusExpiry = 90;
        int OysterExpiry = 90;
        int PlaiceExpiry = 90;
        int PollackExpiry = 90;
        int PrawnsExpiry = 90;
        int SalmonExpiry = 90;
        int SardineExpiry = 90;
        int ScallopExpiry = 90;
        int SeaweedExpiry = 90;
        int ShrimpExpiry = 90;
        int SquidExpiry = 90;
        int SturgeonExpiry = 90;
        int TunaExpiry = 90;
        int TroutExpiry = 90;

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime ArticCharDate = LocalDateTime.now().plusDays(ArticCharExpiry);
        LocalDateTime BreamDate = LocalDateTime.now().plusDays(BreamExpiry);
        LocalDateTime ClamsDate = LocalDateTime.now().plusDays(ClamsExpiry);
        LocalDateTime CodDate = LocalDateTime.now().plusDays(CodExpiry);
        LocalDateTime CrabDate = LocalDateTime.now().plusDays(CrabExpiry);
        LocalDateTime CrayfishDate = LocalDateTime.now().plusDays(CrayfishExpiry);
        LocalDateTime HakeDate = LocalDateTime.now().plusDays(HakeExpiry);
        LocalDateTime HaddockDate = LocalDateTime.now().plusDays(HaddockExpiry);
        LocalDateTime HalibutDate = LocalDateTime.now().plusDays(HalibutExpiry);
        LocalDateTime LobsterDate = LocalDateTime.now().plusDays(LobsterExpiry);
        LocalDateTime MackerelDate = LocalDateTime.now().plusDays(MackerelExpiry);
        LocalDateTime MahiDate = LocalDateTime.now().plusDays(MahiExpiry);
        LocalDateTime MonkfishDate = LocalDateTime.now().plusDays(MonkfishExpiry);
        LocalDateTime OctopusDate = LocalDateTime.now().plusDays(OctopusExpiry);
        LocalDateTime OysterDate = LocalDateTime.now().plusDays(OysterExpiry);
        LocalDateTime PlaiceDate = LocalDateTime.now().plusDays(PlaiceExpiry);
        LocalDateTime PollackDate = LocalDateTime.now().plusDays(PollackExpiry);
        LocalDateTime SalmonDate = LocalDateTime.now().plusDays(SalmonExpiry);
        LocalDateTime SardineDate = LocalDateTime.now().plusDays(SardineExpiry);
        LocalDateTime PrawnsDate = LocalDateTime.now().plusDays(PrawnsExpiry);
        LocalDateTime ScallopDate = LocalDateTime.now().plusDays(ScallopExpiry);
        LocalDateTime SeaweedDate = LocalDateTime.now().plusDays(SeaweedExpiry);
        LocalDateTime ShrimpDate = LocalDateTime.now().plusDays(ShrimpExpiry);
        LocalDateTime SquidDate = LocalDateTime.now().plusDays(SquidExpiry);
        LocalDateTime SturgeonDate = LocalDateTime.now().plusDays(SturgeonExpiry);
        LocalDateTime TunaDate = LocalDateTime.now().plusDays(TunaExpiry);
        LocalDateTime TroutDate = LocalDateTime.now().plusDays(TroutExpiry);

        //set the date as string
        //, , , , , , , , , , , , , , , , , , , , , , , , , ,
        ArticCharDateExpiry = String.valueOf(dtf.format(ArticCharDate));
        BreamDateExpiry = String.valueOf(dtf.format(BreamDate));
        ClamsDateExpiry = String.valueOf(dtf.format(ClamsDate));
        CodDateExpiry = String.valueOf(dtf.format(CodDate));
        CrabDateExpiry = String.valueOf(dtf.format(CrabDate));
        CrayfishDateExpiry = String.valueOf(dtf.format(CrayfishDate));
        HakeDateExpiry = String.valueOf(dtf.format(HakeDate));
        HaddockDateExpiry = String.valueOf(dtf.format(HaddockDate));
        HalibutDateExpiry = String.valueOf(dtf.format(HalibutDate));
        LobsterDateExpiry = String.valueOf(dtf.format(LobsterDate));
        MackerelDateExpiry = String.valueOf(dtf.format(MackerelDate));
        MahiDateExpiry = String.valueOf(dtf.format(MahiDate));
        MonkfishDateExpiry = String.valueOf(dtf.format(MonkfishDate));
        OctopusDateExpiry = String.valueOf(dtf.format(OctopusDate));
        OysterDateExpiry = String.valueOf(dtf.format(OysterDate));
        PlaiceDateExpiry = String.valueOf(dtf.format(PlaiceDate));
        PollackDateExpiry = String.valueOf(dtf.format(PollackDate));
        PrawnsDateExpiry = String.valueOf(dtf.format(PrawnsDate));
        SalmonDateExpiry = String.valueOf(dtf.format(SalmonDate));
        SardineDateExpiry = String.valueOf(dtf.format(SardineDate));
        ScallopDateExpiry = String.valueOf(dtf.format(ScallopDate));
        SeaweedDateExpiry = String.valueOf(dtf.format(SeaweedDate));
        ShrimpDateExpiry = String.valueOf(dtf.format(ShrimpDate));
        SquidDateExpiry = String.valueOf(dtf.format(SquidDate));
        SturgeonDateExpiry = String.valueOf(dtf.format(SturgeonDate));
        TunaDateExpiry = String.valueOf(dtf.format(TunaDate));
        TroutDateExpiry = String.valueOf(dtf.format(TroutDate));

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
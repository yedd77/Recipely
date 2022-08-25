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

public class ingred_mushroom extends AppCompatActivity {

    TextView itemCount , Shiitake, Cremini, Button, Brown, Dried, Oyster, Porcini, MixedMushroom, BabyBella, White, Canned, PortobelloCap, Chestnut;
    LinearLayout addBtn;
    int item = 0;
    Boolean ShiitakeIsClicked = false, CreminiIsClicked = false,ButtonIsClicked = false, BrownIsClicked = false, DriedIsClicked = false, OysterIsClicked = false, PorciniIsClicked = false, MixedMushroomIsClicked = false, BabyBellaIsClicked = false, WhiteIsClicked = false, CannedIsClicked = false, PortobelloCapIsClicked = false, ChestnutIsClicked = false;
    int ShiitakeSelected, CreminiSelected, ButtonSelected, BrownSelected, DriedSelected, OysterSelected, PorciniSelected, MixedMushroomSelected, BabyBellaSelected, WhiteSelected, CannedSelected, PortobelloCapSelected, ChestnutSelected;
    String ShiitakeDateExpiry, CreminiDateExpiry, ButtonDateExpiry, BrownDateExpiry, DriedDateExpiry, OysterDateExpiry, PorciniDateExpiry, MixedMushroomDateExpiry, BabyBellaDateExpiry, WhiteDateExpiry, CannedDateExpiry, PortobelloCapDateExpiry, ChestnutDateExpiry;
    String[] Ingredient = {"Shiitake Mushroom", "Cremini Mushroom", "Button Mushroom", "Brown Mushroom", "Dried Mushroom", "Oyster Mushroom", "Porcini Mushroom", "Mixed Mushroom", "Baby Bella Mushroom", "White Mushroom", "Canned Mushroom", "Portobello Cap Mushroom", "Chestnut Mushroom"}; //TODO - Tambah item kat sini

    DatabaseReference dataRef;
    String currentUserID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_mushroom);

        itemCount = (TextView) findViewById(R.id.itemCount);
        Shiitake = (TextView) findViewById(R.id.mushroomItemShiitake);
        Cremini = (TextView) findViewById(R.id.mushroomItemCremini);
        Button = (TextView) findViewById(R.id.mushroomItemButton);
        Brown = (TextView) findViewById(R.id.mushroomItemBrown);
        Dried = (TextView) findViewById(R.id.mushroomItemDriedMushroom);
        Oyster = (TextView) findViewById(R.id.mushroomItemOysterMushroom);
        Porcini = (TextView) findViewById(R.id.mushroomItemPorcini);
        MixedMushroom = (TextView) findViewById(R.id.mushroomItemMixedMushroom);
        BabyBella = (TextView) findViewById(R.id.mushroomItemBabyBella);
        White = (TextView) findViewById(R.id.mushroomItemWhite);
        Canned = (TextView) findViewById(R.id.mushroomItemCannedMushroom);
        PortobelloCap = (TextView) findViewById(R.id.mushroomItemPortabelloCap);
        Chestnut = (TextView) findViewById(R.id.mushroomItemChestnut);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        dataRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);

        Shiitake.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!ShiitakeIsClicked){

                addItemCount();
                setClicked(Shiitake);
                ShiitakeIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Shiitake);
                ShiitakeIsClicked = false;
            }
        });

        Cremini.setOnClickListener(view ->{

            if (!CreminiIsClicked) {

                addItemCount();
                setClicked(Cremini);
                CreminiIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cremini);
                CreminiIsClicked = false;
            }
        });

        Button.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!ButtonIsClicked){

                addItemCount();
                setClicked(Button);
                ButtonIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Button);
                ButtonIsClicked = false;
            }
        });

        Brown.setOnClickListener(view ->{

            if (!BrownIsClicked) {

                addItemCount();
                setClicked(Brown);
                BrownIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Brown);
                BrownIsClicked = false;
            }
        });

        Dried.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!DriedIsClicked){

                addItemCount();
                setClicked(Dried);
                DriedIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Dried);
                DriedIsClicked = false;
            }
        });

        Oyster.setOnClickListener(view ->{

            if (!OysterIsClicked) {

                addItemCount();
                setClicked(Oyster);
                OysterIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Oyster);
                OysterIsClicked = false;
            }
        });

        Porcini.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!PorciniIsClicked){

                addItemCount();
                setClicked(Porcini);
                PorciniIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Porcini);
                PorciniIsClicked = false;
            }
        });

        MixedMushroom.setOnClickListener(view ->{

            if (!MixedMushroomIsClicked) {

                addItemCount();
                setClicked(MixedMushroom);
                MixedMushroomIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(MixedMushroom);
                MixedMushroomIsClicked = false;
            }
        });

        BabyBella.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!BabyBellaIsClicked){

                addItemCount();
                setClicked(BabyBella);
                BabyBellaIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(BabyBella);
                BabyBellaIsClicked = false;
            }
        });

        White.setOnClickListener(view ->{

            if (!WhiteIsClicked) {

                addItemCount();
                setClicked(White);
                WhiteIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(White);
                WhiteIsClicked = false;
            }
        });

        Canned.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!CannedIsClicked){

                addItemCount();
                setClicked(Canned);
                CannedIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Canned);
                CannedIsClicked = false;
            }
        });

        PortobelloCap.setOnClickListener(view ->{

            if (!PortobelloCapIsClicked) {

                addItemCount();
                setClicked(PortobelloCap);
                PortobelloCapIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(PortobelloCap);
                PortobelloCapIsClicked = false;
            }
        });

        Chestnut.setOnClickListener(view -> {
            //check if is not clicked, item counter will increase by 1,
            // and the boolean will show the item has been clicked
            // button properties will show the item has been clicked and changed color to orange
            if (!ChestnutIsClicked){

                addItemCount();
                setClicked(Chestnut);
                ChestnutIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Chestnut);
                ChestnutIsClicked = false;
            }
        });

        //listener for adding item into db
        addBtn.setOnClickListener(v ->{
            setExpiry();
            checkItem();

            for (int x=0; x<Ingredient.length; x++) {

                int getItemValue = 0;
                String getItemExpiry = "";

                if (Objects.equals(Ingredient[x], "Shiitake") && ShiitakeSelected == 1) {
                    getItemValue = ShiitakeSelected;
                    getItemExpiry = ShiitakeDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                }
                else if (Objects.equals(Ingredient[x], "Cremini") && CreminiSelected == 1) {
                    getItemValue = CreminiSelected;
                    getItemExpiry = CreminiDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Button") && ButtonSelected == 1) {
                    getItemValue = ButtonSelected;
                    getItemExpiry = ButtonDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Brown") && BrownSelected == 1) {
                    getItemValue = BrownSelected;
                    getItemExpiry = BrownDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Dried") && DriedSelected == 1) {
                    getItemValue = DriedSelected;
                    getItemExpiry = DriedDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Oyster") && OysterSelected == 1) {
                    getItemValue = OysterSelected;
                    getItemExpiry = OysterDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Porcini") && PorciniSelected == 1) {
                    getItemValue = PorciniSelected;
                    getItemExpiry = PorciniDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Mixed Mushroom") && MixedMushroomSelected == 1) {
                    getItemValue = MixedMushroomSelected;
                    getItemExpiry = MixedMushroomDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Baby Bella") && BabyBellaSelected == 1) {
                    getItemValue = BabyBellaSelected;
                    getItemExpiry = BabyBellaDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "White") && WhiteSelected == 1) {
                    getItemValue = WhiteSelected;
                    getItemExpiry = WhiteDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Canned") && CannedSelected == 1) {
                    getItemValue = CannedSelected;
                    getItemExpiry = CannedDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Portobello Cap") && PortobelloCapSelected == 1) {
                    getItemValue = PortobelloCapSelected;
                    getItemExpiry = PortobelloCapDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Chestnut") && ChestnutSelected == 1) {
                    getItemValue = ChestnutSelected;
                    getItemExpiry = ChestnutDateExpiry;
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

        if (ShiitakeIsClicked) {
            ShiitakeSelected = 1; // Button selected
        } else {
            ShiitakeSelected = 0; //button not selected
        }

        if (CreminiIsClicked) {
            CreminiSelected = 1;
        } else {
            CreminiSelected = 0;
        }

        if (ButtonIsClicked) {
            ButtonSelected = 1;
        } else {
            ButtonSelected = 0;
        }

        if (BrownIsClicked) {
            BrownSelected = 1;
        } else {
            BrownSelected = 0;
        }

        if (DriedIsClicked) {
            DriedSelected = 1;
        } else {
            DriedSelected = 0;
        }

        if (OysterIsClicked) {
            OysterSelected = 1;
        } else {
            OysterSelected = 0;
        }

        if (PorciniIsClicked) {
            PorciniSelected = 1;
        } else {
            PorciniSelected = 0;
        }

        if (MixedMushroomIsClicked) {
            MixedMushroomSelected = 1;
        } else {
            MixedMushroomSelected = 0;
        }

        if (BabyBellaIsClicked) {
            BabyBellaSelected = 1;
        } else {
            BabyBellaSelected = 0;
        }

        if (WhiteIsClicked) {
            WhiteSelected = 1;
        } else {
            WhiteSelected = 0;
        }

        if (CannedIsClicked) {
            CannedSelected = 1;
        } else {
            CannedSelected = 0;
        }

        if (PortobelloCapIsClicked) {
            PortobelloCapSelected = 1;
        } else {
            PortobelloCapSelected = 0;
        }

        if (ChestnutIsClicked) {
            ChestnutSelected = 1;
        } else {
            ChestnutSelected = 0;
        }

    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places
        int ShiitakeExpiry = 90;
        int CreminiExpiry = 90;
        int ButtonExpiry = 90;
        int BrownExpiry = 90;
        int DriedExpiry = 90;
        int OysterExpiry = 90;
        int PorciniExpiry = 90;
        int MixedMushroomExpiry = 90;
        int BabyBellaExpiry = 90;
        int WhiteExpiry = 90;
        int CannedExpiry = 90;
        int PortobelloCapExpiry = 90;
        int ChestnutExpiry = 90;

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime ShiitakeDate = LocalDateTime.now().plusDays(ShiitakeExpiry);
        LocalDateTime CreminiDate = LocalDateTime.now().plusDays(CreminiExpiry);
        LocalDateTime ButtonDate = LocalDateTime.now().plusDays(ButtonExpiry);
        LocalDateTime BrownDate = LocalDateTime.now().plusDays(BrownExpiry);
        LocalDateTime DriedDate = LocalDateTime.now().plusDays(DriedExpiry);
        LocalDateTime OysterDate = LocalDateTime.now().plusDays(OysterExpiry);
        LocalDateTime PorciniDate = LocalDateTime.now().plusDays(PorciniExpiry);
        LocalDateTime MixedMushroomDate = LocalDateTime.now().plusDays(MixedMushroomExpiry);
        LocalDateTime BabyBellaDate = LocalDateTime.now().plusDays(BabyBellaExpiry);
        LocalDateTime WhiteDate = LocalDateTime.now().plusDays(WhiteExpiry);
        LocalDateTime CannedDate = LocalDateTime.now().plusDays(CannedExpiry);
        LocalDateTime PortobelloCapDate = LocalDateTime.now().plusDays(PortobelloCapExpiry);
        LocalDateTime ChestnutDate = LocalDateTime.now().plusDays(ChestnutExpiry);

        //set the date as string
        ShiitakeDateExpiry = String.valueOf(dtf.format(ShiitakeDate));
        CreminiDateExpiry = String.valueOf(dtf.format(CreminiDate));
        ButtonDateExpiry = String.valueOf(dtf.format(ButtonDate));
        BrownDateExpiry = String.valueOf(dtf.format(BrownDate));
        DriedDateExpiry = String.valueOf(dtf.format(DriedDate));
        OysterDateExpiry = String.valueOf(dtf.format(OysterDate));
        PorciniDateExpiry = String.valueOf(dtf.format(PorciniDate));
        MixedMushroomDateExpiry = String.valueOf(dtf.format(MixedMushroomDate));
        BabyBellaDateExpiry = String.valueOf(dtf.format(BabyBellaDate));
        WhiteDateExpiry = String.valueOf(dtf.format(WhiteDate));
        CannedDateExpiry = String.valueOf(dtf.format(CannedDate));
        PortobelloCapDateExpiry = String.valueOf(dtf.format(PortobelloCapDate));
        ChestnutDateExpiry = String.valueOf(dtf.format(ChestnutDate));
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
package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

public class ingred_fruit extends AppCompatActivity {

    TextView itemCount , Banana, Lemon, Peach, BlackPlum, Orange, Apricot, DriedFruit, RedPlum, MixBerries, Blueberries, Strawberries, Fig, Cantaloupe, Honeydew, Apple, Pineapple, Avocado, Blackberries, Raspberries, Mango, Blackcurrant, Date, Durian, Grapes, Guava, Kiwi, Lychee, Mangosteen, Papaya, Pear, Watermelon, DragonFruit, Prunes, Tamarind;
    LinearLayout addBtn;
    int item = 0;
    Boolean LemonIsClicked = false, BananaIsClicked = false, PeachIsClicked = false, BlackPlumIsClicked = false, OrangeIsClicked = false, ApricotIsClicked = false, DriedFruitIsClicked = false, RedPlumIsClicked = false, MixBerriesIsClicked = false, BlueberriesIsClicked = false, StrawberriesIsClicked = false, FigIsClicked = false, CantaloupeIsClicked = false, HoneydewIsClicked = false, AppleIsClicked = false, PineappleIsClicked = false, AvocadoIsClicked = false, BlackberriesIsClicked = false, RaspberriesIsClicked = false, MangoIsClicked = false, BlackcurrantIsClicked = false, DateIsClicked = false, DurianIsClicked = false, GrapesIsClicked = false, GuavaIsClicked = false, KiwiIsClicked = false, LycheeIsClicked = false, MangosteenIsClicked = false, PapayaIsClicked = false, PearIsClicked = false, WatermelonIsClicked = false, DragonFruitIsClicked = false, PrunesIsClicked = false, TamarindIsClicked = false;
    int LemonSelected, BananaSelected, PeachSelected, BlackPlumSelected, OrangeSelected, ApricotSelected, DriedFruitSelected, RedPlumSelected, MixBerriesSelected, BlueberriesSelected, StrawberriesSelected, FigSelected, CantaloupeSelected, HoneydewSelected, AppleSelected, PineappleSelected, AvocadoSelected, BlackberriesSelected, RaspberriesSelected, MangoSelected, BlackcurrantSelected, DateSelected, DurianSelected, GrapesSelected, GuavaSelected, KiwiSelected, LycheeSelected, MangosteenSelected, PapayaSelected, PearSelected, WatermelonSelected, DragonFruitSelected, PrunesSelected, TamarindSelected;
    String BananaDateExpiry, LemonDateExpiry, PeachDateExpiry, BlackPlumDateExpiry, OrangeDateExpiry, ApricotDateExpiry, DriedFruitDateExpiry, RedPlumDateExpiry, MixBerriesDateExpiry, BlueberriesDateExpiry, StrawberriesDateExpiry, FigDateExpiry, CantaloupeDateExpiry, HoneydewDateExpiry, AppleDateExpiry, PineappleDateExpiry, AvocadoDateExpiry, BlackberriesDateExpiry, RaspberriesDateExpiry, MangoDateExpiry, BlackcurrantDateExpiry, DateDateExpiry, DurianDateExpiry, GrapesDateExpiry, GuavaDateExpiry, KiwiDateExpiry, LycheeDateExpiry, MangosteenDateExpiry, PapayaDateExpiry, PearDateExpiry, WatermelonDateExpiry, DragonFruitDateExpiry, PrunesDateExpiry, TamarindDateExpiry;
    String[] Ingredient = {"Banana" , "Lemon" , "Peach" , "Black Plum" , "Orange" , "Apricot", "Dried Fruit" , "Red Plum" , "Mix Berries" , "Blueberries" , "Strawberries" , "Fig" , "Cantaloupe" , "Honeydew Lemon" , "Apple" , "Pineapple" , "Avocado" , "Blackberries" , "Raspberries" , "Mango" , "Blackcurrant" , "Date Fruit" , "Durian" , "Grapes" , "Guava" , "Kiwi" , "Lychee" , "Mangosteen" , "Papaya" , "Pear" , "Watermelon" , "Dragon Fruit" , "Prunes" , "Tamarind"};

    DatabaseReference dataRef;
    String currentUserID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_fruit);

        itemCount = (TextView) findViewById(R.id.itemCount);
        Banana = (TextView) findViewById(R.id.fruitItemBanana);
        Lemon = (TextView) findViewById(R.id.fruitItemLemon);
        Peach = (TextView) findViewById(R.id.fruitItemPeach);
        BlackPlum = (TextView) findViewById(R.id.fruitItemBlackPlum);
        Orange = (TextView) findViewById(R.id.fruitItemOrange);
        Apricot = (TextView) findViewById(R.id.fruitItemApricot);
        DriedFruit = (TextView) findViewById(R.id.fruitItemDriedFruit);
        RedPlum = (TextView) findViewById(R.id.fruitItemRedPlum);
        MixBerries = (TextView) findViewById(R.id.fruitItemMixBerries);
        Blueberries = (TextView) findViewById(R.id.fruitItemBlueberries);
        Strawberries = (TextView) findViewById(R.id.fruitItemStrawberries);
        Fig = (TextView) findViewById(R.id.fruitItemFig);
        Cantaloupe = (TextView) findViewById(R.id.fruitItemCantaloupe);
        Honeydew = (TextView) findViewById(R.id.fruitItemHoneydewMelon);
        Apple = (TextView) findViewById(R.id.fruitItemApple);
        Pineapple = (TextView) findViewById(R.id.fruitItemPineapple);
        Avocado = (TextView) findViewById(R.id.fruitItemAvocado);
        Blackberries = (TextView) findViewById(R.id.fruitItemBlackberries);
        Raspberries = (TextView) findViewById(R.id.fruitItemRaspberrie);
        Mango = (TextView) findViewById(R.id.fruitItemMango);
        Blackcurrant = (TextView) findViewById(R.id.fruitItemBlackcurrant);
        Date = (TextView) findViewById(R.id.fruitItemDateFruit);
        Durian = (TextView) findViewById(R.id.fruitItemDurian);
        Grapes = (TextView) findViewById(R.id.fruitItemGrapes);
        Guava = (TextView) findViewById(R.id.fruitItemGuava);
        Kiwi = (TextView) findViewById(R.id.fruitItemKiwi);
        Lychee  = (TextView) findViewById(R.id.fruitItemLychee);
        Mangosteen = (TextView) findViewById(R.id.fruitItemMangosteen);
        Papaya = (TextView) findViewById(R.id.fruitItemPapaya);
        Pear = (TextView) findViewById(R.id.fruitItemPear);
        Watermelon = (TextView) findViewById(R.id.fruitItemWatermelon);
        DragonFruit = (TextView) findViewById(R.id.fruitItemDragonFruit);
        Prunes = (TextView) findViewById(R.id.fruitItemPrunes);
        Tamarind = (TextView) findViewById(R.id.fruitItemTamarind);
        addBtn = (LinearLayout) findViewById(R.id.addBtn);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        dataRef = FirebaseDatabase.getInstance().getReference().child("Ingredient").child(currentUserID);

        Banana.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BananaIsClicked){

                addItemCount();
                setClicked(Banana);
                BananaIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Banana);
                BananaIsClicked = false;
            }
        });

        Lemon.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!LemonIsClicked) {

                addItemCount();
                setClicked(Lemon);
                LemonIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Lemon);
                LemonIsClicked = false;
            }
        });

        Peach.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PeachIsClicked){

                addItemCount();
                setClicked(Peach);
                PeachIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Peach);
                PeachIsClicked = false;
            }
        });

        BlackPlum.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BlackPlumIsClicked){

                addItemCount();
                setClicked(BlackPlum);
                BlackPlumIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(BlackPlum);
                BlackPlumIsClicked = false;
            }
        });

        Orange.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!OrangeIsClicked){

                addItemCount();
                setClicked(Orange);
                OrangeIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Orange);
                OrangeIsClicked = false;
            }
        });

        Apricot.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!ApricotIsClicked){

                addItemCount();
                setClicked(Apricot);
                ApricotIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Apricot);
                ApricotIsClicked = false;
            }
        });

        DriedFruit.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!DriedFruitIsClicked){

                addItemCount();
                setClicked(DriedFruit);
                DriedFruitIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(DriedFruit);
                DriedFruitIsClicked = false;
            }
        });

        RedPlum.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!RedPlumIsClicked) {

                addItemCount();
                setClicked(RedPlum);
                RedPlumIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(RedPlum);
                RedPlumIsClicked = false;
            }
        });

        MixBerries.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!MixBerriesIsClicked){

                addItemCount();
                setClicked(MixBerries);
                MixBerriesIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(MixBerries);
                MixBerriesIsClicked = false;
            }
        });

        Blueberries.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BlueberriesIsClicked){

                addItemCount();
                setClicked(Blueberries);
                BlueberriesIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Blueberries);
                BlueberriesIsClicked = false;
            }
        });

        Strawberries.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!StrawberriesIsClicked){

                addItemCount();
                setClicked(Strawberries);
                StrawberriesIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Strawberries);
                StrawberriesIsClicked = false;
            }
        });

        Fig.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!FigIsClicked){

                addItemCount();
                setClicked(Fig);
                FigIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Fig);
                FigIsClicked = false;
            }
        });

        Cantaloupe.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!CantaloupeIsClicked){

                addItemCount();
                setClicked(Cantaloupe);
                CantaloupeIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Cantaloupe);
                CantaloupeIsClicked = false;
            }
        });

        Honeydew.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (!HoneydewIsClicked) {

                addItemCount();
                setClicked(Honeydew);
                HoneydewIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Honeydew);
                HoneydewIsClicked = false;
            }
        });

        Apple.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!AppleIsClicked){

                addItemCount();
                setClicked(Apple);
                AppleIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Apple);
                AppleIsClicked = false;
            }
        });

        Pineapple.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PineappleIsClicked){

                addItemCount();
                setClicked(Pineapple);
                PineappleIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Pineapple);
                PineappleIsClicked = false;
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

        Blackberries.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BlackberriesIsClicked){

                addItemCount();
                setClicked(Blackberries);
                BlackberriesIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Blackberries);
                BlackberriesIsClicked = false;
            }
        });

        Raspberries.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!RaspberriesIsClicked){

                addItemCount();
                setClicked(Raspberries);
                RaspberriesIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Raspberries);
                RaspberriesIsClicked = false;
            }
        });

        Mango.setOnClickListener(view ->{

            //check if the item has been clicked or not
            if (! MangoIsClicked) {

                addItemCount();
                setClicked( Mango);
                MangoIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked( Mango);
                MangoIsClicked = false;
            }
        });

        Blackcurrant.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!BlackcurrantIsClicked){

                addItemCount();
                setClicked(Blackcurrant);
                BlackcurrantIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Blackcurrant);
                BlackcurrantIsClicked = false;
            }
        });

        Date.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!DateIsClicked){

                addItemCount();
                setClicked(Date);
                DateIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Date);
                DateIsClicked = false;
            }
        });

        Durian.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!DurianIsClicked){

                addItemCount();
                setClicked(Durian);
                DurianIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Durian);
                DurianIsClicked = false;
            }
        });

        Grapes.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!GrapesIsClicked){

                addItemCount();
                setClicked(Grapes);
                GrapesIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Grapes);
                GrapesIsClicked = false;
            }
        });

        Guava.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!GuavaIsClicked){

                addItemCount();
                setClicked(Guava);
                GuavaIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Guava);
                GuavaIsClicked = false;
            }
        });

        Kiwi.setOnClickListener(view ->{
            //check if the item has been clicked or not
            if (!KiwiIsClicked) {

                addItemCount();
                setClicked(Kiwi);
                KiwiIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Kiwi);
                KiwiIsClicked = false;
            }
        });

        Lychee.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!LycheeIsClicked){

                addItemCount();
                setClicked(Lychee);
                LycheeIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Lychee);
                LycheeIsClicked = false;
            }
        });

        Mangosteen.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!MangosteenIsClicked){

                addItemCount();
                setClicked(Mangosteen);
                MangosteenIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Mangosteen);
                MangosteenIsClicked = false;
            }
        });

        Papaya.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PapayaIsClicked){

                addItemCount();
                setClicked(Papaya);
                PapayaIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Papaya);
                PapayaIsClicked = false;
            }
        });

        Watermelon.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!WatermelonIsClicked){

                addItemCount();
                setClicked(Watermelon);
                WatermelonIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Watermelon);
                WatermelonIsClicked = false;
            }
        });

        Pear.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!PearIsClicked){

                addItemCount();
                setClicked(Pear);
                PearIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Pear);
                PearIsClicked = false;
            }
        });

        DragonFruit.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!DragonFruitIsClicked){

                addItemCount();
                setClicked(DragonFruit);
                DragonFruitIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(DragonFruit);
                DragonFruitIsClicked = false;
            }
        });

        Prunes.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (! PrunesIsClicked){

                addItemCount();
                setClicked( Prunes);
                PrunesIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked( Prunes);
                PrunesIsClicked = false;
            }
        });

        Tamarind.setOnClickListener(view -> {
            //check if the item has been clicked or not
            if (!TamarindIsClicked){

                addItemCount();
                setClicked(Tamarind);
                TamarindIsClicked = true;
            } else {

                minusItemCount();
                setNotClicked(Tamarind);
                TamarindIsClicked = false;
            }
        });

        //listener for adding item into db
        addBtn.setOnClickListener(v ->{
            setExpiry();
            checkItem();

            for (int x=0; x<Ingredient.length; x++) {

                int getItemValue = 0;
                String getItemExpiry = "";

                if (Objects.equals(Ingredient[x], "Banana") && BananaSelected == 1) {
                    getItemValue = BananaSelected;
                    getItemExpiry = BananaDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);

                }
                else if (Objects.equals(Ingredient[x], "Lemon") && LemonSelected == 1) {
                    getItemValue = LemonSelected;
                    getItemExpiry = LemonDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Peach") && PeachSelected == 1) {
                    getItemValue = PeachSelected;
                    getItemExpiry = PeachDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Black Plum") && BlackPlumSelected == 1) {
                    getItemValue = BlackPlumSelected;
                    getItemExpiry = BlackPlumDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Orange") && OrangeSelected == 1) {
                    getItemValue = OrangeSelected;
                    getItemExpiry = OrangeDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Apricot") && ApricotSelected == 1) {
                    getItemValue = ApricotSelected;
                    getItemExpiry = ApricotDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Dried Fruit") && DriedFruitSelected == 1) {
                    getItemValue = DriedFruitSelected;
                    getItemExpiry = DriedFruitDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Red Plum") && RedPlumSelected == 1) {
                    getItemValue = RedPlumSelected;
                    getItemExpiry = RedPlumDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Mix Berries") && MixBerriesSelected == 1) {
                    getItemValue = MixBerriesSelected;
                    getItemExpiry = MixBerriesDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Blueberries") && BlueberriesSelected == 1) {
                    getItemValue = BlueberriesSelected;
                    getItemExpiry = BlueberriesDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Strawberries") && StrawberriesSelected == 1) {
                    getItemValue = StrawberriesSelected;
                    getItemExpiry = StrawberriesDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Fig") && FigSelected == 1) {
                    getItemValue = FigSelected;
                    getItemExpiry = FigDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Cantaloupe") && CantaloupeSelected == 1) {
                    getItemValue = CantaloupeSelected;
                    getItemExpiry = CantaloupeDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Honeydew Lemon") && HoneydewSelected == 1) {
                    getItemValue = HoneydewSelected;
                    getItemExpiry = HoneydewDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Apple") && AppleSelected == 1) {
                    getItemValue = AppleSelected;
                    getItemExpiry = AppleDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Pineapple") && PineappleSelected == 1) {
                    getItemValue = PineappleSelected;
                    getItemExpiry = PineappleDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Avocado") && AvocadoSelected == 1) {
                    getItemValue = AvocadoSelected;
                    getItemExpiry = AvocadoDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Blackberries") && BlackberriesSelected == 1) {
                    getItemValue = BlackberriesSelected;
                    getItemExpiry = BlackberriesDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Raspberries") && RaspberriesSelected == 1) {
                    getItemValue = RaspberriesSelected;
                    getItemExpiry = RaspberriesDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Mango") && MangoSelected == 1) {
                    getItemValue = MangoSelected;
                    getItemExpiry = MangoDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Blackcurrant") && BlackcurrantSelected == 1) {
                    getItemValue = BlackcurrantSelected;
                    getItemExpiry = BlackcurrantDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Date Fruit") && DateSelected == 1) {
                    getItemValue = DateSelected;
                    getItemExpiry = DateDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Durian") && DurianSelected == 1) {
                    getItemValue = DurianSelected;
                    getItemExpiry = DurianDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Grapes") && GrapesSelected == 1) {
                    getItemValue = GrapesSelected;
                    getItemExpiry = GrapesDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Guava") && GuavaSelected == 1) {
                    getItemValue = GuavaSelected;
                    getItemExpiry = GuavaDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Kiwi") && KiwiSelected == 1) {
                    getItemValue = KiwiSelected;
                    getItemExpiry = KiwiDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Lychee") && LycheeSelected == 1) {
                    getItemValue = LycheeSelected;
                    getItemExpiry = LycheeDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Mangosteen") && MangosteenSelected == 1) {
                    getItemValue = MangosteenSelected;
                    getItemExpiry = MangosteenDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Papaya") && PapayaSelected == 1) {
                    getItemValue = PapayaSelected;
                    getItemExpiry = PapayaDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Pear") && PearSelected == 1) {
                    getItemValue = PearSelected;
                    getItemExpiry = PearDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Watermelon") && WatermelonSelected == 1) {
                    getItemValue = WatermelonSelected;
                    getItemExpiry = WatermelonDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Dragon Fruit") && DragonFruitSelected == 1) {
                    getItemValue = DragonFruitSelected;
                    getItemExpiry = DragonFruitDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Prunes") && PrunesSelected == 1) {
                    getItemValue = PrunesSelected;
                    getItemExpiry = PrunesDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
                else if (Objects.equals(Ingredient[x], "Tamarind") && TamarindSelected == 1) {
                    getItemValue = TamarindSelected;
                    getItemExpiry = TamarindDateExpiry;
                    addItemByIngredient(Ingredient[x], getItemValue, getItemExpiry);
                }
            }
            customToast("Successful", "Item added into fridge", "Success");
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

        if (LemonIsClicked) {
            LemonSelected = 1;
        } else {
            LemonSelected = 0;
        }

        if (BananaIsClicked) {
            BananaSelected = 1;
        } else {
            BananaSelected = 0;
        }

        if (PeachIsClicked) {
            PeachSelected = 1;
        } else {
            PeachSelected = 0;
        }

        if (OrangeIsClicked) {
            OrangeSelected = 1;
        } else {
            OrangeSelected = 0;
        }

        if (BlackPlumIsClicked) {
            BlackPlumSelected = 1;
        } else {
            BlackPlumSelected = 0;
        }

        if (ApricotIsClicked) {
            ApricotSelected = 1;
        } else {
            ApricotSelected = 0;
        }

        if (DriedFruitIsClicked) {
            DriedFruitSelected = 1;
        } else {
            DriedFruitSelected = 0;
        }

        if (RedPlumIsClicked) {
            RedPlumSelected = 1;
        } else {
            RedPlumSelected = 0;
        }

        if (MixBerriesIsClicked) {
            MixBerriesSelected = 1;
        } else {
            MixBerriesSelected = 0;
        }

        if (BlueberriesIsClicked) {
            BlueberriesSelected = 1;
        } else {
            BlueberriesSelected = 0;
        }

        if (StrawberriesIsClicked) {
            StrawberriesSelected = 1;
        } else {
            StrawberriesSelected = 0;
        }

        if (FigIsClicked) {
            FigSelected = 1;
        } else {
            FigSelected = 0;
        }
        if (CantaloupeIsClicked) {
            CantaloupeSelected = 1;
        } else {
            CantaloupeSelected = 0;
        }

        if (HoneydewIsClicked) {
            HoneydewSelected = 1;
        } else {
            HoneydewSelected = 0;
        }

        if (AppleIsClicked) {
            AppleSelected = 1;
        } else {
            AppleSelected = 0;
        }

        if (PineappleIsClicked) {
            PineappleSelected = 1;
        } else {
            PineappleSelected = 0;
        }

        if (AvocadoIsClicked) {
            AvocadoSelected = 1;
        } else {
            AvocadoSelected = 0;
        }

        if (BlackberriesIsClicked) {
            BlackberriesSelected = 1;
        } else {
            BlackberriesSelected = 0;
        }
        if (RaspberriesIsClicked) {
            RaspberriesSelected = 1;
        } else {
            RaspberriesSelected = 0;
        }

        if (MangoIsClicked) {
            MangoSelected = 1;
        } else {
            MangoSelected = 0;
        }

        if (BlackcurrantIsClicked) {
            BlackcurrantSelected = 1;
        } else {
            BlackcurrantSelected = 0;
        }

        if (DateIsClicked) {
            DateSelected = 1;
        } else {
            DateSelected = 0;
        }

        if (DurianIsClicked) {
            DurianSelected = 1;
        } else {
            DurianSelected = 0;
        }

        if (GrapesIsClicked) {
            GrapesSelected = 1;
        } else {
            GrapesSelected = 0;
        }
        if (GuavaIsClicked) {
            GuavaSelected = 1;
        } else {
            GuavaSelected = 0;
        }

        if (KiwiIsClicked) {
            KiwiSelected = 1;
        } else {
            KiwiSelected = 0;
        }

        if (LycheeIsClicked) {
            LycheeSelected = 1;
        } else {
            LycheeSelected = 0;
        }

        if (MangosteenIsClicked) {
            MangosteenSelected = 1;
        } else {
            MangosteenSelected = 0;
        }

        if (PapayaIsClicked) {
            PapayaSelected = 1;
        } else {
            PapayaSelected = 0;
        }

        if (PearIsClicked) {
            PearSelected = 1;
        } else {
            PearSelected = 0;
        }
        if (WatermelonIsClicked) {
            WatermelonSelected = 1;
        } else {
            WatermelonSelected = 0;
        }

        if (DragonFruitIsClicked) {
            DragonFruitSelected = 1;
        } else {
            DragonFruitSelected = 0;
        }

        if (PrunesIsClicked) {
            PrunesSelected = 1;
        } else {
            PrunesSelected = 0;
        }

        if (TamarindIsClicked) {
            TamarindSelected = 1;
        } else {
            TamarindSelected = 0;
        }
    }

    private void setExpiry() {

        // set the number of days the ingredient is safe to use
        // expiry days is estimated if user store the ingredient in suitable places
        int BananaExpiry = 7;
        int LemonExpiry = 28;
        int PeachExpiry = 4;
        int BlackPlumExpiry = 5;
        int OrangeExpiry = 21;
        int ApricotExpiry = 3;
        int DriedFruitExpiry = 120;
        int RedPlumExpiry = 5;
        int MixBerriesExpiry = 7;
        int BlueberriesExpiry = 5;
        int StrawberriesExpiry = 7;
        int FigExpiry = 3;
        int CantaloupeExpiry = 21;
        int HoneydewExpiry = 8;
        int AppleExpiry = 35;
        int PineappleExpiry = 5;
        int AvocadoExpiry = 21;
        int BlackberriesExpiry = 3;
        int RaspberriesExpiry = 3;
        int MangoExpiry = 14;
        int BlackcurrantExpiry = 5;
        int DateExpiry = 180;
        int DurianExpiry = 5;
        int GrapesExpiry = 10;
        int GuavaExpiry = 15;
        int KiwiExpiry = 10;
        int LycheeExpiry = 10;
        int MangosteenExpiry = 7;
        int PapayaExpiry = 7;
        int PearExpiry = 10;
        int WatermelonExpiry = 14;
        int DragonFruitExpiry = 14;
        int PrunesExpiry = 360;
        int TamarindExpiry = 180;

        //set date time formatter
        //DOCS: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // set the date of expiry start from date on local device
        LocalDateTime BananaDate = LocalDateTime.now().plusDays(BananaExpiry);
        LocalDateTime LemonDate = LocalDateTime.now().plusDays(LemonExpiry);
        LocalDateTime PeachDate = LocalDateTime.now().plusDays(PeachExpiry);
        LocalDateTime BlackPlumDate = LocalDateTime.now().plusDays(BlackPlumExpiry);
        LocalDateTime OrangeDate = LocalDateTime.now().plusDays(OrangeExpiry);
        LocalDateTime ApricotDate = LocalDateTime.now().plusDays(ApricotExpiry);
        LocalDateTime DriedFruitDate = LocalDateTime.now().plusDays(DriedFruitExpiry);
        LocalDateTime RedPlumDate = LocalDateTime.now().plusDays(RedPlumExpiry);
        LocalDateTime MixBerriesDate = LocalDateTime.now().plusDays(MixBerriesExpiry);
        LocalDateTime BlueberriesDate = LocalDateTime.now().plusDays(BlueberriesExpiry);
        LocalDateTime StrawberriesDate = LocalDateTime.now().plusDays(StrawberriesExpiry);
        LocalDateTime FigDate = LocalDateTime.now().plusDays(FigExpiry);
        LocalDateTime CantaloupeDate = LocalDateTime.now().plusDays(CantaloupeExpiry);
        LocalDateTime HoneydewDate = LocalDateTime.now().plusDays(HoneydewExpiry);
        LocalDateTime AppleDate = LocalDateTime.now().plusDays(AppleExpiry);
        LocalDateTime PineappleDate = LocalDateTime.now().plusDays(PineappleExpiry);
        LocalDateTime AvocadoDate = LocalDateTime.now().plusDays(AvocadoExpiry);
        LocalDateTime BlackberriesDate = LocalDateTime.now().plusDays(BlackberriesExpiry);
        LocalDateTime RaspberriesDate = LocalDateTime.now().plusDays(RaspberriesExpiry);
        LocalDateTime MangoDate = LocalDateTime.now().plusDays(MangoExpiry);
        LocalDateTime BlackcurrantDate = LocalDateTime.now().plusDays(BlackcurrantExpiry);
        LocalDateTime DateDate = LocalDateTime.now().plusDays(DateExpiry);
        LocalDateTime DurianDate = LocalDateTime.now().plusDays(DurianExpiry);
        LocalDateTime GrapesDate = LocalDateTime.now().plusDays(GrapesExpiry);
        LocalDateTime GuavaDate = LocalDateTime.now().plusDays(GuavaExpiry);
        LocalDateTime KiwiDate = LocalDateTime.now().plusDays(KiwiExpiry);
        LocalDateTime LycheeDate = LocalDateTime.now().plusDays(LycheeExpiry);
        LocalDateTime MangosteenDate = LocalDateTime.now().plusDays(MangosteenExpiry);
        LocalDateTime PapayaDate = LocalDateTime.now().plusDays(PapayaExpiry);
        LocalDateTime PearDate = LocalDateTime.now().plusDays(PearExpiry);
        LocalDateTime WatermelonDate = LocalDateTime.now().plusDays(WatermelonExpiry);
        LocalDateTime DragonFruitDate = LocalDateTime.now().plusDays(DragonFruitExpiry);
        LocalDateTime PrunesDate = LocalDateTime.now().plusDays(PrunesExpiry);
        LocalDateTime TamarindDate = LocalDateTime.now().plusDays(TamarindExpiry);

        //set the date as string
        BananaDateExpiry = String.valueOf(dtf.format(BananaDate));
        LemonDateExpiry = String.valueOf(dtf.format(LemonDate));
        PeachDateExpiry = String.valueOf(dtf.format(PeachDate));
        BlackPlumDateExpiry = String.valueOf(dtf.format(BlackPlumDate));
        OrangeDateExpiry = String.valueOf(dtf.format(OrangeDate));
        ApricotDateExpiry = String.valueOf(dtf.format(ApricotDate));
        DriedFruitDateExpiry = String.valueOf(dtf.format(DriedFruitDate));
        RedPlumDateExpiry = String.valueOf(dtf.format(RedPlumDate));
        MixBerriesDateExpiry = String.valueOf(dtf.format(MixBerriesDate));
        BlueberriesDateExpiry = String.valueOf(dtf.format(ApricotDate));
        StrawberriesDateExpiry = String.valueOf(dtf.format(ApricotDate));
        FigDateExpiry = String.valueOf(dtf.format(ApricotDate));
        CantaloupeDateExpiry = String.valueOf(dtf.format(ApricotDate));
        HoneydewDateExpiry = String.valueOf(dtf.format(ApricotDate));
        AppleDateExpiry = String.valueOf(dtf.format(ApricotDate));
        PineappleDateExpiry = String.valueOf(dtf.format(ApricotDate));
        AvocadoDateExpiry = String.valueOf(dtf.format(ApricotDate));
        BlackberriesDateExpiry = String.valueOf(dtf.format(ApricotDate));
        RaspberriesDateExpiry = String.valueOf(dtf.format(ApricotDate));
        MangoDateExpiry = String.valueOf(dtf.format(ApricotDate));
        BlackcurrantDateExpiry = String.valueOf(dtf.format(ApricotDate));
        DateDateExpiry = String.valueOf(dtf.format(ApricotDate));
        DurianDateExpiry = String.valueOf(dtf.format(ApricotDate));
        GrapesDateExpiry = String.valueOf(dtf.format(ApricotDate));
        GuavaDateExpiry = String.valueOf(dtf.format(ApricotDate));
        KiwiDateExpiry = String.valueOf(dtf.format(ApricotDate));
        LycheeDateExpiry = String.valueOf(dtf.format(ApricotDate));
        MangosteenDateExpiry = String.valueOf(dtf.format(ApricotDate));
        PapayaDateExpiry = String.valueOf(dtf.format(ApricotDate));
        PearDateExpiry = String.valueOf(dtf.format(ApricotDate));
        WatermelonDateExpiry = String.valueOf(dtf.format(ApricotDate));
        DragonFruitDateExpiry = String.valueOf(dtf.format(ApricotDate));
        PrunesDateExpiry = String.valueOf(dtf.format(ApricotDate));
        TamarindDateExpiry = String.valueOf(dtf.format(ApricotDate));
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

    private void customToast(String Title, String Desc, String status) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.toast_root));

        TextView ToastTitle = layout.findViewById(R.id.ToastTitle);
        TextView ToastDesc = layout.findViewById(R.id.ToastDesc);
        ImageView toastDraw = layout.findViewById(R.id.toastDraw);

        if (status.equals("Success")){
            toastDraw.setImageResource(R.drawable.symbol_successful);
        } else if (status.equals("Failure")) {
            toastDraw.setImageResource(R.drawable.symbol_error);
        }
        ToastTitle.setText(Title);
        ToastDesc.setText(Desc);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }
}
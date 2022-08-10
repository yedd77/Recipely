package com.example.recipely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.recipely.databinding.ActivityHomePageBinding;
import com.example.recipely.databinding.ActivityMainBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homePage extends AppCompatActivity  {

    ActivityHomePageBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hooks
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set the default fragment once the app started
        replaceFragment(new home());

        //set event listener for bottom nav bar
        binding.bottomNavView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.botNavBarHome:
                    replaceFragment(new home());
                    break;
                case R.id.botNavBarIngred:
                    replaceFragment(new ingredient());
                    break;
                case R.id.botNavBarFridge:
                    replaceFragment(new fridge());
                    break;
                case R.id.botNavBarAcc:
                    replaceFragment(new account());
                    break;


            }
            return true;
        });
    }

    // replace fragment passed from bottomNavView listener
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout , fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //check if user already auth
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            Intent signInPage = new Intent(this, LoginActivity.class);
            startActivity(signInPage);
        }
    }
}
package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class clearFridge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_fridge);

        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        FirebaseAuth mAuth;
        String currentUser;

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser().getUid();

        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("Ingredient").child(currentUser);

        deleteBtn.setOnClickListener(v ->{
            dRef.removeValue();
            Toast.makeText(this, "Fridge has been cleared", Toast.LENGTH_SHORT).show();
            Intent fridgePage = new Intent(getBaseContext(), homePage.class);
            startActivity(fridgePage);
        });
    }
}
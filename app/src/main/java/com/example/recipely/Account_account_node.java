package com.example.recipely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.recipely.Models.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Account_account_node extends AppCompatActivity {

    TextView emailAddress;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_account_node);

        //display current user email
        emailAddress = (TextView) findViewById(R.id.emailAddress);
        String emailUser = "";

        //set user email on page
        user = FirebaseAuth.getInstance().getCurrentUser();
        for (UserInfo profile : user.getProviderData()){
            emailUser = profile.getEmail();
        }

        emailAddress.setText(emailUser);

        //button to change password
        Button changePassBtn = (Button) findViewById(R.id.changePassBtn);

        changePassBtn.setOnClickListener(view ->{
            Intent changePass = new Intent(getBaseContext(), resetPassword.class);
            startActivity(changePass);
        });

        //button to logOut
        Button LogOutBtn = (Button) findViewById(R.id.LogOutBtn);

        LogOutBtn.setOnClickListener(view ->{
            FirebaseAuth.getInstance().signOut();
            Intent signOut = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(signOut);
        });

        //Debug
        Button debugButton = (Button) findViewById(R.id.debugButton);
        debugButton.setOnClickListener( v->{

        });


    }
}
package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

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

    }
}
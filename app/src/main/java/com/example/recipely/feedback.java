package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class feedback extends AppCompatActivity {
    EditText subject, message;
    Button submit;
    FirebaseUser user;
    String emailUser;
    FirebaseDatabase db;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        subject = (EditText) findViewById(R.id.subject);
        message = (EditText) findViewById(R.id.message);
        submit = (Button) findViewById(R.id.Submit);

        submit.setOnClickListener(v ->{

            user = FirebaseAuth.getInstance().getCurrentUser();
            for (UserInfo profile : user.getProviderData()){
                emailUser = profile.getEmail();
            }

            HashMap data = new HashMap();
            data.put("UserEmail", subject );
            data.put("Subject", emailUser );
            data.put("Message", message );

            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Feedback").setValue(data);
            Toast.makeText(this, "Feedback has been accepted", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(getBaseContext(), homePage.class);
            startActivity(back);
        });
    }
}
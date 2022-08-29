package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
            String textSubject = subject.getText().toString();
            String textMessage = message.getText().toString();

            saveFeedback(emailUser, textSubject, textMessage);

            customToast("Successful", "Feedback has been accepted", "Success");
            Intent back = new Intent(getBaseContext(), homePage.class);
            startActivity(back);
        });
    }

    private void saveFeedback(String emailUser, String subject, String message) {

        HashMap data = new HashMap();
        data.put("UserEmail", subject );
        data.put("Subject", emailUser );
        data.put("Message", message );

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Feedback").setValue(data);
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
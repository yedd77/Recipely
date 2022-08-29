package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
            customToast("Successful", "Fridge has been cleared", "Success");
            Intent fridgePage = new Intent(getBaseContext(), homePage.class);
            startActivity(fridgePage);
        });
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
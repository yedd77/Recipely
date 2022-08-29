package com.example.recipely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class resetPassword extends AppCompatActivity {

    EditText loginEmail;
    Button forgotPassBtn;
    FirebaseAuth mAuth;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth = FirebaseAuth.getInstance();
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        forgotPassBtn = (Button) findViewById(R.id.Submit);
        myDialog = new Dialog(this);

        //listener for submit email
        forgotPassBtn.setOnClickListener(view -> {
            forgotPass();
        });
    }

    private void forgotPass() {
        String email = loginEmail.getText().toString().trim();

        //check if the email submitted is empty
        if (email.isEmpty()){
            loginEmail.setError("Email field is required");
            loginEmail.requestFocus();
            return;
        }
        //check if the email format is valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Please submit a valid email");
            loginEmail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            //check if the email is send
            if (task.isSuccessful()){
                customToast("Successful", "Please check your email and click the link given", "Success");
                Intent loginPage = new Intent(this , LoginActivity.class);
                startActivity(loginPage);
            } else {
                customToast("Unsuccessful", "Something went wrong, please try again", "Failure");
            }
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
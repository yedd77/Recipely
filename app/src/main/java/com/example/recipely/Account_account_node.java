package com.example.recipely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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

import com.example.recipely.Models.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account_account_node extends AppCompatActivity {


    EditText usernameEdit, emailAddress;
    FirebaseUser user;
    LinearLayout save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_account_node);

        //display current user email
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        save = (LinearLayout) findViewById(R.id.save);

        save.setVisibility(View.GONE);
        String emailUser = "";
        String usernameUser = "";

        //set user email on page
        user = FirebaseAuth.getInstance().getCurrentUser();
        for (UserInfo profile : user.getProviderData()){
            emailUser = profile.getEmail();
            usernameUser = profile.getDisplayName();
        }

        emailAddress.setText(emailUser);
        emailAddress.setEnabled(false);

        if (!Objects.equals(usernameUser, "")){
            usernameEdit.setText(usernameUser);
        } else {
            usernameEdit.setHint("Set an username");
        }

        usernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                save.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        save.setOnClickListener(v ->{
            String setUsername = usernameEdit.getText().toString();

            if (TextUtils.isEmpty(setUsername)){
                usernameEdit.setError("Please enter username");
                usernameEdit.requestFocus();
            } else {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(setUsername)
                        .build();
                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    customToast("Successful", "Username has been updated", "Success");
                                } else {
                                    customToast("Unsuccessful", "Something went wrong", "Failure");
                                }
                            }
                        });
            }
        });

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
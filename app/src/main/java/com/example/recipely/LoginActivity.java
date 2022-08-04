package com.example.recipely;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    TextView SignUp, forgotPass;
    GoogleSignInOptions GSO;
    GoogleSignInClient GSC;
    LinearLayout GoogleSign;
    FirebaseAuth mAuth;
    Button logInBtn;
    EditText loginEmail, loginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SignUp = (TextView) findViewById(R.id.signUpPage);
        forgotPass = (TextView) findViewById(R.id.forgotPass);
        mAuth = FirebaseAuth.getInstance();
        logInBtn = (Button) findViewById(R.id.logInBtn);
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPass = (EditText) findViewById(R.id.loginPass);

        //link to sign up page
        SignUp.setOnClickListener(view -> {
            Intent signUpPage = new Intent(this , register.class);
            startActivity(signUpPage);
        });

        //link to forgot pass page
        forgotPass.setOnClickListener(view ->{
            Intent forgotPassPage = new Intent(this , resetPassword.class);
            startActivity(forgotPassPage);
        });

        logInBtn.setOnClickListener(view -> {
            loginUser();
        });
    }

    //function login user
    private void loginUser() {
        String email = loginEmail.getText().toString();
        String pass = loginPass.getText().toString();

        if(TextUtils.isEmpty(email)){
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();
        } else if (TextUtils.isEmpty(pass)){
            loginPass.setError("Password is required");
            loginPass.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                @Override
                public void onComplete(@NonNull Task<AuthResult> task){
                    if (task.isSuccessful()){
                        Intent homepage = new Intent (LoginActivity.this , homePage.class);
                        startActivity(homepage);
                    } else {
                        Toast.makeText(LoginActivity.this, "Registration Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
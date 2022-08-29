package com.example.recipely;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

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

        if(TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
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
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                            customToast("Unsuccessful", "Invalid Credential, please try again", "Failure");
                        } else if (task.getException() instanceof  FirebaseAuthInvalidUserException){
                            customToast("Unsuccessful", "Can't found user", "Failure");
                        } else{
                            customToast("Unsuccessful", "Something went wrong/", "Failure");
                        }
                    }
                }
            });
        }
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
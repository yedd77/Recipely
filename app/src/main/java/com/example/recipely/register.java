package com.example.recipely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class register extends AppCompatActivity {

    TextView signIn;
    EditText loginEmail, regPass, username, regPassConfirm;
    FirebaseAuth mAuth;
    Button createAccBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regPassConfirm = (EditText) findViewById(R.id.regPassConfirm);
        regPass = (EditText) findViewById(R.id.regPass);
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        createAccBtn = (Button) findViewById(R.id.createAcc);
        signIn = (TextView) findViewById(R.id.signInPage);
        mAuth = FirebaseAuth.getInstance();

        //create account button
        createAccBtn.setOnClickListener(view -> {
            createAcc();
        });

        signIn.setOnClickListener(view -> {
            Intent signInPage = new Intent(this , LoginActivity.class);
            startActivity(signInPage);
        });
    }

    private void createAcc() {
        String email = loginEmail.getText().toString();
        String pass = regPass.getText().toString();
        String conPass = regPassConfirm.getText().toString();

        if (TextUtils.isEmpty(email)){
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();

        } else if (TextUtils.isEmpty(pass)){
            regPass.setError("Password is required");
            regPass.requestFocus();

        } else if (TextUtils.isEmpty(conPass)){
            regPassConfirm.setError("Password confirmation is required");
            regPassConfirm.requestFocus();

        } else if (!pass.equals(conPass)){
            regPassConfirm.setError("Password didn't match");
            regPassConfirm.requestFocus();

        } else {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                customToast("Successful", "Your account has been created", "Success");
                                Intent loginPage = new Intent(register.this , LoginActivity.class);
                                startActivity(loginPage);
                            } else {
                                customToast("Unsuccessful", "Something went wrong, please try again", "Failure");
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
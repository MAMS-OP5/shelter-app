package com.example.afgapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText fName, fEmail, fPassword, fPhoneNum;
    Button fRegisterBtn;
    TextView fLoginBtn;
    Button backBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fName = findViewById(R.id.enterFacName);
        fEmail = findViewById(R.id.enterEmail);
        fPassword = findViewById(R.id.enterPassword2);
        fPhoneNum = findViewById((R.id.enterPhone));
        fRegisterBtn = findViewById(R.id.registerSubmitButton);
        fLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.registerProgressBar);

        backBtn = findViewById(R.id.backBtn);

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ShelterPov.class));
            finish();
        }
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        fRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = fEmail.getText().toString().trim();
                String password = fPassword.getText().toString().trim();

                if(email.isEmpty()) {
                    fEmail.setError("Email is Required");
                    return;
                }

                if(password.isEmpty()) {
                    fPassword.setError("Password is Required");
                    return;
                }

                if(password.length() < 6) {
                    fPassword.setError("Password Must be Longer Than 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user in firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ShelterPov.class));
                        }else {
                            Toast.makeText(Register.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
             }
        });
    }
}

/**
 * @author Group OP5 (Shreya Gouda, Cara Murphy, Sahej Singh)
 * Adds the ability to register a new account using email, password, phone number, and address and
 * store that information in firestore and firebase authentication.
 * Adds functionality to activity_register.xml
 */
package com.example.afgapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    //Variable declarations
    public static final String TAG = "TAG";
    EditText fName, fEmail, fPassword, fPhoneNum, fAddress, fCity, fState, fZip;
    Button fRegisterBtn;
    TextView fLoginBtn;
    Button backBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Variable instantiations
        fName = findViewById(R.id.enterFacName);
        fEmail = findViewById(R.id.enterEmail);
        fPassword = findViewById(R.id.enterPassword2);
        fPhoneNum = findViewById(R.id.enterPhone);
        fAddress = findViewById(R.id.enterAddressLine1);
        fCity = findViewById(R.id.enterCity);
        fState = findViewById(R.id.enterState);
        fZip = findViewById(R.id.enterZip);
        fRegisterBtn = findViewById(R.id.registerBtn);
        fLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        backBtn = findViewById(R.id.backBtn);

        //If user already logged in, just send to shelter pov
        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ShelterPov.class));
            finish();
        }
        //Sends user back to main activity
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        //Sends entered data to firestore and firebase authentication for storing for future
        //login and searching usage.
        fRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = fEmail.getText().toString().trim();
                String password = fPassword.getText().toString().trim();
                String phone = fPhoneNum.getText().toString().trim();
                String facName = fName.getText().toString().trim();
                String address1 = fAddress.getText().toString().trim();
                String city = fCity.getText().toString().trim();
                String state = fState.getText().toString().trim();
                String zip = fZip.getText().toString().trim();

                if(email.isEmpty()) {
                    fEmail.setError("Email is Required");
                    return;
                }

                if(phone.isEmpty()) {
                    fPhoneNum.setError("Phone Number is Required");
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
                            //Verification to email
                            FirebaseUser fUser = fAuth.getCurrentUser();
                            fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Verification link has been sent to " + email, Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                                }
                            });
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName", facName);
                            user.put("email", email);
                            user.put("phone", phone);
                            user.put("address1", address1);
                            user.put("city", city);
                            user.put("state", state);
                            user.put("zipcode", zip);
                            user.put("desc", "");
                            user.put("userID", userID);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile created for: " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure : " + e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), ShelterPov.class));
                        }else {
                            Toast.makeText(Register.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
             }
        });

        //Sends user to login if textView is clicked
        fLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}
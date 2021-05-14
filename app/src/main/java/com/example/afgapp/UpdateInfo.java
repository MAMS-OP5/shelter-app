package com.example.afgapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class UpdateInfo extends AppCompatActivity {
    private static final String TAG = "TAG";
    Button backBtn, resetPassLocal, updateInfoBtn;
    EditText fName, fEmail, fDesc, fPhoneNum, fAddress, fCity, fState, fZip;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        Intent data = getIntent();
        String facName = data.getStringExtra("facName");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");
        String address1 = data.getStringExtra("address1");
        String city = data.getStringExtra("city");
        String state = data.getStringExtra("state");
        String zip = data.getStringExtra("zip");
        String desc = data.getStringExtra("desc");

        Log.d(TAG, "onCreate: " + facName + " " + email + " " + phone + " " + address1 + " " + city + " " + state + " " + zip + " " + desc);

        backBtn = findViewById(R.id.backBtn);
        resetPassLocal = findViewById(R.id.resetPassBtn);
        updateInfoBtn = findViewById(R.id.updateInfoBtn);
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();

        fName = findViewById(R.id.editFacName);
        fEmail = findViewById(R.id.editEmailAddress);
        fDesc = findViewById(R.id.facilityDesc);
        fPhoneNum = findViewById(R.id.editPhone);
        fAddress = findViewById(R.id.enterAddressLine1);
        fCity = findViewById(R.id.enterCity);
        fState = findViewById(R.id.enterState);
        fZip = findViewById(R.id.enterZip);

        //Set editable text in editTexts
        fName.setText(facName);
        fEmail.setText(email);
        fDesc.setText(desc);
        fPhoneNum.setText(phone);
        fAddress.setText(address1);
        fCity.setText(city);
        fState.setText(state);
        fZip.setText(zip);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShelterPov.class));
            }
        });

        updateInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fName.getText().toString().isEmpty() || fEmail.getText().toString().isEmpty() || fPhoneNum.getText().toString().isEmpty() || fAddress.getText().toString().isEmpty() || fCity.getText().toString().isEmpty() || fState.getText().toString().isEmpty() || fZip.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateInfo.this, "One or More Fields are Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = fEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email", email);
                        edited.put("fName", fName.getText().toString());
                        edited.put("phone", fPhoneNum.getText().toString());
                        edited.put("fDesc", fDesc.getText().toString());
                        edited.put("address1", fAddress.getText().toString());
                        edited.put("city", fCity.getText().toString());
                        edited.put("state", fState.getText().toString());
                        edited.put("zipcode", fZip.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UpdateInfo.this, "Successfully Changed Info", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), ShelterPov.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(UpdateInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(UpdateInfo.this, "Successfully Changed Email", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(UpdateInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        resetPassLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetPassword = new EditText(v.getContext());

                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter New Password");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email and send reset link
                        String newPassword = resetPassword.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateInfo.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateInfo.this, "Password Reset Failed: "  + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Close dialog

                    }
                });

                passwordResetDialog.create().show();
            }
        });
    }
}

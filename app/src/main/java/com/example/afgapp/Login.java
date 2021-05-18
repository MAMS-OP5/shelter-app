/**
 * @author Group OP5 (Shreya Gouda, Cara Murphy, Sahej Singh)
 * Adds the ability to login to a previously registered account by using an email and password
 * Adds functionality to activity_login.xml
 */
package com.example.afgapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    //Variable Declarations
    EditText fEmail, fPassword;
    Button fLoginButton;
    TextView fCreateButton, forgotPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Variable Instantiations
        fEmail = findViewById(R.id.enterEmail);
        fPassword = findViewById(R.id.enterPassword);
        fLoginButton = findViewById((R.id.loginBtn));
        fCreateButton = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        forgotPassword = findViewById(R.id.forgotPass);
        backBtn = findViewById(R.id.backBtn);


         //Allows user to login using firebase authentication after entering email and password
         //appropriate into fields
        fLoginButton.setOnClickListener(new View.OnClickListener() {
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

                //Authenticate User
                //Sends user to activity_shelter_pov.xml to be able to view and edit
                //their shelter info
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ShelterPov.class));
                        } else {
                            Toast.makeText(Login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        //Sends user back to Registration page (last page).
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
        //Sends user to registration activity after clicking on corresponding textView.
        fCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        //Enables user to reset their password if they forgot it through email.
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter Your Email to Receive a Password Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "A reset link has been sent to " + mail, Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error, Reset Link Not Sent: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

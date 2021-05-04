package com.example.afgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class ClaimFacility extends AppCompatActivity {

    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_facility);

        Button lookShelter = (Button) findViewById(R.id.submitButton);
        backBtn = findViewById(R.id.backBtn);

        lookShelter.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ClaimFacility.this, ShelterPov.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        Button amShelter = (Button) findViewById(R.id.haveFacilityButton);

        amShelter.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ClaimFacility.this, Results.class));
            }
        });
    }
}
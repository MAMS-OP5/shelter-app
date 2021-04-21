package com.example.afgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class viewShelterInfo extends AppCompatActivity {

    Button claimFacilityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shelter_info);

        claimFacilityBtn = findViewById(R.id.claimThisFacility);
        claimFacilityBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ClaimFacility.class);
            startActivity(intent);
        });
    }
}
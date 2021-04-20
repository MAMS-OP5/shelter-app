package com.example.afgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button LookForShelterButton = (Button) findViewById(R.id.LookForShelterButton);
        Button FacilityOwnerButton = (Button) findViewById(R.id.FacilityOwnerButton);

        LookForShelterButton.setOnClickListener(this);
        FacilityOwnerButton.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.LookForShelterButton:
                Toast.makeText(this, "Bringing you to the search page!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this,Search.class);
                startActivity(intent1);
                break;
            case R.id.FacilityOwnerButton:
                Toast.makeText(this, "Bringing you to the claim facility page!", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this,ClaimFacility.class);
                startActivity(intent2);
                break;
        }

    }
}


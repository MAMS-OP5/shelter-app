
package com.example.afgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override //onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button one, looking for a shelter, sends to search
        Button lookShelter = (Button) findViewById(R.id.lookForShelterButton);

        //Allows users to browse shelters by sending to search activity.
        lookShelter.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Search.class);
                startActivity(intent);
            }
        });

        //button two, shelter owner, sends to register
        Button haveShelter = (Button) findViewById(R.id.haveFacilityButton);

        //Allows users to register or login by sending them to the register class.
        haveShelter.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

    }
}


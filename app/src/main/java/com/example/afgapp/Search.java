package com.example.afgapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;

public class Search extends AppCompatActivity implements LocationListener {

    ArrayAdapter<String> arrayAdapter;
    LocationManager locationManager;
    FirebaseAuth fAuth;

    private static final String TAG = "SearchBoxChange";
    public static String searchCity=null;
    public static String showAll=null;
    FusedLocationProviderClient fusedLocationProviderClient;

    private Boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView lookShelter = findViewById(R.id.searchHeader);
        fAuth = FirebaseAuth.getInstance();

        EditText searchBox = findViewById(R.id.searchBox);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "Searchbox has changed to: " + s.toString());
                searchCity = s.toString();
            }
        });


        //Back Button
        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MainActivity.class);
                startActivity(intent);
            }
        });


                    //Search Button
                    Button search = (Button) findViewById(R.id.searchBtn);

                    search.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Search.this, Results.class);
                            intent.putExtra("cityName", searchCity);
                            startActivity(intent);


                        }
                    });




        //Get all facilities


        Button seeAllBtn = (Button) findViewById(R.id.seeAllBtn);


        seeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, Results.class);
                intent.putExtra("cityName", showAll);
                startActivity(intent);

            }

        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

}
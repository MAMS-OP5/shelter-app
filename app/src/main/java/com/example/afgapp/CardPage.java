package com.example.afgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class CardPage extends AppCompatActivity {

    Button backBtn;
    StorageReference storageReference;

    @Override //onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardpage);

        getIncomingIntent();

        backBtn = findViewById(R.id.backBtn);
        //Back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search.class)); //goes to Search class for now to help wth testing
            }
        });

    }

    //get data from teh recycler view card that was clicked and set it
    private void getIncomingIntent() {
        String shelterName = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");
        String desc = getIntent().getStringExtra("desc");


        setName(shelterName);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        setResources(desc);

    }

    //set name field from string from intent
    private void setName(String name) {
        TextView shelterName = findViewById(R.id.shelterName);
        shelterName.setText(name);
    }

    //set email field from string from intent
    private void setEmail(String email) {
        TextView emailDisplay = findViewById(R.id.shelterEmailDisplay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            emailDisplay.setText(Html.fromHtml("<b>Email:</b><br><p>"+email+"</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            emailDisplay.setText(Html.fromHtml("<b>Email:</b><br><p>"+email+"</p>"));
        }
    }

    //set phone field from string from intent
    private void setPhone(String phone) {
        TextView phoneDisplay = findViewById(R.id.shelterPhoneDisplay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            phoneDisplay.setText(Html.fromHtml("<b>Phone:</b><br><p>"+phone+"</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            phoneDisplay.setText(Html.fromHtml("<b>Phone::</b><br><p>"+phone+"</p>"));
        }
    }

    //set address field from string from intent
    private void setAddress(String address) {
        TextView addressDisplay = findViewById(R.id.shelterAddressDisplay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addressDisplay.setText(Html.fromHtml("<b>Address:</b><br><p>"+address+"</p><br>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            addressDisplay.setText(Html.fromHtml("<b>Address:</b><br><p>"+address+"</p><br>"));
        }
    }

    //set resources field from string from intent
    private void setResources(String desc) {
        TextView resourcesDisplay = findViewById(R.id.shelterResourcesDisplay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resourcesDisplay.setText(Html.fromHtml("<b>Facility Resources:</b><br><p>"+desc+"</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            resourcesDisplay.setText(Html.fromHtml("<b>Facility Resources:</b><br><p>"+desc+"</p>"));
        }

    }

}



package com.example.afgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    @Override
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

    private void getIncomingIntent() {
        String shelterName = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");
        String desc = getIntent().getStringExtra("desc");
        String userID = getIntent().getStringExtra("userID");


        setName(shelterName);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        setResources(desc);
        //setImage(userID);

    }

    private void setName(String name) {
        TextView shelterName = findViewById(R.id.shelterName);
        shelterName.setText(name);
    }

    private void setEmail(String email) {
        TextView emailDisplay = findViewById(R.id.shelterEmailDisplay);
        emailDisplay.setText(email);
    }

    private void setPhone(String phone) {
        TextView phoneDisplay = findViewById(R.id.shelterPhoneDisplay);
        phoneDisplay.setText(phone);
    }

    private void setAddress(String address) {
        TextView addressDisplay = findViewById(R.id.shelterAddressDisplay);
        addressDisplay.setText(address);
    }

    private void setResources(String desc) {
        TextView resourcesDisplay = findViewById(R.id.shelterResourcesDisplay);
        resourcesDisplay.setText("Facility Resources: " + desc);
    }

   /* private void setImage(String userID){
        storageReference = FirebaseStorage.getInstance().getReference();
        ImageView image = findViewById(R.id.imageCard);
        StorageReference ref = storageReference.child("users/"+userID);
        //gs://afg-op5.appspot.com/users/9aRWm4zOjyPOlGRXey83bdGs8jH2/shelter.jpg
        Task<Uri> uri = ref.getDownloadUrl();
        uri.addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
String url=uri.toString();
                Picasso.get().load(url).into(image);
            }*/

}



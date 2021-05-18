package com.example.afgapp;

import android.net.Uri;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class Card {
    // Variable to store data corresponding
    // to fName keyword in database
    private String fName;

    // Variable to store data corresponding
    // to email keyword in database
    private String address1;

    // Variable to store data corresponding
    // to phone keyword in database
    private String phone;

    // Variable to store data corresponding
    // to email keyword in database
    private String email;

    // Variable to store data corresponding
    // to zipcode keyword in database
    private String zipcode;

    // Variable to store data corresponding
    // to city keyword in database
    private String city;

    // Variable to store data corresponding
    // to state keyword in database
    private String state;

    // Variables to store data corresponding
    // to description keywords in database
    private String desc;

    private String fDesc;


    // Mandatory empty constructor for use of FirebaseUI
    public Card() {
    }

    //For personal use
    private Card(String fName, String address1, String phone, String email, String zipcode, String city, String state, String desc, String fDesc) {
        this.fName = fName;
        this.address1 = address1;
        this.phone = phone;
        this.email = email;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.desc=desc;
        this.fDesc=fDesc;
    }


    // Getter and setter methods
    public String getfName() {
        return fName;
    }

    public void setfName(String shelterName) {
        this.fName = shelterName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipcode;
    }

    public void setZipCode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getfDesc() {
        return fDesc;
    }

    public void setfDesc(String fDesc) {
        this.fDesc = fDesc;
    }

    //Testing code for inputting uploaded images
   /*private URI getURI(String userID){
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference ref = storageReference.child("users/"+userID+"shelter.jpg");

        


       UploadTask uploadTask = storageReference.putBytes(mUploadBytes);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(), "Filed", Toast.LENGTH_SHORT).show();

                Uri firebaseUri = taskSnapshot.getDownloadUrl();

                Log.d(TAG, "onSuccess: firebase download url: " + firebaseUri.toString());

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        Task<Uri> uri = ref.getDownloadUrl();
     return uri;
    }*/

}



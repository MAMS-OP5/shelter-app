package com.example.afgapp;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

    private Uri imgUri;

    StorageReference storageReference;


    // Mandatory empty constructor for use of FirebaseUI
    public Card() {
    }

    //For personal use
    private Card(String fName, String address1, String phone, String email, String zipcode, String city, String state, String desc, String fDesc, Uri imgUri) {
        this.fName = fName;
        this.address1 = address1;
        this.phone = phone;
        this.email = email;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.desc=desc;
        this.fDesc=fDesc;
        this.imgUri=imgUri;
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

    public Uri getImgUri(){
        return imgUri;
    }
    public void setImgUri(Uri imgUri){
        this.imgUri=imgUri;
         /*storageReference = FirebaseStorage.getInstance().getReference();
        this.imgUri = storageReference.child("users/shelter.jpg").getPath();*/
    }

}



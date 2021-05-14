package com.example.afgapp;

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

    private String state;


    // Mandatory empty constructor for use of FirebaseUI
    public Card() {
    }

    //For personal use
    private Card(String fName, String address1, String phone, String email, String zipcode, String city) {
        this.fName = fName;
        this.address1 = address1;
        this.phone = phone;
        this.email = email;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
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
}



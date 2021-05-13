package com.example.afgapp;

public class Card {
        // Variable to store data corresponding
        // to fName keyword in database
        private String fName;

        // Variable to store data corresponding
        // to email keyword in database
        private String AddressLine1;

        // Variable to store data corresponding
        // to phone keyword in database
        private String phone;

        // Mandatory empty constructor for use of FirebaseUI
        public Card() {}

        //For personal use
        private Card(String fName, String email, String phone){
            this.fName=fName;
            this.AddressLine1=AddressLine1;
            this.phone=phone;
        }


        // Getter and setter methods
        public String getfName()
        {
            return fName;
        }
        public void setfName(String shelterName)
        {
            this.fName = shelterName;
        }
        public String getAddress()
        {
            return AddressLine1;
        }
        public void setAddress(String email)
        {
            this.AddressLine1 = AddressLine1;
        }
        public String getPhone()
        {
            return phone;
        }
        public void setPhone(String phone)
        {
            this.phone = phone;
        }
    }



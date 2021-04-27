package com.example.afgapp;

public class Card {
        // Variable to store data corresponding
        // to name keyword in database
        private String shelterNameCard;

        // Variable to store data corresponding
        // to email keyword in database
        private String emailCard;

        // Variable to store data corresponding
        // to phone keyword in database
        private String phoneCard;

        // Mandatory empty constructor
        // for use of FirebaseUI
        public void card() {}

        // Getter and setter method
        public String getShelterName()
        {
            return shelterNameCard;
        }
        public void setShelterName(String shelterName)
        {
            this.shelterNameCard = shelterName;
        }
        public String getEmail()
        {
            return emailCard;
        }
        public void setEmail(String email)
        {
            this.emailCard = email;
        }
        public String getPhone()
        {
            return phoneCard;
        }
        public void setPhone(String phone)
        {
            this.phoneCard = phone;
        }
    }



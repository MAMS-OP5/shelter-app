package com.example.afgapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Results extends AppCompatActivity {

    private RecyclerView resultsView;
    private FirestoreRecyclerAdapter adapter;
    private FirebaseFirestore fStore;
    Button backBtn;
    String searchCity;
    Query query;
    TextView resultsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        fStore = FirebaseFirestore.getInstance();
        resultsView = findViewById(R.id.recyclerView);
        backBtn = findViewById(R.id.backBtn);
        resultsInfo=findViewById(R.id.resultsInfo);


        //Get the city that the person entered in the search bar
        Intent intent=getIntent();
        searchCity = intent.getExtras().getString("cityName");

        if(searchCity!=null) {
            System.out.println(searchCity);
            searchCity=Capitalization(searchCity);
        }
        System.out.println(searchCity);

        //Update ResultsInfo to say what is being displayed
        if(searchCity==null){
            resultsInfo.setText("Displaying all facilities");
        }
        else{
            resultsInfo.setText("Displaying facilities in "+searchCity+".");
        }

        //Set query to display the correct results for the matching city.
        if(searchCity==null){
            query = fStore.collection("users");
        }else{
            query = fStore.collection("users")
                    .whereEqualTo(
                            "city",
                            searchCity);
        }

        //Recycler Options
        FirestoreRecyclerOptions<Card> options = new FirestoreRecyclerOptions.Builder<Card>().setQuery(query, Card.class).build();

        adapter = new FirestoreRecyclerAdapter<Card, CardViewHolder>(options) {
            @NonNull
            @Override
            public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
                return new CardViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull CardViewHolder holder, int i, @NonNull Card card) {
                holder.fNameCard.setText(card.getfName());
                holder.address1Card.setText(card.getAddress1());
                holder.emailCard.setText(card.getEmail());
                holder.phoneCard.setText(card.getPhone());
                holder.descCard.setText(card.getDesc());
                holder.userIDCard.setText(card.getuserID());
               // holder.imageCard.setImageURI(card.getURI());

            }
        };

        resultsView.setHasFixedSize(true);
        resultsView.setLayoutManager(new LinearLayoutManager(this));
        resultsView.setAdapter(adapter);

        //Back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });

    }

    private class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView fNameCard;
        private TextView address1Card;
        private TextView emailCard;
        private TextView phoneCard;
        private TextView descCard;
        private TextView userIDCard;
        private ImageView imageCard;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            fNameCard = itemView.findViewById(R.id.fNameCard);
            address1Card = itemView.findViewById(R.id.address1Card);
            emailCard = itemView.findViewById(R.id.emailCard);
            phoneCard = itemView.findViewById(R.id.phoneCard);
            descCard=itemView.findViewById(R.id.descCard);
            userIDCard=itemView.findViewById(R.id.userIDCard);
            imageCard=itemView.findViewById(R.id.imageCard);

            //if a card is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(Results.this, CardPage.class);
                intent.putExtra("name",fNameCard.getText());
                intent.putExtra("email",emailCard.getText());
                intent.putExtra("phone",phoneCard.getText());
                intent.putExtra("address",address1Card.getText());
                intent.putExtra("desc",descCard.getText());
                intent.putExtra("userID",userIDCard.getText());

                startActivity(intent);
                }
            });
        }
    }


    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static String Capitalization(String searchCity){
        String words[]=searchCity.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }
}




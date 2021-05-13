package com.example.afgapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class Results extends AppCompatActivity {

    private RecyclerView resultsView;
    private FirestoreRecyclerAdapter adapter;
    private FirebaseFirestore fStore;
    Button backBtn;
    String searchKeyword;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        fStore = FirebaseFirestore.getInstance();
        resultsView = findViewById(R.id.recyclerView);
        backBtn = findViewById(R.id.backBtn);

        // Create a instance of the database and get its reference
        //  ref = FirebaseDatabase.getInstance().getReference();

        //Get the city that the person entered in the search bar

        Intent intent = getIntent();
        if(intent.getStringArrayExtra("keyword")==null){
            query = fStore.collection("users");
        }
        else {
            searchKeyword = intent.getStringArrayExtra("keyword").toString();
            // Query
            query = fStore.collection("users")
                    .whereEqualTo("city", searchKeyword.toString());
            // add .orderBy?
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
                holder.addressCard.setText(card.getAddress());
                holder.phoneCard.setText(card.getPhone());
            }
        };

        resultsView.setHasFixedSize(true);
        resultsView.setLayoutManager(new LinearLayoutManager(this));
        resultsView.setAdapter(adapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });

    }

    private class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView fNameCard;
        private TextView addressCard;
        private TextView phoneCard;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            fNameCard = itemView.findViewById(R.id.fNameCard);
            addressCard = itemView.findViewById(R.id.addressCard);
            phoneCard = itemView.findViewById(R.id.phoneCard);
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
}




package com.example.afgapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;


public class Results extends AppCompatActivity {
    TextView name, email, phone, resources, address;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    private RecyclerView recyclerView;
    CardAdapter adapter;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Create a instance of the database and get
        // its reference
        ref= FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);




        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Card> options = new FirebaseRecyclerOptions.Builder<Card>().setQuery(ref, Card.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new CardAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button back = (Button) findViewById(R.id.backBtn);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Results.this, Search.class);
                startActivity(intent);
            }
        });
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}

       /* phone = findViewById(R.id.shelterPhoneDisplay);
        name = findViewById(R.id.shelterName);
        email = findViewById(R.id.shelterEmailDisplay);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        ref= FirebaseDatabase.getInstance().getReference().child("users").child("1");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String shelterName=dataSnapshot.child("fName").getValue().toString();
                name.setText(shelterName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}*/





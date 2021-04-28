package com.example.afgapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CardAdapter extends FirebaseRecyclerAdapter<Card, CardAdapter.CardViewholder>{
    // FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View


        public CardAdapter(
                @NonNull FirebaseRecyclerOptions<Card> options)
        {
            super(options);
        }

        // Function to bind the view in Card view(here
        // "card.xml") iwth data in
        // model class(here "Card.class")
        @Override
        protected void
        onBindViewHolder(@NonNull CardViewholder holder, int position, @NonNull Card model)
        {

            // Add shelterNameCard from model class (here
            // "card.class")to appropriate view in Card
            // view (here "card.xml")
            holder.shelterNameCard.setText(model.getShelterName());

            // Add emailCard from model class (here
            // "person.class")to appropriate view in Card
            // view (here "card.xml")
            holder.emailCard.setText(model.getEmail());

            // Add phoneCard from model class (here
            // "card.class")to appropriate view in Card
            // view (here "card.xml")
            holder.phoneCard.setText(model.getPhone());
        }

        // Function to tell the class about the Card view (here
        // "card.xml")in
        // which the data will be shown
        @NonNull
        @Override
        public CardViewholder
        onCreateViewHolder(@NonNull ViewGroup parent,
                           int viewType)
        {
            View view
                    = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card, parent, false);
            return new CardAdapter.CardViewholder(view);
        }

    // Sub Class to create references of the views in Crad
        // view (here "card.xml")
        class CardViewholder
                extends RecyclerView.ViewHolder {
            TextView shelterNameCard, emailCard, phoneCard;
            public CardViewholder(@NonNull View itemView)
            {
                super(itemView);

                shelterNameCard
                        = itemView.findViewById(R.id.shelterNameCard);
                emailCard = itemView.findViewById(R.id.emailCard);
                phoneCard = itemView.findViewById(R.id.phoneCard);
            }
        }
    }



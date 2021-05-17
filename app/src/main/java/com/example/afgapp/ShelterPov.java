package com.example.afgapp;

    import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import android.app.Activity;
import android.content.Intent;
    import android.net.Uri;
import android.os.Bundle;
//import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
 import com.squareup.picasso.Picasso;

public class ShelterPov extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView name, email, phone, desc, address, verifyMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button resendCode, updateInfo, browse;
    FloatingActionButton changeImg;
    ImageView shelterImg;
    //StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_pov);
        phone = findViewById(R.id.shelterPhoneDisplay);
        name = findViewById(R.id.shelterName);
        email = findViewById(R.id.shelterEmailDisplay);
        address = findViewById(R.id.shelterAddressDisplay);
        final String[] address1 = {""};
        final String[] city = {""};
        final String[] state = {""};
        final String[] zip = {""};

        desc = findViewById(R.id.shelterResourcesDisplay);

        fAuth = FirebaseAuth.getInstance();

        /*fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/shelter.jpg");
       profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(shelterImg);
            }
        });*/

        resendCode = findViewById(R.id.resendVerifyLink);
        verifyMsg = findViewById(R.id.verifyReminder);

        updateInfo = findViewById(R.id.updateInfoButton);
        browse = findViewById(R.id.shelterSideBrowse);

        shelterImg = findViewById(R.id.shelterImage);
        changeImg = findViewById(R.id.changePhotoBtn);

        userId = fAuth.getCurrentUser().getUid();
        final FirebaseUser user = fAuth.getCurrentUser();

        if(desc.getText().toString().isEmpty()) {
            desc.setVisibility(View.GONE);
        }

        if(!user.isEmailVerified()) {
            resendCode.setVisibility(View.VISIBLE);
            verifyMsg.setVisibility(View.VISIBLE);

            resendCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            resendCode.setVisibility(View.GONE);
                            verifyMsg.setVisibility(View.GONE);
                            Toast.makeText(v.getContext(), "Verification link has been sent to " + email.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Email not sent" + e.getMessage());
                        }
                    });
                }
            });
        }
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                name.setText(documentSnapshot.getString("fName"));
                phone.setText(documentSnapshot.getString("phone"));
                email.setText(documentSnapshot.getString("email"));
                address1[0] = documentSnapshot.getString("address1");
                city[0] = documentSnapshot.getString("city");
                state[0] = documentSnapshot.getString("state");
                zip[0] = documentSnapshot.getString("zipcode");
                address.setText(address1[0] + ", " + city[0] + ", " + state[0] + ", " + zip[0]);
                desc.setText(documentSnapshot.getString("desc"));
            }
        });
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpdateInfo.class);
                i.putExtra("facName", name.getText().toString());
                i.putExtra("email", email.getText().toString());
                i.putExtra("phone", phone.getText().toString());
                i.putExtra("address1", address1[0]);
                i.putExtra("city", city[0]);
                i.putExtra("state", state[0]);
                i.putExtra("zip", zip[0]);
                i.putExtra("desc", desc.getText().toString());
                startActivity(i);
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });


        /*changeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });*/
    }


    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000) {
            if(resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();

                shelterImg.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        //upload image to firebase storage
        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/shelter.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        final ImageView.ScaleType CENTER_INSIDE;
                       Picasso.get().load(uri).placeholder(R.drawable.ic_launcher_foreground).into(shelterImg);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ShelterPov.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
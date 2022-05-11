package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Place;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.R;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.databinding.ActivityOtherEventBinding;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.UUID;

public class OtherEventActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityOtherEventBinding binding;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        spinner = binding.spinner4;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Place.placeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void publishEvent(View view) {

        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String title = binding.eventTitleText.getText().toString();
                int quota = Integer.parseInt(binding.quotaNumberText.getText().toString());
                String id = UUID.randomUUID().toString();
                String userId = auth.getCurrentUser().getUid();

                User user = documentSnapshot.toObject(User.class);
                Event event = new Event(title,user,quota,"Tutoring",null);
                //sets interest
                String interestString = "";
                event.setInterest(interestString);
                event.setUuid(userId + id);
                //sets description and other stuffs
                event.setDescription(binding.eventDescriptionText.getText().toString());
                event.setEventDocumentPlace(userId + id);
                event.setHost(user);

                /*firebaseFirestore.collection("UserData").document(userId).collection("Events").document(userId + id).set(event).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });*/
                //adds the event to users createdEvents
                //firebaseFirestore.collection("UserData").document(userId).update("createdEvents", FieldValue.arrayUnion(event));
                //adds the event to placeData and gets event class to add to the event
                //firebaseFirestore.collection("PlaceData").document("Odeon").update("upcomingEvents", FieldValue.arrayUnion(event));

                firebaseFirestore.collection("EventData").document(userId + id).set(event);

                firebaseFirestore.collection("PlaceData").document("Odeon").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Place place = documentSnapshot.toObject(Place.class);
                        event.setEventPlace(place);
                        //sets the event for the page
                        firebaseFirestore.collection("EventData").document(userId + id).update("eventPlace", place);
                        //adds the event to users createdEvents
                        firebaseFirestore.collection("UserData").document(userId).update("createdEvents", FieldValue.arrayUnion(event));
                        //adds the event to places
                        firebaseFirestore.collection("PlaceData").document("Odeon").update("upcomingEvents", FieldValue.arrayUnion(event));
                    }
                });

                Toast.makeText(OtherEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OtherEventActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(e.getLocalizedMessage());
                Toast.makeText(OtherEventActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
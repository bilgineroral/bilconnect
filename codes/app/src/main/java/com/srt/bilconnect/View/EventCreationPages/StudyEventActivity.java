package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.UUID;

public class StudyEventActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityStudyEventBinding binding;

    int selectedInterest;
    boolean[] selected;

    String selectedPlace;

    Button tutoringButton;
    Button gettingTutoredButton;
    Button talkingButton;
    Button quietButton;
    Button selectedButton;
    Button[] buttons;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudyEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        selectedPlace = "";
        selectedInterest = -1;

        buttons = new Button[4];
        buttons[0] = talkingButton = binding.talkingButton; buttons[1] = gettingTutoredButton = binding.gettingTutoredButton;
        buttons[2] = quietButton = binding.quietButton; buttons[3] = tutoringButton = binding.tutoringButton;

        selected = new boolean[this.buttons.length];

        setContentView(view);

        spinner = binding.spinner3;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Place.placeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPlace = Place.placeNames[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                if(selectedInterest == 0) { interestString = "Tutoring"; }
                else if(selectedInterest == 1) { interestString = "Getting Tutored"; }
                else if(selectedInterest == 2) { interestString = "Studying With Talking"; }
                else if(selectedInterest == 3) { interestString = "Quiet Studying"; }
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

                Toast.makeText(StudyEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudyEventActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(e.getLocalizedMessage());
                Toast.makeText(StudyEventActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void selected(View view) {
        selectedButton = findViewById(view.getId());
        int i = 0;

        for (i = 0; i < buttons.length; i++) {
            if (selectedButton == buttons[i]) break;
        }

        if (selected[i]) {
            selectedButton.setBackgroundColor(Color.argb(100,244,67,54));
            selected[i] = false;
            selectedInterest = -1;
        }
        else {
            for (int j = 0; j < buttons.length; j++) {
                if (j == i) continue;
                buttons[j].setBackgroundColor(Color.argb(100,244,67,54));
                selected[j] = false;
            }
            selectedButton.setBackgroundColor(Color.parseColor("#ffe39994"));
            selected[i] = true;
            selectedInterest = i;
        }

    }
}
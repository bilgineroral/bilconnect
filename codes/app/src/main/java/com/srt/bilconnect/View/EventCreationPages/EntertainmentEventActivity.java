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
import com.google.firebase.firestore.FirebaseFirestore;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Place;
import com.srt.bilconnect.View.Fragments.MapFragment;
import com.srt.bilconnect.databinding.ActivityEntertainmentEventBinding;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.ArrayList;
import java.util.UUID;

public class EntertainmentEventActivity extends AppCompatActivity {

    int selectedInterest;
    boolean[] selected;
    Button chitchatButton;
    Button eatingButton;
    Button coffeeButton;
    Button partyingButton;
    Button concertButton;
    ActivityEntertainmentEventBinding binding;
    Button selectedButton;
    Button[] buttons;
    MapFragment mapFragment;
    Spinner spinner;
    String selectedPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntertainmentEventBinding.inflate(getLayoutInflater());

        selectedInterest = -1;
        selectedPlace = "";

        chitchatButton = binding.chitButton;
        eatingButton = binding.eatingButton;
        coffeeButton = binding.coffeeButton;
        partyingButton = binding.partyingButton;
        concertButton = binding.concertButton;
        buttons = new Button[5];

        buttons[0] = chitchatButton; buttons[1] = eatingButton;
        buttons[2] = coffeeButton; buttons[3] = partyingButton;
        buttons[4] = concertButton;
        selected = new boolean[this.buttons.length];

        spinner = binding.spinner2;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPlace = Place.placeNames[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Place.placeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        mapFragment = new MapFragment();

        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void selected(View view) {
        selectedButton = findViewById(view.getId());
        int i = 0;

        for (i = 0; i < buttons.length; i++) {
            if (selectedButton == buttons[i]) break;
        }

        if (selected[i]) {
            selectedButton.setBackgroundColor(Color.argb(100,103,58,183));
            selected[i] = false;
            selectedInterest = -1;
        }
        else {
            for (int j = 0; j < buttons.length; j++) {
                if (j == i) continue;
                buttons[j].setBackgroundColor(Color.argb(100,103,58,183));
                selected[j] = false;
            }
            selectedButton.setBackgroundColor(Color.parseColor("#ffb8a6da"));
            selected[i] = true;
            selectedInterest = i;
        }

    }

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

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
                event.setDescription(binding.eventDescriptionText.getText().toString());
                event.setEventDocumentPlace(userId + id);
                event.setHost(user);

                firebaseFirestore.collection("UserData").document(userId).collection("Events").document(userId + id).set(event).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

                firebaseFirestore.collection("EventData").document(userId + id).set(event);

                Toast.makeText(EntertainmentEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EntertainmentEventActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(e.getLocalizedMessage());
                Toast.makeText(EntertainmentEventActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

    String selectedPlace;
    Spinner spinner;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        selectedPlace = "";

        spinner = binding.spinner4;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Place.placeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPlace = Place.placeNames[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                event = new Event(title,user,quota,"Tutoring",null);
                //sets interest
                String interestString = "";
                event.setInterest(interestString);
                //sets description and other stuffs
                event.setDescription(binding.eventDescriptionText.getText().toString());
                event.setEventDocumentPlace(userId + id);
                event.setHost(user);
                //get event place
                firebaseFirestore.collection("PlaceData").document(selectedPlace).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            Place place = snapshot.toObject(Place.class);
                            event.setEventPlace(place);
                        }
                    }
                });


                firebaseFirestore.collection("EventData").document(userId + id).set(event);

                //adds the event to users createdEvents
                firebaseFirestore.collection("UserData").document(userId).update("createdEvents", FieldValue.arrayUnion(event));
                //adds the event to places
                firebaseFirestore.collection("PlaceData").document(selectedPlace).update("upcomingEvents", FieldValue.arrayUnion(event));

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
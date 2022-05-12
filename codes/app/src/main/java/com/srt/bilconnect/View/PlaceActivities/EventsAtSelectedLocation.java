package com.srt.bilconnect.View.PlaceActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Place;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.R;
import com.srt.bilconnect.databinding.ActivityEventsAtSelectedLocationBinding;

import java.util.ArrayList;

public class EventsAtSelectedLocation extends AppCompatActivity {


    ActivityEventsAtSelectedLocationBinding binding;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        binding = ActivityEventsAtSelectedLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String eventPlace = intent.getStringExtra("EventPlace");

        firebaseFirestore.collection("PlaceData").document(eventPlace).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                ArrayList<Event> eventsAtPlace = new ArrayList<>();
                if(task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Place place = documentSnapshot.toObject(Place.class);
                    eventsAtPlace = place.getUpcomingEvents();
                    EventAdapter eventAdapter = new EventAdapter(eventsAtPlace);
                    binding.recycler.setAdapter(eventAdapter);
                }
            }
        });

    }

}
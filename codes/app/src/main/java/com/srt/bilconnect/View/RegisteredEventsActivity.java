package com.srt.bilconnect.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Place;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.databinding.ActivityEventPageBinding;
import com.srt.bilconnect.databinding.ActivityEventsAtSelectedLocationBinding;
import com.srt.bilconnect.databinding.ActivityRegisteredEventsBinding;

import java.util.ArrayList;

public class RegisteredEventsActivity extends AppCompatActivity {

    ActivityRegisteredEventsBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        binding = ActivityRegisteredEventsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recylerEvents.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String eventPlace = intent.getStringExtra("EventPlace");

        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);

                ArrayList<Event> events = user.getRegisteredEvents();
                EventAdapter eventAdapter = new EventAdapter(events);
                binding.recylerEvents.setAdapter(eventAdapter);
            }
        });
    }

    public void onResume() {
        super.onResume();
        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);

                ArrayList<Event> events = user.getRegisteredEvents();
                EventAdapter eventAdapter = new EventAdapter(events);
                binding.recylerEvents.setAdapter(eventAdapter);
            }
        });
    }
}
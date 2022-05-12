package com.srt.bilconnect.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.squareup.picasso.Picasso;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.databinding.ActivityEventPageBinding;

import java.util.Calendar;

public class EventPageActivity extends AppCompatActivity {

    ActivityEventPageBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    private User user;
    private Event ourEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        Event event = (Event) intent.getSerializableExtra("event");
        ourEvent = event;
        binding.eventHeader.setText(event.getTitle());
        binding.usernameText.setText(event.getHost().getUsername());
        binding.quota.setText("" + event.getQuota());
        binding.textDate.setText(event.getDate());
        binding.textTime.setText(event.getTime());
        binding.placeText.setText(event.getEventPlace().getPlaceName());
        binding.details.setText(event.getDescription());
        Picasso.get().load(event.getHost().getProfilePhotoURL()).into(binding.imageView20);
    }

    public void goToUser(View view) {
        firebaseFirestore.collection("UserData").whereEqualTo("email", ourEvent.getHost().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        User user = documentSnapshot.toObject(User.class);
                        Intent intent = new Intent(EventPageActivity.this, OtherUserProfileActivity.class);
                        intent.putExtra("email", user.getEmail());

                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void registerToTheEvent(View view) {
        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                if(!user.getEmail().equals(ourEvent.getHost().getEmail())) {
                    firebaseFirestore.collection("EventData").document(ourEvent.getEventDocumentPlace()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Event event = documentSnapshot.toObject(Event.class);
                            if(event.getQuota() > event.getAttendees().size() && Calendar.getInstance().getTime().compareTo(event.getZaman()) < 0) {
                                firebaseFirestore.collection("EventData").document(event.getEventDocumentPlace()).update("attendees", FieldValue.arrayUnion(user));
                                firebaseFirestore.collection("EventData").document(event.getEventDocumentPlace()).update("quota", event.getQuota() - 1);
                                firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).update("registeredEvents", FieldValue.arrayUnion(event));
                                firebaseFirestore.collection("PlaceData").document(event.getEventPlace().getPlaceName()).update("attendees", FieldValue.arrayUnion(user));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(EventPageActivity.this, "Unable to Register Becuase You are the Event Host", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent = new Intent(EventPageActivity.this, MainPageActivity.class);
        startActivity(intent);
        finish();
    }
}
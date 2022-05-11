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
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.databinding.ActivityEventPageBinding;

public class EventPageActivity extends AppCompatActivity {

    ActivityEventPageBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
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
        // binding.placeText.setText(event.getEventPlace().getPlaceName());
        binding.details.setText(event.getDescription());
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
                User user = documentSnapshot.toObject(User.class);
                if(!user.getEmail().equals(ourEvent.getHost().getEmail())) {
                    user.registerToEvent(ourEvent);
                    ourEvent.registerUser(user);
                    ourEvent.setQuota(ourEvent.getQuota() - 1);
                    if(ourEvent.getQuota() > ourEvent.getAttendees().size() /*datele alakalı yeri ekle*/) {
                        firebaseFirestore.collection("EventData").document(ourEvent.getEventDocumentPlace()).update("attendees", FieldValue.arrayUnion(user));
                        firebaseFirestore.collection("EventData").document(ourEvent.getEventDocumentPlace()).update("quota", ourEvent.getQuota());
                        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).update("registeredEvents", FieldValue.arrayUnion(ourEvent));
                        firebaseFirestore.collection("PlaceData").document(ourEvent.getEventPlace().getPlaceName()).update("attendees", FieldValue.arrayUnion(user));
                    }
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
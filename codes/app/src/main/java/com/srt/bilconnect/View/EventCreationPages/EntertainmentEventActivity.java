package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.databinding.ActivityEntertainmentEventBinding;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.ArrayList;
import java.util.UUID;

public class EntertainmentEventActivity extends AppCompatActivity {

    boolean[] selected;
    Button chitchatButton;
    Button eatingButton;
    Button coffeeButton;
    Button partyingButton;
    Button concertButton;
    ActivityEntertainmentEventBinding binding;
    Button selectedButton;
    ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntertainmentEventBinding.inflate(getLayoutInflater());

        selected = new boolean[5];

        chitchatButton = binding.chitButton;
        eatingButton = binding.eatingButton;
        coffeeButton = binding.coffeeButton;
        partyingButton = binding.partyingButton;
        concertButton = binding.concertButton;
        buttons = new ArrayList<>();

        buttons.add(chitchatButton); buttons.add(eatingButton);
        buttons.add(coffeeButton); buttons.add(partyingButton);
        buttons.add(concertButton);

        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void selected(View view) {
        selectedButton = findViewById(view.getId());
        int i = 0;

        for (i = 0; i < buttons.size(); i++) {
            if (selectedButton == buttons.get(i)) break;
        }

        if (selected[i]) {
            selectedButton.setBackgroundColor(Color.argb(100,103,58,183));
            selected[i] = false;
        }
        else {
            for (int j = 0; j < buttons.size(); j++) {
                if (j == i) continue;
                buttons.get(j).setBackgroundColor(Color.argb(100,103,58,183));
                selected[j] = false;
            }
            selectedButton.setBackgroundColor(Color.parseColor("#ffb8a6da"));
            selected[i] = true;
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
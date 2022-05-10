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
import com.srt.bilconnect.R;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.databinding.ActivitySportsEventBinding;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.ArrayList;
import java.util.UUID;

public class SportsEventActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ActivitySportsEventBinding binding;

    Button football;
    Button basketball;
    Button volleyball;
    Button tennis;
    Button fitness;
    Button walking;
    Button swimming;
    Button tableTennis;
    Button american;
    ArrayList<Button> buttons;
    Button selectedButton;

    int selectedInterest;
    boolean[] selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySportsEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        buttons = new ArrayList<>();

        buttons.add(football = binding.footballButton);
        buttons.add(basketball = binding.basketballButton);
        buttons.add(volleyball = binding.volleyballButton);
        buttons.add(tennis = binding.tennisButton);
        buttons.add(fitness = binding.fitnessButton);
        buttons.add(walking = binding.walkingButton);
        buttons.add(swimming = binding.swimmingButton);
        buttons.add(tableTennis = binding.tableTennisButton);
        buttons.add(american = binding.americanButton);

        selected = new boolean[buttons.size()];
        selectedInterest = -1;

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
                Toast.makeText(SportsEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SportsEventActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(e.getLocalizedMessage());
                Toast.makeText(SportsEventActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void selected(View view) {
        selectedButton = findViewById(view.getId());
        int i = 0;

        for (i = 0; i < buttons.size(); i++) {
            if (selectedButton == buttons.get(i)) break;
        }

        if (selected[i]) {
            selectedButton.setBackgroundColor(Color.argb(100,76,175,80));
            selected[i] = false;
            selectedInterest = -1;
        }
        else {
            for (int j = 0; j < buttons.size(); j++) {
                if (j == i) continue;
                buttons.get(j).setBackgroundColor(Color.argb(100,76,175,80));
                selected[j] = false;
            }
            selectedButton.setBackgroundColor(Color.parseColor("#ff99cc9b"));
            selected[i] = true;
            selectedInterest = i;
        }

    }
}
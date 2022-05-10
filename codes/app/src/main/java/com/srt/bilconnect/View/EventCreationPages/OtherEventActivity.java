package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.srt.bilconnect.databinding.ActivityOtherEventBinding;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.UUID;

public class OtherEventActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityOtherEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
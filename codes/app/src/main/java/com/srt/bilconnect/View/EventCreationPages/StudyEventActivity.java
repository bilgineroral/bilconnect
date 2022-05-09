package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Interest;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.ArrayList;
import java.util.UUID;

public class StudyEventActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private ActivityStudyEventBinding binding;
    private ArrayList<Interest> interests;//can be implemented in an arraylist
    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudyEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void publishEvent(View view) {
        System.err.println("hello");
        String title = binding.eventTitleText.getText().toString();
        //get user and add this event to the user
        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                System.out.println(documentSnapshot);
                user = documentSnapshot.toObject(User.class);
            }
        });
        //
        //Toast.makeText(this, user.getUsername(), Toast.LENGTH_LONG).show();
        int quota = Integer.parseInt(binding.quotaNumberText.getText().toString());

        Event event = new Event(title,user,quota,null,null);
        String id = UUID.randomUUID().toString();
        //user.addCreatedEvent(event);
        //Toast.makeText(this, user.getUsername(), Toast.LENGTH_LONG).show();
        //firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).set(user);
        firebaseFirestore.collection("EventData").document(auth.getCurrentUser().getUid() + id).set(event).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(StudyEventActivity.this, "Event Created!", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                //startActivity(intent);
                //finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StudyEventActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
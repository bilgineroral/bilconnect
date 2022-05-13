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
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;
import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.R;
import com.srt.bilconnect.databinding.ActivityOtherEventBinding;
import com.srt.bilconnect.databinding.ActivityOtherUserProfileBinding;

public class OtherUserProfileActivity extends AppCompatActivity {

    ActivityOtherUserProfileBinding binding;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    String email;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(OtherUserProfileActivity.this));
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        firebaseFirestore.collection("UserData").whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                     user = new User();
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        user = documentSnapshot.toObject(User.class);
                    }
                    try {
                        binding.nickNameText3.setText(user.getUsername());
                        binding.deptText3.setText(user.getDepartment());
                        binding.bioText3.setText(user.getBio());
                        Picasso.get().load(user.getProfilePhotoURL()).into(binding.profilePicture4);
                        EventAdapter eventAdapter = new EventAdapter(user.getCreatedEvents());
                        binding.recyclerView.setAdapter(eventAdapter);

                    } catch (Exception e) {
                        Toast.makeText(OtherUserProfileActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void follow(View view) {
        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User usero = documentSnapshot.toObject(User.class);
                firebaseFirestore.collection("UserData").document(user.getUserID()).update("followers", FieldValue.arrayUnion(usero));
            }
        });
    }
}
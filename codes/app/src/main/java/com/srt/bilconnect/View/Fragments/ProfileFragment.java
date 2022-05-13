package com.srt.bilconnect.View.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.View.RegisteredEventsActivity;
import com.srt.bilconnect.View.EditProfileActivity;
import com.srt.bilconnect.databinding.FragmentProfileBinding;

import java.util.Map;

public class ProfileFragment extends Fragment {

    private ListView listView;
    private Button seeRegisteredEventsButton;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private User user;
    private FragmentProfileBinding binding;
    private Map data;
    ImageView imageView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //sets all the textviews and imageviews according to the firebase data
        getData();
        imageView = binding.imageView14;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }

        });
        //
        seeRegisteredEventsButton = binding.button2;

        seeRegisteredEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), RegisteredEventsActivity.class);
                startActivity(intent);
            }

        });

        firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    User user = task.getResult().toObject(User.class);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    EventAdapter adapter = new EventAdapter(user.getCreatedEvents());
                    binding.recyclerView.setAdapter(adapter);
                }
            }
        });


        return view;
    }

    private void getData() {
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                binding.nickNameText.setText(user.getUsername());
                binding.deptText.setText(user.getDepartment());
                binding.bioText.setText("Bio: " + user.getBio());
                try {
                    Picasso.get().load(user.getProfilePhotoURL()).into(binding.profilePicture);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}
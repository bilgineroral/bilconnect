package com.srt.bilconnect.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Place;
import com.srt.bilconnect.View.PlaceActivities.EventsAtSelectedLocation;
import com.srt.bilconnect.databinding.FragmentMapBinding;

import java.util.ArrayList;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private FirebaseFirestore firebaseFirestore;

    TextView textViewFirst;
    TextView textViewSecond;
    TextView textViewThird;
    TextView textViewFourth;
    TextView textViewFifth;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;

    public Place place1;
    public Place place2;
    public Place place3;
    public Place place4;
    public Place place5;
    public ArrayList<Place> places;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        firebaseFirestore = FirebaseFirestore.getInstance();
/*
        places = new ArrayList<>();
        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);
        places.add(place5);*/


        textViewFirst = binding.textFirst;
        textViewSecond = binding.textSecond;
        textViewThird = binding.textthird;
        textViewFourth = binding.textFourth;
        textViewFifth = binding.textFifth;

        imageView1 = binding.imageView10;
        imageView2 = binding.imageView15;
        imageView3 = binding.imageView16;
        imageView4 = binding.imageView18;
        imageView5 = binding.imageView17;

        firebaseFirestore.collection("PlaceData").document("Odeon").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place5 = documentSnapshot.toObject(Place.class);
                if(Integer.parseInt(textViewFifth.getText().toString()) != 0 || place5.getUpcomingEvents().size() != 0) {
                    imageView5.setVisibility(View.VISIBLE);
                    binding.textSecond.setText(Integer.toString(place5.getUpcomingEvents().size()));
                    binding.textSecond.setVisibility(View.VISIBLE);}
            }
        });

        firebaseFirestore.collection("PlaceData").document("Bilkent Center").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place5 = documentSnapshot.toObject(Place.class);
                if(Integer.parseInt(textViewFifth.getText().toString()) != 0 || place5.getUpcomingEvents().size() != 0) {
                    imageView5.setVisibility(View.VISIBLE);
                    binding.textSecond.setText(Integer.toString(place5.getUpcomingEvents().size()));
                    binding.textSecond.setVisibility(View.VISIBLE);}
            }
        });

        firebaseFirestore.collection("PlaceData").document("81").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place5 = documentSnapshot.toObject(Place.class);
                if(Integer.parseInt(textViewFifth.getText().toString()) != 0 || place5.getUpcomingEvents().size() != 0) {
                    imageView5.setVisibility(View.VISIBLE);
                    binding.textSecond.setText(Integer.toString(place5.getUpcomingEvents().size()));
                    binding.textSecond.setVisibility(View.VISIBLE);}
            }
        });

        firebaseFirestore.collection("PlaceData").document("Mayfest").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place5 = documentSnapshot.toObject(Place.class);
                if(Integer.parseInt(textViewFifth.getText().toString()) != 0 || place5.getUpcomingEvents().size() != 0) {
                    imageView5.setVisibility(View.VISIBLE);
                    binding.textSecond.setText(Integer.toString(place5.getUpcomingEvents().size()));
                    binding.textSecond.setVisibility(View.VISIBLE);}
            }
        });

        firebaseFirestore.collection("PlaceData").document("East Campus").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place5 = documentSnapshot.toObject(Place.class);
                if(Integer.parseInt(textViewFifth.getText().toString()) != 0 || place5.getUpcomingEvents().size() != 0) {
                    imageView5.setVisibility(View.VISIBLE);
                    binding.textSecond.setText(Integer.toString(place5.getUpcomingEvents().size()));
                    binding.textSecond.setVisibility(View.VISIBLE);}
            }
        });


        imageView1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setPlaces() {
        firebaseFirestore.collection("PlaceData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    int i = 0;
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        Place place;
                        place = documentSnapshot.toObject(Place.class);
                        places.set(i, place);
                        i++;
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        firebaseFirestore.collection("PlaceData").document("Odeon").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Place place;
                place = documentSnapshot.toObject(Place.class);
                places.set(4, place);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
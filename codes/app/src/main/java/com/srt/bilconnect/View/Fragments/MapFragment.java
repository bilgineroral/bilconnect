package com.srt.bilconnect.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srt.bilconnect.Model.Place;
import com.srt.bilconnect.View.EventsAtSelectedLocation;
import com.srt.bilconnect.databinding.FragmentMapBinding;

import java.util.ArrayList;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    public Place place;
    public ArrayList<Place> places;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        firebaseFirestore.collection("PlaceData").document("Odeon").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place = documentSnapshot.toObject(Place.class);

                binding.textOdeon.setText(Integer.toString(place.getUpcomingEvents().size()));

            }
        });

        firebaseFirestore.collection("PlaceData").document("Bilkent Center").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place = documentSnapshot.toObject(Place.class);

                binding.textBilkentCenter.setText(Integer.toString(place.getUpcomingEvents().size()));

            }
        });

        firebaseFirestore.collection("PlaceData").document("81").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place = documentSnapshot.toObject(Place.class);

                binding.textDorm81.setText(Integer.toString(place.getUpcomingEvents().size()));

            }
        });

        firebaseFirestore.collection("PlaceData").document("Mayfest").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place = documentSnapshot.toObject(Place.class);

                binding.textMayfest.setText(Integer.toString(place.getUpcomingEvents().size()));

            }
        });

        firebaseFirestore.collection("PlaceData").document("East Campus").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                place = documentSnapshot.toObject(Place.class);

                binding.textEastCampus.setText(Integer.toString(place.getUpcomingEvents().size()));

            }
        });


        binding.imageViewOdeon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                intent.putExtra("EventPlace", "Odeon");
                startActivity(intent);
            }
        });

        binding.imageViewMayfest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                intent.putExtra("EventPlace", "Mayfest");
                startActivity(intent);
            }
        });

        binding.imageViewDorm81.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                intent.putExtra("EventPlace", "81");
                startActivity(intent);
            }
        });

        binding.imageViewEastCampus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                intent.putExtra("EventPlace", "East Campus");
                startActivity(intent);
            }
        });

        binding.imageViewBilkentCenter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                intent.putExtra("EventPlace", "Bilkent Center");
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

   /* public void setPlaces() {
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

    }*/
}
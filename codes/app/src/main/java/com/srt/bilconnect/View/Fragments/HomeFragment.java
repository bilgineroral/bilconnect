package com.srt.bilconnect.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srt.bilconnect.Adapter.AdapterUsers;
import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.View.EventPageActivity;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.R;
import com.srt.bilconnect.View.FirstPageActivity;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.View.UsersFragment;
import com.srt.bilconnect.databinding.FragmentHomeBinding;
import com.srt.bilconnect.databinding.FragmentUsersBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Spinner spinner;
    String[] sortItems;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        sortItems = new String[2];
        sortItems[0] = "Sort by date.";
        sortItems[1] = "Sort lexicographically.";
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        binding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Event> testList = new ArrayList<>();
        //gets event data from database
        firebaseFirestore.collection("EventData").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        testList.add(documentSnapshot.toObject(Event.class));
                    }
                }
                EventAdapter eventAdapter = new EventAdapter(testList);
                binding.recyclerViewHome.setAdapter(eventAdapter);


            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
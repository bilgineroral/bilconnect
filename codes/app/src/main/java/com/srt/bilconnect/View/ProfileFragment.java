package com.srt.bilconnect.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.databinding.FragmentProfileBinding;

import java.util.stream.Collectors;

public class ProfileFragment extends Fragment {

    ListView listView;
    Button seeRegisteredEventsButton;
    User user;
    private FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        seeRegisteredEventsButton = binding.button2;

        seeRegisteredEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisteredEventsActivity.class);
                startActivity(intent);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventAdapter adapter = new EventAdapter(user.getPastEvents());
        binding.recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
package com.srt.bilconnect.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Spinner spinner;
    String[] sortItems;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        sortItems = new String[2];
        sortItems[0] = "Sort by date.";
        sortItems[1] = "Sort lexicographically.";
        /*
        //spinner = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, sortItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // BUGGED, TO BE FIXED
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: Toast.makeText(getActivity(), "test0", Toast.LENGTH_SHORT).show();
                    case 1: Toast.makeText(getActivity(), "test1", Toast.LENGTH_SHORT).show();
                    // kac tane secenek varsa artik
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Event> testList = new ArrayList<>();
        testList.add(new Event("mayfest buluşma", new User("denem123", "2412req","denem@gmail.com", "12323", "CS"), 6));
        testList.add(new Event("basketbol", new User("Eren", "2412req","denem@gmail.com", "12323", "CS"), 5));
        testList.add(new Event("kahve içicez", new User("Deniz", "2412req","denem@gmail.com", "12323", "CS"), 3));
        testList.add(new Event("futbol", new User("Ali", "2412req","denem@gmail.com", "12323", "CS"), 22));
        testList.add(new Event("ders", new User("Ayşe", "2412req","denem@gmail.com", "12323", "CS"), 9));
        testList.add(new Event("deneme", new User("Sinan", "2412req","denem@gmail.com", "12323", "CS"), 8));
        testList.add(new Event("eğlence", new User("Melis", "2412req","denem@gmail.com", "12323", "CS"), 6));
        testList.add(new Event("Tutoring", new User("Adem", "2412req","denem@gmail.com", "12323", "CS"), 5));
        EventAdapter eventAdapter = new EventAdapter(testList);
        binding.recyclerViewHome.setAdapter(eventAdapter);
*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.srt.bilconnect.View.PlaceActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.srt.bilconnect.Adapter.EventAdapter;
import com.srt.bilconnect.Adapter.MapEventAdapter;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.R;
import com.srt.bilconnect.databinding.ActivityEventsAtSelectedLocationBinding;

import java.util.ArrayList;

public class EventsAtSelectedLocation extends AppCompatActivity {


    ActivityEventsAtSelectedLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_at_selected_location);

        binding = ActivityEventsAtSelectedLocationBinding.inflate(getLayoutInflater());

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Event> testList1 = new ArrayList<>();
        testList1.add(new Event("event title test", new User("username test", "userID test", "email test",
                "bilkentID test", "department test"), 6, "details test"));
        testList1.add(new Event("event title test2", new User("username test2", "userID test2", "email test2",
                "bilkentID test2", "department test2"), 12, "details test2"));

        MapEventAdapter mapEventAdapter = new MapEventAdapter(testList1);
        binding.recycler.setAdapter(mapEventAdapter);
    }

}
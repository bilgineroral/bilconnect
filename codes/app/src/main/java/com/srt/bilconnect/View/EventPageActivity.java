package com.srt.bilconnect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.databinding.ActivityEventPageBinding;

public class EventPageActivity extends AppCompatActivity {

    ActivityEventPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Event event = (Event) intent.getSerializableExtra("event");
        binding.nameText.setText(event.eventName);
    }
}
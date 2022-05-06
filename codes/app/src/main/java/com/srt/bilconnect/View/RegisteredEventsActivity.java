package com.srt.bilconnect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.srt.bilconnect.databinding.ActivityRegisteredEventsBinding;

public class RegisteredEventsActivity extends AppCompatActivity {

    ActivityRegisteredEventsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisteredEventsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
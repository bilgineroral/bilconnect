package com.srt.bilconnect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.srt.bilconnect.databinding.ActivityEventCreationBinding;

public class EventCreationActivity extends AppCompatActivity {

    private ActivityEventCreationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventCreationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}
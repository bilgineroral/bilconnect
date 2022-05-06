package com.srt.bilconnect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.srt.bilconnect.View.EventCreationPages.EntertainmentEventActivity;
import com.srt.bilconnect.View.EventCreationPages.OtherEventActivity;
import com.srt.bilconnect.View.EventCreationPages.SportsEventActivity;
import com.srt.bilconnect.View.EventCreationPages.StudyEventActivity;
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

    public void sendToSports(View view) {
        Intent intent = new Intent(getApplicationContext(), SportsEventActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendToStudy(View view) {
        Intent intent = new Intent(getApplicationContext(), StudyEventActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendToEnter(View view) {
        Intent intent = new Intent(getApplicationContext(), EntertainmentEventActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendToOther(View view) {
        Intent intent = new Intent(getApplicationContext(), OtherEventActivity.class);
        startActivity(intent);
        finish();
    }
}
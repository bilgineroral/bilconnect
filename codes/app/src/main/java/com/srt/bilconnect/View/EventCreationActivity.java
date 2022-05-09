package com.srt.bilconnect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    public void sendToSports(View view){
        Intent intent = new Intent(EventCreationActivity.this, SportsEventActivity.class);
        startActivity(intent);
    }

    public void sendToStudy(View view){
        Intent intent = new Intent(EventCreationActivity.this, StudyEventActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void sendToEnter(View view){
        Intent intent = new Intent(EventCreationActivity.this, EntertainmentEventActivity.class);
        startActivity(intent);
    }

    public void sendToOther(View view){
        Intent intent = new Intent(EventCreationActivity.this, OtherEventActivity.class);
        startActivity(intent);
    }
}
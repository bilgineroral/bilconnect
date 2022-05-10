package com.srt.bilconnect.View.EventCreationPages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.srt.bilconnect.databinding.ActivityEntertainmentEventBinding;

import java.util.ArrayList;

public class EntertainmentEventActivity extends AppCompatActivity {

    boolean[] selected;
    Button chitchatButton;
    Button eatingButton;
    Button coffeeButton;
    Button partyingButton;
    Button concertButton;
    ActivityEntertainmentEventBinding binding;
    Button selectedButton;
    ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntertainmentEventBinding.inflate(getLayoutInflater());

        selected = new boolean[5];

        chitchatButton = binding.chitButton;
        eatingButton = binding.eatingButton;
        coffeeButton = binding.coffeeButton;
        partyingButton = binding.partyingButton;
        concertButton = binding.concertButton;
        buttons = new ArrayList<>();

        buttons.add(chitchatButton); buttons.add(eatingButton);
        buttons.add(coffeeButton); buttons.add(partyingButton);
        buttons.add(concertButton);

        setContentView(binding.getRoot());

    }

    public void selected(View view) {
        selectedButton = findViewById(view.getId());
        int i = 0;

        for (i = 0; i < buttons.size(); i++) {
            if (selectedButton == buttons.get(i)) break;
        }

        if (selected[i]) {
            selectedButton.setBackgroundColor(Color.argb(100,103,58,183));
            selected[i] = false;
        }
        else {
            for (int j = 0; j < buttons.size(); j++) {
                if (j == i) continue;
                buttons.get(j).setBackgroundColor(Color.argb(100,103,58,183));
                selected[j] = false;
            }
            selectedButton.setBackgroundColor(Color.parseColor("#ffb8a6da"));
            selected[i] = true;
        }

    }

}
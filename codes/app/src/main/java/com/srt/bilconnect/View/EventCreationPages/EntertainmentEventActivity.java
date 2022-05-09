package com.srt.bilconnect.View.EventCreationPages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.srt.bilconnect.R;
import com.srt.bilconnect.databinding.ActivityEntertainmentEventBinding;

import java.util.ArrayList;

import com.srt.bilconnect.R;

public class EntertainmentEventActivity extends AppCompatActivity {

    boolean oneInterestSelected;
    boolean[] isSelected;
    Button chitchatButton;
    Button eatingButton;
    Button coffeeButton;
    Button partyingButton;
    ActivityEntertainmentEventBinding binding;
    Button selectedButton;
    ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntertainmentEventBinding.inflate(getLayoutInflater());

        isSelected = new boolean[4];
        for (int i = 0; i < isSelected.length; i++) {
            isSelected[i] = false;
        }
        oneInterestSelected = false;
        chitchatButton = binding.chitButton;
        eatingButton = binding.eatingButton;
        coffeeButton = binding.coffeeButton;
        partyingButton = binding.partyingButton;
        selectedButton = null;
        buttons = new ArrayList<>();

        buttons.add(chitchatButton); buttons.add(eatingButton);
        buttons.add(coffeeButton); buttons.add(partyingButton);

        setContentView(binding.getRoot());

    }

    public void selected(View view) {
        selectedButton = findViewById(view.getId());
        int i = 0;

        while (buttons.get(i) != selectedButton) i++;

            if (isSelected[i] == true) { //seçtiğimi tekrar seçince
                selectedButton.setBackgroundColor(Color.argb(100,103,58,183));
                isSelected[i] = false;
            }
            else {
                for (int j = 0; j < buttons.size(); j++) {
                    if (j == i) continue;
                    buttons.get(j).setBackgroundColor(Color.argb(100, 76, 30, 159));
                }
                selectedButton.setBackgroundColor(Color.argb(100,103,58,183));
                isSelected[i] = true;
            }

    }

}
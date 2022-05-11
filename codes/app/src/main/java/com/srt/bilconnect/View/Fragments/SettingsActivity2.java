package com.srt.bilconnect.View.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.srt.bilconnect.EditInterests;
import com.srt.bilconnect.R;
import com.srt.bilconnect.View.AdditionalInfoActivity;
import com.srt.bilconnect.View.ChangePasswordActivity;
import com.srt.bilconnect.databinding.FragmentProfileBinding;


public class SettingsActivity2 extends AppCompatActivity {
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        textView = findViewById(R.id.textView5);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity2.this, ChangePasswordActivity.class);
                startActivity(intent);
            }

        });
    }
}
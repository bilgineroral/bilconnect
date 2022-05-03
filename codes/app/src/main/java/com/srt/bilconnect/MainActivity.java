package com.srt.bilconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.srt.bilconnect.databinding.ActivityMainBinding;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();


    }

    public void loginClicked(View view) {

        auth.signInWithEmailAndPassword(binding.emailText.getText().toString(), binding.passwordText.getText().toString());

    }

    public void signUpClicked(View view) {

        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void forgotPasswordClicked(View view) {

    }

}
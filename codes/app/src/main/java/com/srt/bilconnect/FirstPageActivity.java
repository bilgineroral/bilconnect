package com.srt.bilconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.srt.bilconnect.databinding.ActivityMainBinding;

public class FirstPageActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        if(user != null) {
            Intent intent = new Intent(FirstPageActivity.this,/* buraya main pagein adresini koy */ MainPageActivity.class);//ekle
            startActivity(intent);
            finish();
        }
    }

    public void loginClicked(View view) {
        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();

        if(email.equals("") || password.equals("")) {
            Toast.makeText(this, "You need to enter all of the information above!", Toast.LENGTH_LONG).show();
        }
        else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(FirstPageActivity.this, /* buraya anasayfanın clasını yaz oraya göndersin */ MainPageActivity.class);//ekle
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(FirstPageActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public void signUpClicked(View view) {

        Intent intent = new Intent(FirstPageActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void forgotPasswordClicked(View view) {
    //send to forgot password page
    }

}
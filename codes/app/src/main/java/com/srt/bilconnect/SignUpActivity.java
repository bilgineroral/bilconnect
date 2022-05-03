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
import com.srt.bilconnect.databinding.ActivityMainBinding;
import com.srt.bilconnect.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
    }

    public void signUpContrinueClicked(View view) {
        String username = binding.usernameText.getText().toString();
        String password = binding.passwordTextSignUp.getText().toString();
        String department = binding.departmentText.getText().toString();
        String bilkentId = binding.bilkentIdText.getText().toString();
        String email = binding.emailTextSignup.getText().toString();

        if(username.equals("") || password.equals("") || department.equals("") || bilkentId.equals("") || email.equals("")) {
            Toast.makeText(this, "You need to enter all of the information above!", Toast.LENGTH_LONG).show();
        }
        else{
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class/* nereye gideceğimizi yazacağız bittikten sonra koy!! */);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
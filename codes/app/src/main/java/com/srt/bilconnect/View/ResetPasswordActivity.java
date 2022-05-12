package com.srt.bilconnect.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.srt.bilconnect.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity {//bu kullanıcı hesabına girememişken şifresini değiştirmesini sağlayacak

    private ActivityResetPasswordBinding binding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
    }

    public void resetPassword(View view) {
        if(binding.resetEmailText.getText().toString().equals("")) {
            Toast.makeText(this, "Enter your email!", Toast.LENGTH_LONG).show();
        } else {
            auth.sendPasswordResetEmail(binding.resetEmailText.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ResetPasswordActivity.this, "Reset email successfully sent", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ResetPasswordActivity.this, FirstPageActivity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ResetPasswordActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.srt.bilconnect.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {//bu kullanıcı hesabına girmişken şifresini değiştirmesini sağlayacak

    private ActivityChangePasswordBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
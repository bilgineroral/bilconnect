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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.srt.bilconnect.databinding.ActivitySignUpBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void signUpContinueClicked(View view) {// konunma göre kaydolabilme özelliğini ekle
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

                    /*HashMap<String, Object> userData = new HashMap<>();
                    userData.put("Username", username);
                    userData.put("Department", department);
                    userData.put("Bilkent ID", bilkentId);
                    userData.put("E-Mail", email);*/

                    String userID = auth.getCurrentUser().getUid().toString();

                    ArrayList<String> questionsAnswered = new ArrayList<>();
                    questionsAnswered.add(binding.teacherNameText.getText().toString());
                    questionsAnswered.add(binding.favColorText.getText().toString());
                    questionsAnswered.add(binding.petNameText.getText().toString());

                    User newUser = new User(userID,email,bilkentId,questionsAnswered,department);

                    firebaseFirestore.collection("UserData").document(userID).set(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpActivity.this, "Account Created!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    //firebaseFirestore.collection("UserData" + "/" + userID).add(userData);

                    Intent intent = new Intent(SignUpActivity.this, AdditionalInfoActivity.class/* nereye gideceğimizi yazacağız bittikten sonra koy!! */);//ekle
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
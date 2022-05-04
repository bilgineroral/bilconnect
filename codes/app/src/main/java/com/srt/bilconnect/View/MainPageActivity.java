package com.srt.bilconnect.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.srt.bilconnect.R;
import com.srt.bilconnect.databinding.ActivityMainBinding;
import com.srt.bilconnect.databinding.ActivityMainPageBinding;

public class MainPageActivity extends AppCompatActivity {

    private ActivityMainPageBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.settings_menu) {
            //Settings menüsünü aç

        }
        else if(item.getItemId() == R.id.signout) {
            //Kullanının hesabını kapat
            auth.signOut();

            Intent intentToFirstPage = new Intent(MainPageActivity.this, FirstPageActivity.class);
            startActivity(intentToFirstPage);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
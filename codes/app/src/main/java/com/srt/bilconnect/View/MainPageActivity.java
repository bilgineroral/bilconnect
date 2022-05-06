package com.srt.bilconnect.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.srt.bilconnect.R;
import com.srt.bilconnect.databinding.ActivityMainPageBinding;


public class MainPageActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private ActivityMainPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_nav);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void addEvent(View view) {

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
            //Kullananın hesabını kapat
            auth.signOut();

            Intent intentToFirstPage = new Intent(MainPageActivity.this, FirstPageActivity.class);
            startActivity(intentToFirstPage);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
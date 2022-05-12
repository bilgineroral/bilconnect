package com.srt.bilconnect.View;

import android.app.Activity;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.srt.bilconnect.Model.User;
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
        Intent intent = new Intent(MainPageActivity.this, EventCreationActivity.class);
        startActivity(intent);
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
            Intent intentToSettings = new Intent(MainPageActivity.this, SettingsActivity.class);
            startActivity(intentToSettings);
        }
        else if(item.getItemId() == R.id.signout) {
            auth.signOut();

            Intent intentToFirstPage = new Intent(MainPageActivity.this, FirstPageActivity.class);
            startActivity(intentToFirstPage);
            finish();
        }

        if(item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(MainPageActivity.this, SearchPageActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
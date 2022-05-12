package com.srt.bilconnect.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srt.bilconnect.Adapter.AdapterUsers;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.R;

import com.srt.bilconnect.databinding.FragmentUsersBinding;

import java.util.ArrayList;


public class UsersFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterUsers adapterUsers;
    ArrayList<User> userList;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    String query;

    FragmentUsersBinding binding;

    public UsersFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(getLayoutInflater());


        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = binding.usersRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        userList = new ArrayList<>();

        getAllUsers();

        return binding.getRoot();
    }

    private void getAllUsers() {
        firebaseFirestore.collection("UserData").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot documentSnapshot : value) {
                    if (documentSnapshot.toString() != auth.getCurrentUser().getUid()) {
                        userList.add(documentSnapshot.toObject(User.class));
                    }
                    adapterUsers = new AdapterUsers(getActivity(), userList);

                    recyclerView.setAdapter(adapterUsers);
                }
            }
        });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstance);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu,menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        //search listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //called when user tries to search
                if(!TextUtils.isEmpty(s.trim()))
                {
                    searchUsers(s);
                }else{
                    getAllUsers();
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s.trim()))
                {
                    searchUsers(s);
                }else{
                    getAllUsers();
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    public void searchUsers(String s) {
        query = s;
        firebaseFirestore.collection("UserData").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot documentSnapshot : value) {
                    User user = documentSnapshot.toObject(User.class);
                    if (user.getUserID() != auth.getCurrentUser().getUid()) {

                        if(user.getUsername().toLowerCase().contains(query.toLowerCase())) {
                            userList.add(user);
                        }

                    }
                    adapterUsers = new AdapterUsers(getActivity(), userList);
                    adapterUsers.notifyDataSetChanged();
                    recyclerView.setAdapter(adapterUsers);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.settings_menu) {
            Intent intentToSettings = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intentToSettings);
        }
        else if(item.getItemId() == R.id.signout) {
            auth.signOut();

            Intent intentToFirstPage = new Intent(getActivity(), FirstPageActivity.class);
            startActivity(intentToFirstPage);
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
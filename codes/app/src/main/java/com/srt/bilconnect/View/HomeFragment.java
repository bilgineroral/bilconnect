package com.srt.bilconnect.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.srt.bilconnect.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    TextView homeView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeView = binding.homeTextViewTest;
        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whenClicked(view);
            }
        });
        View root = binding.getRoot();
        return root;
    }

    public void whenClicked(View view) {
        Toast.makeText(getActivity(), "Home Fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
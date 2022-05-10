package com.srt.bilconnect.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.srt.bilconnect.View.PlaceActivities.EventsAtSelectedLocation;
import com.srt.bilconnect.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;

    TextView textViewFirst;
    TextView textViewSecond;
    TextView textViewThird;
    TextView textViewFourth;
    TextView textViewFifth;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        textViewFirst = binding.textView29;
        textViewSecond = binding.textView6;
        textViewThird = binding.textView31;
        textViewFourth = binding.textView30;
        textViewFifth = binding.textView32;

        imageView1 = binding.imageView10;
        imageView2 = binding.imageView15;
        imageView3 = binding.imageView16;
        imageView4 = binding.imageView18;
        imageView5 = binding.imageView17;

        /*
        imageView1.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.INVISIBLE);
        imageView5.setVisibility(View.INVISIBLE);

        textViewFirst.setVisibility(View.INVISIBLE);
        textViewSecond.setVisibility(View.INVISIBLE);
        textViewThird.setVisibility(View.INVISIBLE);
        textViewFourth.setVisibility(View.INVISIBLE);
        textViewFifth.setVisibility(View.INVISIBLE);

         */



        imageView1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), EventsAtSelectedLocation.class);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
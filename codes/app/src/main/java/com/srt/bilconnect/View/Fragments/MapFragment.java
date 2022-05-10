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

import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Place;
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

    public Place place1;
    public Place place2;
    public Place place3;
    public Place place4;
    public Place place5;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        place1 = new Place("Bilkent Center");
        place2 = new Place("Odeon");
        place3 = new Place("East Campus");
        place4 = new Place("Mayfest");
        place5 = new Place("81");

        place3.getUpcomingEvents().add(new Event());
        place4.getUpcomingEvents().add(new Event());

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

        textViewThird.setText("" + place3.getUpcomingEvents().size());
        textViewFourth.setText("" + place4.getUpcomingEvents().size());

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


        if(Integer.parseInt(textViewFirst.getText().toString()) != 0 || place1.getUpcomingEvents().size() != 0) {
            imageView1.setVisibility(View.VISIBLE);
            textViewFirst.setVisibility(View.VISIBLE); }
        if(Integer.parseInt(textViewSecond.getText().toString()) != 0 || place2.getUpcomingEvents().size() != 0) {
            imageView2.setVisibility(View.VISIBLE);
            textViewSecond.setVisibility(View.VISIBLE);}
        if(Integer.parseInt(textViewThird.getText().toString()) != 0 || place3.getUpcomingEvents().size() != 0) {
            imageView3.setVisibility(View.VISIBLE);
            textViewThird.setVisibility(View.VISIBLE);}
        if(Integer.parseInt(textViewFourth.getText().toString()) != 0 || place4.getUpcomingEvents().size() != 0) {
            imageView4.setVisibility(View.VISIBLE);
            textViewFourth.setVisibility(View.VISIBLE);}
        if(Integer.parseInt(textViewFifth.getText().toString()) != 0 || place5.getUpcomingEvents().size() != 0) {
            imageView5.setVisibility(View.VISIBLE);
            textViewFifth.setVisibility(View.VISIBLE);}



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

    public Place getPlace1() { return place1; }

    public Place getPlace2() { return place2; }

    public Place getPlace3() { return place3; }

    public Place getPlace4() { return place4; }

    public Place getPlace5() { return place5; }


}
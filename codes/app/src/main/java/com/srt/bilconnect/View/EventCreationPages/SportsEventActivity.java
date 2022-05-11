package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.Place;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.R;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.databinding.ActivitySportsEventBinding;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class SportsEventActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ActivitySportsEventBinding binding;

    String date;
    String time;

    Button football;
    Button basketball;
    Button volleyball;
    Button tennis;
    Button fitness;
    Button walking;
    Button swimming;
    Button tableTennis;
    Button american;
    Button[] buttons;
    Button selectedButton;
    Spinner spinner;
    String selectedPlace;
    Event event;

    ImageView footballView, basketballView, volleyballView, tennisView, fitnessView, walkingView,
        swimmingView, tableTennisView, americanView;
    ImageView selectedImage;
    ImageView[] images;

    int selectedInterest;
    boolean[] selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySportsEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        buttons = new Button[9];
        images = new ImageView[buttons.length];

        time = "";
        date = "";
        selectedPlace = "";

        buttons[0] = football = binding.footballButton;
        buttons[1] = basketball = binding.basketballButton;
        buttons[2] = volleyball = binding.volleyballButton;
        buttons[3] = tennis = binding.tennisButton;
        buttons[4] = fitness = binding.fitnessButton;
        buttons[5] = walking = binding.walkingButton;
        buttons[6] = swimming = binding.swimmingButton;
        buttons[7] = tableTennis = binding.tableTennisButton;
        buttons[8] = american = binding.americanButton;

        images[0] = footballView = binding.footballView; images[1] = basketballView = binding.basketballView;
        images[2] = volleyballView = binding.volleyballView; images[3] = tennisView = binding.tennisView;
        images[4] = fitnessView = binding.fitnessView; images[5] = walkingView = binding.walkingView;
        images[6] = swimmingView = binding.swimmingView; images[7] = tableTennisView = binding.tabTennisView;
        images[8] = americanView = binding.americanView;

        selected = new boolean[buttons.length];
        selectedInterest = -1;

        spinner = binding.spinner5;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Place.placeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPlace = Place.placeNames[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void publishEvent(View view) {

        if(selectedInterest >= 0 && !binding.eventTitleText.getText().toString().equals("") &&
                !binding.quotaNumberText.getText().toString().equals("") && !date.equals("")
                && !binding.eventDescriptionText.getText().toString().equals("")) {
            firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String title = binding.eventTitleText.getText().toString();
                    int quota = Integer.parseInt(binding.quotaNumberText.getText().toString());
                    String id = UUID.randomUUID().toString();
                    String userId = auth.getCurrentUser().getUid();

                User user = documentSnapshot.toObject(User.class);
                event = new Event(title,user,quota,"Tutoring",null);
                //sets interest
                String interestString = "";
                if(selectedInterest == 0) { interestString = "Football"; }
                else if(selectedInterest == 1) { interestString = "Basketball"; }
                else if(selectedInterest == 2) { interestString = "Volleyball"; }
                else if(selectedInterest == 3) { interestString = "Tennis"; }
                else if(selectedInterest == 4) { interestString = "Fitness"; }
                else if(selectedInterest == 5) { interestString = "Walking"; }
                else if(selectedInterest == 6) { interestString = "Swimming"; }
                else if(selectedInterest == 7) { interestString = "Table Tennis"; }
                else if(selectedInterest == 8) { interestString = "American Football"; }
                event.setInterest(interestString);
                //sets description and other stuffs
                event.setDescription(binding.eventDescriptionText.getText().toString());
                event.setEventDocumentPlace(userId + id);
                event.setHost(user);
                //get event place
                firebaseFirestore.collection("PlaceData").document(selectedPlace).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            Place place = snapshot.toObject(Place.class);
                            event.setEventPlace(place);

                            firebaseFirestore.collection("EventData").document(event.getEventDocumentPlace()).set(event);
                            //adds the event to users createdEvents
                            firebaseFirestore.collection("UserData").document(event.getHost().getUserID()).update("createdEvents", FieldValue.arrayUnion(event));
                            //adds the event to places
                            firebaseFirestore.collection("PlaceData").document(selectedPlace).update("upcomingEvents", FieldValue.arrayUnion(event));

                            Toast.makeText(SportsEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SportsEventActivity.this, MainPageActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(e.getLocalizedMessage());
                Toast.makeText(SportsEventActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void imageSelected(View view) {
        selectedImage = findViewById(view.getId());
        int i = 0;

        for (i = 0; i < images.length; i++) {
            if (selectedImage == images[i]) break;
        }
        selected(buttons[i]);
    }

    public void selected(View view) {
        selectedButton = findViewById(view.getId());
        int i = 0;

        for (i = 0; i < buttons.length; i++) {
            if (selectedButton == buttons[i]) break;
        }

        if (selected[i]) {
            selectedButton.setBackgroundColor(Color.argb(100,76,175,80));
            selected[i] = false;
            selectedInterest = -1;
        }
        else {
            for (int j = 0; j < buttons.length; j++) {
                if (j == i) continue;
                buttons[j].setBackgroundColor(Color.argb(100,76,175,80));
                selected[j] = false;
            }
            selectedButton.setBackgroundColor(Color.parseColor("#ff99cc9b"));
            selected[i] = true;
            selectedInterest = i;
        }

    }

    public void selectDate(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String hour = "" + i;
                String minute = "" + i1;
                if (i < 10) hour = "0" + hour;
                if (i1 < 10) minute = "0" + minute;
                time = hour + ":" + minute;
            }
        }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
        timePickerDialog.show();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date = i2 + "/" + i1 + "/" + i;
            }
        }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }

}
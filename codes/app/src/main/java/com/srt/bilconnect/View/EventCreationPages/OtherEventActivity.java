package com.srt.bilconnect.View.EventCreationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.srt.bilconnect.databinding.ActivityOtherEventBinding;
import com.srt.bilconnect.databinding.ActivityStudyEventBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class OtherEventActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ActivityOtherEventBinding binding;

    String time;
    String date;
    Date zaman;
    String selectedPlace;
    Spinner spinner;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        time = "";
        date = "";
        selectedPlace = "";

        spinner = binding.spinner4;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Place.placeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPlace = Place.placeNames[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void publishEvent(View view) {

        if(!binding.eventTitleText.getText().toString().equals("") &&
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
                    event = new Event(title, user, quota, "Tutoring", null);
                    //sets time and date
                    event.setDate(date);
                    event.setTime(time);
                    event.setZaman(zaman);
                    //sets interest
                    String interestString = "";
                    event.setInterest(interestString);
                    //sets description and other stuffs
                    event.setDescription(binding.eventDescriptionText.getText().toString());
                    event.setEventDocumentPlace(userId + id);
                    event.setHost(user);
                    //get event place
                    firebaseFirestore.collection("PlaceData").document(selectedPlace).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Place place = documentSnapshot.toObject(Place.class);
                            event.setEventPlace(place);
                            firebaseFirestore.collection("EventData").document(event.getEventDocumentPlace()).set(event);
                            //adds the event to users createdEvents
                            firebaseFirestore.collection("UserData").document(event.getHost().getUserID()).update("createdEvents", FieldValue.arrayUnion(event));
                            //adds the event to places
                            firebaseFirestore.collection("PlaceData").document(selectedPlace).update("upcomingEvents", FieldValue.arrayUnion(event));

                            Toast.makeText(OtherEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(OtherEventActivity.this, MainPageActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println(e.getLocalizedMessage());
                            Toast.makeText(OtherEventActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
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
                Calendar c1 = Calendar.getInstance();
                c1.set(Calendar.DAY_OF_MONTH, i2);
                c1.set(Calendar.MONTH, i1);
                c1.set(Calendar.YEAR, i);
                zaman = c1.getTime();
            }
        }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }

}
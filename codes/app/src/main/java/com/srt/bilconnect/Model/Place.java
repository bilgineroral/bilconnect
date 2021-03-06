package com.srt.bilconnect.Model;

import android.graphics.Point;
import android.widget.ExpandableListView;

import java.io.Serializable;
import java.util.ArrayList;

public class Place implements Serializable {

    private String placeName;
    private ArrayList<Event> upcomingEvents;
    public static String[] placeNames = {"81", "Mayfest", "Odeon", "Bilkent Center", "East Campus"};

    public Place() {}

    public Place(String placeName) {
        this.placeName = placeName;
        upcomingEvents = new ArrayList<>();
    }

    public static void setPlaceNames(String[] placeNames) {
        Place.placeNames = placeNames;
    }

    public static String[] getPlaceNames() {return placeNames;}

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getPlaceName() { return this.placeName; }

    public void setUpcomingEvents(ArrayList<Event> upcomingEvents) {
        this.upcomingEvents = upcomingEvents;
    }

    public ArrayList<Event> getUpcomingEvents() {
        return this.upcomingEvents;
    }
}

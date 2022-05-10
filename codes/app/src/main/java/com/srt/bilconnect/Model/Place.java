package com.srt.bilconnect.Model;

import android.graphics.Point;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class Place{

    private String placeName;
    private double rating = 0;
    private Point coordinates;
    private ArrayList<User> followers;
    private ArrayList<Event> upcomingEvents;

    public Place(String placeName) {
        this.placeName = placeName;
        followers = new ArrayList<>();
        upcomingEvents = new ArrayList<>();
    }

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getPlaceName() { return this.placeName; }


    public void addFollower(Followable anObject) {
        followers.add((User) anObject);
    }

    public ArrayList<Event> getUpcomingEvents() {
        return this.upcomingEvents;
    }

    public void removeFollower(Followable anObject) {
        followers.remove((User) anObject);
    }
}

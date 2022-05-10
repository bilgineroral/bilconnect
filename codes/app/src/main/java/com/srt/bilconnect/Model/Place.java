package com.srt.bilconnect.Model;

import android.graphics.Point;

import java.util.ArrayList;

public class Place implements Followable{

    private String placeName;
    private ArrayList<User> followers;
    private ArrayList<Event> upcomingEvents;
    private double rating = 0;
    private Point coordinates;

    public Place(String placeName) {
        this.placeName = placeName;
    }

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getPlaceName() { return this.placeName; }


    @Override
    public void addFollower(Followable anObject) {
        followers.add((User) anObject);
    }

    @Override
    public void removeFollower(Followable anObject) {
        followers.remove((User) anObject);
    }

    public ArrayList<Event> getUpcomingEvents() {
        return upcomingEvents;
    }
}

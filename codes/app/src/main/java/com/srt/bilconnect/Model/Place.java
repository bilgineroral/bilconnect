package com.srt.bilconnect.Model;

import android.graphics.Point;

import java.util.ArrayList;

public class Place implements Followable{

    private String placeName;
    private ArrayList<User> followers = new ArrayList<User>();
    private ArrayList<Event> upcomingEvents = new ArrayList<Event>();
    private ArrayList<Event> pastEvents = new ArrayList<Event>();
    private ArrayList<Double> ratings = new ArrayList<Double>();
    private double rating = 0;
    private ArrayList<Interest> interests = new ArrayList<Interest>();
    private Point coordinates;

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public void changeRating(double newRating) //aldığı şey double olacak ancak oydan alacak mesela 4.7 almalı kişiden
    {
        ratings.add(newRating);
        rating = (rating + newRating) / ratings.size();
    }

    public String getPlaceName() { return this.placeName; }


    @Override
    public void addFollower(Followable anObject) {
        followers.add((User) anObject);
    }

    @Override
    public void removeFollower(Followable anObject) {
        followers.remove((User) anObject);
    }
}

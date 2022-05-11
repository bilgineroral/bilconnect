package com.srt.bilconnect.Model;

import android.graphics.Point;
import android.widget.ExpandableListView;

import java.io.Serializable;
import java.util.ArrayList;

<<<<<<< HEAD
public class Place implements Followable{

    private String placeName;
    private ArrayList<User> followers;
    private ArrayList<Event> upcomingEvents;
    private double rating = 0;
    private Point coordinates;
    private ArrayList<User> followers;
=======
public class Place implements Serializable {

    private String placeName;
    //private ArrayList<User> followers;
>>>>>>> main
    private ArrayList<Event> upcomingEvents;
    public static String[] placeNames = {"81", "Mayfest", "Odeon", "Bilkent Center", "East Campus"};

    public Place() {}

    public Place(String placeName) {
        this.placeName = placeName;
        //followers = new ArrayList<>();
        upcomingEvents = new ArrayList<>();
    }

    public static String[] getPlaceNames() {return placeNames;}

    //public static void setPlaceNames(String[] placeNames) {Place.placeNames = placeNames;}

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getPlaceName() { return this.placeName; }

    /*public ArrayList<User> getFollowers() {
        return followers;
    }*/

    /*
    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }*/

    public void setUpcomingEvents(ArrayList<Event> upcomingEvents) {
        this.upcomingEvents = upcomingEvents;
    }
/*
    public void addFollower(Followable anObject) {
        followers.add((User) anObject);
    }*/

    public ArrayList<Event> getUpcomingEvents() {
        return this.upcomingEvents;
    }
/*
    public void removeFollower(Followable anObject) {
        followers.remove((User) anObject);
    }*/
}

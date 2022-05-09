package com.srt.bilconnect.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    // TEST VARIABLES
    private String title;
    private String description;
    private User host;
    private int quota;
    private ArrayList<User> attendees;
    private Place eventPlace;
    private ArrayList<Interest> interests;

    public Event() {}
    public Event(String title, User host, int quota, ArrayList<Interest> interests, Place eventPlace) {
        this.interests = interests;
        this.eventPlace = eventPlace;
        this.title = title;
        this.host = host;
        this.quota = quota;
    }

    public Event(String s, User user, int i) {
        this.title = s;
        this.host = user;
        this.quota = i;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        this.interests = interests;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttendees(ArrayList<User> attendees) {
        this.attendees = attendees;
    }

    public void setEventPlace(Place eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<User> getAttendees() {
        return attendees;
    }

    public Place getEventPlace() {
        return eventPlace;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public User getHost() {
        return host;
    }

    public int getQuota() {
        return quota;
    }
}

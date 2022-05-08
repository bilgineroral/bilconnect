package com.srt.bilconnect.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    // TEST VARIABLES
    private String eventName;
    private String description;
    private User host;
    private int quota;
    private ArrayList<User> attendees;
    private Place eventPlace;
    private ArrayList<Interest> interests;

    public Event() {}
    public Event(String text, User host, int quota) {

        this.eventName = text;
        this.host = host;
        this.quota = quota;
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

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getEventName() {
        return eventName;
    }

    public User getHost() {
        return host;
    }

    public int getQuota() {
        return quota;
    }

    public ArrayList<Interest> getInterests() {return this.interests;}
    public void setInterests(ArrayList<Interest> interests) {this.interests = interests;}
}

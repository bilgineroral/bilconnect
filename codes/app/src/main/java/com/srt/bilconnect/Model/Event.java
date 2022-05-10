package com.srt.bilconnect.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    // TEST VARIABLES
    private String title;
    private String description;
    private User host;
    private int quota;
    protected ArrayList<Comment> comments;
    protected ArrayList<User> attendees;
    private Place eventPlace;
    private String interest;
    private String eventDocumentPlace;

    public Event() {}

    public Event(String title, User host, int quota, String interest, Place eventPlace) {
        this.interest = interest;
        this.eventPlace = eventPlace;
        this.title = title;
        this.host = host;
        this.quota = quota;
        attendees = new ArrayList<>();
    }

    public Event(String s, User user, int i) {
        this.title = s;
        this.host = user;
        this.quota = i;
    }

    public String getEventDocumentPlace() {
        return eventDocumentPlace;
    }

    public void setEventDocumentPlace(String eventDocumentPlace) {
        this.eventDocumentPlace = eventDocumentPlace;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

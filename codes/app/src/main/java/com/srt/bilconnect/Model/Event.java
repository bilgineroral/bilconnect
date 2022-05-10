package com.srt.bilconnect.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    // TEST VARIABLES
    private String title;
    private String description;
    private User host;
    private int quota;
    private Place eventPlace;
    private String interest;
    private String eventDocumentPlace;
    private ArrayList<User> attendees;
    private ArrayList<Comment> comments;

    public Event() {}

    public Event(String title, User host, int quota, String interest, Place eventPlace) {
        this.interest = interest;
        this.eventPlace = eventPlace;
        this.title = title;
        this.host = host;
        this.quota = quota;
        attendees = new ArrayList<>();
        comments = new ArrayList<>();
    }

    // TEST CTOR'U (BILGINER)
    public Event(String title, User host, int quota, String details) {
        this.title = title;
        this.host = host;
        this.quota = quota;
        this.description = details;
    }

    public ArrayList<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<User> attendees) {
        this.attendees = attendees;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
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

    public void setEventPlace(Place eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getDescription() {
        return description;
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

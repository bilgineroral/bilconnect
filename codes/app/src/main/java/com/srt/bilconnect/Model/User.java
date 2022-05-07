package com.srt.bilconnect.Model;

import java.util.ArrayList;

public class User implements Followable{
    private String username;
    private String userID;
    private String bilkentID;
    private String email;
    private ArrayList<String> questionAnswers;
    private String department;
    private String profilePhotoURL;
    private String bio;
    private double rating;
    private ArrayList<User> followers;
    private ArrayList<Followable> followedAccounts;
    private ArrayList<User> blockedAccounts;
    private ArrayList<Interest> interests;
    private ArrayList<Event> pastEvents;
    private ArrayList<Event> createdEvents;
    private ArrayList<Event> registeredEvents;
//interestleri ekle


//eventleri ekle
//

    public User() {
        // test icin koydum..(Bilginer)
        pastEvents = new ArrayList<>();
        pastEvents.add(new Event("takilmaca", new User(), 12,null,null));
        pastEvents.add(new Event("ogle yemegi", new User(), 4,null,null));
        pastEvents.add(new Event("kodlama eventi", new User(), 6,null,null));
    }

    public User(String username, String userID, String email, String bilkentID, ArrayList<String> questionAnswers, String department) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.bilkentID = bilkentID;
        this.questionAnswers = questionAnswers;
        this.department = department;
    }

    public void addCreatedEvent(Event anEvent) {
        this.createdEvents.add(anEvent);
    }

    public void addRegisteredEvents(Event anEvent) {
        if(registeredEvents.contains(anEvent)) {}
        else {
            registeredEvents.add(anEvent);
        }
    }

    public void unregisterFromEvent(Event anEvent) {
        if(registeredEvents.contains(anEvent)) {
            registeredEvents.remove(anEvent);
        }
    }

    public ArrayList<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(ArrayList<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }

    public ArrayList<Event> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(ArrayList<Event> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPastEvents(ArrayList<Event> pastEvents) {
        this.pastEvents = pastEvents;
    }

    public String getBio() {
        return bio;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public void setFollowedAccounts(ArrayList<Followable> followedAccounts) {
        this.followedAccounts = followedAccounts;
    }

    public void setBlockedAccounts(ArrayList<User> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }

    public void setInterests(ArrayList<Interest> interests) {
        this.interests = interests;
    }

    public ArrayList<Followable> getFollowedAccounts() {
        return followedAccounts;
    }

    public ArrayList<User> getBlockedAccounts() {
        return blockedAccounts;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getQuestionAnswers() {
        return questionAnswers;
    }

    public String getDepartment() {
        return department;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public String getBilkentID() {
        return bilkentID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setBilkentID(String bilkentID) {
        this.bilkentID = bilkentID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuestionAnswers(ArrayList<String> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public ArrayList<Event> getPastEvents() {
        return pastEvents;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    @Override
    public void addFollower(Followable anObject) {

    }

    @Override
    public void removeFollower(Followable anObject) {

    }
}

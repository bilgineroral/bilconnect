package com.srt.bilconnect.Model;

import com.srt.bilconnect.View.AdditionalInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    public String username;//done
    public String userID;//done
    public String bilkentID;//done
    public String email;//done
    public String department;//done
    public String profilePhotoURL;//done
    public String bio;//done
    public String password;//not done
    public double rating;//not done
    public ArrayList<Event> createdEvents;//done
    public ArrayList<User> followers;//not done
    public ArrayList<User> followedAccounts;//not done
    public ArrayList<Event> registeredEvents;//not done

//eventleri ekle
    /*
    public User() {//boş user constructoru firebase için gerekli
        // test icin koydum..(Bilginer)

        pastEvents = new ArrayList<>();
        pastEvents.add(new Event("takilmaca", new User(), 12));
        pastEvents.add(new Event("ogle yemegi", new User(), 4));
        pastEvents.add(new Event("kodlama eventi", new User(), 6));

    }*/
    public User() {}
    public User(String username, String userID, String email, String bilkentID, String department) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.bilkentID = bilkentID;
        this.department = department;
        createdEvents = new ArrayList<>();
        followers = new ArrayList<>();
        followedAccounts = new ArrayList<>();
        registeredEvents = new ArrayList<>();
    }

    public void registerToEvent(Event anEvent) {
        this.registeredEvents.add(anEvent);
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getFollowedAccounts() {
        return followedAccounts;
    }

    public void setFollowedAccounts(ArrayList<User> followedAccounts) {
        this.followedAccounts = followedAccounts;
    }

    public ArrayList<Event> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(ArrayList<Event> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public ArrayList<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(ArrayList<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public double getRating() {
        return rating;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
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


    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}

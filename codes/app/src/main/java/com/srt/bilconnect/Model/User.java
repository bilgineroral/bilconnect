package com.srt.bilconnect.Model;

import java.util.ArrayList;

public class User implements Followable{
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
    private ArrayList<Event> pastEvents;
//interestleri ekle
//eventleri ekle
//

    public User() {
        pastEvents = new ArrayList<>();
        pastEvents.add(new Event("e1"));
        pastEvents.add(new Event("e2"));
    }

    public User(String userID, String email, String bilkentID, ArrayList<String> questionAnswers, String department) {
        this.userID = userID;
        this.email = email;
        this.bilkentID = bilkentID;
        this.questionAnswers = questionAnswers;
        this.department = department;
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
}

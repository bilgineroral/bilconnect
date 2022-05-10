package com.srt.bilconnect.Model;

import com.srt.bilconnect.View.AdditionalInfoActivity;

import java.util.ArrayList;

public class User implements Followable{

    public String username;
    public String userID;
    public String bilkentID;
    public String email;
    public String department;
    public String profilePhotoURL;
    public String bio;
    public String dorm;
    public String password;
    public double rating;
    public ArrayList<User> followers;
    public ArrayList<Followable> followedAccounts;
    public ArrayList<User> blockedAccounts;
    public ArrayList<String> interests;
    public ArrayList<Event> pastEvents;
    public ArrayList<Event> registeredEvents;
    public ArrayList<Event> createdEvents;

//interestleri ekle


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
        followers = new ArrayList<>();
        followedAccounts = new ArrayList<>();
        blockedAccounts = new ArrayList<>();
        interests = new ArrayList<>();
        pastEvents = new ArrayList<>();
        registeredEvents = new ArrayList<>();
        createdEvents = new ArrayList<>();
    }


    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
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

    public ArrayList<Followable> getFollowedAccounts() {
        return followedAccounts;
    }

    public ArrayList<User> getBlockedAccounts() {
        return blockedAccounts;
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

<<<<<<< Updated upstream
    public ArrayList<Event> getPastEvents() {

=======
    public void setQuestionAnswers(ArrayList<String> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public ArrayList<PastEvent> getPastEvents() {
>>>>>>> Stashed changes
        return pastEvents;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public String getDorm() {return dorm;}
    public void setDorm(String dorm) {this.dorm = dorm;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Override
    public void addFollower(Followable anObject) {
        followers.add((User) anObject);
    }

    @Override
    public void removeFollower(Followable anObject) {
        followers.remove((User) anObject);
    }

    public void register(CurrentEvent anEvent){
        registeredEvents.add(anEvent);
    }

    public void createEvent(){
        CurrentEvent newEvent = new CurrentEvent();
        createdEvents.add(newEvent);
    }

    public void unregister(Event anEvent){
        registeredEvents.remove(anEvent);
    }

    public void follow(Followable aUser){
        followedAccounts.add(aUser);
    }

    public void unfollow(Followable aUser){
        followedAccounts.remove(aUser);
    }

    public void block(Followable aUser){
        unfollow(aUser);
        blockedAccounts.add((User) aUser);
    }

    public void addCreatedEvent(Event anEvent) {
        createdEvents.add(anEvent);
    }

}

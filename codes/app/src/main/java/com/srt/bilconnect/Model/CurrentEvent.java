package com.srt.bilconnect.Model;

import java.util.ArrayList;

public class CurrentEvent extends Event {

    public CurrentEvent(){
        super();
    }

    public CurrentEvent(String title, User host, int quota, ArrayList<Interest> interests, Place eventPlace){
        super(title, host, quota, interests, eventPlace);
    }

    public void addAttendee(User user){
        attendees.add(user);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}

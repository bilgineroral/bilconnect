package com.srt.bilconnect.Model;

import java.io.Serializable;

public class Comment implements Serializable {
    private User commenter;
    private String comment;
    private Event event;

    public Comment(User commenter, String comment, Event event){
        this.commenter = commenter;
        this.comment = comment;
        this.event = event;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

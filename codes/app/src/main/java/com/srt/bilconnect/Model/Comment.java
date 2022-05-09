package com.srt.bilconnect.Model;

public class Comment {
    private User commenter;
    private String comment;
    private Event event;

    public Comment(User commenter, String comment, Event event){
        this.commenter = commenter;
        this.comment = comment;
        this.event = event;
    }
}

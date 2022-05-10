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
}

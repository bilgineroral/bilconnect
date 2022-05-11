package com.srt.bilconnect.Model;

import java.io.Serializable;

public class Review extends Comment implements Serializable {
    private double rating;

    public Review(User commenter, String comment, Event event) {
        super(commenter, comment, event);
    }
}

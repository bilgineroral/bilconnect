package com.srt.bilconnect.Model;

public class Review extends Comment{
    private double rating;

    public Review(User commenter, String comment, Event event) {
        super(commenter, comment, event);
    }
}

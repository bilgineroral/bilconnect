package com.srt.bilconnect.Model;

import java.util.ArrayList;

public class PastEvent extends Event{

    private ArrayList<Review> reviews;

    public PastEvent() { super(); }

    public void addReview(Review review){
        reviews.add(review);
    }
}

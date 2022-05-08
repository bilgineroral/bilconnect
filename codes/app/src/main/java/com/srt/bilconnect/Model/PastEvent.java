package com.srt.bilconnect.Model;

import java.util.ArrayList;

public class PastEvent {

    private ArrayList<Review> reviews;

    public void addReview(Review review){
        reviews.add(review);
    }
}

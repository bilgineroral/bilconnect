package com.srt.bilconnect.Model;

import android.graphics.Point;

import java.util.ArrayList;

public class Place{

    private String placeName;
    private double rating = 0;
    private Point coordinates;

    public Place(String placeName) {
        this.placeName = placeName;
    }

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getPlaceName() { return this.placeName; }


}

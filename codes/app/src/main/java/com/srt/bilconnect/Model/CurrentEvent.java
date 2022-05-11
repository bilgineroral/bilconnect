package com.srt.bilconnect.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CurrentEvent extends Event implements Serializable {

    public CurrentEvent(){
        super();
    }

    public CurrentEvent(String title, User host, int quota, ArrayList<Interest> interests, Place eventPlace){
        //super(title, host, quota, interests, eventPlace);
    }
}

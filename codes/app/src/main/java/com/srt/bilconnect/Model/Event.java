package com.srt.bilconnect.Model;

import java.io.Serializable;

public class Event implements Serializable {

    // TEST VARIABLES
    public String eventName;
    public String hostName;
    public int quota;

    public Event(String text, String hostName, int quota) {

        this.eventName = text;
        this.hostName = hostName;
        this.quota = quota;
    }
}

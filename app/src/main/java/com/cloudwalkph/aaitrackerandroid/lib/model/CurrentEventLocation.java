package com.cloudwalkph.aaitrackerandroid.lib.model;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class CurrentEventLocation {

    private Integer eventId;
    private String eventName;
    private Integer eventLocationId;
    private String eventLocationName;

    private static CurrentEventLocation mInstance = null;

    public static CurrentEventLocation getInstance(){
        if(mInstance == null)
        {
            mInstance = new CurrentEventLocation();
        }
        return mInstance;
    }

    public Integer getEventId() {
        return this.eventId;
    }

    public void setEventId(Integer value) {
        eventId = value;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String value) {
        eventName = value;
    }

    public Integer getEventLocationId() {
        return this.eventLocationId;
    }

    public void setEventLocationId(Integer value) { eventLocationId = value; }

    public String getEventLocationName() {
        return this.eventLocationName;
    }

    public void setEventLocationName(String value) { eventLocationName = value; }
}

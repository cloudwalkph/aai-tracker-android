package com.cloudwalkph.aaitrackerandroid.lib.model;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class CurrentEventLocation {

    private Integer eventId;
    private Integer eventLocationId;

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

    public Integer getEventLocationId() {
        return this.eventLocationId;
    }

    public void setEventLocationId(Integer value) { eventLocationId = value; }
}

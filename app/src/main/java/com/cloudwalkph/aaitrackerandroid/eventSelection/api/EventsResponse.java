package com.cloudwalkph.aaitrackerandroid.eventSelection.api;

import com.cloudwalkph.aaitrackerandroid.lib.model.events.Event;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class EventsResponse {

    @SerializedName("data")
    private RealmList<Event> events = null;
    @SerializedName("status")
    private int status;

    /**
     *
     * @return
     * The events
     */
    public RealmList<Event> getEvents() {
        return events;
    }

    /**
     *
     * @param events
     * The events
     */
    public void setEvents(RealmList<Event> events) {
        this.events = events;
    }

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

}

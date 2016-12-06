package com.cloudwalkph.aaitrackerandroid.lib.model.events;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class Event extends RealmObject {
    @PrimaryKey
    @SerializedName("event_id")
    private int eventId;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("polls")
    private RealmList<Poll> polls = null;
    @SerializedName("locations")
    private RealmList<Location> locations = null;
    @SerializedName("status")
    private String status;

    /**
     *
     * @return
     * The eventId
     */
    public int getEventId() {
        return eventId;
    }

    /**
     *
     * @param eventId
     * The event_id
     */
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     * The start_date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     * The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     * The end_date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     * The polls
     */
    public RealmList<Poll> getPolls() {
        return polls;
    }

    /**
     *
     * @param polls
     * The polls
     */
    public void setPolls(RealmList<Poll> polls) {
        this.polls = polls;
    }

    /**
     *
     * @return
     * The locations
     */
    public RealmList<Location> getLocations() {
        return locations;
    }

    /**
     *
     * @param locations
     * The locations
     */
    public void setLocations(RealmList<Location> locations) {
        this.locations = locations;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return this.name;
    }

}


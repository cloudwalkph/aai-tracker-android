package com.cloudwalkph.aaitrackerandroid.lib.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public class PollAnswer extends RealmObject{

    @SerializedName("poll_id")
    private String pollId;
    @SerializedName("event_location_answer_id")
    private int eventLocationAnswerId;
    private String value;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("created_at")
    private String createdAt;
    @PrimaryKey
    private int id;

    /**
     *
     * @return
     * The pollId
     */
    public String getPollId() {
        return pollId;
    }

    /**
     *
     * @param pollId
     * The poll_id
     */
    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    /**
     *
     * @return
     * The eventLocationAnswerId
     */
    public long getEventLocationAnswerId() {
        return eventLocationAnswerId;
    }

    /**
     *
     * @param eventLocationAnswerId
     * The event_location_answer_id
     */
    public void setEventLocationAnswerId(int eventLocationAnswerId) {
        this.eventLocationAnswerId = eventLocationAnswerId;
    }

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

}

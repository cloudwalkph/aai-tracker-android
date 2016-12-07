package com.cloudwalkph.aaitrackerandroid.lib.model.eventAnswers;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public class EventAnswer extends RealmObject {
    @PrimaryKey
    private int id;
    private String uuid;
    @SerializedName("event_id")
    private String eventId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("event_location_id")
    private String eventLocationId;
    private String image;
    @SerializedName("hit_date")
    private String hitDate;
    private String name;
    @SerializedName("contact_number")
    private String contactNumber;
    private String email;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("created_at")
    private String createdAt;
    private RealmList<PollAnswer> pollAnswers = new RealmList<PollAnswer>();


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

    /**
     *
     * @return
     * The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     * The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return
     * The eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     *
     * @param eventId
     * The event_id
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     *
     * @return
     * The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The eventLocationId
     */
    public String getEventLocationId() {
        return eventLocationId;
    }

    /**
     *
     * @param eventLocationId
     * The event_location_id
     */
    public void setEventLocationId(String eventLocationId) {
        this.eventLocationId = eventLocationId;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The hitDate
     */
    public String getHitDate() {
        return hitDate;
    }

    /**
     *
     * @param hitDate
     * The hitDate
     */
    public void setHitDate(String hitDate) {
        this.hitDate = hitDate;
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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param contactNumber
     * The contactNumber
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     *
     * @return
     * The contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
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
     * The pollAnswers
     */
    public RealmList<PollAnswer> getPollAnswers() {
        return pollAnswers;
    }

    /**
     *
     * @param pollAnswers
     * The pollAnswers
     */
    public void setPollAnswers(RealmList<PollAnswer> pollAnswers) {
        this.pollAnswers = pollAnswers;
    }
}

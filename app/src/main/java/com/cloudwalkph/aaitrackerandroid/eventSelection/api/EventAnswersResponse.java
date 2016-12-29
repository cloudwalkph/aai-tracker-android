package com.cloudwalkph.aaitrackerandroid.eventSelection.api;

import com.cloudwalkph.aaitrackerandroid.lib.model.eventAnswers.EventAnswer;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

/**
 * Created by trick.sunga on 07/12/2016.
 */

public class EventAnswersResponse {

    @SerializedName("data")
    private RealmList<EventAnswer> eventAnswers = null;
    @SerializedName("status")
    private int status;

    /**
     *
     * @return
     * The eventAnswers
     */
    public RealmList<EventAnswer> getEventAnswers() {
        return eventAnswers;
    }

    /**
     *
     * @param eventAnswers
     * The eventAnswers
     */
    public void setEvents(RealmList<EventAnswer> eventAnswers) {
        this.eventAnswers = eventAnswers;
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

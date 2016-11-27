package com.cloudwalkph.aaitrackerandroid.poll.api;

import com.cloudwalkph.aaitrackerandroid.lib.model.EventAnswer;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public class PollAnswerResponse {

    private EventAnswer data;
    private int status;

    /**
     *
     * @return
     * The data
     */
    public EventAnswer getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(EventAnswer data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The status
     */
    public long getStatus() {
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

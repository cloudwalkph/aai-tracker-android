package com.cloudwalkph.aaitrackerandroid.service.api;

import com.google.gson.annotations.SerializedName;


/**
 * Created by trick.sunga on 23/11/2016.
 */

public class PollAnswerBody {

    @SerializedName("poll_id")
    public String pollId;
    public String value;

}

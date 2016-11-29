package com.cloudwalkph.aaitrackerandroid.poll.api;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by trick.sunga on 24/11/2016.
 */

public class AnswerBody extends RealmObject {

    @SerializedName("poll_id")
    public String pollId;
    public String value;

}

package com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by trick.sunga on 25/11/2016.
 */

public class LocalPollAnswer extends RealmObject {

    @SerializedName("poll_id")
    public String pollId;
    public String value;
}

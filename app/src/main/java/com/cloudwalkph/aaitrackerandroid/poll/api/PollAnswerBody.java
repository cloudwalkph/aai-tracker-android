package com.cloudwalkph.aaitrackerandroid.poll.api;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public class PollAnswerBody extends RealmObject {

    @PrimaryKey
    public String uuid;
    @SerializedName("user_id")
    public String userId;
    public String image;
    public RealmList<AnswerBody> answers;
    public boolean isPosted;

}

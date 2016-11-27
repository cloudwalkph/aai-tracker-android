package com.cloudwalkph.aaitrackerandroid.lib.service.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by trick.sunga on 24/11/2016.
 */

public class AnswerBody {

    public String uuid;
    @SerializedName("user_id")
    public String userId;
    public String image;
    @SerializedName("answers")
    public List<PollAnswerBody> pollAnswerBodies;

}

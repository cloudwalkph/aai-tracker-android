package com.cloudwalkph.aaitrackerandroid.lib.model;

import com.google.gson.annotations.SerializedName;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trick.sunga on 25/11/2016.
 */

public class LocalEventAnswer extends RealmObject {

    @SerializedName("event_id")
    public String eventId;
    @SerializedName("event_location_id")
    public String eventLocationId;
    @PrimaryKey
    public String uuid;
    @SerializedName("user_id")
    public String userId;
    public String origImage;
    public String image;
    @SerializedName("hit_date")
    public String hitDate;
    public RealmList<LocalPollAnswer> localPollAnswers;
    public boolean isPosted;
}

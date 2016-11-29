package com.cloudwalkph.aaitrackerandroid.poll;

import com.cloudwalkph.aaitrackerandroid.poll.api.AnswerBody;

import io.realm.RealmList;
import okhttp3.MultipartBody;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public interface PollWorker {
    void postImage(MultipartBody.Part image);
    void postAnswer(String eventId, String eventLocationId, String uuid, String userId, String image, RealmList<AnswerBody> answers);
}

package com.cloudwalkph.aaitrackerandroid.poll;

import com.cloudwalkph.aaitrackerandroid.lib.model.EventAnswer;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public interface OnPollAnswerWorkerListener {

    void onPollImageUploadSuccess(String filename);

    void onPollImageUploadFail(String message);

    void onPollAnswerSuccess(EventAnswer eventAnswer);

    void onPollAnswerFail(String message);
}

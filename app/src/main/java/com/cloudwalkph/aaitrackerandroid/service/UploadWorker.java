package com.cloudwalkph.aaitrackerandroid.service;

import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;
import com.cloudwalkph.aaitrackerandroid.service.api.PollAnswerResponse;

import java.io.IOException;

/**
 * Created by trick.sunga on 24/11/2016.
 */

public interface UploadWorker {

    String postImage(String imageFile) throws IOException;
    PollAnswerResponse postAnswer(LocalEventAnswer localEventAnswer) throws IOException;
}

package com.cloudwalkph.aaitrackerandroid.lib.service;

import com.cloudwalkph.aaitrackerandroid.lib.model.LocalAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.service.api.PollAnswerResponse;

import java.io.IOException;

/**
 * Created by trick.sunga on 24/11/2016.
 */

public interface UploadWorker {

    String postImage(String imageFile) throws IOException;
    PollAnswerResponse postAnswer(LocalAnswer localAnswer) throws IOException;
}

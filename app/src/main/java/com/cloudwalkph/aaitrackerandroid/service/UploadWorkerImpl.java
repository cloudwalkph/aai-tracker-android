package com.cloudwalkph.aaitrackerandroid.service;

import com.cloudwalkph.aaitrackerandroid.lib.api.ServiceGenerator;
import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalPollAnswer;
import com.cloudwalkph.aaitrackerandroid.service.api.UploadClient;
import com.cloudwalkph.aaitrackerandroid.service.api.AnswerBody;
import com.cloudwalkph.aaitrackerandroid.service.api.PollAnswerBody;
import com.cloudwalkph.aaitrackerandroid.service.api.PollAnswerResponse;
import com.cloudwalkph.aaitrackerandroid.service.api.PollImageResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by trick.sunga on 24/11/2016.
 */

public class UploadWorkerImpl implements UploadWorker {

    @Override
    public String postImage(String imageFile) throws IOException {
        File file = new File(imageFile);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        UploadClient client = ServiceGenerator.createService(UploadClient.class);
        Call<PollImageResponse> call = client.postImage(image);
        PollImageResponse pollImageResponse = call.execute().body();

        return pollImageResponse.getData().getFileName();
    }

    @Override
    public PollAnswerResponse postAnswer(LocalEventAnswer localEventAnswer) throws IOException {
        AnswerBody answerBody = new AnswerBody();
        answerBody.uuid = localEventAnswer.uuid;
        answerBody.userId = localEventAnswer.userId;
        answerBody.image = localEventAnswer.image;
        answerBody.hitDate = localEventAnswer.hitDate;

        List<PollAnswerBody> pollAnswerBodies = new ArrayList<PollAnswerBody>();
        for (LocalPollAnswer localPollAnswer : localEventAnswer.localPollAnswers) {
            PollAnswerBody pollAnswerBody = new PollAnswerBody();
            pollAnswerBody.pollId = localPollAnswer.pollId;
            pollAnswerBody.value = localPollAnswer.value;
            pollAnswerBodies.add(pollAnswerBody);
        }
        answerBody.pollAnswerBodies = pollAnswerBodies;


        UploadClient client = ServiceGenerator.createService(UploadClient.class);
        Call<PollAnswerResponse> call = client.postAnswer(localEventAnswer.eventId, localEventAnswer.eventLocationId, answerBody);
        PollAnswerResponse pollAnswerResponse = call.execute().body();

        return pollAnswerResponse;
    }
}

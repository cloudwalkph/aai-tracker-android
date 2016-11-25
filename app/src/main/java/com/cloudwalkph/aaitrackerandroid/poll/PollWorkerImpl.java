package com.cloudwalkph.aaitrackerandroid.poll;

import com.cloudwalkph.aaitrackerandroid.lib.api.ServiceGenerator;
import com.cloudwalkph.aaitrackerandroid.lib.model.EventAnswer;
import com.cloudwalkph.aaitrackerandroid.poll.api.AnswerBody;
import com.cloudwalkph.aaitrackerandroid.poll.api.PollAnswerBody;
import com.cloudwalkph.aaitrackerandroid.poll.api.PollAnswerResponse;
import com.cloudwalkph.aaitrackerandroid.poll.api.PollClient;
import com.cloudwalkph.aaitrackerandroid.poll.api.PollImageResponse;

import io.realm.Realm;
import io.realm.RealmList;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public class PollWorkerImpl implements PollWorker {
    private OnPollAnswerWorkerListener listener;

    public PollWorkerImpl(OnPollAnswerWorkerListener listener) {
        this.listener = listener;
    }

    @Override
    public void postImage(MultipartBody.Part image) {
        PollClient client = ServiceGenerator.createService(PollClient.class);
        Call<PollImageResponse> call = client.postImage(image);
        call.enqueue(new Callback<PollImageResponse>() {
            @Override
            public void onResponse(Call<PollImageResponse> call, Response<PollImageResponse> response) {
                if (response.isSuccessful()) {
                    PollImageResponse pollImageResponse = response.body();
                    listener.onPollImageUploadSuccess(pollImageResponse.getData().getFileName());
                } else {
                    listener.onPollImageUploadFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<PollImageResponse> call, Throwable t) {
                listener.onPollImageUploadFail(t.getMessage());
            }
        });
    }

    @Override
    public void postAnswer(String eventId, String eventLocationId, String uuid, String userId, String image, RealmList<AnswerBody> answers) {
        PollAnswerBody pollAnswerBody = new PollAnswerBody();
        pollAnswerBody.uuid = uuid;
        pollAnswerBody.userId = userId;
        pollAnswerBody.image = image;
        pollAnswerBody.answers = answers;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(pollAnswerBody);
        realm.commitTransaction();

        PollClient client = ServiceGenerator.createService(PollClient.class);
        Call<PollAnswerResponse> call = client.postAnswer(eventId, eventLocationId, pollAnswerBody);
        call.enqueue(new Callback<PollAnswerResponse>() {
            @Override
            public void onResponse(Call<PollAnswerResponse> call, Response<PollAnswerResponse> response) {
                if (response.isSuccessful()) {
                    EventAnswer eventAnswer = response.body().getData();
                    listener.onPollAnswerSuccess(eventAnswer);
                } else {
                    listener.onPollAnswerFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<PollAnswerResponse> call, Throwable t) {
                listener.onPollAnswerFail(t.getMessage());
            }
        });
    }
}

package com.cloudwalkph.aaitrackerandroid.poll.api;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by trick.sunga on 23/11/2016.
 */

public interface PollClient {

    @POST("/api/v1/upload")
    @Multipart
    Call<PollImageResponse> postImage(@Part MultipartBody.Part image);

    @POST("/api/v1/events/{eventId}/event-locations/{eventLocationId}/answer")
    Call<PollAnswerResponse> postAnswer(@Path("eventId") String eventId,
                                        @Path("eventLocationId") String eventLocationId,
                                        @Body PollAnswerBody pollAnswerBody);
}

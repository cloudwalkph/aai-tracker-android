package com.cloudwalkph.aaitrackerandroid.eventSelection.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public interface EventsClient {

    @GET("/api/v1/events")
    Call<EventsResponse> getEvents();
}

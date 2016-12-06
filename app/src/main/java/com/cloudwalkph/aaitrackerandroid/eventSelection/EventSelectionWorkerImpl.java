package com.cloudwalkph.aaitrackerandroid.eventSelection;

import com.cloudwalkph.aaitrackerandroid.eventSelection.api.EventsClient;
import com.cloudwalkph.aaitrackerandroid.eventSelection.api.EventsResponse;
import com.cloudwalkph.aaitrackerandroid.lib.api.ServiceGenerator;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Event;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Location;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class EventSelectionWorkerImpl implements EventSelectionWorker {
    private OnEventSelectionWorkerListener listener;

    public EventSelectionWorkerImpl(OnEventSelectionWorkerListener listener) {
        this.listener = listener;
    }

    @Override
    public void loadEvents() {
        EventsClient client = ServiceGenerator.createService(EventsClient.class);
        Call<EventsResponse> call = client.getEvents();
        call.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                if (response.isSuccessful()) {
                    EventsResponse eventsResponse = response.body();
                    Realm realm = Realm.getDefaultInstance();
                    RealmList<Event> eventRealmList = eventsResponse.getEvents();
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(eventRealmList);
                    realm.commitTransaction();
                    listener.onLoadEventsSuccess(eventRealmList);
                } else {
                    listener.onLoadEventsFail("Problem loading events");
                }
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                // something went completely south (like no internet connection)
                Realm realm = Realm.getDefaultInstance();
                RealmResults<Event> eventRealmResults = realm.where(Event.class).findAll();

                RealmList<Event> eventRealmList = new RealmList<>();
                eventRealmList.addAll(eventRealmResults.subList(0, eventRealmResults.size()));

                listener.onLoadEventsSuccess(eventRealmList);
            }
        });
    }

    @Override
    public void loadLocations(Integer eventId) {
        Realm realm = Realm.getDefaultInstance();
        Event event = realm.where(Event.class).equalTo("eventId", eventId).findFirst();

        RealmList<Location> locationRealmList = event.getLocations();
        listener.onLoadLocationsSuccess(locationRealmList);
    }
}

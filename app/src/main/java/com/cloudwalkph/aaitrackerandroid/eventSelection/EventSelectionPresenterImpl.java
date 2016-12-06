package com.cloudwalkph.aaitrackerandroid.eventSelection;

import android.util.Log;

import com.cloudwalkph.aaitrackerandroid.lib.model.events.Event;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Location;

import io.realm.RealmList;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class EventSelectionPresenterImpl implements EventSelectionPresenter, OnEventSelectionWorkerListener {
    private EventSelectionView view;

    public EventSelectionPresenterImpl(EventSelectionView view) {
        this.view = view;
    }

    @Override
    public void loadEvents() {
        view.setProgressDialogVisible(true);

        EventSelectionWorker eventSelectionWorker = new EventSelectionWorkerImpl(this);
        eventSelectionWorker.loadEvents();
    }

    @Override
    public void loadLocations(Integer eventId) {
        view.setProgressDialogVisible(true);

        EventSelectionWorker eventSelectionWorker = new EventSelectionWorkerImpl(this);
        eventSelectionWorker.loadLocations(eventId);
    }

    @Override
    public void onLoadEventsSuccess(RealmList<Event> eventRealmList) {
        view.setProgressDialogVisible(false);
        view.setEvents(eventRealmList);
    }

    @Override
    public void onLoadEventsFail(String message) {
        view.setProgressDialogVisible(false);
        view.setErrors(message);
    }

    @Override
    public void onLoadLocationsSuccess(RealmList<Location> locationRealmList) {
        view.setProgressDialogVisible(false);
        view.setLocations(locationRealmList);
    }

    @Override
    public void onLoadLocationsFail(String message) {
        view.setProgressDialogVisible(false);
        view.setErrors(message);
    }
}

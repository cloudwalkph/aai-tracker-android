package com.cloudwalkph.aaitrackerandroid.eventSelection;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cloudwalkph.aaitrackerandroid.R;
import com.cloudwalkph.aaitrackerandroid.lib.model.CurrentEventLocation;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Event;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Location;
import com.cloudwalkph.aaitrackerandroid.lib.model.events.Poll;
import com.cloudwalkph.aaitrackerandroid.lib.ui.BaseFragment;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenController;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerImpl;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerProvider;
import com.cloudwalkph.aaitrackerandroid.poll.PollView;
import com.cloudwalkph.aaitrackerandroid.poll.PollViewImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import io.realm.RealmList;

/**
 * Created by trick.sunga on 06/12/2016.
 */

public class EventSelectionViewImpl extends BaseFragment implements EventSelectionView, ScreenControllerProvider {
    public static final String TAG = EventSelectionViewImpl.class.getSimpleName();
    private EventSelectionPresenter presenter;

    View rootView;
    ProgressDialog progressDialog;

    Integer eventId;
    String eventName;
    Integer eventLocationId;
    String eventLocationName;

    @BindView(R.id.eventSpinner)
    Spinner eventSpinner;
    @BindView(R.id.locationSpinner)
    Spinner locationSpinner;
    @BindView(R.id.goToPoll)
    Button goToPoll;

    private ScreenController screenController;

    @Override
    public String getScreenTitle() {
        return "";
    }

    @Override
    public ScreenController getScreenController() {
        return screenController;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_event_selection, container, false);
        initializePresenter();
        initializeScreenController();
        initializeView(rootView);

        initializeEvents();

        setHasOptionsMenu(true);
        return rootView;
    }

    private void initializePresenter(){
        presenter = new EventSelectionPresenterImpl(this);
    }

    private void initializeScreenController() {
        screenController = new ScreenControllerImpl(((AppCompatActivity) getActivity()).getSupportActionBar(), getActivity().getSupportFragmentManager(), R.id.container);
    }

    private void initializeView(View rootView){
        ButterKnife.bind(this, rootView);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
    }

    private void initializeEvents() {
        presenter.loadEvents();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.hitCount);

        item.setTitle("");
    }

    @OnItemSelected(R.id.eventSpinner)
    public void selectEvent(Spinner spinner) {
        Event event = (Event)spinner.getSelectedItem();
        this.eventId = event.getEventId();
        this.eventName = event.getName();
        presenter.loadLocations(event.getEventId());
    }

    @OnItemSelected(R.id.locationSpinner)
    public void selectLocation(Spinner spinner) {
        Location location = (Location)spinner.getSelectedItem();
        this.eventLocationId = location.getId();
        this.eventLocationName = location.getName();
    }

    @OnClick(R.id.goToPoll)
    public void goToPoll(View view) {
        if(eventId == 0) {
            Toast.makeText(getActivity(), "Please select event.", Toast.LENGTH_LONG).show();
        } else if(eventLocationId == 0) {
            Toast.makeText(getActivity(), "Please select location.", Toast.LENGTH_LONG).show();
        } else {
            CurrentEventLocation currentEventLocation = CurrentEventLocation.getInstance();
            currentEventLocation.setEventId(this.eventId);
            currentEventLocation.setEventName(this.eventName);
            currentEventLocation.setEventLocationId(this.eventLocationId);
            currentEventLocation.setEventLocationName(this.eventLocationName);
            screenController.addScreen(new PollViewImpl(), PollView.TAG);
        }
    }

    @Override
    public void setProgressDialogVisible(boolean visible) {
        if(visible) {
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Fetching data, please wait...");
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setEvents(RealmList<Event> eventRealmList) {
        ArrayList<Event> events = new ArrayList<Event>();
        events.addAll(eventRealmList.subList(0, eventRealmList.size()));
        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(getActivity(), R.layout.spinner_item, events);
        eventSpinner.setAdapter(adapter);
    }

    @Override
    public void setLocations(RealmList<Location> locationRealmList) {
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.addAll(locationRealmList.subList(0, locationRealmList.size()));
        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(getActivity(), R.layout.spinner_item, locations);
        locationSpinner.setAdapter(adapter);
    }

    @Override
    public void setErrors(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}

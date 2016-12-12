package com.cloudwalkph.aaitrackerandroid.poll;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.cloudwalkph.aaitrackerandroid.R;
import com.cloudwalkph.aaitrackerandroid.lib.model.CurrentEventLocation;
import com.cloudwalkph.aaitrackerandroid.lib.ui.BaseFragment;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenController;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerImpl;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerProvider;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by trick.sunga on 22/11/2016.
 */

public class PollViewImpl extends BaseFragment implements PollView, ScreenControllerProvider {
    public static final String TAG = PollViewImpl.class.getSimpleName();
    private PollPresenter presenter;

    View rootView;
    ProgressDialog progressDialog;

    File imageFile;
    String selectedGender;
    String selectedAge;
    String name;
    String contactNumber;
    String email;

    Integer hitCount = 0;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.picture)
    ImageView profilePic;
    @BindView(R.id.saveAnswer)
    Button saveAnswer;

    @BindView(R.id.genderMale)
    Button genderMale;
    @BindView(R.id.genderFemale)
    Button genderFemale;
    @BindViews({R.id.genderMale, R.id.genderFemale})
    List<Button> genderButtons;

    @BindView(R.id.age1520)
    Button age1520;
    @BindView(R.id.age2125)
    Button age2125;
    @BindView(R.id.age2630)
    Button age2530;
    @BindView(R.id.age3135)
    Button age3135;
    @BindViews({R.id.age1520, R.id.age2125, R.id.age2630, R.id.age3135})
    List<Button> ageButtons;

    @BindView(R.id.answerName)
    EditText answerName;
    @BindView(R.id.answerContactNumber)
    EditText answerContactNumber;
    @BindView(R.id.answerEmail)
    EditText answerEmail;
    @BindViews({R.id.answerName, R.id.answerContactNumber, R.id.answerEmail})
    List<EditText> textFields;

    @BindColor(R.color.medix_gray)
    int medixGray;
    @BindColor(R.color.medix_blue)
    int medixBlue;

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
        rootView = inflater.inflate(R.layout.fragment_poll, container, false);
        initializePresenter();
        initializeScreenController();
        initializeView(rootView);

        initializeHitCount();

        setHasOptionsMenu(true);
        return rootView;
    }

    private void initializePresenter(){
        presenter = new PollPresenterImpl(this);
    }

    private void initializeScreenController() {
        screenController = new ScreenControllerImpl(((AppCompatActivity) getActivity()).getSupportActionBar(), getActivity().getSupportFragmentManager(), R.id.container);
    }

    private void initializeView(View rootView){
        ButterKnife.bind(this, rootView);

        String currentEvent = CurrentEventLocation.getInstance().getEventName();
        String currentLocation = CurrentEventLocation.getInstance().getEventLocationName();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(currentEvent + " - " + currentLocation);

        selectedAge = "";
        selectedGender = "";
        name = "";
        contactNumber = "";
        email = "";
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
    }

    private void initializeHitCount() {
        hitCount = presenter.loadHitCount();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.hitCount);

        item.setTitle(hitCount + " hits");
    }

    @OnClick(R.id.picture)
    public void takePicture(View view) {
        EasyImage.openCamera(this, 0);
    }

    @OnClick({R.id.genderMale, R.id.genderFemale})
    public void selectGender(Button button) {
        switch(button.getId()) {
            case R.id.genderMale:
                selectedGender = "male";
                break;
            case R.id.genderFemale:
                selectedGender = "female";
                break;
        }
        button.setBackgroundColor(medixBlue);
        for (Button genderButton : genderButtons) {
            if (button.getId() != genderButton.getId()) {
                genderButton.setBackgroundColor(medixGray);
            }
        }
        Log.d("SELECTGENDER", selectedGender);
    }

    @OnClick({R.id.age1520, R.id.age2125, R.id.age2630, R.id.age3135})
    public void selectAge(Button button) {
        switch(button.getId()) {
            case R.id.age1520:
                selectedAge = "15-20";
                break;
            case R.id.age2125:
                selectedAge = "21-25";
                break;
            case R.id.age2630:
                selectedAge = "26-30";
                break;
            case R.id.age3135:
                selectedAge = "31-35";
                break;
        }
        button.setBackgroundColor(medixBlue);
        for (Button ageButton : ageButtons) {
            if (button.getId() != ageButton.getId()) {
                ageButton.setBackgroundColor(medixGray);
            }
        }
        Log.d("SELECTAGE", selectedAge);
    }

    @OnTextChanged(value = R.id.answerName, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void setAnswerName(Editable editable) {
        this.name = editable.toString();
    }

    @OnTextChanged(value = R.id.answerContactNumber, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void setAnswerContactNumber(Editable editable) {
        this.contactNumber = editable.toString();
    }

    @OnTextChanged(value = R.id.answerEmail, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void setAnswerEmail(Editable editable) {
        this.email = editable.toString();
    }

    @OnClick(R.id.saveAnswer)
    public void saveAnswer(View view) {
        // validate
        if(imageFile == null) {
            Toast.makeText(getActivity(), "Please capture an image.", Toast.LENGTH_LONG).show();
        } else if(selectedGender.equals("")) {
            Toast.makeText(getActivity(), "Please select gender.", Toast.LENGTH_LONG).show();
        } else if(selectedAge.equals("")) {
            Toast.makeText(getActivity(), "Please select age.", Toast.LENGTH_LONG).show();
        } else if (name.equals("")) {
            Toast.makeText(getActivity(), "Please enter name.", Toast.LENGTH_LONG).show();
        } else {
            presenter.saveAnswer(selectedAge, selectedGender, imageFile.getAbsolutePath(), name, contactNumber, email);
        }
    }

    ButterKnife.Action<View> RESET_BUTTONS = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setBackgroundColor(medixGray);
        }
    };

    ButterKnife.Action<EditText> RESET_FIELDS = new ButterKnife.Action<EditText>() {
        @Override
        public void apply(EditText editText, int index) {
            editText.setText("");
        }
    };

    @Override
    public void setProgressDialogVisible(boolean visible) {
        Log.d(TAG,"setProgressBarVisible "+visible );
        if(visible) {
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Saving data, please wait...");
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showSuccessDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Success!");
        alertDialog.setMessage("Answer has been saved. Press ok to dismiss.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void resetForm() {
        hitCount = presenter.loadHitCount();
        ActivityCompat.invalidateOptionsMenu(getActivity());
        scrollView.fullScroll(scrollView.FOCUS_UP);
        imageFile = null;
        selectedAge = "";
        selectedGender = "";
        name = "";
        contactNumber = "";
        email = "";
        ButterKnife.apply(genderButtons, RESET_BUTTONS);
        ButterKnife.apply(ageButtons, RESET_BUTTONS);
        ButterKnife.apply(textFields, RESET_FIELDS);
        Picasso.with(getContext())
                .load(R.drawable.camera)
                .into(profilePic);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
                //Handle the images
                imageFile = imageFiles.get(0);
                Picasso.with(getContext())
                        .load(imageFiles.get(0))
                        .placeholder(R.drawable.camera)
                        .error(R.drawable.camera)
                        .resize(320, 480)
                        .centerInside()
                        .into(profilePic);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
    }
}

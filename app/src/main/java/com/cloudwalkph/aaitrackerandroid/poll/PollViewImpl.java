package com.cloudwalkph.aaitrackerandroid.poll;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudwalkph.aaitrackerandroid.R;
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

        selectedAge = "";
        selectedGender = "";
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
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

    @OnClick(R.id.saveAnswer)
    public void saveAnswer(View view) {
        // validate
        if(imageFile == null) {
            Toast.makeText(getActivity(), "Please capture an image.", Toast.LENGTH_LONG).show();
        } else if(selectedGender.equals("")) {
            Toast.makeText(getActivity(), "Please select gender.", Toast.LENGTH_LONG).show();
        } else if(selectedAge.equals("")) {
            Toast.makeText(getActivity(), "Please select age.", Toast.LENGTH_LONG).show();
        } else {
            presenter.saveAnswer(selectedAge, selectedGender, imageFile.getAbsolutePath());
        }
    }

    ButterKnife.Action<View> RESET_BUTTONS = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setBackgroundColor(medixGray);
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
    public void setContainerVisible(boolean visible) {
        Log.d(TAG, "setContainerVisible " + visible);
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
        imageFile = null;
        selectedAge = "";
        selectedGender = "";
        ButterKnife.apply(genderButtons, RESET_BUTTONS);
        ButterKnife.apply(ageButtons, RESET_BUTTONS);
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
}

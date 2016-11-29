package com.cloudwalkph.aaitrackerandroid.poll;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cloudwalkph.aaitrackerandroid.R;
import com.cloudwalkph.aaitrackerandroid.lib.ui.BaseFragment;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenController;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerImpl;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerProvider;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.Bind;
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

    @Bind(R.id.picture)
    ImageView profilePic;
    @Bind(R.id.saveAnswer)
    Button saveAnswer;

    @Bind(R.id.genderMale)
    RadioButton genderMale;
    @Bind(R.id.genderFemale)
    RadioButton genderFemale;

    @Bind(R.id.age1520)
    RadioButton age1520;
    @Bind(R.id.age2125)
    RadioButton age2125;
    @Bind(R.id.age2630)
    RadioButton age2530;
    @Bind(R.id.age3135)
    RadioButton age3135;

    @Bind(R.id.genderGroup)
    RadioGroup genderGroup;
    @Bind(R.id.ageGroup)
    RadioGroup ageGroup;

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

        selectedAge = "15-20";
        selectedGender = "male";
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
    }

    @OnClick(R.id.picture)
    public void takePicture(View view) {
        EasyImage.openCamera(this, 0);
    }

    @OnClick({R.id.genderMale, R.id.genderFemale})
    public void selectGender(RadioButton radioButton) {
        switch(radioButton.getId()) {
            case R.id.genderMale:
                selectedGender = "male";
                break;
            case R.id.genderFemale:
                selectedGender = "female";
                break;
        }
        Log.d("SELECTGENDER", selectedGender);
    }

    @OnClick({R.id.age1520, R.id.age2125, R.id.age2630, R.id.age3135})
    public void selectAge(RadioButton radioButton) {
        switch(radioButton.getId()) {
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
        Log.d("SELECTAGE", selectedAge);
    }

    @OnClick(R.id.saveAnswer)
    public void saveAnswer(View view) {
        // validate
        if(imageFile == null) {
            Toast.makeText(getActivity(), "Please capture an image", Toast.LENGTH_LONG).show();
        } else {
            presenter.saveAnswer(selectedAge, selectedGender, imageFile.getAbsolutePath());
        }
    }

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
    public void resetForm() {
        imageFile = null;
        selectedAge = "15-20";
        selectedGender = "male";
        age1520.setChecked(true);
        genderMale.setChecked(true);
        Picasso.with(getContext())
                .load(R.drawable.ic_insert_photo_white_48dp)
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
//                        .placeholder(R.drawable.generic_profile_pic)
//                        .error(R.drawable.generic_profile_pic)
                        .resize(320, 480)
                        .centerInside()
                        .into(profilePic);
            }
        });
    }
}

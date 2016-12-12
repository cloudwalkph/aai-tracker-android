package com.cloudwalkph.aaitrackerandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.cloudwalkph.aaitrackerandroid.eventSelection.EventSelectionView;
import com.cloudwalkph.aaitrackerandroid.eventSelection.EventSelectionViewImpl;
import com.cloudwalkph.aaitrackerandroid.lib.ui.UiUtils;
import com.cloudwalkph.aaitrackerandroid.results.ResultView;
import com.cloudwalkph.aaitrackerandroid.results.ResultViewImpl;
import com.cloudwalkph.aaitrackerandroid.service.UploadService;
import com.cloudwalkph.aaitrackerandroid.lib.ui.OnBackPressedListener;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenController;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerImpl;
import com.cloudwalkph.aaitrackerandroid.lib.ui.ScreenControllerProvider;
import com.cloudwalkph.aaitrackerandroid.login.LoginView;
import com.cloudwalkph.aaitrackerandroid.login.LoginViewImpl;
import com.cloudwalkph.aaitrackerandroid.service.UploadServiceReceiver;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import pl.aprilapps.easyphotopicker.EasyImage;

public class MainActivity extends AppCompatActivity implements ScreenControllerProvider {

    ScreenControllerImpl screenController;
    OnBackPressedListener onBackPressedListener;
    UploadServiceReceiver uploadServiceReceiver;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                        .withDescendingOrder()
                        .withLimit(1000)
                        .databaseNamePattern(Pattern.compile(".+\\.realm"))
                        .build())
                .build());

        EasyImage.configuration(this)
                .setImagesFolderName("AAITracker") //images folder name, default is "EasyImage"
                //.saveInAppExternalFilesDir() //if you want to use root internal memory for storying images
                .saveInRootPicturesDirectory(); //if you want to use internal memory for storying images - default

        initializeServices();
        initializeToolbar();
        initializeScreenController();
        navigateToLoginScreen();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public ScreenController getScreenController() {
        return screenController;
    }

    private void initializeServices() {
        // Receiver
        IntentFilter filter = new IntentFilter(UploadServiceReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        uploadServiceReceiver = new UploadServiceReceiver(this);
        registerReceiver(uploadServiceReceiver, filter);

        // Service
        Intent intent = new Intent(this, UploadService.class);
        startService(intent);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setLogo(null);
    }

    private void initializeScreenController() {
        screenController = new ScreenControllerImpl(getSupportActionBar(), getSupportFragmentManager(), R.id.container);
    }

    private void navigateToLoginScreen() {
        screenController.navigateToScreen(new LoginViewImpl(), LoginView.TAG);
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {
                exitApp();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view != null && (view instanceof EditText || view instanceof AppCompatEditText)) {
                Rect r = new Rect();
                view.getGlobalVisibleRect(r);
                int rawX = (int)ev.getRawX();
                int rawY = (int)ev.getRawY();
                if (!r.contains(rawX, rawY)) {
                    UiUtils.hideKeyboard(this);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void exitApp() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up generic_button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.actionEventSelection:
                screenController.removeAllOtherScreens();
                screenController.navigateToScreen(new EventSelectionViewImpl(), EventSelectionView.TAG);
                break;
            case R.id.actionViewResult:
                screenController.removeAllOtherScreens();
                screenController.navigateToScreen(new ResultViewImpl(), ResultView.TAG);
                break;
            case R.id.actionLogout:
                exitApp();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (uploadServiceReceiver != null) {
                unregisterReceiver(uploadServiceReceiver);
            }
        } catch (IllegalArgumentException e) {
            uploadServiceReceiver = null;
        }
    }
}

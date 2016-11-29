package com.cloudwalkph.aaitrackerandroid.lib.ui;

import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

/**
 * <b>NOTE: All functionalities referencing to NavigationDrawer aren't implemented yet.<b></b><br><br>
 * <p/>
 * Does all the top-level screen management using a single view container for the fragments. The dependencies
 * of the implementors of this interface are the following: <br><br>
 * <p/>
 * - {@link ActionBar} or {@link Toolbar}<br>
 * - {@link FragmentManager}<br>
 * - a reference to the id of the fragment holder. This is the view where the fragments will be added/replaced into
 *
 * @author Julious Igmen
 */
public interface ScreenController extends FragmentManager.OnBackStackChangedListener {

    /**
     * Adds a screen to the stack, causing the toolbar to show a back arrow if visible.
     *
     * @param screen Instance of the Fragment to stack.
     * @param tag Unique string tag for the screen, preferably the class name.
     */
    public void addScreen(Fragment screen, String tag);

    void closeDrawer();

    /**
     * Checks if a Fragment with such tag exists in the backstack.
     *
     * @param tag Unique string tag for the screen, preferably the class name.
     * @return true If a Fragment with the tag in the parameter is in the backstack.
     */
    public boolean hasScreen(String tag);

    boolean isDrawerOpen();

    /**
     * @return true If we can swipe in the edges to show/hide the drawer.
     */
    public boolean isNavigationDrawerEnabled();

    public boolean isToolbarVisible();

    /**
     * Uses {@link FragmentTransaction#replace(int, Fragment)} to show a Fragment into the screen.
     *
     * @param screen Instance of the Fragment to show.
     * @param tag Unique string tag for the screen, preferably the class name.
     */
    public void navigateToScreen(Fragment screen, String tag);

    void openDrawer();

    /**
     * Pops the backstack, removing all stacking screens and all those added via {@link #addScreen(Fragment, String)}.
     */
    public void removeAllOtherScreens();

    /**
     * Removes the screen that is on top of the stack, which is added via {@link #addScreen(Fragment, String)}.
     */
    public void removeTopScreen();

    void setToolbarVisible(boolean visible);

    public void showDialog(DialogFragment dialog, String tag);

    /**
     * Used when the dialog comes from a fragment within a fragment.
     *
     * @param childFragmentManager Must be from {@link Fragment#getChildFragmentManager()}.
     */
    void showDialog(DialogFragment dialog, String tag, FragmentManager childFragmentManager);

    /**
     * @param enabled If the drawer can be dragged to show/hide from the edges (left/hide, wherever the drawer comes from).
     */
    public void setGlobalNavigationEnabled(boolean enabled);

    public void setNavigationDrawerEnabled(boolean enabled);

    public void setToolbarTitle(String title);

    void setToolbarSubtitle(String subtitle);

    void setToolbarHomeIcon(Drawable icon);
}
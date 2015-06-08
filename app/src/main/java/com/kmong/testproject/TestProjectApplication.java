package com.kmong.testproject;

import android.app.Application;

/**
 * Created by GON on 2015-03-24.
 */
public class TestProjectApplication  extends Application{

    // region Static Variables
    private static TestProjectApplication sCurrentApplication = null;
    // endregion

    @Override
    public void onCreate() {
        super.onCreate();

        sCurrentApplication = this;
    }

    // region Getters
    public static TestProjectApplication get() {
        return sCurrentApplication;
    }
}
// endregion
package com.example.android.dickssportinggoods.ApplicationActivity;

import android.app.Application;

import com.example.android.dickssportinggoods.dependencyInjection.component.ApplicationComponent;
import com.example.android.dickssportinggoods.dependencyInjection.component.DaggerApplicationComponent;
import com.example.android.dickssportinggoods.dependencyInjection.module.ApplicationModule;


public class VenueApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, "https://movesync-qa.dcsg.com"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

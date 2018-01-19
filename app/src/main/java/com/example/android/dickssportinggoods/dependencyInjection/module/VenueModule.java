package com.example.android.dickssportinggoods.dependencyInjection.module;


import dagger.Module;
import dagger.Provides;

import com.example.android.dickssportinggoods.api.VenueApiService;
import com.example.android.dickssportinggoods.dependencyInjection.scope.PerActivity;
import com.example.android.dickssportinggoods.mvp.View.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class VenueModule {
    private MainView mView;

    public VenueModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    VenueApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(VenueApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}

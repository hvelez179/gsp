package com.example.android.dickssportinggoods.dependencyInjection.component;


import dagger.Component;
import dagger.Module;

@PerActivity
@Module
@Component(modules = VenueModule.class, dependencies = ApplicationComponent.class)
public interface VenueComponent {
    void inject(MainActivity activity);
}

package mihau.eu.githubsearch;

import android.app.Activity;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerApplication;
import mihau.eu.githubsearch.di.component.AppComponent;
import mihau.eu.githubsearch.di.component.DaggerAppComponent;
import mihau.eu.githubsearch.di.module.AppModule;
import mihau.eu.githubsearch.di.module.RestModule;

public class GitHubApplication extends DaggerApplication {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .appModule(new AppModule(this))
                .restModule(new RestModule())
                .build();
        appComponent.inject(this);
        return appComponent;
    }
}

package mihau.eu.githubsearch.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import mihau.eu.githubsearch.di.module.ActivityBindingModule;
import mihau.eu.githubsearch.di.module.AppModule;
import mihau.eu.githubsearch.di.module.RestModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, RestModule.class, ActivityBindingModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        Builder appModule(AppModule appModule);

        Builder restModule(RestModule restModule);

        AppComponent build();

    }
}

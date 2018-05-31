package mihau.eu.githubsearch.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mihau.eu.githubsearch.api.GitHubRepository;
import mihau.eu.githubsearch.utils.providers.resources.AppResourcesProvider;
import mihau.eu.githubsearch.utils.providers.resources.ResourceProvider;
import mihau.eu.githubsearch.utils.providers.scheduler.AppSchedulerProvider;
import mihau.eu.githubsearch.utils.providers.scheduler.SchedulerProvider;
import mihau.eu.githubsearch.viewmodel.ViewModelFactory;

@Module
public class AppModule {

    Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(ResourceProvider resourceProvider, GitHubRepository gitHubRepository) {
        return new ViewModelFactory(resourceProvider, gitHubRepository);
    }

    @Provides
    @Singleton
    ResourceProvider provideResourceProvider() {
        return new AppResourcesProvider(app);
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}

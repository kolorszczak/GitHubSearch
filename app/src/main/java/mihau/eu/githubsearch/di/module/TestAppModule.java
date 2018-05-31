package mihau.eu.githubsearch.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mihau.eu.githubsearch.utils.providers.resources.ResourceProvider;
import mihau.eu.githubsearch.utils.providers.resources.TestResourceProvider;
import mihau.eu.githubsearch.utils.providers.scheduler.SchedulerProvider;
import mihau.eu.githubsearch.utils.providers.scheduler.TestSchedulerProvider;

@Module
public class TestAppModule {

    @Provides
    @Singleton
    ResourceProvider provideResourceProvider() {
        return new TestResourceProvider();
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new TestSchedulerProvider();
    }
}

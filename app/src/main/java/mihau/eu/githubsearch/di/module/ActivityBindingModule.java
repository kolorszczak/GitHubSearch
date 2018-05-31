package mihau.eu.githubsearch.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mihau.eu.githubsearch.view.DescriptionActivity;
import mihau.eu.githubsearch.view.SearchActivity;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract SearchActivity bindSearchActivity();

    @ContributesAndroidInjector
    abstract DescriptionActivity bindDescriptionActivity();
}

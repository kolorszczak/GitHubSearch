package mihau.eu.githubsearch.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import javax.inject.Inject;

import mihau.eu.githubsearch.api.GitHubRepository;
import mihau.eu.githubsearch.utils.providers.resources.ResourceProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    public ResourceProvider resourcesProvider;
    public GitHubRepository repository;

    @Inject
    public ViewModelFactory(ResourceProvider resourcesProvider, GitHubRepository repository) {
        this.resourcesProvider = resourcesProvider;
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository, resourcesProvider);
        }
        throw new RuntimeException("Unknown viewModel class");
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T get(@NonNull Class<T> modelClass, FragmentActivity activity) {
        return ViewModelProviders.of(activity, this).get(modelClass);
    }

}
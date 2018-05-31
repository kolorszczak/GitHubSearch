package mihau.eu.githubsearch.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;
import mihau.eu.githubsearch.api.GitHubRepository;
import mihau.eu.githubsearch.model.User;
import mihau.eu.githubsearch.utils.providers.resources.ResourceProvider;

public class UserViewModel extends ViewModel {

    private static final String TAG = UserViewModel.class.getSimpleName();

    public ObservableBoolean isError = new ObservableBoolean();
    public ObservableBoolean isLoading = new ObservableBoolean();
    public PublishSubject<SearchEvent> searchEventPublishSubject = PublishSubject.create();

    private User user;
    private GitHubRepository gitHubRepository;
    private ResourceProvider resourcesProvider;


    @Inject
    public UserViewModel(GitHubRepository gitHubRepository, ResourceProvider resourcesProvider) {
        this.gitHubRepository = gitHubRepository;
        this.resourcesProvider = resourcesProvider;
        this.isError.set(true);
        this.isLoading.set(false);
    }

    public User getUser() {
        return user;
    }


    @SuppressLint("CheckResult")
    public void search(String userName) {
        if (!isLoading.get()) {

            isLoading.set(true);
            isError.set(false);

            searchEventPublishSubject.onNext(new SearchEvent(SearchEvent.Type.LOADING));

            gitHubRepository.getUser(userName)
                    .subscribe(user -> {
                        this.user = user;
                        searchEventPublishSubject.onNext(new SearchEvent(SearchEvent.Type.SUCCESS));
                    }, throwable -> {
                        isLoading.set(false);
                        isError.set(true);
                        searchEventPublishSubject.onNext(new SearchEvent(SearchEvent.Type.ERROR, throwable));
                    }, () -> {
                        isLoading.set(false);
                    });
        }
    }
}

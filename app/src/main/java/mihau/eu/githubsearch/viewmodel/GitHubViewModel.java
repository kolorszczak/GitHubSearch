package mihau.eu.githubsearch.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.api.RestClient;
import mihau.eu.githubsearch.model.Repository;
import mihau.eu.githubsearch.model.Result;
import mihau.eu.githubsearch.model.User;
import mihau.eu.githubsearch.utils.providers.AppResourcesProvider;

public class GitHubViewModel {

    private static final String TAG = GitHubViewModel.class.getSimpleName();

    public ObservableField<String> keyword = new ObservableField<>();

    public ObservableBoolean isError = new ObservableBoolean();
    public ObservableField<String> error = new ObservableField<>();
    public ObservableInt errorImgResId = new ObservableInt();

    public ObservableLong userTotal = new ObservableLong(0L);
    public ObservableLong repositoryTotal = new ObservableLong(0L);
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public Integer currentUserPage = 1;
    public Integer currentRepositoryPage = 1;
    public List<Repository> repositories = new ArrayList<>();
    public List<User> users = new ArrayList<>();

    private AppResourcesProvider resourcesProvider;
    public PublishSubject<SearchEvent> searchEventPublishSubject = PublishSubject.create();

    public GitHubViewModel(AppResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;

        this.isError.set(true);
        this.error.set(resourcesProvider.getString(R.string.emptyContent));
        this.errorImgResId.set(R.drawable.im_empty);
    }

    public void clear() {
        isLoading.set(false);
        keyword.set("");
        currentRepositoryPage = 1;
        currentUserPage = 1;
        userTotal.set(0);
        repositoryTotal.set(0);
        isError.set(true);
        error.set(resourcesProvider.getString(R.string.emptyContent));
        errorImgResId.set(R.drawable.im_empty);
        repositories.clear();
        users.clear();
    }

    @SuppressLint("CheckResult")
    public void search(String query) {
        if (!isLoading.get() ||
                !(query.equals(keyword.get()) &&
                        (currentUserPage == 1 && currentRepositoryPage == 1))) {


            if (currentRepositoryPage == 1 && currentUserPage == 1) {
                searchEventPublishSubject.onNext(new SearchEvent(SearchEvent.Type.CLEAR));
            }

            isLoading.set(true);
            isError.set(false);
            keyword.set(query);

            searchEventPublishSubject.onNext(new SearchEvent(SearchEvent.Type.LOADING));

            Observable
                    .zip(RestClient.getInstance().searchRepositories(query, currentRepositoryPage),
                            RestClient.getInstance().searchUsers(query, currentUserPage),
                            (repositoryResponse, userResponse) -> {
                                userTotal.set(userResponse.getTotalCount());
                                repositoryTotal.set(repositoryResponse.getTotalCount());
                                return new Result(repositoryResponse, userResponse);
                            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        repositories.addAll(result.getRepositories().getItems());
                        users.addAll(result.getUsers().getItems());
                        searchEventPublishSubject.onNext(new SearchEvent(SearchEvent.Type.SUCCESS));
                    }, throwable -> {
                        isLoading.set(false);
                        searchEventPublishSubject.onNext(new SearchEvent(SearchEvent.Type.ERROR, throwable));
                    }, () -> {
                        isLoading.set(false);
                        currentUserPage++;
                        currentRepositoryPage++;
                    });
        }
    }
}
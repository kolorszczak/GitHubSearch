package mihau.eu.githubsearch.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;
import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.api.GitHubRepository;
import mihau.eu.githubsearch.model.Repository;
import mihau.eu.githubsearch.model.User;
import mihau.eu.githubsearch.utils.list.item.RepositoryItem;
import mihau.eu.githubsearch.utils.list.item.UserItem;
import mihau.eu.githubsearch.utils.providers.resources.ResourceProvider;

public class SearchViewModel extends ViewModel {

    private static final String TAG = SearchViewModel.class.getSimpleName();

    public ObservableField<String> keyword = new ObservableField<>();

    public ObservableBoolean isError = new ObservableBoolean();
    public ObservableField<String> error = new ObservableField<>();
    public ObservableInt errorImgResId = new ObservableInt();

    public ObservableLong userTotal = new ObservableLong(0L);
    public ObservableLong repositoryTotal = new ObservableLong(0L);
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public Integer currentUserPage = 1;
    public Integer currentRepositoryPage = 1;

    public PublishSubject<SearchEvent> searchEventPublishSubject = PublishSubject.create();

    private List<Repository> repositories = new ArrayList<>();
    private List<User> users = new ArrayList<>();private GitHubRepository gitHubRepository;
    private ResourceProvider resourcesProvider;


    @Inject
    public SearchViewModel(GitHubRepository gitHubRepository, ResourceProvider resourcesProvider) {
        this.gitHubRepository = gitHubRepository;
        this.resourcesProvider = resourcesProvider;
        this.isError.set(true);
        this.keyword.set("");
        this.error.set(resourcesProvider.getString(R.string.emptyContent));
        this.errorImgResId.set(R.drawable.im_empty);
    }

    @SuppressWarnings("unchecked")
    public List<? extends AbstractItem> getList() {
        List list = new ArrayList();
        for (Repository repository : repositories) {
            list.add(new RepositoryItem(repository));
        }
        for (User user : users) {
            list.add(new UserItem(user));
        }
        return list;
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


            gitHubRepository.getQuery(query, currentRepositoryPage, currentUserPage)
                    .subscribe(result -> {
                        userTotal.set(result.getUsers().getTotalCount());
                        repositoryTotal.set(result.getRepositories().getTotalCount());
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
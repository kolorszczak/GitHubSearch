package mihau.eu.githubsearch.api;

import javax.inject.Inject;

import io.reactivex.Observable;
import mihau.eu.githubsearch.model.Result;
import mihau.eu.githubsearch.utils.providers.scheduler.SchedulerProvider;

public class GitHubRepository {

    private APIService apiService;
    private SchedulerProvider schedulerProvider;

    @Inject
    public GitHubRepository(APIService apiService, SchedulerProvider schedulerProvider) {
        this.apiService = apiService;
        this.schedulerProvider = schedulerProvider;
    }

    public Observable<Result> getQuery(String query, Integer currentRepositoryPage, Integer currentUserPage) {
        return Observable
                .zip(apiService.searchRepositories(query, currentRepositoryPage),
                        apiService.searchUsers(query, currentUserPage),
                        Result::new)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui());
    }
}

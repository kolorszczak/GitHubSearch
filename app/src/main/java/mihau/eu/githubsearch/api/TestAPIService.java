package mihau.eu.githubsearch.api;

import java.util.ArrayList;

import io.reactivex.Observable;
import mihau.eu.githubsearch.model.Owner;
import mihau.eu.githubsearch.model.Repository;
import mihau.eu.githubsearch.model.Response;
import mihau.eu.githubsearch.model.User;

public class TestAPIService implements APIService {

    @Override
    public Observable<Response<Repository>> searchRepositories(String keywords, Integer page) {
        ArrayList<Repository> repositories = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            repositories.add(new Repository("name", i, "url",
                    new Owner("", "", "", "", "", ""),
                    true, "", "", false, "", "", "",
                    "", "", 1, 2, 2, "",
                    2, 2, "", "", 2D));
        }
        return Observable.just(new Response<>(30, false, repositories));
    }

    @Override
    public Observable<Response<User>> searchUsers(String keywords, Integer page) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            users.add(new User("login", i, "url", "gravatar", "ur",
                    "", "", "", "", "", "", "", 2D));
        }
        return Observable.just(new Response<>(30, false, users));
    }
}

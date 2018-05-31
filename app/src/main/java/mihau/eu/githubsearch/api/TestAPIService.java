package mihau.eu.githubsearch.api;

import java.util.ArrayList;

import io.reactivex.Observable;
import mihau.eu.githubsearch.model.Repository;
import mihau.eu.githubsearch.model.Response;
import mihau.eu.githubsearch.model.User;

public class TestAPIService implements APIService {

    @Override
    public Observable<Response<Repository>> searchRepositories(String keywords, Integer page) {
        if (page == 4) {
            return Observable.error(new Throwable("Mock error"));
        }

        ArrayList<Repository> repositories = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            repositories.add(new Repository("name", i, "url",
                    new User("login", i, "url", "gravatar", "ur",
                            "", "", "", "", "", "", 12, 33, "", 2D),
                    true, "", "", false, "", "", "",
                    "", "", 1, 2, 2, "",
                    2, 2, "", "", 2D));
        }
        return Observable.just(new Response<>(120, false, repositories));
    }

    @Override
    public Observable<Response<User>> searchUsers(String keywords, Integer page) {
        if (page == 4) {
            return Observable.error(new Throwable("Mock error"));
        }

        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            users.add(new User("login", i, "url", "gravatar", "ur",
                    "", "", "", "", "", "", 12, 33, "", 2D));
        }
        return Observable.just(new Response<>(120, false, users));
    }

    @Override
    public Observable<User> searchUser(String userName) {
        if (userName == null) {
            return Observable.error(new Throwable("Mock error"));
        }

        return Observable.just(new User("login", 1, "url", "gravatar", "ur",
                "", "", "", "", "", "", 12, 33, "", 2D));
    }


}

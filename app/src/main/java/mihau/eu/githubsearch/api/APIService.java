package mihau.eu.githubsearch.api;

import io.reactivex.Observable;
import mihau.eu.githubsearch.model.Repository;
import mihau.eu.githubsearch.model.Response;
import mihau.eu.githubsearch.model.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("search/repositories")
    Observable<Response<Repository>> searchRepositories(@Query("q") String keywords, @Query("page") Integer page);

    @GET("search/users")
    Observable<Response<User>> searchUsers(@Query("q") String keywords, @Query("page") Integer page);

    @GET("users/{userName}")
    Observable<User> searchUser(@Path("userName") String userName);
}

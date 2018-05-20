package mihau.eu.githubsearch.api;

import io.reactivex.Observable;
import mihau.eu.githubsearch.api.response.RepositoryResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("search/repositories")
    Observable<RepositoryResponse> searchRepositories(@Query("q") String keywords,
                                                      @Query("sort") String sortBy,
                                                      @Query("order") String order);
}

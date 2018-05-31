package mihau.eu.githubsearch.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mihau.eu.githubsearch.BuildConfig;
import mihau.eu.githubsearch.api.APIService;
import mihau.eu.githubsearch.api.GitHubRepository;
import mihau.eu.githubsearch.utils.providers.scheduler.SchedulerProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestModule {

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Accept", "*/*").build();
                    return chain.proceed(request);
                });

        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    APIService provideApiService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @Provides
    @Singleton
    static GitHubRepository provideGithubRepository(APIService service, SchedulerProvider schedulerProvider) {
        return new GitHubRepository(service, schedulerProvider);
    }
}

package mihau.eu.githubsearch.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mihau.eu.githubsearch.api.RestClient;
import mihau.eu.githubsearch.utils.manager.ErrorUtils;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestClient.getInstance()
                .searchRepositories("GitHubSearch???!@?#?!@#?@SAKDNJMAOKSDNASOJKDNJAUSD", "updated", "desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryResponse -> {
                    Log.d(TAG, repositoryResponse.toString());
                }, throwable -> {
                    ErrorUtils.parseThrowable(SplashActivity.this, throwable);
                });
    }
}
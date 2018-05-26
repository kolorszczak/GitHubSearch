package mihau.eu.githubsearch.viewmodel;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.api.RestClient;
import mihau.eu.githubsearch.model.Query;
import mihau.eu.githubsearch.utils.list.item.SearchItem;
import mihau.eu.githubsearch.utils.manager.AppUtils;

public class Events {

    private static final String TAG = GitHubViewModel.class.getSimpleName();

    private Context context;
    private GitHubViewModel viewModel;

    public Events(Context context, GitHubViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @SuppressLint("CheckResult")
    public void search(String keyword) {
        if (viewModel.isLoading.get() ||
                (keyword.equals(viewModel.keyword.get()) &&
                        (viewModel.currentUserPage == 1 && viewModel.currentRepositoryPage == 1))) {
            return;
        }

        viewModel.isLoading.set(true);
        viewModel.keyword.set(keyword);

        if (viewModel.currentRepositoryPage == 1 && viewModel.currentUserPage == 1) {
            viewModel.itemAdapter.clear();
        }

        Observable
                .zip(RestClient.getInstance().searchRepositories(keyword, viewModel.currentRepositoryPage),
                        RestClient.getInstance().searchUsers(keyword, viewModel.currentUserPage),
                        (repositoryResponse, userResponse) -> {
                            viewModel.userTotal.set(userResponse.totalCount);
                            viewModel.repositoryTotal.set(repositoryResponse.totalCount);
                            return new Query(repositoryResponse, userResponse);
                        })
                .flatMapIterable((Function<Query, Iterable<SearchItem>>) searchItems -> searchItems)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchItem -> {
                    viewModel.itemAdapter.add(searchItem);
                }, throwable -> {
                    new AlertDialog.Builder(context)
                            .setTitle(context.getString(R.string.error))
                            .setMessage(AppUtils.parseThrowable(context, throwable)).show();
                }, () -> {
                    viewModel.isLoading.set(false);
                    viewModel.currentUserPage++;
                    viewModel.currentRepositoryPage++;
                });
    }
}

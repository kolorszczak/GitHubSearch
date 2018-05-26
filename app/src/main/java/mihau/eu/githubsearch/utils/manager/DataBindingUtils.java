package mihau.eu.githubsearch.utils.manager;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;

import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.utils.list.RxSearchObservable;
import mihau.eu.githubsearch.viewmodel.GitHubViewModel;

public class DataBindingUtils {

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"bindObservable"})
    public static void setBindingObservable(SearchView view, GitHubViewModel viewModel) {
        RxSearchObservable.fromView(view)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(text -> !text.isEmpty())
                .filter(s -> !s.equals(viewModel.keyword.get()))
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    viewModel.itemAdapter.clear();
                    viewModel.events.search(s);
                });

        ImageView closeButton = view.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(v -> new Handler().post(() -> {
            viewModel.clear();
            view.setQuery("", false);
        }));
    }
}

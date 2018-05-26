package mihau.eu.githubsearch.utils.manager;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
                    viewModel.events.search(s);
                });

        ImageView closeButton = view.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(v -> new Handler().post(() -> {
            viewModel.clear();
            view.setQuery("", false);
        }));
    }

    @BindingAdapter(value = {"paging"})
    public static void setPaging(RecyclerView view, GitHubViewModel viewModel) {
        view.addOnScrollListener(new EndlessRecyclerOnScrollListener(viewModel.footerAdapter) {

            @Override
            public void onLoadMore(int currentPage) {
                if (!viewModel.isLoading.get() && currentPage * 30 < viewModel.userTotal.get() + viewModel.repositoryTotal.get()) {
                    new Handler().post(() -> viewModel.events.search(viewModel.keyword.get()));
                }
            }
        });
    }

    @BindingAdapter(value = {"itemDecoration"})
    public static void setItemDecoration(RecyclerView view, RecyclerView.ItemDecoration itemDecoration) {
        view.addItemDecoration(itemDecoration);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter(value = {"url"})
    public static void setUrl(WebView view, String url) {
        WebSettings webSetting = view.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setAllowFileAccessFromFileURLs(true);
        webSetting.setAllowUniversalAccessFromFileURLs(true);
        webSetting.setBlockNetworkImage(false);

        view.setWebChromeClient(new WebChromeClient());
        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        view.loadUrl(url);
    }
}

package mihau.eu.githubsearch.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.commons.utils.FastAdapterDiffUtil;
import com.mikepenz.fastadapter.utils.ComparableItemListImpl;
import com.mikepenz.fastadapter_extensions.items.ProgressItem;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.base.BaseActivity;
import mihau.eu.githubsearch.databinding.ActivitySearchBinding;
import mihau.eu.githubsearch.utils.list.AscendingComparator;
import mihau.eu.githubsearch.utils.list.RxSearchObservable;
import mihau.eu.githubsearch.utils.list.VerticalSpaceItemDecoration;
import mihau.eu.githubsearch.utils.list.item.UserItem;
import mihau.eu.githubsearch.utils.providers.resources.ResourceProvider;
import mihau.eu.githubsearch.viewmodel.SearchViewModel;
import mihau.eu.githubsearch.viewmodel.ViewModelFactory;

public class SearchActivity extends BaseActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    ResourceProvider resourceProvider;

    public FastAdapter fastAdapter;
    public ItemAdapter itemAdapter;
    public ItemAdapter<ProgressItem> footerAdapter;
    public VerticalSpaceItemDecoration itemDecoration;

    private SearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        viewModel = viewModelFactory.get(SearchViewModel.class, this);
        binding.setViewModel(viewModel);
        setupAdapters(this, binding.recyclerView);
        setPaging(binding.recyclerView);
        setupSearchVIew(binding.searchView);
    }

    private void handleError(Throwable throwable) {
        if (resourceProvider.getErrorCode(throwable) == 503) {
            viewModel.error.set(resourceProvider.getString(R.string.noInternet));
            viewModel.errorImgResId.set(R.drawable.im_internet);
            viewModel.isError.set(true);
        } else if (viewModel.currentRepositoryPage == 1 && viewModel.currentUserPage == 1 && resourceProvider.getErrorCode(throwable) >= 400) {
            viewModel.error.set(resourceProvider.getString(R.string.emptyContent));
            viewModel.errorImgResId.set(R.drawable.im_empty);
            viewModel.isError.set(true);
        }

        new AlertDialog.Builder(this)
                .setTitle(resourceProvider.getString(R.string.error))
                .setMessage(resourceProvider.getErrorMessage(throwable)).show();
    }

    @SuppressWarnings("unchecked")
    @SuppressLint("CheckResult")
    private void setupSearchVIew(SearchView view) {
        RxSearchObservable.fromView(view)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(text -> !text.isEmpty())
                .filter(s -> !s.equals(viewModel.keyword.get()))
                .distinctUntilChanged()
                .subscribe(s -> viewModel.search(s));

        viewModel.searchEventPublishSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchEvent -> {
                    switch (searchEvent.type) {
                        case LOADING:
                            footerAdapter.add(new ProgressItem());
                            break;
                        case SUCCESS:
                            footerAdapter.clear();
                            DiffUtil.DiffResult diff = FastAdapterDiffUtil.calculateDiff(itemAdapter, viewModel.getList());
                            FastAdapterDiffUtil.set(itemAdapter, diff);
                            break;
                        case CLEAR:
                            itemAdapter.clear();
                            footerAdapter.clear();
                            break;
                        case ERROR:
                            footerAdapter.clear();
                            handleError(searchEvent.error);
                            break;
                    }
                });

        ImageView closeButton = view.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(v -> new Handler().post(() -> {
            viewModel.clear();
            view.setQuery("", false);
        }));
    }

    public void setPaging(RecyclerView view) {
        view.addOnScrollListener(new EndlessRecyclerOnScrollListener(footerAdapter) {

            @Override
            public void onLoadMore(int currentPage) {
                if (!viewModel.isLoading.get() && currentPage * 30 < viewModel.userTotal.get() + viewModel.repositoryTotal.get()) {
                    new Handler().post(() -> viewModel.search(viewModel.keyword.get()));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void setupAdapters(Context context, RecyclerView recyclerView) {
        itemAdapter = new ItemAdapter<>(new ComparableItemListImpl<>(new AscendingComparator()));
        footerAdapter = new ItemAdapter<>();
        itemDecoration = new VerticalSpaceItemDecoration();


        fastAdapter = FastAdapter.with(Arrays.asList(itemAdapter, footerAdapter));
        FastAdapterDiffUtil.set(itemAdapter, viewModel.getList());
        fastAdapter.withOnClickListener((v, adapter, item, position) -> {
            if (item instanceof UserItem && ((UserItem) item).user != null) {
                context.startActivity(new Intent(context, DescriptionActivity.class).putExtra("user", ((UserItem) item).user));
                ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return false;
        });

        recyclerView.setAdapter(fastAdapter);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration());
    }
}

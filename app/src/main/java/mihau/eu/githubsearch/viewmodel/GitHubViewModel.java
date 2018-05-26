package mihau.eu.githubsearch.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter_extensions.items.ProgressItem;

import java.util.Arrays;

import mihau.eu.githubsearch.utils.list.item.SearchItem;
import mihau.eu.githubsearch.view.DescriptionActivity;

public class GitHubViewModel {

    private static final String TAG = GitHubViewModel.class.getSimpleName();

    public ObservableField<String> keyword = new ObservableField<>();
    public ObservableLong userTotal = new ObservableLong(0L);
    public ObservableLong repositoryTotal = new ObservableLong(0L);
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public Integer currentUserPage = 1;
    public Integer currentRepositoryPage = 1;

    public FastAdapter fastAdapter;
    public ItemAdapter<SearchItem> itemAdapter;
    public ItemAdapter<ProgressItem> footerAdapter;

    public GitHubViewModel(Context context) {
        setupAdapters(context);
    }

    private void setupAdapters(Context context) {
        itemAdapter = new ItemAdapter<>();
        footerAdapter = new ItemAdapter<>();

        fastAdapter = FastAdapter.with(Arrays.asList(itemAdapter, footerAdapter));
        fastAdapter.withOnClickListener((v, adapter, item, position) -> {
            if (item instanceof SearchItem) {
                context.startActivity(new Intent(context, DescriptionActivity.class));
                ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return false;
        });
    }

    public void clear() {
        itemAdapter.clear();
        isLoading.set(false);
        keyword.set("");
        currentRepositoryPage = 1;
        currentUserPage = 1;
        userTotal.set(0);
        repositoryTotal.set(0);
    }
}
package mihau.eu.githubsearch.utils.list.item;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.databinding.ItemSearchBinding;
import mihau.eu.githubsearch.model.Repository;
import mihau.eu.githubsearch.model.User;

public class SearchItem extends AbstractItem<SearchItem, SearchItem.ViewHolder> {

    public Repository repository;
    public User user;

    public SearchItem(Repository repository) {
        this.repository = repository;
        this.user = null;
    }

    public SearchItem(User user) {
        this.repository = null;
        this.user = user;
    }

    @Override
    public int getType() {
        return R.id.item_search;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_search;
    }

    @Override
    public void bindView(@NonNull SearchItem.ViewHolder holder, @NonNull List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.binding.setName(repository == null ? user.login : repository.name);
        holder.binding.setTitle(repository == null ? getString(holder, R.string.repository) : getString(holder, R.string.user));
    }

    @Override
    public long getIdentifier() {
        return repository == null ? user.id : repository.id;
    }

    private String getString(SearchItem.ViewHolder holder, @StringRes int string) {
        return holder.itemView.getContext().getString(string);
    }

    @Override
    public SearchItem.ViewHolder getViewHolder(View v) {
        return new SearchItem.ViewHolder(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemSearchBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemSearchBinding.bind(itemView);
        }
    }
}
package mihau.eu.githubsearch.utils.list.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.databinding.ItemRepositoryBinding;
import mihau.eu.githubsearch.model.Repository;

public class RepositoryItem extends AbstractItem<RepositoryItem, RepositoryItem.ViewHolder> {

    public Repository repository;

    public RepositoryItem(Repository repository) {
        this.repository = repository;
    }

    @Override
    public int getType() {
        return R.id.item_repository;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_repository;
    }

    @Override
    public void bindView(@NonNull RepositoryItem.ViewHolder holder, @NonNull List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.binding.setRepository(repository);
    }

    @Override
    public long getIdentifier() {
        return repository.getId();
    }

    @NonNull
    @Override
    public RepositoryItem.ViewHolder getViewHolder(View v) {
        return new RepositoryItem.ViewHolder(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemRepositoryBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemRepositoryBinding.bind(itemView);
        }
    }
}
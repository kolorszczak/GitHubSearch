package mihau.eu.githubsearch.utils.list.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.databinding.ItemUserBinding;
import mihau.eu.githubsearch.model.User;

public class UserItem extends AbstractItem<UserItem, UserItem.ViewHolder> {

    public User user;

    public UserItem(User user) {
        this.user = user;
    }

    @Override
    public int getType() {
        return R.id.item_user;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_user;
    }

    @Override
    public void bindView(@NonNull UserItem.ViewHolder holder, @NonNull List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.binding.setUser(user);
    }

    @Override
    public long getIdentifier() {
        return user.getId();
    }

    @Override
    public UserItem.ViewHolder getViewHolder(View v) {
        return new UserItem.ViewHolder(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = ItemUserBinding.bind(itemView);
        }
    }
}
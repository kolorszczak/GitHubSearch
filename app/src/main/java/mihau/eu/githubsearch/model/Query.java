package mihau.eu.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.facebook.stetho.common.ListUtil;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import mihau.eu.githubsearch.utils.list.item.SearchItem;
import mihau.eu.githubsearch.utils.manager.ParcelableUtils;

public class Query implements Iterable<SearchItem>, Parcelable {

    public List<Repository> repositories;
    public List<User> users;

    public Query(Response<Repository> repositories, Response<User> users) {
        this.repositories = repositories.items;
        this.users = users.items;
    }

    public Query(Parcel in) {
        this.repositories = ParcelableUtils.readParcelableListHelper(in);
        this.users = ParcelableUtils.readParcelableListHelper(in);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;

        Query query = (Query) o;
        return ListUtil.identityEquals(repositories, query.repositories) &&
                ListUtil.identityEquals(users, query.users);
    }

    @Override
    public int hashCode() {
        return 4 * repositories.hashCode() + users.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        ParcelableUtils.writeParcelableListHelper(out, this.repositories);
        ParcelableUtils.writeParcelableListHelper(out, this.users);
    }

    public static final Creator<Query> CREATOR = new Creator<Query>() {

        public Query createFromParcel(Parcel in) {
            return new Query(in);
        }

        public Query[] newArray(int size) {
            return new Query[size];
        }
    };

    @NonNull
    @Override
    public Iterator<SearchItem> iterator() {
        return new Iterator<SearchItem>() {
            private int cursor = 0, all = repositories.size() + users.size();

            @Override
            public boolean hasNext() {
                return cursor < all;
            }

            @Override
            public SearchItem next() {
                if (hasNext()) {
                    if (repositories.size() > users.size()) {
                        if (all - repositories.size() <= cursor) {
                            return new SearchItem(repositories.get(cursor++ - users.size()));
                        } else {
                            return new SearchItem(users.get(cursor++));
                        }
                    } else {
                        if (all - users.size() <= cursor) {
                            return new SearchItem(users.get(cursor++ - repositories.size()));
                        } else {
                            return new SearchItem(repositories.get(cursor++));
                        }
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }
}

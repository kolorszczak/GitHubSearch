package mihau.eu.githubsearch.model;

import com.facebook.stetho.common.ListUtil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response<T> {

    @SerializedName("total_count")
    public Integer totalCount;
    @SerializedName("incomplete_results")
    public Boolean incompleteResults;
    @SerializedName("items")
    public List<T> items;

    public Response(Integer totalCount, Boolean incompleteResults, List<T> items) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;

        Response<T> response = (Response<T>) o;
        return ListUtil.identityEquals(items, response.items);
    }

    @Override
    public int hashCode() {
        return 6 * items.hashCode();
    }
}


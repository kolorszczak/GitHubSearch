package mihau.eu.githubsearch.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mihau.eu.githubsearch.model.Item;

public class RepositoryResponse {

    @SerializedName("total_count")
    public Integer totalCount;
    @SerializedName("incomplete_results")
    public Boolean incompleteResults;
    @SerializedName("items")
    public List<Item> items;

    public RepositoryResponse(Integer totalCount, Boolean incompleteResults, List<Item> items) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }
}

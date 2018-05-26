package mihau.eu.githubsearch.utils.list;

import java.io.Serializable;
import java.util.Comparator;

import mihau.eu.githubsearch.utils.list.item.SearchItem;

public class AscendingComparator implements Comparator<SearchItem>, Serializable {
    @Override
    public int compare(SearchItem lhs, SearchItem rhs) {
        return Long.compare(lhs.getIdentifier(), rhs.getIdentifier());
    }
}
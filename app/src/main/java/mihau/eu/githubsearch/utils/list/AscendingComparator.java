package mihau.eu.githubsearch.utils.list;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.io.Serializable;
import java.util.Comparator;

public class AscendingComparator implements Comparator<AbstractItem>, Serializable {
    @Override
    public int compare(AbstractItem lhs, AbstractItem rhs) {
        return Long.compare(lhs.getIdentifier(), rhs.getIdentifier());
    }
}
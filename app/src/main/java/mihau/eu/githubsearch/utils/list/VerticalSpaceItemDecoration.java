package mihau.eu.githubsearch.utils.list;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int gap = (int) (20 * view.getContext().getResources().getDisplayMetrics().density);

        if (parent.getAdapter().getItemCount() == 1) {
            //if we have one item
            outRect.top = gap;
            outRect.bottom = gap;
        } else {
            //if we have two and more items
            if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                outRect.top = gap;
                outRect.bottom = gap;
            } else {
                outRect.top = gap;
            }
        }
    }
}
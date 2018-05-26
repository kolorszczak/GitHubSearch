package mihau.eu.githubsearch.base;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import mihau.eu.githubsearch.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            if (view instanceof EditText) {
                view.clearFocus();
            }

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        super.onBackPressed();
    }

    private View findViewAt(ViewGroup viewGroup, int x, int y) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                View foundView = findViewAt((ViewGroup) child, x, y);
                if (foundView != null && foundView.isShown()) {
                    return foundView;
                }
            } else {
                int[] location = new int[2];
                child.getLocationOnScreen(location);
                Rect rect = new Rect(location[0], location[1], location[0] + child.getWidth(), location[1] + child.getHeight());
                if (rect.contains(x, y)) {
                    return child;
                }
            }
        }

        return null;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText || v instanceof SearchView) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();

                    View newFocus = findViewAt(findViewById(R.id.coordinator), (int) event.getRawX(), (int) event.getRawY());

                    if (newFocus == null || !(newFocus instanceof EditText)) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}

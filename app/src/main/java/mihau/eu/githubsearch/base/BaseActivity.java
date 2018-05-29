package mihau.eu.githubsearch.base;

import android.app.AlertDialog;
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

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (view != null && imm != null
                && !imm.hideSoftInputFromWindow(view.getWindowToken(), 0) && isTaskRoot()) {
            if (view instanceof EditText) {
                view.clearFocus();
            }
            showExitDialog();
        } else if (isTaskRoot()) {
            showExitDialog();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        hideKeyboard(event, getCurrentFocus());
        return super.dispatchTouchEvent(event);
    }

    private void hideKeyboard(MotionEvent event, View currentFocus) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (currentFocus instanceof EditText || currentFocus instanceof SearchView) {
                Rect outRect = new Rect();
                currentFocus.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    // clear focus on currently focused view if clicked outside
                    currentFocus.clearFocus();

                    View newFocus = findViewAt(findViewById(R.id.coordinator), (int) event.getRawX(), (int) event.getRawY());
                    // hide keyboard if newly focused view is not keyboard owner
                    if (newFocus == null || !(newFocus instanceof EditText || currentFocus instanceof SearchView)) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                        }
                    }
                }
            }
        }
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

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.confirmQuit))
                .setMessage(R.string.confirmQuitContent)
                .setPositiveButton(R.string.quit, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    finish();
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss()).show();
    }
}

package mihau.eu.githubsearch.utils.providers;

import android.content.Context;
import android.support.annotation.StringRes;

public class AppResourcesProvider implements ResourceProvider {

    private Context context;

    public AppResourcesProvider(Context context) {
        this.context = context;
    }

    public String getString(@StringRes int resId) {
        return context.getString(resId);
    }
}

package mihau.eu.githubsearch.utils.providers.resources;

import android.content.Context;
import android.support.annotation.StringRes;

import dagger.Module;
import dagger.Provides;

@Module
public class AppResourcesProvider implements ResourceProvider {

    private Context context;

    public AppResourcesProvider(Context context) {
        this.context = context;
    }

    @Provides
    public String getString(@StringRes int resId) {
        return context.getString(resId);
    }
}

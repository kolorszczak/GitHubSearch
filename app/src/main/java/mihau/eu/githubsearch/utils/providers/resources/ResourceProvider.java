package mihau.eu.githubsearch.utils.providers.resources;

import android.support.annotation.StringRes;

import dagger.Component;

@Component
public interface ResourceProvider {

    String getString(@StringRes int resId);
}

package mihau.eu.githubsearch.utils.providers.resources;

import android.support.annotation.StringRes;

public interface ResourceProvider {

    String getString(@StringRes int resId);
    String getErrorMessage(Throwable throwable);
    Integer getErrorCode(Throwable throwable);
}

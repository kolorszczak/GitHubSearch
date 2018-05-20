package mihau.eu.githubsearch;

import android.app.Application;
import android.content.Context;

public class GitHubApplication extends Application {

    public static GitHubApplication getInstance(Context context) {
        return (GitHubApplication) context.getApplicationContext();
    }
}

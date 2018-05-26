package mihau.eu.githubsearch;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

public class GitHubApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    public static GitHubApplication getInstance(Context context) {
        return (GitHubApplication) context.getApplicationContext();
    }
}

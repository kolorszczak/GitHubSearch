package mihau.eu.githubsearch;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class GitHubApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}

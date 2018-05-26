package mihau.eu.githubsearch.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        openSearchActivity();
    }

    private void openSearchActivity() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, SearchActivity.class));
            finish();
        }, SPLASH_TIME);
    }
}
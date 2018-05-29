package mihau.eu.githubsearch.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.base.BaseActivity;
import mihau.eu.githubsearch.databinding.ActivityDescriptionBinding;
import mihau.eu.githubsearch.model.User;

public class DescriptionActivity extends BaseActivity {

    private static final String TAG = DescriptionActivity.class.getSimpleName();

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("user");
        ActivityDescriptionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_description);
        binding.setUser(user);
    }
}
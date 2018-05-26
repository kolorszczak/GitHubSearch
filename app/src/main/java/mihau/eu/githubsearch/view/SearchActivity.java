package mihau.eu.githubsearch.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.base.BaseActivity;
import mihau.eu.githubsearch.databinding.ActivitySearchBinding;
import mihau.eu.githubsearch.viewmodel.GitHubViewModel;

public class SearchActivity extends BaseActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding() {
        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setViewModel(new GitHubViewModel());
    }
}

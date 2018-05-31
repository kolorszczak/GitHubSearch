package mihau.eu.githubsearch.view;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.base.BaseActivity;
import mihau.eu.githubsearch.databinding.ActivityDescriptionBinding;
import mihau.eu.githubsearch.model.User;
import mihau.eu.githubsearch.utils.providers.resources.ResourceProvider;
import mihau.eu.githubsearch.viewmodel.UserViewModel;
import mihau.eu.githubsearch.viewmodel.ViewModelFactory;

public class DescriptionActivity extends BaseActivity {

    private static final String TAG = DescriptionActivity.class.getSimpleName();

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    ResourceProvider resourceProvider;

    private ActivityDescriptionBinding binding;
    private UserViewModel viewModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = viewModelFactory.get(UserViewModel.class, this);
        getUser(getIntent().getParcelableExtra("user"));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_description);
    }

    @SuppressLint("CheckResult")
    private void getUser(User user) {
        viewModel.search(user.getLogin());

        viewModel.searchEventPublishSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchEvent -> {
                    switch (searchEvent.type) {
                        case LOADING:
                            //loading
                            break;
                        case SUCCESS:
                            binding.setUser(viewModel.getUser());
                            break;
                        case ERROR:
                            new AlertDialog.Builder(this)
                                    .setTitle("")
                                    .setMessage("").show();
                            break;
                    }
                });
    }
}
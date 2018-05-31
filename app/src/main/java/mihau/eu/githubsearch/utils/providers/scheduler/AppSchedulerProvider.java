package mihau.eu.githubsearch.utils.providers.scheduler;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppSchedulerProvider implements SchedulerProvider {

    @Inject
    public AppSchedulerProvider() {
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }
}

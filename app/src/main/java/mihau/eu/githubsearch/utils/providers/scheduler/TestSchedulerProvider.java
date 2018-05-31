package mihau.eu.githubsearch.utils.providers.scheduler;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerProvider implements SchedulerProvider {

    @Inject
    public TestSchedulerProvider() {
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }
}

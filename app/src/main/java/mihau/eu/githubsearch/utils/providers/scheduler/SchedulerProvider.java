package mihau.eu.githubsearch.utils.providers.scheduler;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler io();
    Scheduler ui();
    Scheduler computation();
}

package mihau.eu.githubsearach;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import io.reactivex.observers.TestObserver;
import mihau.eu.githubsearch.api.GitHubRepository;
import mihau.eu.githubsearch.api.TestAPIService;
import mihau.eu.githubsearch.utils.providers.resources.TestResourceProvider;
import mihau.eu.githubsearch.utils.providers.scheduler.TestSchedulerProvider;
import mihau.eu.githubsearch.viewmodel.SearchEvent;
import mihau.eu.githubsearch.viewmodel.UserViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserViewModelTest {

    private UserViewModel userViewModel;

    @Before
    public void before() {
        TestSchedulerProvider schedulerProvider = new TestSchedulerProvider();
        GitHubRepository gitHubRepository = new GitHubRepository(new TestAPIService(), schedulerProvider);
        TestResourceProvider resourcesProvider = new TestResourceProvider();
        userViewModel = new UserViewModel(gitHubRepository, resourcesProvider);
    }

    @After
    public void after() {
    }

    @Test
    public void testInitialization() {
        assertEquals(userViewModel.isError.get(), true);
        assertEquals(userViewModel.isLoading.get(), false);
    }

    @Test
    public void testSearch() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = userViewModel.searchEventPublishSubject.test();
        userViewModel.search("test");
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));
        testObserver.assertValueSequence(sequence);
    }

    @Test
    public void testGetUser() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = userViewModel.searchEventPublishSubject.test();
        userViewModel.search("test");
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));
        testObserver.assertValueSequence(sequence);

        assertNotNull(userViewModel.getUser());
    }

    @Test
    public void testSearchWhileLoading() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = userViewModel.searchEventPublishSubject.test();

        userViewModel.search("test");
        userViewModel.isLoading.set(true);
        userViewModel.search("test");

        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        testObserver.assertValueSequence(sequence);
    }

    @Test
    public void testFailedSearch() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = userViewModel.searchEventPublishSubject.test();

        userViewModel.search(null);

        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.ERROR, new Throwable("")));

        testObserver.assertValueSequence(sequence);

        assertEquals(userViewModel.isError.get(), true);
    }
}

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
import mihau.eu.githubsearch.viewmodel.SearchViewModel;

import static org.junit.Assert.assertEquals;


public class SearchViewModelTest {

    private SearchViewModel searchViewModel;

    @Before
    public void before() {
        TestSchedulerProvider schedulerProvider = new TestSchedulerProvider();
        GitHubRepository gitHubRepository = new GitHubRepository(new TestAPIService(), schedulerProvider);
        TestResourceProvider resourcesProvider = new TestResourceProvider();
        searchViewModel = new SearchViewModel(gitHubRepository, resourcesProvider);
    }

    @After
    public void after() {
    }

    @Test
    public void testSearch() {
        assertEquals(searchViewModel.currentUserPage, new Integer(1));
        TestObserver<SearchEvent> testObserver = searchViewModel.searchEventPublishSubject.test();
        searchViewModel.search("test");
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        sequence.add(new SearchEvent(SearchEvent.Type.CLEAR));
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));
        testObserver.assertValueSequence(sequence);
    }
}
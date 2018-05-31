package mihau.eu.githubsearach;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import io.reactivex.observers.TestObserver;
import mihau.eu.githubsearch.R;
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
    public void testInitialization() {
        assertEquals(searchViewModel.currentRepositoryPage, new Integer(1));
        assertEquals(searchViewModel.currentUserPage, new Integer(1));
        assertEquals(searchViewModel.error.get(), "mock");
        assertEquals(searchViewModel.errorImgResId.get(), R.drawable.im_empty);
        assertEquals(searchViewModel.isError.get(), true);
        assertEquals(searchViewModel.isLoading.get(), false);
        assertEquals(searchViewModel.keyword.get(), "");
        assertEquals(searchViewModel.repositoryTotal.get(), 0L);
        assertEquals(searchViewModel.userTotal.get(), 0L);
    }

    @Test
    public void testSearch() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = searchViewModel.searchEventPublishSubject.test();
        searchViewModel.search("test");
        sequence.add(new SearchEvent(SearchEvent.Type.CLEAR));
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));
        testObserver.assertValueSequence(sequence);
    }

    @Test
    public void testSearchWhileLoading() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = searchViewModel.searchEventPublishSubject.test();

        searchViewModel.search("test");
        searchViewModel.isLoading.set(true);
        searchViewModel.currentRepositoryPage = 1;
        searchViewModel.currentUserPage = 1;
        searchViewModel.search("test");

        // First page
        sequence.add(new SearchEvent(SearchEvent.Type.CLEAR));
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        testObserver.assertValueSequence(sequence);

        assertEquals(searchViewModel.getList().size(), 60L); //1 pages with 30 items each for 2 requests
    }

    @Test
    public void testFailedSearch() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = searchViewModel.searchEventPublishSubject.test();

        searchViewModel.search("test");
        searchViewModel.search(searchViewModel.keyword.get());
        searchViewModel.search(searchViewModel.keyword.get());
        searchViewModel.search(searchViewModel.keyword.get());

        // First page
        sequence.add(new SearchEvent(SearchEvent.Type.CLEAR));
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        // Second page
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        // Third page
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        // Fourth page
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.ERROR, new Throwable("")));

        testObserver.assertValueSequence(sequence);

        assertEquals(searchViewModel.getList().size(), 180L); //3 pages with 30 items each for 2 requests
        assertEquals(searchViewModel.error.get(), "mock");
    }

    @Test
    public void testPaging() {
        ArrayList<SearchEvent> sequence = new ArrayList<>();
        TestObserver<SearchEvent> testObserver = searchViewModel.searchEventPublishSubject.test();

        searchViewModel.search("test");
        searchViewModel.search(searchViewModel.keyword.get());
        searchViewModel.search(searchViewModel.keyword.get());

        // First page
        sequence.add(new SearchEvent(SearchEvent.Type.CLEAR));
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        // Second page
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        // Third page
        sequence.add(new SearchEvent(SearchEvent.Type.LOADING));
        sequence.add(new SearchEvent(SearchEvent.Type.SUCCESS));

        testObserver.assertValueSequence(sequence);

        assertEquals(searchViewModel.getList().size(), 180L); //3 pages with 30 items each for 2 requests
    }

    @Test
    public void testClear() {
        searchViewModel.clear();

        assertEquals(searchViewModel.isLoading.get(), false);
        assertEquals(searchViewModel.keyword.get(), "");
        assertEquals(searchViewModel.currentRepositoryPage, new Integer(1));
        assertEquals(searchViewModel.currentUserPage, new Integer(1));
        assertEquals(searchViewModel.userTotal.get(), 0L);
        assertEquals(searchViewModel.repositoryTotal.get(), 0L);
        assertEquals(searchViewModel.isError.get(), true);
        assertEquals(searchViewModel.error.get(), "mock");
        assertEquals(searchViewModel.errorImgResId.get(), R.drawable.im_empty);
        assertEquals(searchViewModel.getList().size(), 0);
    }
}
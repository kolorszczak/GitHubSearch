package mihau.eu.githubsearch.utils.providers;

public class TestInterfaceProvider implements ResourceProvider {
    @Override
    public String getString(int resId) {
        return "mock";
    }
}

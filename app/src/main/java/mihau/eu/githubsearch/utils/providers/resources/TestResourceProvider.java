package mihau.eu.githubsearch.utils.providers.resources;

import dagger.Module;

@Module
public class TestResourceProvider implements ResourceProvider {
    @Override
    public String getString(int resId) {
        return "mock";
    }

    @Override
    public String getErrorMessage(Throwable throwable) {
        return "Mock Error";
    }

    @Override
    public Integer getErrorCode(Throwable throwable) {
        return 500;
    }
}

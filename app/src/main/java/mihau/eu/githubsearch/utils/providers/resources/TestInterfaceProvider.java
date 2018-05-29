package mihau.eu.githubsearch.utils.providers.resources;

import dagger.Module;

@Module
public class TestInterfaceProvider implements ResourceProvider {
    @Override
    public String getString(int resId) {
        return "mock";
    }
}

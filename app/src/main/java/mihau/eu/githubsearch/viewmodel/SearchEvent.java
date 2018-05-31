package mihau.eu.githubsearch.viewmodel;

public class SearchEvent {

    public Type type;
    public Throwable error;

    public SearchEvent(Type type) {
        this.type = type;
    }

    public SearchEvent(Type type, Throwable error) {
        this.type = type;
        this.error = error;
    }

    public enum Type {
        LOADING, SUCCESS, CLEAR, ERROR
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;

        SearchEvent searchEvent = (SearchEvent) o;
        return type.equals(searchEvent.type);
    }

    @Override
    public int hashCode() {
        return 6 * type.hashCode();
    }
}

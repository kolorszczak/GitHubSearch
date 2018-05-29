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
}

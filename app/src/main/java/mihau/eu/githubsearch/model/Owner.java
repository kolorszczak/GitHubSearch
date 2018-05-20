package mihau.eu.githubsearch.model;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private Integer id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("url")
    private String url;
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    @SerializedName("type")
    private String type;

    public Owner(String login, Integer id, String avatarUrl, String gravatarId, String url, String receivedEventsUrl, String type) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
    }
}

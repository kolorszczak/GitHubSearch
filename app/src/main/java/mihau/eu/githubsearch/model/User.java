package mihau.eu.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import mihau.eu.githubsearch.utils.manager.ParcelableUtils;

public class User implements Parcelable {

    @SerializedName("login")
    public String login;
    @SerializedName("id")
    public Integer id;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("gravatar_id")
    public String gravatarId;
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("followers_url")
    public String followersUrl;
    @SerializedName("subscriptions_url")
    public String subscriptionsUrl;
    @SerializedName("organizations_url")
    public String organizationsUrl;
    @SerializedName("repos_url")
    public String reposUrl;
    @SerializedName("received_events_url")
    public String receivedEventsUrl;
    @SerializedName("type")
    public String type;
    @SerializedName("score")
    public Double score;

    public User(String login, Integer id, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String receivedEventsUrl, String type, Double score) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.score = score;
    }

    public User(Parcel in) {
        this.login = ParcelableUtils.readStringHelper(in);
        this.id = ParcelableUtils.readIntegerHelper(in);
        this.avatarUrl = ParcelableUtils.readStringHelper(in);
        this.gravatarId = ParcelableUtils.readStringHelper(in);
        this.url = ParcelableUtils.readStringHelper(in);
        this.htmlUrl = ParcelableUtils.readStringHelper(in);
        this.followersUrl = ParcelableUtils.readStringHelper(in);
        this.subscriptionsUrl = ParcelableUtils.readStringHelper(in);
        this.organizationsUrl = ParcelableUtils.readStringHelper(in);
        this.reposUrl = ParcelableUtils.readStringHelper(in);
        this.receivedEventsUrl = ParcelableUtils.readStringHelper(in);
        this.type = ParcelableUtils.readStringHelper(in);
        this.score = ParcelableUtils.readDoubleHelper(in);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;

        User repository = (User) o;
        return id.equals(repository.id);
    }

    @Override
    public int hashCode() {
        return 6 * id + login.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        ParcelableUtils.writeStringHelper(out, this.login);
        ParcelableUtils.writeIntegerHelper(out, this.id);
        ParcelableUtils.writeStringHelper(out, this.avatarUrl);
        ParcelableUtils.writeStringHelper(out, this.gravatarId);
        ParcelableUtils.writeStringHelper(out, this.url);
        ParcelableUtils.writeStringHelper(out, this.htmlUrl);
        ParcelableUtils.writeStringHelper(out, this.followersUrl);
        ParcelableUtils.writeStringHelper(out, this.subscriptionsUrl);
        ParcelableUtils.writeStringHelper(out, this.organizationsUrl);
        ParcelableUtils.writeStringHelper(out, this.reposUrl);
        ParcelableUtils.writeStringHelper(out, this.receivedEventsUrl);
        ParcelableUtils.writeStringHelper(out, this.type);
        ParcelableUtils.writeDoubleHelper(out, this.score);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {

        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

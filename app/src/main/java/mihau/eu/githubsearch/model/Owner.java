package mihau.eu.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import mihau.eu.githubsearch.utils.manager.ParcelableUtils;

public class Owner implements Parcelable {

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
    @SerializedName("received_events_url")
    public String receivedEventsUrl;
    @SerializedName("type")
    public String type;

    public Owner(String login, Integer id, String avatarUrl, String gravatarId, String url, String receivedEventsUrl, String type) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
    }

    public Owner(Parcel in) {
        this.login = ParcelableUtils.readStringHelper(in);
        this.id = ParcelableUtils.readIntegerHelper(in);
        this.avatarUrl = ParcelableUtils.readStringHelper(in);
        this.gravatarId = ParcelableUtils.readStringHelper(in);
        this.url = ParcelableUtils.readStringHelper(in);
        this.receivedEventsUrl = ParcelableUtils.readStringHelper(in);
        this.type = ParcelableUtils.readStringHelper(in);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;
        return this.id.equals(owner.id);
    }

    @Override
    public int hashCode() {
        return 3 * id + login.hashCode();
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
        ParcelableUtils.writeStringHelper(out, this.receivedEventsUrl);
        ParcelableUtils.writeStringHelper(out, this.type);
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {

        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
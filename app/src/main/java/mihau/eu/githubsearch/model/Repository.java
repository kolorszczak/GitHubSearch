package mihau.eu.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import mihau.eu.githubsearch.utils.manager.ParcelableUtils;

public class Repository implements Parcelable {

    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public Integer id;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("owner")
    public Owner owner;
    @SerializedName("public ")
    public Boolean isPublic ;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("description")
    public String description;
    @SerializedName("fork")
    public Boolean fork;
    @SerializedName("url")
    public String url;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("pushed_at")
    public String pushedAt;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("size")
    public Integer size;
    @SerializedName("stargazers_count")
    public Integer stargazersCount;
    @SerializedName("watchers_count")
    public Integer watchersCount;
    @SerializedName("language")
    public String language;
    @SerializedName("forks_count")
    public Integer forksCount;
    @SerializedName("open_issues_count")
    public Integer openIssuesCount;
    @SerializedName("master_branch")
    public String masterBranch;
    @SerializedName("default_branch")
    public String defaultBranch;
    @SerializedName("score")
    public Double score;

    public Repository(Integer id, String name, String fullName, Owner owner, Boolean isPublic, String htmlUrl, String description, Boolean fork, String url, String createdAt, String updatedAt, String pushedAt, String homepage, Integer size, Integer stargazersCount, Integer watchersCount, String language, Integer forksCount, Integer openIssuesCount, String masterBranch, String defaultBranch, Double score) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.isPublic = isPublic;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.fork = fork;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pushedAt = pushedAt;
        this.homepage = homepage;
        this.size = size;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.language = language;
        this.forksCount = forksCount;
        this.openIssuesCount = openIssuesCount;
        this.masterBranch = masterBranch;
        this.defaultBranch = defaultBranch;
        this.score = score;
    }

    public Repository(Parcel in) {
        this.id = ParcelableUtils.readIntegerHelper(in);
        this.name = ParcelableUtils.readStringHelper(in);
        this.fullName = ParcelableUtils.readStringHelper(in);
        this.owner = ParcelableUtils.readParcelableHelper(in, Owner.class);
        this.isPublic = ParcelableUtils.readBooleanHelper(in);
        this.htmlUrl = ParcelableUtils.readStringHelper(in);
        this.description = ParcelableUtils.readStringHelper(in);
        this.fork = ParcelableUtils.readBooleanHelper(in);
        this.url = ParcelableUtils.readStringHelper(in);
        this.createdAt = ParcelableUtils.readStringHelper(in);
        this.updatedAt = ParcelableUtils.readStringHelper(in);
        this.pushedAt = ParcelableUtils.readStringHelper(in);
        this.homepage = ParcelableUtils.readStringHelper(in);
        this.size = ParcelableUtils.readIntegerHelper(in);
        this.stargazersCount = ParcelableUtils.readIntegerHelper(in);
        this.watchersCount = ParcelableUtils.readIntegerHelper(in);
        this.language = ParcelableUtils.readStringHelper(in);
        this.forksCount = ParcelableUtils.readIntegerHelper(in);
        this.openIssuesCount = ParcelableUtils.readIntegerHelper(in);
        this.masterBranch = ParcelableUtils.readStringHelper(in);
        this.defaultBranch = ParcelableUtils.readStringHelper(in);
        this.score = ParcelableUtils.readDoubleHelper(in);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;

        Repository repository = (Repository) o;
        return id.equals(repository.id);
    }

    @Override
    public int hashCode() {
        return  5 * id + name.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        ParcelableUtils.writeIntegerHelper(out, this.id);
        ParcelableUtils.writeStringHelper(out, this.name);
        ParcelableUtils.writeStringHelper(out, this.fullName);
        ParcelableUtils.writeParcelableHelper(out, this.owner, flags);
        ParcelableUtils.writeBooleanHelper(out, this.isPublic);
        ParcelableUtils.writeStringHelper(out, this.htmlUrl);
        ParcelableUtils.writeStringHelper(out, this.description);
        ParcelableUtils.writeBooleanHelper(out, this.fork);
        ParcelableUtils.writeStringHelper(out, this.url);
        ParcelableUtils.writeStringHelper(out, this.createdAt);
        ParcelableUtils.writeStringHelper(out, this.updatedAt);
        ParcelableUtils.writeStringHelper(out, this.pushedAt);
        ParcelableUtils.writeStringHelper(out, this.homepage);
        ParcelableUtils.writeIntegerHelper(out, this.size);
        ParcelableUtils.writeIntegerHelper(out, this.stargazersCount);
        ParcelableUtils.writeIntegerHelper(out, this.watchersCount);
        ParcelableUtils.writeStringHelper(out, this.language);
        ParcelableUtils.writeIntegerHelper(out, this.forksCount);
        ParcelableUtils.writeIntegerHelper(out, this.openIssuesCount);
        ParcelableUtils.writeStringHelper(out, this.masterBranch);
        ParcelableUtils.writeStringHelper(out, this.defaultBranch);
        ParcelableUtils.writeDoubleHelper(out, this.score);
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {

        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}

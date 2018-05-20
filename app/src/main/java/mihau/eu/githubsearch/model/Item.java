package mihau.eu.githubsearch.model;


import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
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

    public Item(Integer id, String name, String fullName, Owner owner, Boolean isPublic, String htmlUrl, String description, Boolean fork, String url, String createdAt, String updatedAt, String pushedAt, String homepage, Integer size, Integer stargazersCount, Integer watchersCount, String language, Integer forksCount, Integer openIssuesCount, String masterBranch, String defaultBranch, Double score) {
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
}

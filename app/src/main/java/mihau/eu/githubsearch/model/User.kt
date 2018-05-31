package mihau.eu.githubsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val login: String,
        val id: Int,
        val avatarUrl: String,
        val gravatarId: String,
        val url: String,
        val htmlUrl: String,
        val followersUrl: String,
        val subscriptionsUrl: String,
        val organizationsUrl: String,
        val reposUrl: String,
        val receivedEventsUrl: String,
        val followers: Int,
        val publicRepos: Int,
        val type: String,
        val score: Double
) : Parcelable
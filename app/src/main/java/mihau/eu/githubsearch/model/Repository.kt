package mihau.eu.githubsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
        val name: String,
        val id: Int,
        val fullName: String,
        val owner: Owner,
        val isPublic: Boolean,
        val htmlUrl: String,
        val description: String,
        val fork: Boolean,
        val url: String,
        val createdAt: String,
        val updatedAt: String,
        val pushedAt: String,
        val homepage: String,
        val size: Int,
        val stargazersCount: Int,
        val watchersCount: Int,
        val language: String,
        val forksCount: Int,
        val openIssuesCount: Int,
        val masterBranch: String,
        val defaultBranch: String,
        val score: Double
) : Parcelable
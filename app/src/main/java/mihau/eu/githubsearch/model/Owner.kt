package mihau.eu.githubsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
        val login: String,
        val avatarUrl: String,
        val gravatarId: String,
        val url: String,
        val receivedEventsUrl: String,
        val type: String
) : Parcelable
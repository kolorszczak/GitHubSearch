package mihau.eu.githubsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response<out T>(
        val totalCount: Int,
        val incompleteResults: Boolean,
        val items: List<T>) : Parcelable where T : Parcelable

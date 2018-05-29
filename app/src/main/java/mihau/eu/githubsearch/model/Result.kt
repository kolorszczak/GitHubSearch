package mihau.eu.githubsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
        val repositories: Response<Repository>,
        val users: Response<User>
) : Parcelable
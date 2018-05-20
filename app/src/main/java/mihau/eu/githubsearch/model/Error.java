package mihau.eu.githubsearch.model;

import android.content.Context;
import android.support.annotation.StringRes;

import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("message")
    public String message;

    public Error(Context context, @StringRes int message) {
        this.message = context.getString(message);
    }

    public Error(String message) {
        this.message = message;
    }
}

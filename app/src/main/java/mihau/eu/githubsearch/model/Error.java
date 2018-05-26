package mihau.eu.githubsearch.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringRes;

import com.google.gson.annotations.SerializedName;

import mihau.eu.githubsearch.utils.manager.ParcelableUtils;

public class Error implements Parcelable {

    @SerializedName("message")
    public String message;

    public Error(Context context, @StringRes int message) {
        this.message = context.getString(message);
    }

    public Error(String message) {
        this.message = message;
    }

    public Error(Parcel in) {
        this.message = ParcelableUtils.readStringHelper(in);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;

        Error error = (Error) o;
        return this.message.equals(error.message);
    }

    @Override
    public int hashCode() {
        return  2 * message.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        ParcelableUtils.writeStringHelper(out, this.message);
    }

    public static final Creator<Error> CREATOR = new Creator<Error>() {

        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        public Error[] newArray(int size) {
            return new Error[size];
        }
    };
}

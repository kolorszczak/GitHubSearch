package mihau.eu.githubsearch.utils.manager;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.UnknownHostException;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.model.Error;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorUtils {

    public static Error parseThrowable(Context context, Throwable throwable) {
        Error error = new Error(context, R.string.serverError);
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).response().code() >= 500) {  // all server errors
//                Crashlytics.logException(throwable);
            } else {
                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                if (responseBody != null) {
                    try {
                        String json = responseBody.string().trim().replaceAll("\n", "").replaceAll("\r", "");
                        error = new Gson().fromJson(json, Error.class);
                    } catch (IOException e) {
//                        Crashlytics.logException(throwable);
                    }
                }
            }
        } else if (throwable instanceof UnknownHostException) {
            error = new Error(context, R.string.noInternetConnection);
        } else {
//            Crashlytics.logException(throwable);
        }
        return error;
    }
}

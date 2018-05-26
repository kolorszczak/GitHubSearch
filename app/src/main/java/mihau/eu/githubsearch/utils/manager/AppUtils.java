package mihau.eu.githubsearch.utils.manager;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.model.Error;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class AppUtils {

    /**
     * Method that is parsing throwable object into String error message
     *
     * @param context   Context object
     * @param throwable Throwable object
     * @return String message parsed from throwable
     */
    public static String parseThrowable(Context context, Throwable throwable) {
        Error error = new Error(context, R.string.serverError);
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).response().code() < 500) {
                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                if (responseBody != null) {
                    try {
                        String json = responseBody.string().trim().replaceAll("\n", "").replaceAll("\r", "");
                        error = new Gson().fromJson(json, Error.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException) {
            error = new Error(context, R.string.noInternetConnection);
        }
        return error.message;
    }
}

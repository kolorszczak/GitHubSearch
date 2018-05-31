package mihau.eu.githubsearch.utils.providers.resources;

import android.content.Context;
import android.support.annotation.StringRes;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import mihau.eu.githubsearch.R;
import mihau.eu.githubsearch.model.Error;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


public class AppResourcesProvider implements ResourceProvider {

    private Context context;

    @Inject
    public AppResourcesProvider(Context context) {
        this.context = context;
    }

    @Override
    public String getString(@StringRes int resId) {
        return context.getString(resId);
    }

    @Override
    public String getErrorMessage(Throwable throwable) {
        Error error = new Error(getString(R.string.serverError));
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).response().code() < 500) {
                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                if (responseBody != null) {
                    try {
                        String json = responseBody.string().trim().replaceAll("\n", "").replaceAll("\r", "");
                        error = new Gson().fromJson(json, Error.class);
                    } catch (IOException e) {
                        error = new Error(getString(R.string.serverError));
                    }
                }
            }
        } else if (throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException) {
            error = new Error(getString(R.string.noInternetConnection));
        }
        return error.getMessage();
    }

    @Override
    public Integer getErrorCode(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return ((HttpException) throwable).response().code();
        } else if (throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException) {
            return 503;
        }
        return 500;
    }
}

package mihau.eu.githubsearch.utils.manager;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;

import mihau.eu.githubsearch.R;

public class DataBindingUtils {

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter(value = {"url"})
    public static void setUrl(WebView view, String url) {
        WebSettings webSetting = view.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setAllowFileAccessFromFileURLs(true);
        webSetting.setAllowUniversalAccessFromFileURLs(true);
        webSetting.setBlockNetworkImage(false);

        view.setWebChromeClient(new WebChromeClient());
        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        view.loadUrl(url);
    }

    @BindingAdapter(value = {"image"})
    public static void setImage(ImageView view, String url) {
        Glide.with(view)
                .load(url)
                .apply(new RequestOptions()
                        .error(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_error))
                        .placeholder(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_wait))
                        .fitCenter())
                .into(view);
    }

    @BindingAdapter(value = {"resId"})
    public static void setResId(ImageView view, int resId) {
        Glide.with(view)
                .load(resId)
                .into(view);
    }

    @BindingAdapter("android:text")
    public static void setDouble(TextView view, Double value) {
        if (value == null)
            return;
        DecimalFormat df = new DecimalFormat("#.#");
        if (Double.isNaN(value)) {
            view.setText("");
        } else {
            view.setText(df.format(value));
        }
    }
}

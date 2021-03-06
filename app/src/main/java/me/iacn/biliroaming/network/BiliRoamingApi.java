package me.iacn.biliroaming.network;

import android.net.Uri;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import me.iacn.biliroaming.BuildConfig;

/**
 * Created by iAcn on 2019/3/27
 * Email i@iacn.me
 */
public class BiliRoamingApi {

    private static final String BILIROAMING_SEASON_URL = "api.iacn.me/biliroaming/season";
    private static final String BILIROAMING_PLAYURL_URL = "api.iacn.me/biliroaming/playurl";

    public static String getSeason(String id, String accessKey, boolean useCache) throws IOException {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https").encodedAuthority(BILIROAMING_SEASON_URL).appendPath(id);

        if (!TextUtils.isEmpty(accessKey)) {
            builder.appendQueryParameter("access_key", accessKey);
        }
        builder.appendQueryParameter("use_cache", useCache ? "1" : "0");

        return getContent(builder.toString());
    }

    public static String getPlayUrl(String queryString) throws IOException {
        return getContent("https://" + BILIROAMING_PLAYURL_URL + "?" + queryString);
    }

    private static String getContent(String urlString) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Build", String.valueOf(BuildConfig.VERSION_CODE));
        connection.setConnectTimeout(4000);
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            String encoding = connection.getContentEncoding();
            return StreamUtils.getContent(inputStream, encoding);
        }
        return null;
    }
}
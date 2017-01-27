package com.example.android.newsapp;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Loads a list of NewsData by using an AsyncTask to perform the  network request to the given URL.
 */
public class NewsDataLoader extends AsyncTaskLoader<List<NewsData>> {
    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = NewsDataLoader.class.getName();
    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link NewsDataLoader}.
     * * @param context of the activity
     * * @param url to load data from
     */
    public NewsDataLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    /**
     * This is on a background thread.
     */
    @Override
    public List<NewsData> loadInBackground() {

        //check if url is null then simply return;
        if (mUrl == null){
            return null;
        }
        // Perform the network request, parse the response, and ex tract a list of NewsData
        List<NewsData> newsData = QueryResolver.fetchNewsData(mUrl);
        return newsData;
    }
}

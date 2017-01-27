package com.example.android.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Arts extends Fragment implements LoaderManager.LoaderCallbacks <List<NewsData>> {

    //String which holds the query link
    String input = "https://api.nytimes.com/svc/topstories/v1/arts.json?" +
            "api-key="; //add your New York Times API Key

    private static final int NEWS_DATA_LOADER_ID = 13;

    //create adapter global which can used in all placed
    private NewsDataAdapter newsDataAdapter;

    View progressBar;

    public Arts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get the view
        View rootView = inflater.inflate(R.layout.news_list_item,container,false);

        //Find a reference to the {@link ListView} in the layout
        ListView newsDataListView = (ListView) rootView.findViewById(R.id.list);


        //Create a new adapter that takes an empty list of earthquakes as input
        newsDataAdapter = new NewsDataAdapter(getContext(), new ArrayList<NewsData>());

        //set the adapter on the {@link ListView}
        newsDataListView.setAdapter(newsDataAdapter);

        //set the onItemClickListener
        newsDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current earthquake that was clicked on
                NewsData currentData = newsDataAdapter.getItem(position);
                final String URL = "url";
                final String TITLE = "toolbarTitle";
                String mUrl = currentData.getWebUrl();
                String mTitle = getResources().getString(R.string.arts);

                //send the string from the one activity to another activity
                Intent sendIntent = new Intent(getContext(),ItemClickResult.class);
                sendIntent.putExtra(URL,mUrl);
                sendIntent.putExtra(TITLE,mTitle);

                // Send the intent to launch a new activity
                startActivity(sendIntent);
            }
        });
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        progressBar = rootView.findViewById(R.id.loading_indicator);
        // If there is a network connection, fetch data

        if (networkInfo != null && networkInfo.isConnected()) {
            //get the reference of the Progressbar and set visibility VISIBLE
            progressBar.setVisibility(View.VISIBLE);

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_DATA_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            progressBar.setVisibility(View.GONE);

            // Update empty state with no connection error message
            Toast.makeText(getContext(),"Check Your internet Connection",Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    @Override
    public android.support.v4.content.Loader<List<NewsData>> onCreateLoader(int id, Bundle args) {
        return new NewsDataLoader(getContext(),input);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<NewsData>> loader, List<NewsData> newsData) {

        //set Visibility to GONE to Progressbar
        progressBar.setVisibility(View.GONE);

        newsDataAdapter.clear();
        // If there is a valid list of {@link NewsData}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsData != null && !newsData.isEmpty()) {
            newsDataAdapter.addAll(newsData);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<NewsData>> loader) {
        // Loader reset, so we can clear out our existing data.
        newsDataAdapter.clear();
    }
}

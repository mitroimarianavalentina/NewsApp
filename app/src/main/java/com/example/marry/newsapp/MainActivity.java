package com.example.marry.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>{

    /**
     * Constant value for the articles loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int ARTICLE_LOADER_ID = 1;

    /** Tag for the log messages */
    public static final String LOG_TAG = MainActivity.class.getName();

    /** URL for the articles */
    private static final String ARTICLES_REQUEST_URL =
            "https://content.guardianapis.com/search?q=simon%20halep&api-key=test";

    /** Adapter for the list of earthquakes */
    private NewsAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView listView =  findViewById(R.id.list_view);

        mEmptyStateTextView =  findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);


        // Create a new adapter that takes an empty list of articles as input
        mAdapter = new NewsAdapter(this,0, new ArrayList<Article>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browse
        // to open a website with more information about the selected article.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current article that was clicked on
                Article currentArticle = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentArticle.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    // We need onCreateLoader(), for when the LoaderManager has determined that the loader with
    // our specified ID isn't running, so we should create a new one.
    @Override
    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(this, ARTICLES_REQUEST_URL);
    }

    // We need onLoadFinished(), where we'll do exactly what we did in onPostExecute(), and use
    // the earthquake data to update our UI - by updating the dataset in the adapter.
    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_articles);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (articles != null && !articles.isEmpty()) {
            mAdapter.addAll(articles);
        }
    }

    // And we need onLoaderReset(), we're we're being informed that the data from our loader
    // is no longer valid. This isn't actually a case that's going to come up with our simple
    // loader, but the correct thing to do is to remove all the earthquake data from our UI by
    // clearing out the adapter’s data set.
    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}




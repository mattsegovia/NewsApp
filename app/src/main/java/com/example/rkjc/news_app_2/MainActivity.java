package com.example.rkjc.news_app_2;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsListClick{

    private RecyclerView mSearchResultsRecyclerView;
    private NewsAdapter mAdapter;
    private ArrayList<NewsItem> newsItemsList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchResultsRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mSearchResultsRecyclerView.setLayoutManager(layoutManager);
        mSearchResultsRecyclerView.setHasFixedSize(true);

        mAdapter = new NewsAdapter(this, newsItemsList);
        mSearchResultsRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void queryNews() {
        URL searchUrl = NetworkUtils.buildUrl();
        new NewsQueryTask().execute(searchUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.recycler_item) {
            queryNews();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onItemClick(int i) {
        String link = newsItemsList.get(i).getUrl();

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }


    public class NewsQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String results) {
            if (results != null && !results.equals("")) {

                newsItemsList = JsonUtils.parseNews(results);
                mAdapter.setmNewsItems(newsItemsList, MainActivity.this);
                Log.d("doInBackground", "doInBackground: " + newsItemsList);

                Log.d("getAdapter", "onPostExecute: " + mSearchResultsRecyclerView);

                //TODO Remove this and make this work by not setting the adapter twice
                mSearchResultsRecyclerView.setAdapter(mAdapter);
            }
        }
    }
}

package com.shariffproductions.simpletwitterclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.shariffproductions.simpletwitterclient.models.Tweet;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    private ArrayList<Tweet> tweets;
    private TweetAdapter tweetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        initTimelineFeedView();

        tweets.clear();
        getHomeTimelineTweets(RestApplication.getRestClient());
    }

    private void initTimelineFeedView() {
        tweets = new ArrayList<>();
        tweetAdapter = new TweetAdapter(this, tweets);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(tweetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void getHomeTimelineTweets(RestClient client) {
        client.getHomeTimelineTweets(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                ArrayList<Tweet> timelineTweets = Tweet.fromJson(jsonArray);
                tweets.addAll(timelineTweets);
                tweetAdapter.notifyDataSetChanged();
            }
        });
    }
}

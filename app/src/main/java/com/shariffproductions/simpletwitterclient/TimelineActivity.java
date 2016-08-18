package com.shariffproductions.simpletwitterclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.shariffproductions.simpletwitterclient.models.Tweet;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        RestClient client = RestApplication.getRestClient();
        client.getHomeTimelineTweets(1, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                ArrayList<Tweet> tweets = Tweet.fromJson(jsonArray);
            }
        });
    }
}

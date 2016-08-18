package com.shariffproductions.simpletwitterclient.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "items")
public class Tweet extends Model {
	@Column(name = "userId")
	public String userId;

	@Column(name = "userHandle")
	public String userHandle;

	@Column(name = "timestamp")
	public String timestamp;

	@Column(name = "body")
	public String body;

	@Column(name = "profileImageUrl")
	public String profileImageUrl;

	public Tweet() {
		super();
	}

	public Tweet(JSONObject object){
		super();

		try {
			this.userId = object.getJSONObject("user").getString("id_str");
			this.userHandle = object.getJSONObject("user").getString("screen_name");
			this.timestamp = object.getString("created_at");
			this.body = object.getString("text");
			this.profileImageUrl = object.getJSONObject("user").getString("profile_image_url");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

		for (int i=0; i < jsonArray.length(); i++) {
			JSONObject tweetJson = null;
			try {
				tweetJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Tweet tweet = new Tweet(tweetJson);
			tweet.save();
			tweets.add(tweet);
		}

		return tweets;
	}

//	// Record Finders
//	public static Tweet byId(long id) {
//		return new Select().from(Tweet.class).where("id = ?", id).executeSingle();
//	}
//
//	public static List<Tweet> recentItems() {
//		return new Select().from(Tweet.class).orderBy("id DESC").limit("300").execute();
//	}
}

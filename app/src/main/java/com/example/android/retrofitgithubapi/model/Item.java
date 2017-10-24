package com.example.android.retrofitgithubapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ETORO on 23/10/2017.
 */

public class Item {

    //Recall that with @Serialized, your String variable doesn't have to be the same name with that in the Json as far as the @Serialized
    //got the right variable name from the Json
    @SerializedName("login")
    @Expose
    private String username;

    @SerializedName("avatar_url")
    @Expose
    private String avatar;

    @SerializedName("html_url")
    @Expose
    private String link;

    @SerializedName("followers_url")
    @Expose
    private String followers;

    @SerializedName("following_url")
    @Expose
    private String following;

    //WE CAN USE THE SET METHOD IF WE WANT TO PUSH TO THE API TO  SEND TO THE DB TO  CHANGE THE USERNAME(OR OTHER ITEMS)

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public Item(String username, String avatar, String link, String followers, String following){
        setUsername(username);
        setLink(link);
        setFollowing(following);
        setFollowers(followers);
        setAvatar(avatar);

    }
}

package com.example.android.retrofitgithubapi.controller;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.retrofitgithubapi.R;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    TextView link, followers, following, username;
    Toolbar toolbar;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO CHECK THIS OUT
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //we get to handle this in the manifest..
        username = (TextView) findViewById(R.id.textview_detail_username);
        link = (TextView) findViewById(R.id.textview_detail_githublink);
        followers = (TextView) findViewById(R.id.textview_detail_followers);
        following  = (TextView) findViewById(R.id.textview_detail_following);
        avatar = (ImageView) findViewById(R.id.imageview_detail_avatar);


        String username = getIntent().getExtras().getString("USERNAME");
        String avatar = getIntent().getExtras().getString("AVATAR");
        String link = getIntent().getExtras().getString("LINK");
        String followers= getIntent().getExtras().getString("FOLLOWERS");
        String following = getIntent().getExtras().getString("FOLLOWING");

        this.link.setText(link);
        Linkify.addLinks(this.link, Linkify.WEB_URLS);      //telling our device that the link is not just an ordinary text but a
                                                            //web url link. I'm using this.link cos I have 2 link variables, nothing special
        this.username.setText(username);
        this.following.setText(following);
        this.followers.setText(followers);

        //Same syntax as Picasso in Custom Adapter
        Glide.with(this).load(avatar)
                .placeholder(R.drawable.loadingImage)
                .into(this.avatar);

        //COULD HAVE DONE THIS IN MANIFEST. TODO CHECK HOW?
        getSupportActionBar().setTitle("Details Activity");

    }

        //INTEGRATING THE SHARE ACTION (FROM A TOOLBAR)
    private Intent shareForecastIntent(){
        String username = getIntent().getExtras().getString("USERNAME");
        String link = getIntent().getExtras().getString("LINK");

        //EXPLICIT INTENT DECLARATION
        //text/plain, tells it that the intent is a text, so it will alert it to text editing apps like message, gmail, notepad etc, when the share button is pressed.
        //other types exists.
        //TODO READ MORE ON THE EXPLICIT INTENT ShareCompat.IntentBuilder class
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome Developer @"+ username + ", "+ link)
                .getIntent();

        return shareIntent;             //returns the explicit share intent
    }


    //INFLATING OUR MENU, How to do this everytime
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_action_share);
        menuItem.setIntent(shareForecastIntent());  //when share menu is clicked, the explicit share Intent runs, set the share button to the intent method
        return true;

    }




}

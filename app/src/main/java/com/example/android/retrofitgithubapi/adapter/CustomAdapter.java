package com.example.android.retrofitgithubapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofitgithubapi.R;
import com.example.android.retrofitgithubapi.controller.DetailActivity;
import com.example.android.retrofitgithubapi.model.Item;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ETORO on 23/10/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Item> items;
    private Context context;

    public CustomAdapter(Context context,List<Item> itemArrayList){
        this.context = context;
        this.items = itemArrayList;
    }
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        holder.username.setText(items.get(position).getUsername());     //sets the text of the textview to the value of the username
                                                                        //variable in our Item Class, which is the value of the
                                                                        // login variable in our api Json
                                                                        //data. Reacall getUsername is a methos in our Item class
        holder.link.setText(items.get(position).getLink());
        holder.followers.setText(items.get(position).getFollowers());
        holder.following.setText(items.get(position).getFollowing());


        //the picasso, puts the avatar into the image view, the plcaeholder is for the image that would show
        //for the mean time while the original avatar is loading. it is optional
        Picasso.with(context).load(items.get(position).getAvatar())
                .placeholder(R.drawable.loadingGif)
                .into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView followers, following, username, link;
        private ImageView avatar;
        private CardView cardView;      //we don't actually need this
        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.textview_list_username);
            link = (TextView) itemView.findViewById(R.id.textview_list_githublink);
            followers = (TextView) itemView.findViewById(R.id.textview_list_followers);
            following = (TextView) itemView.findViewById(R.id.textview_list_following);
            avatar = (ImageView) itemView.findViewById(R.id.imageview_list_avatar);
            cardView = (CardView) itemView.findViewById(R.id.cardview_list);

            //set on click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();        //get the adapter position
                    if(position!= RecyclerView.NO_POSITION){        //if it is not pointing at an empty position
                        Item clickedData = items.get(position);     //gets all the data from that position and
                                                                    //saves it in the variable, so that we can use the variable to refer to the content we need

                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("USERNAME", clickedData.getUsername());
                        intent.putExtra("AVATAR", clickedData.getAvatar());
                        intent.putExtra("FOLLOWERS", clickedData.getFollowers());
                        intent.putExtra("FOLLOWING", items.get(position).getAvatar());
                        intent.putExtra("LINK", clickedData.getLink());
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedData.getUsername(), Toast.LENGTH_SHORT).show();



                    }
                }
            });

        }
    }


}


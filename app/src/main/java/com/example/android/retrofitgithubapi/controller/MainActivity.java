package com.example.android.retrofitgithubapi.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofitgithubapi.R;
import com.example.android.retrofitgithubapi.adapter.CustomAdapter;
import com.example.android.retrofitgithubapi.api.ApiClient;
import com.example.android.retrofitgithubapi.api.ApiService;
import com.example.android.retrofitgithubapi.model.Item;
import com.example.android.retrofitgithubapi.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;
    private TextView disconnected;
    private Item item;
    private ProgressDialog progressDialog;      //our circle loading sign
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout_main);
        //TODO CHECK OUT
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark);   //this sets the color of the refresh spinning dialog

        //This is like onClickListener for Buttons, but for Swipe. it is OnRefreshListener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){


            //what to do when swiped
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(MainActivity.this, "GitHub Users Refreshed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initViews(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Users..."); //message to show while progressDialog is showing
        progressDialog.setCancelable(false);        //so that the user won't be able to cancel it
        progressDialog.show();                  //shows the dialog

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.smoothScrollToPosition(0);     //for smooth scroll
        loadJSON();
    }

    private void loadJSON(){
        disconnected = (TextView) findViewById(R.id.textview_main_disconnected);
        try{
            //ApiClient apiClient = new ApiClient();   //not needed since getClient() is a static method
            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            Call<ItemResponse> call = apiService.getItems();        //getItems() method from our interface class, ApiService
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();      //this points to the getItems() method in the ItemResponse Class. I guess the response.body() points to the ItemResponse class
                    mAdapter = new CustomAdapter(getApplicationContext(), items);
                    recyclerView.setAdapter(mAdapter);
                    //could have also done:
                    //recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), items)  here we won't need to instantiate a variable, mAdapter for our adapter
                    //TODO FIND OUT MORE ON smoothScrollToPosition(0);
                    recyclerView.smoothScrollToPosition(0);
                    swipeRefreshLayout.setRefreshing(false);   //CHECK THIS OUT
                    progressDialog.hide();      //hides the progressDialog, since the result would be populated by this time

                    }
                //COULD NOT GET THE DATA:
                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.hide();      //still hide the proigressDialog
                    disconnected.setVisibility(View.VISIBLE);       //show the textview that will display internet connectivity error


                }
            });

        }

        //IF IT COULDN'T PERFORM THE TRY OPERATION, ie the retrofit call
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

}

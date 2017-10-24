package com.example.android.retrofitgithubapi.api;

import com.example.android.retrofitgithubapi.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ETORO on 23/10/2017.
 */

public interface ApiService {
    @GET("/search/users?q=language:java+location:lagos")
    Call<ItemResponse> getItems();

}

package com.example.android.retrofitgithubapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ETORO on 23/10/2017.
 */

//THIS GETS THE RESPONSE OF THE SERVICE

public class ItemResponse {
    @SerializedName("items")        //the string item is still from our Api. It is the name of the array that houses our JSON data
    @Expose
    private List<Item> items;       //this gets a List of type Item, hence our items, houses the data list from our JSON

    public List<Item> getItems(){
        return items;
    }

    //Incase you want to change the items
    public void setItems(List<Item> items){
        this.items = items;

    }

}

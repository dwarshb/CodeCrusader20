package com.bhadra.dwarsh.codecrusader20.Interface;

import com.bhadra.dwarsh.codecrusader20.Pojo.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String url = "https://jsonplaceholder.typicode.com";
    @GET("users")
    Call<List<Item>> getusers();
}

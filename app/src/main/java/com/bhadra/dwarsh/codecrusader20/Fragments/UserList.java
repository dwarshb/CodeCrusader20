package com.bhadra.dwarsh.codecrusader20.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhadra.dwarsh.codecrusader20.Adapter.ItemAdapter;
import com.bhadra.dwarsh.codecrusader20.Interface.API;
import com.bhadra.dwarsh.codecrusader20.Pojo.Item;
import com.bhadra.dwarsh.codecrusader20.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserList extends Fragment {
    RecyclerView foodlist;
    ItemAdapter adapter;
    ArrayList<Item> items = new ArrayList<>();
    public UserList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        foodlist = (RecyclerView)view.findViewById(R.id.itemlist);
        adapter = new ItemAdapter(getContext(),items,0);
        foodlist.setAdapter(adapter);
        items.clear();
        //RetroFit Initialize
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<List<Item>> calluser = api.getusers();
        calluser.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                //Add Response of API in List
                for(int i=0;i<response.body().size();i++) {
                    items.add(response.body().get(i));
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                //Display Dialog to show error of API
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Cannot Load Data from API");
                dialog.setMessage(t.getMessage());
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

        return view;
    }

}




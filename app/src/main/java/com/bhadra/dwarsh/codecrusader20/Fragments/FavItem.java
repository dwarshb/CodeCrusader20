package com.bhadra.dwarsh.codecrusader20.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhadra.dwarsh.codecrusader20.Adapter.ItemAdapter;
import com.bhadra.dwarsh.codecrusader20.Database.DBHelper;
import com.bhadra.dwarsh.codecrusader20.Pojo.Item;
import com.bhadra.dwarsh.codecrusader20.R;

import java.util.ArrayList;

public class FavItem extends Fragment {
    RecyclerView foodlist;
    ItemAdapter adapter;
    public FavItem() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        foodlist = (RecyclerView)view.findViewById(R.id.itemlist);
        DBHelper mydb = new DBHelper(getContext());
        adapter = new ItemAdapter(getContext(),mydb.getAllFav(),1);
        foodlist.setAdapter(adapter);
        return view;
    }

}



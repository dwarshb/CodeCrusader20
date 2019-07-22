package com.bhadra.dwarsh.codecrusader20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bhadra.dwarsh.codecrusader20.Database.DBHelper;
import com.bhadra.dwarsh.codecrusader20.DetailScreen;
import com.bhadra.dwarsh.codecrusader20.Pojo.Address;
import com.bhadra.dwarsh.codecrusader20.Pojo.Item;
import com.bhadra.dwarsh.codecrusader20.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> itemList;
    public ItemAdapter()
    {}
    public ItemAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.bindViewHolder(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView idtv,usernametv,emailidtv;
        int id;
        String address;
        DBHelper mydb;
        private CheckBox fav;
        public MyViewHolder(final View itemView) {
            super(itemView);
            idtv = (TextView)itemView.findViewById(R.id.userid);
            usernametv = (TextView)itemView.findViewById(R.id.username);
            emailidtv = (TextView)itemView.findViewById(R.id.emailid);
            fav = (CheckBox)itemView.findViewById(R.id.favourite);
            mydb = new DBHelper(context);
        }

        public void bindViewHolder(final Item item) {
            idtv.setText(item.getId()+"");
            id = Integer.parseInt(idtv.getText().toString());
            usernametv.setText(item.getUsername());
            emailidtv.setText(item.getEmail());
            itemView.findViewById(R.id.itemlayout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.getAddress()!=null) {
                        address = item.getAddress().getStreet() + "," + item.getAddress().getSuite() + "," + item.getAddress().getCity() + "," + item.getAddress().getZipcode();
                        Log.e("Error", address);
                        Intent intent = new Intent(context, DetailScreen.class);
                        intent.putExtra("ADDRESS", address);
                        intent.putExtra("POSITION", item.getAddress().getGeo().getLat() + "," + item.getAddress().getGeo().getLng());
                        context.startActivity(intent);
                    }
                }
            });
            for (int i=0;i<mydb.getAllFav().size();i++) {
                if (mydb.getAllFav().get(i).getUsername().equals(item.getUsername())) {
                    Log.e("Error", "Here" + item.getUsername());
                    fav.setChecked(true);
                } else {
                    Log.e("Error", "No");
                }
            }
            fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                    {
                        Log.e("Error",id+","+idtv.getText().toString());
                        if (mydb.insertContact(id,usernametv.getText().toString(),emailidtv.getText().toString()))
                        {
                            Snackbar.make(itemView,"ID:"+id+" Added to Favourite",Snackbar.LENGTH_LONG).show();
                        }
                        else
                        {
                            Snackbar.make(itemView,"Failed to Add Favourite",Snackbar.LENGTH_LONG).show();
                            fav.setChecked(false);
                        }
                    }
                    else {
                        mydb.deleteContact(id);
                        Snackbar.make(itemView,"Item Removed from Favourite",Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}

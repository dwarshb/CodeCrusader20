package com.bhadra.dwarsh.codecrusader20.Pojo;


import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    int id;
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;
    @SerializedName("address")
    Address address;
    public Item() {}
    public Item(int id,String username,String email)
    {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}

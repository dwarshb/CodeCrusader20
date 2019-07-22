package com.bhadra.dwarsh.codecrusader20.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bhadra.dwarsh.codecrusader20.Fragments.FavItem;
import com.bhadra.dwarsh.codecrusader20.Fragments.UserList;

public class TabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UserList userlist = new UserList();
                return userlist;
            case 1:
                FavItem favitem = new FavItem();
                return favitem;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}


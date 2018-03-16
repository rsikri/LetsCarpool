package com.sarthakmeh.shareyourride.Adapters;

/**
 * Created by sarthakmeh on 7/6/16.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.sarthakmeh.shareyourride.Fragments.ListViewFragment;
import com.sarthakmeh.shareyourride.Fragments.MapViewFragment;

import org.json.JSONArray;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
    private final String[] tabTitles = new String[]{"ListView", "MapView"};
    private JSONArray userFriends;

    public TabPagerAdapter(FragmentManager fm, JSONArray jsonArray) {
        super(fm);
        this.userFriends = jsonArray;
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return ListViewFragment.newInstance(userFriends);
            case 1:
                return MapViewFragment.newInstance(userFriends);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return PAGE_COUNT;
    }
}

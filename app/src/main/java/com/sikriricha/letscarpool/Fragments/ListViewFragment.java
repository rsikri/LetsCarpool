package com.sarthakmeh.shareyourride.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sarthakmeh.shareyourride.Adapters.FriendsListAdapter;
import com.sarthakmeh.shareyourride.Models.User;
import com.sarthakmeh.shareyourride.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sarthakmeh on 7/6/16.
 */
public class ListViewFragment extends Fragment {

    ArrayList<User> friendsList = new ArrayList<>();
    ListView friends_lv;
    FriendsListAdapter adapter;
    static JSONArray friendsArr = new JSONArray();
    SharedPreferences preferences;
    
    public static ListViewFragment newInstance(JSONArray jsonArray) {
        friendsArr = jsonArray;
        return new ListViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listview, container, false);
        friends_lv = (ListView) rootView.findViewById(R.id.lv_friends);

        preferences = getActivity().getSharedPreferences("SYD", Context.MODE_PRIVATE);

        for (int i=0; i<friendsArr.length(); i++) {
            User user = new User();
            try {
                user.setUsername(new JSONObject(friendsArr.get(i).toString()).getString("name"));
                user.setGender(new JSONObject(friendsArr.get(i).toString()).getString("gender"));
                user.setImg_url(new JSONObject(friendsArr.get(i).toString()).getString("img"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!user.getUsername().matches(preferences.getString("username", ""))) friendsList.add(user);
        }

        adapter = new FriendsListAdapter(getContext(), friendsList, preferences.getString("username", ""));
        friends_lv.setAdapter(adapter);

        friends_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FriendProfileDialogFragment friendProfileDialogFragment = new FriendProfileDialogFragment(getContext(), friendsList.get(position).getUsername());
                friendProfileDialogFragment.show(getActivity().getFragmentManager(), "Show Friend Profile");
            }
        });

        return rootView;
    }
}

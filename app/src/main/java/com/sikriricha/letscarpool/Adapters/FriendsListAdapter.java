package com.sarthakmeh.shareyourride.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarthakmeh.shareyourride.Models.User;
import com.sarthakmeh.shareyourride.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sarthakmeh on 7/6/16.
 */
public class FriendsListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> list = new ArrayList();
    private LayoutInflater inflater;
    private String user;

    public FriendsListAdapter(Context context, ArrayList<User> list, String user) {
        this.context = context;
        this.list = list;
        this.user = user;
        Log.d("TAG", list.toString());
        inflater = LayoutInflater.from(this.context);
    }

    public FriendsListAdapter(){
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder mViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.friends_list_item,null);
            mViewHolder = new MyViewHolder();
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        mViewHolder.username = (TextView) convertView.findViewById(R.id.item_friends_username);
        mViewHolder.username.setText(list.get(position).getUsername());
        mViewHolder.gender = (TextView) convertView.findViewById(R.id.item_friends_gender);
        mViewHolder.gender.setText(list.get(position).getGender());
        mViewHolder.img = (ImageView) convertView.findViewById(R.id.item_friends_image);
        if (list.get(position).getImg_url() == null) {
            Picasso.with(context).load(R.mipmap.icon).into(mViewHolder.img);
        }else {
            Picasso.with(context).load(list.get(position).getImg_url()).into(mViewHolder.img);
        }

        return convertView;
    }

    public static class MyViewHolder{
        TextView username, gender;
        ImageView img;
    }
}

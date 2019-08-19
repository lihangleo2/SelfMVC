package com.lihang.leopro.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihang.leopro.R;

import java.util.ArrayList;


/**
 * Created by lihang on 2018/11/28.
 */
public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> faceList;
    private Context context;


    public CardAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<String> faceList) {
        this.faceList = faceList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

    }


    @Override
    public int getItemCount() {
        return faceList == null ? 0 : faceList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }


}

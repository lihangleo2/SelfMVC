package com.lihang.leopro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lihang.leopro.R;

import java.util.ArrayList;

/**
 * Created by leo
 * on 2019/2/28.
 */
public class ListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> cepList;
    private LayoutInflater inflater;

    public ListItemAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    public void setEmailInfos(ArrayList<String> cepList) {
        this.cepList = cepList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View v = inflater.inflate(R.layout.item_list, parent, false);
        holder = new ReViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReViewHolder reViewHolder = (ReViewHolder) holder;
        reViewHolder.text.setText("测试最新刷新控件==="+position);
    }

    @Override
    public int getItemCount() {
        return cepList == null ? 0 : cepList.size();
    }

    class ReViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ReViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }

    }


}

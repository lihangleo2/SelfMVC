package com.lihang.leopro.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lihang.leopro.R;
import java.util.ArrayList;


public class GuideAdapter extends PagerAdapter {

    ArrayList<Integer> guides;
    Context mContext;
    private View.OnClickListener listener;
    LayoutInflater inflater;

    public GuideAdapter(Context context, ArrayList<Integer> guides, View.OnClickListener listener) {
        this.mContext = context;
        this.guides = guides;
        this.listener = listener;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() { // 获得size
        return guides.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.item_guide, null);
        ImageView image = view.findViewById(R.id.image);

        image.setImageResource(guides.get(position));
        ((ViewPager) container).addView(view);
        return view;
    }
}
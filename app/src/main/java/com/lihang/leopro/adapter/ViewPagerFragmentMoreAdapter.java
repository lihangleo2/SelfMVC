package com.lihang.leopro.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lihang.leopro.ui.fragment.son.CategoryFragment;

import java.util.ArrayList;


/**
 * Created by Administrator on 2018/1/19.
 * 这是多fragment的Adapter
 */

public class ViewPagerFragmentMoreAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> homeFocusBeenList;

    public ViewPagerFragmentMoreAdapter(FragmentManager fm, ArrayList<String> homeFocusBeenList) {
        super(fm);
        this.homeFocusBeenList = homeFocusBeenList;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newFragment(position);
    }

    @Override
    public int getCount() {
        return homeFocusBeenList != null ? homeFocusBeenList.size() : 0;
    }
}

package com.lihang.leopro.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.lihang.leopro.R;
import com.lihang.leopro.adapter.ViewPagerFragmentMoreAdapter;
import com.lihang.leopro.base.BaseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeAnchor;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeRule;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by leo
 * on 2019/7/8.
 */
public class Fragment_three extends BaseFragment {
    @BindView(R.id.viewPagerQuestion)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator_question)
    MagicIndicator magicIndicator;
    private ViewPagerFragmentMoreAdapter adapter;
    private ArrayList<String> mDataList = new ArrayList<>();
    private CommonNavigator commonNavigator;

    ImageView badgeImageView;//第一个红点

    @Override
    public int getContentViewId() {
        return R.layout.fragemnt_three;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            mDataList.add("页面_" + i);
        }

        adapter = new ViewPagerFragmentMoreAdapter(getActivity().getSupportFragmentManager(), mDataList);
        viewPager.setAdapter(adapter);

        badgeImageView = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.simple_red_dot_badge_layout, null);

        initMagicIndicator();
    }

    @Override
    public void onClick(View v) {

    }



    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(getResources().getColor(R.color.transparent));
        commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);

                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.blackbb));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.mine_color));
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                        //点击按钮的的时候取消 小红点(和AutoCancelBadge属性有点像)
//                        badgePagerTitleView.setBadgeView(null);
                    }
                });


                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);


                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.mine_color));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);

        //添加小红点相关
        ((BadgePagerTitleView) commonNavigator.getPagerTitleView(3)).setBadgeView(badgeImageView);
        ((BadgePagerTitleView) commonNavigator.getPagerTitleView(3)).setXBadgeRule(new BadgeRule(BadgeAnchor.CONTENT_RIGHT, -UIUtil.dip2px(getActivity(), 6)));
        ((BadgePagerTitleView) commonNavigator.getPagerTitleView(3)).setYBadgeRule(new BadgeRule(BadgeAnchor.CONTENT_TOP, 0));

        //这个设置 选中tab时小红点要不要取消
        ((BadgePagerTitleView) commonNavigator.getPagerTitleView(3)).setAutoCancelBadge(true);
    }
}

package com.lihang.leopro.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lihang.leopro.R;
import com.lihang.leopro.adapter.GuideAdapter;
import com.lihang.leopro.base.BaseActivity;
import com.lihang.smartloadview.SmartLoadingView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.ArrayList;

import butterknife.BindView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by leo
 * on 2019/7/4.
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.image_bg)
    ImageView image_bg;
    GuideAdapter adapter;
    @BindView(R.id.smartLoadingView)
    SmartLoadingView smartLoadingView;
    private ArrayList<Integer> guides = new ArrayList<>();
    Animation aAnimation;
    @BindView(R.id.magic_indicator)
    MagicIndicator magic_indicator;

    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void setListener() {
        smartLoadingView.setLoginClickListener(new SmartLoadingView.LoginClickListener() {
            @Override
            public void click() {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(0, R.animator.set_anim_activity_exit);
            }
        });
    }

    @Override
    protected void processLogic() {
        aAnimation = new AlphaAnimation(0f, 1.0f);
        aAnimation.setDuration(1000);
        guides.add(R.mipmap.guide_1);
        guides.add(R.mipmap.guide_2);
        guides.add(R.mipmap.guide_3);
        guides.add(R.mipmap.guide_4);
        adapter = new GuideAdapter(GuideActivity.this, guides, this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    Glide.with(GuideActivity.this).load(R.mipmap.guide_1_bg)
                            .transition(withCrossFade())
                            .into(image_bg);
                    magic_indicator.setVisibility(View.VISIBLE);
                    smartLoadingView.setVisibility(View.GONE);
                } else if (i == 1) {
                    Glide.with(GuideActivity.this).load(R.mipmap.guide_2_bg)
                            .transition(withCrossFade())
                            .into(image_bg);
                    magic_indicator.setVisibility(View.VISIBLE);
                    smartLoadingView.setVisibility(View.GONE);
                } else if (i == 2) {
                    Glide.with(GuideActivity.this).load(R.mipmap.guide_3_bg)
                            .transition(withCrossFade())
                            .into(image_bg);
                    magic_indicator.setVisibility(View.VISIBLE);
                    smartLoadingView.setVisibility(View.GONE);
                } else {
                    Glide.with(GuideActivity.this).load(R.mipmap.guide_4_bg)
                            .transition(withCrossFade())
                            .into(image_bg);
                    if (smartLoadingView.getVisibility() == View.GONE) {
                        magic_indicator.setVisibility(View.GONE);
                        smartLoadingView.setVisibility(View.VISIBLE);
                        smartLoadingView.startAnimation(aAnimation);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        //index
        initMagicIndicator1();
    }



    private void initMagicIndicator1() {
        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setCircleCount(guides.size());
        circleNavigator.setCircleColor(Color.WHITE);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                viewPager.setCurrentItem(index);
            }
        });
        magic_indicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magic_indicator, viewPager);
    }




    @Override
    public void onClick(View v) {

    }
}

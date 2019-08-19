package com.lihang.leopro.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lihang.leopro.R;
import com.lihang.leopro.base.BaseActivity;
import com.lihang.leopro.utils.PreferenceUtil;

import butterknife.BindView;

/**
 * Created by leo
 * on 2019/7/4.
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.image_dou)
    ImageView image_dou;
    private AnimationDrawable animationDrawable;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if (image_dou!=null){

                        boolean flag = (boolean) PreferenceUtil.get("isFirstApp", false);
                        if (flag) {//flag
                            transfer(MainActivity.class);
                            finish();
                        } else {
                            startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                            finish();
                        }

                    }
                    break;
            }
        }
    };

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcom;
    }

    @Override
    public void setListener() {

    }

    @Override
    protected void processLogic() {
        setXml2FrameAnim1();
    }


    /**
     * 通过XML添加帧动画方法一
     */
    private void setXml2FrameAnim1() {

        // 把动画资源设置为imageView的背景,也可直接在XML里面设置
        image_dou.setBackgroundResource(R.drawable.animdou_solink);//animdou
        animationDrawable = (AnimationDrawable) image_dou.getBackground();
        animationDrawable.start();
        mHandler.sendEmptyMessageDelayed(1,1500);

    }

    @Override
    public void onClick(View v) {

    }
}

package com.lihang.leopro.ui.activity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;
import com.lihang.leopro.R;
import com.lihang.leopro.base.BaseActivity;
import com.lihang.leopro.bean.basebean.ParamsBuilder;
import com.lihang.leopro.bean.basebean.ResponModel;
import com.lihang.leopro.bean.basebean.User;
import com.lihang.leopro.common.JSONS;
import com.lihang.leopro.common.PARAMS;
import com.lihang.leopro.common.SystemConst;
import com.lihang.leopro.model.NetWorkListener;
import com.lihang.leopro.utils.KeyBoardUtils;
import com.lihang.leopro.utils.LogUtils;
import com.lihang.leopro.utils.ToastUtils;
import com.lihang.smartloadview.SmartLoadingView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;

import static com.lihang.leopro.utils.KeyBoardUtils.isShouldHideInput;

/**
 * Created by leo
 * on 2019/7/4.
 * 视频做登录背景，
 * 适配任何比例视频，类似ImageView的centerCrop.
 */

public class LoginActivity extends BaseActivity implements SurfaceHolder.Callback {
    final int LOGIN_TYPE = 99;
    final int UPLOAD_TYPE = 100;
    @BindView(R.id.video_view)
    SurfaceView surfaceView;
    MediaPlayer player;
    SurfaceHolder holder;
    @BindView(R.id.text_protrol)
    TextView text_protrol;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_passworld)
    EditText edit_passworld;
    @BindView(R.id.smartLoadingView)
    SmartLoadingView smartLoadingView;

    @BindView(R.id.relative_login)
    RelativeLayout relative_login;
    @BindView(R.id.relative_title)
    RelativeLayout relative_title;

    //沉浸式状态栏
    protected ImmersionBar mImmersionBar;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 11:
                    //联网成功：通过设置监听AnimatorListener即是启动 小圆扩散全屏动画。在此动画全部完成后拿到回调onAnimtionEnd
                    smartLoadingView.loginSuccess(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            overridePendingTransition(R.anim.scale_test_home, R.anim.scale_test2);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    break;
            }
        }
    };

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void setListener() {
        smartLoadingView.setLoginClickListener(new SmartLoadingView.LoginClickListener() {
            @Override
            public void click() {
                //按钮点击后去进行联网操作
                //这里模拟联网操作
                mHandler.sendEmptyMessageDelayed(11, 2000);


                //正常网络请求 正常
//                getLogin(getStringByUI(edit_phone), getStringByUI(edit_passworld));
                //现在的图片上传
//                upLoadPic();

            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    protected void processLogic() {
        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        surfaceView.setLayoutParams(lp);
        //设置沉浸式状态栏，不放在BaseActivity是因为并不是所有都有用到
        mImmersionBar = ImmersionBar.with(this);
        //设置状态栏字体颜色为黑色
//                .statusBarDarkFont(true);
        //设置系统状态栏的颜色
//                .statusBarColor(R.color.mine_color);
        //布局是否在状态栏下
//                .fitsSystemWindows(true);
        //设置EditText冲突模式
//                .keyboardEnable(true)
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mImmersionBar.init();

        /**
         * 背景视频
         * */
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setKeepScreenOn(true);
        player = new MediaPlayer();
        //设置视频静音
        player.setVolume(0, 0);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //防止刚进入页面视频瞬间拉伸
                if (!player.isPlaying()) {
                    player.start();
                }
            }
        });
        try {
            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.my_baby);
            player.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                    file.getLength());
            player.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            player.setLooping(true);
            player.prepare();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        /**
         * 协议
         * */

        SpannableString spanText = new SpannableString("确认已阅读使用协议书");
        spanText.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.homeyellow));       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }

            @Override
            public void onClick(View view) {
                ToastUtils.showToast("点击了协议书了");

            }
        }, 5, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_protrol.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮
        text_protrol.setText(spanText);
        text_protrol.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件


        //布局动画
        Animation fromRight = AnimationUtils.loadAnimation(this, R.anim.translate_fromright);
        relative_login.startAnimation(fromRight);
        Animation fromTop = AnimationUtils.loadAnimation(this, R.anim.translate_fromtop);
        relative_title.startAnimation(fromTop);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!player.isPlaying()) {
            player.start();
        }
        LogUtils.i("是不是运行了", "onResume==============");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        player.setDisplay(holder);
        LogUtils.i("是不是运行了", "每次都运行了吗----------");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (player.isPlaying()) {
            player.pause();
        }
        LogUtils.i("是不是运行了", "onPause==============");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //加上这句防止泄露
        mImmersionBar.destroy();
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
    }

    @Override
    public void onClick(View v) {

    }


    //点击软键盘其他区域，关闭软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                edit_phone.clearFocus();
                edit_passworld.clearFocus();
                KeyBoardUtils.closeKeybord(edit_phone, LoginActivity.this);

            }
        }
        return super.dispatchTouchEvent(ev);
    }


}

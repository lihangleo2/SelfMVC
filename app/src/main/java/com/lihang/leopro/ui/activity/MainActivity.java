package com.lihang.leopro.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.lihang.leopro.R;
import com.lihang.leopro.base.BaseActivity;
import com.lihang.leopro.ui.fragment.Fragment_four;
import com.lihang.leopro.ui.fragment.Fragment_one;
import com.lihang.leopro.ui.fragment.Fragment_three;
import com.lihang.leopro.ui.fragment.Fragment_two;
import com.lihang.leopro.utils.ToastUtils;
import com.lihang.leopro.utils.networks.NetStateChangeObserver;
import com.lihang.leopro.utils.networks.NetStateChangeReceiver;
import com.lihang.leopro.utils.networks.NetworkType;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NetStateChangeObserver {
    private static final int HOME_ONE = 0;
    private static final int HOME_TWO = 1;
    private static final int HOME_THREE = 2;
    private static final int HOME_FOUR = 3;
    private int index;
    private int currentTabIndex = 0;

    @BindView(R.id.relative_tab_1)
    RelativeLayout relative_tab_1;
    @BindView(R.id.relative_tab_2)
    RelativeLayout relative_tab_2;
    @BindView(R.id.relative_tab_3)
    RelativeLayout relative_tab_3;
    @BindView(R.id.relative_tab_4)
    RelativeLayout relative_tab_4;
    private RelativeLayout[] mTabs;

    Fragment_one fragment_one;
    Fragment_two fragment_two;
    Fragment_three fragment_three;
    Fragment_four fragment_four;


    private FragmentManager manager;
    private ArrayList<Fragment> list_fragment = new ArrayList<Fragment>();

    //沉浸式状态栏
    protected ImmersionBar mImmersionBar;


    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void setListener() {
        relative_tab_1.setOnClickListener(this);
        relative_tab_2.setOnClickListener(this);
        relative_tab_3.setOnClickListener(this);
        relative_tab_4.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        //注册广播
        NetStateChangeReceiver.registerReceiver(this);

        mImmersionBar = ImmersionBar.with(this)

                //设置状态栏字体颜色为黑色
//                .statusBarDarkFont(true);
                //设置系统状态栏的颜色
                .statusBarColor(R.color.shape2)
                //布局是否在状态栏下
                .fitsSystemWindows(true);
        //设置EditText冲突模式
//                .keyboardEnable(true)
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mImmersionBar.init();

        manager = getSupportFragmentManager();
        mTabs = new RelativeLayout[4];
        mTabs[0] = relative_tab_1;
        mTabs[1] = relative_tab_2;
        mTabs[2] = relative_tab_3;
        mTabs[3] = relative_tab_4;


        fragment_one = new Fragment_one();
        fragment_two = new Fragment_two();
        fragment_three = new Fragment_three();
        fragment_four = new Fragment_four();

        list_fragment.add(fragment_one);
        list_fragment.add(fragment_two);
        list_fragment.add(fragment_three);
        list_fragment.add(fragment_four);
        switchFragment(R.id.relative_tab_1);


        //获取渠道号
//        String app_version = "";
//        ApplicationInfo appInfo = null;
//        try {
//            appInfo = MyApplication.getContext().getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        app_version = appInfo.metaData.getString("ATMAN_CHANNEL");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_tab_1:
                switchFragment(R.id.relative_tab_1);
                break;
            case R.id.relative_tab_2:
                switchFragment(R.id.relative_tab_2);
                break;
            case R.id.relative_tab_3:
                switchFragment(R.id.relative_tab_3);
                break;
            case R.id.relative_tab_4:
                switchFragment(R.id.relative_tab_4);
                break;
        }
    }


    public void switchFragment(int id) {
        FragmentTransaction ft = manager.beginTransaction();
        //fragment切换带动画
//        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
//                android.R.anim.fade_in, android.R.anim.fade_out);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(id);
        String tag = (String) relativeLayout.getTag();
        Fragment f = manager.findFragmentByTag(tag);
        if (f == null) {
            int num = Integer.parseInt(tag);
            ft.add(R.id.framLayout, list_fragment.get(num), tag);
        }

        for (int i = 0; i < list_fragment.size(); i++) {
            Fragment fragment = list_fragment.get(i);
            if (fragment.getTag() != null) {
                if (fragment.getTag().equals(tag)) {
                    ft.show(fragment);
                } else {
                    ft.hide(fragment);
                }
            }
        }
        ft.commitAllowingStateLoss();
        switch (id) {
            case R.id.relative_tab_1://首页
                index = HOME_ONE;
                break;
            case R.id.relative_tab_2:
                index = HOME_TWO;
                break;
            case R.id.relative_tab_3:
                index = HOME_THREE;
                break;
            case R.id.relative_tab_4:
                index = HOME_FOUR;
                break;
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }


    @Override
    protected void onResume() {
        super.onResume();
        //绑定观察者
        NetStateChangeReceiver.registerObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解绑观察者
        NetStateChangeReceiver.unRegisterObserver(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("这个是对的么", "重写方法了");
        mImmersionBar.destroy();
        //注释广播
        NetStateChangeReceiver.unRegisterReceiver(this);
    }

    @Override
    public void onNetDisconnected() {
        //断开网络连接回调
        ToastUtils.showToast("网络断开!");
    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        //连接网络回调
        ToastUtils.showToast("网络已连接" + networkType.toString());
    }
}

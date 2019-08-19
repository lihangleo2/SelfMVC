package com.lihang.leopro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.lihang.leopro.base.SystemEnv;
import com.lihang.leopro.bean.basebean.User;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


/**
 * Created by leo on 2017/9/12.
 */

public class MyApplication extends Application  {

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, R.color.colorAccent);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsFooter(context);
            }
        });
    }


    private static MyApplication context;
    private static User loginUser = null;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //捕获崩溃日志，位置在外部存储的LianSou
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());


        //监控各Activity的生命周期，可用于数据埋点
        //同时可定义个int count=0；在onActivityStopped   --；在onActivityStarted++;可监控进入手机后台和从后台回来
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


    public static Context getContext() {
        return context;
    }


    public static MyApplication getInstance() {
        if (context == null) {
            context = new MyApplication();
        }
        return context;
    }


    public final User getLoginUser() {
        if (loginUser == null) {
            loginUser = SystemEnv.getUser();
        }
        return loginUser;
    }

    public static void setInstance(MyApplication app) {
        context = app;
    }

    public final void login(User loginUser) {
        MyApplication.loginUser = loginUser;
    }


    /**
     * 退出登录,清空数据
     */
    public void logout() {
        loginUser = null;
    }

}

package com.lihang.leopro.base;

import android.content.Context;

import com.lihang.leopro.MyApplication;
import com.lihang.leopro.bean.basebean.User;
import com.lihang.leopro.utils.PreferenceUtil;


/**
 * Created by leo
 * on 2016/9/20.
 * 用于存储 等录者用户信息
 */
public class SystemEnv {
    private static Context context = MyApplication.getInstance().getApplicationContext();

    /**
     * 登录用户信息存取
     */
    private static final String USER = "USER";

    public static void saveUser(User user) {
        PreferenceUtil.save(user, USER);
    }

    public static User getUser() {
        User user = PreferenceUtil.find(USER, User.class);
        return user;
    }

    public static void deleteUser() {
        PreferenceUtil.deleteAll(User.class);
    }
}

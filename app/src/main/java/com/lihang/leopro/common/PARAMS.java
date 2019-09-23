package com.lihang.leopro.common;

import java.util.HashMap;

/**
 * Created by leo on 2017/9/13.
 * 键值对上传类
 */

public class PARAMS {
    public static String  pageSize = "10";


    public static HashMap<String, String> gank(String en_name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("en_name", en_name);
        return map;
    }
    /**
     * 登录
     */
    public static HashMap<String, String> login(String username,String password,String grant_type,String scope,String userType) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        map.put("grant_type", grant_type);
        map.put("scope", scope);
        map.put("userType", userType);
        return map;
    }


    /**
     * 获取验证码
     */
    public static HashMap<String, String> getSmscode(String mobile) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("mobile", mobile);
        return map;
    }


    /**
     * 脸搜上传图片
     */
    public static HashMap<String, String> uploadImage(String keyName,String mimeType,String pathType) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("keyName", keyName);
        map.put("mimeType", mimeType);
        map.put("pathType", pathType);
        return map;
    }

    /**
     * 脸搜上传图片
     */
    public static HashMap<String, String> uploadPic(String sequence) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sequence", sequence);
        return map;
    }
}

package com.lihang.leopro.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leo on 2016/7/12.
 */
public class StringUtils {


    /**
     * 判断手机号 是否正确
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 判断身份证号是否正确（其中包括了月份和日期进行了判断）
     * */
    public static boolean isIDcards(String text) {
        Pattern p = Pattern
                .compile("^\\d{6}(18|19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|[xX])$");
        Matcher m = p.matcher(text);
        return m.matches();
    }


}

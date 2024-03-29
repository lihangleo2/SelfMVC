package com.lihang.leopro.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.lihang.leopro.MyApplication;
import com.lihang.leopro.okhttps.EasyOk;
import com.lihang.leopro.bean.basebean.User;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by leo
 * on 2019/7/3.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    //获取当前activity布局文件
    public abstract int getContentViewId();

    //设置监听事件
    public abstract void setListener();

    //处理逻辑业务
    protected abstract void processLogic();

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mUnbinder = ButterKnife.bind(this);
        setListener();
        processLogic();

    }

    //每个页面获取登入者信息
    public final User getLoginUser() {
        return MyApplication.getInstance().getLoginUser();
    }

    //简单跳转
    public void transfer(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


    //快速获取textView 或 EditText上文字内容
    public String getStringByUI(View view) {
        if (view instanceof EditText) {
            return ((EditText) view).getText().toString().trim();
        } else if (view instanceof TextView) {
            return ((TextView) view).getText().toString().trim();
        }
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        //退出一个页面。取消这个页面所有正在进行的网络请求
        EasyOk.getInstance().cancleOkhttpTag(this.getClass().toString());
    }


}

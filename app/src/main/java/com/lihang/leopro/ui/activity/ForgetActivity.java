package com.lihang.leopro.ui.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lihang.leopro.R;
import com.lihang.leopro.base.BaseActivity;
import com.lihang.leopro.utils.KeyBoardUtils;
import com.lihang.leopro.utils.LogUtils;
import com.lihang.leopro.utils.TimeCount;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by leo
 * on 2019/7/8.
 */
public class ForgetActivity extends BaseActivity {
    @BindView(R.id.text_code_zc)
    TextView text_code_zc;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    //重置密码
    private TimeCount time;

    @Override
    public int getContentViewId() {
        return R.layout.activity_forget;
    }

    @Override
    public void setListener() {
        text_code_zc.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        EventBus.getDefault().register(this);
        time = new TimeCount(60000, 1000, text_code_zc);
    }

    //EventBus的回调，参数是Object类型
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onbackEvent(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_code_zc:
                LogUtils.i("我走了几遍", "111122222");
                time.start();
                break;
        }
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (KeyBoardUtils.isShouldHideInput(v, ev)) {
                KeyBoardUtils.closeKeybord(edit_phone, ForgetActivity.this);
                edit_phone.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}

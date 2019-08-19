package com.lihang.leopro.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lihang.leopro.R;
import com.lihang.leopro.ui.activity.ForgetActivity;
import com.lihang.leopro.ui.activity.WebViewActivity;
import com.lihang.leopro.base.BaseFragment;
import com.lihang.leopro.utils.ToastUtils;
import com.lihang.leopro.customview.dialogm.DialogUtil;
import com.lihang.leopro.customview.dialogm.mydialog.UpdateDialog;

import butterknife.BindView;

/**
 * Created by leo
 * on 2019/7/8.
 */
public class Fragment_four extends BaseFragment {
    @BindView(R.id.text_forget)
    TextView text_forget;
    @BindView(R.id.text_webview)
    TextView text_webview;
    @BindView(R.id.text_dialog)
    TextView text_dialog;
    @BindView(R.id.text_download)
    TextView text_download;

    @Override
    public int getContentViewId() {
        return R.layout.fragemnt_four;
    }

    @Override
    protected void setListener() {
        text_forget.setOnClickListener(this);
        text_webview.setOnClickListener(this);
        text_dialog.setOnClickListener(this);
        text_download.setOnClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_forget:
                transfer(ForgetActivity.class);
                break;

            case R.id.text_webview:
                transfer(WebViewActivity.class);
                break;
            case R.id.text_dialog:

                DialogUtil.alertIosDialog(getActivity(), "是否退出登录？", "确定", "取消", new DialogUtil.DialogAlertListener() {
                    @Override
                    public void yes() {
                        ToastUtils.showToast("点击了确定");
                    }

                });
                break;

            case R.id.text_download:
                UpdateDialog updateDialog = new UpdateDialog(getActivity());
                updateDialog.show();
                break;
        }
    }
}

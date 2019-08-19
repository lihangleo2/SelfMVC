package com.lihang.leopro.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lihang.leopro.R;
import com.lihang.leopro.adapter.CardAdapter;
import com.lihang.leopro.base.BaseFragment;
import com.lihang.leopro.customview.horismartrefresh.SmartRefreshHorizontal;
import com.lihang.leopro.model.ModelSuperImpl;
import com.lihang.leopro.model.PermissionListener;
import com.lihang.leopro.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by leo
 * on 2019/7/8.
 */
public class Fragment_two extends BaseFragment implements PermissionListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshHorizontal smartRefreshLayout;
    private CardAdapter adapter;
    @BindView(R.id.text_quanx)
    TextView text_quanx;
    private RxPermissions rxPermissions;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 11:
                    smartRefreshLayout.finishRefresh();
                    break;
                case 12:
                    smartRefreshLayout.finishLoadMore();
                    //以下对横向无用
//                    smartRefreshLayout.finishLoadMore(false);//加载失败
//                    smartRefreshLayout.finishLoadMoreWithNoMoreData();//没有更多数据了
                    break;
            }
        }
    };

    @Override
    public int getContentViewId() {
        return R.layout.fragemnt_two;
    }

    @Override
    protected void setListener() {
        text_quanx.setOnClickListener(this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mhandler.sendEmptyMessageDelayed(11, 2000);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mhandler.sendEmptyMessageDelayed(12, 2500);
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        adapter = new CardAdapter(getActivity());

        ArrayList<String> stringArr = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            stringArr.add("1");
        }

//        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);

        adapter.setData(stringArr);
        recyclerView.setAdapter(adapter);
        rxPermissions = new RxPermissions(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_quanx:
                ModelSuperImpl.permission().requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//
//                            //有些手机保持禁止以后会出现aBoolean为true的情况，则加上下一句
//                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                return;
//                            }
//                            ToastUtils.showToast("权限申请成功");
//
//                        } else {
//                            Toast.makeText(getActivity(), "存储权限被拒绝", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                break;
        }
    }

    @Override
    public void permissionSuccess(int command) {
        //command 处理同一页面请求多种权限
        ToastUtils.showToast("权限申请成功");
    }

    @Override
    public void permissionFail(int command) {
        ToastUtils.showToast("权限申请失败");
    }
}

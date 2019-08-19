package com.lihang.leopro.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lihang.leopro.R;
import com.lihang.leopro.adapter.ListItemAdapter;
import com.lihang.leopro.base.BaseFragment;
import com.lihang.leopro.bean.AnimationItem;
import com.lihang.leopro.bean.basebean.ParamsBuilder;
import com.lihang.leopro.common.PARAMS;
import com.lihang.leopro.model.ModelSuperImpl;
import com.lihang.leopro.model.NetWorkListener;
import com.lihang.leopro.model.PermissionListener;
import com.lihang.leopro.utils.LogUtils;
import com.lihang.leopro.utils.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by leo
 * on 2019/7/8.
 */
public class Fragment_one extends BaseFragment implements NetWorkListener, PermissionListener {
    final int GET_COMMAND = 99;
    final int UPLOAD_COMMAND = 100;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private ListItemAdapter adapter;
    private AnimationItem animationItem;//recycleView动画类

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
                    //现在没有更多数据了，只要刷新后会自动重置
//                    smartRefreshLayout.finishLoadMoreWithNoMoreData();//没有更多数据了
                    break;
            }
        }
    };

    @BindView(R.id.floatbutton)
    ImageView floatbutton;//回到首页的操作

    @Override
    public int getContentViewId() {
        return R.layout.fragemnt_one;
    }

    @Override
    protected void setListener() {
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


        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                int firstVisibleItemIndex = linearLayoutManager.findFirstVisibleItemPosition();
                int firstVisibleItemIndexAbout = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                LogUtils.i("现在监听看", firstVisibleItemIndex + "==");
                LogUtils.i("现在监听看", firstVisibleItemIndexAbout + "*****************");

                if (firstVisibleItemIndex == 0) {
                    if (floatbutton.getVisibility() == View.VISIBLE) {
                        floatbutton.setVisibility(View.GONE);
                        Animation animator = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_backtop_exit);
                        floatbutton.setAnimation(animator);
                        animator.start();
                    }
                } else {
                    if (floatbutton.getVisibility() == View.GONE) {
                        floatbutton.setVisibility(View.VISIBLE);
                        Animation animator = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_backtop_enter);
                        floatbutton.setAnimation(animator);
                        animator.start();
                    }
                }


            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        adapter = new ListItemAdapter(getActivity());

        ArrayList<String> stringArr = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            stringArr.add("6");
        }

//        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);

        adapter.setEmailInfos(stringArr);
        recyclerView.setAdapter(adapter);

        animationItem = new AnimationItem("Slide from right", R.anim.layout_animation_from_right);
        runLayoutAnimation(recyclerView, animationItem);
    }

    /*
     * 开启recycleView加载动画
     * */
    private void runLayoutAnimation(final RecyclerView recyclerView, final AnimationItem item) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, item.getResourceId());

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onClick(View v) {

    }

    //这里是ButtonKnife单独使用的样子
//    @OnClick(R.id.buttonPanel)
//    public void getClick() {
//        ModelSuperImpl.netWork().gankGet(ParamsBuilder.build().params(PARAMS.gank("android"))
//                .command(GET_COMMAND), this);
//    }

    //这里是一群控件使用的样子，就不用写setOnclickListtener了
    @OnClick({R.id.buttonPanel, R.id.buttonPane2})
    public void uploadClick(View view) {
        switch (view.getId()) {
            case R.id.buttonPanel:
                ModelSuperImpl.netWork().gankGet(ParamsBuilder.build().params(PARAMS.gank("android"))
                        .command(GET_COMMAND), this);
                break;

            case R.id.buttonPane2:
                ModelSuperImpl.permission().requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
                break;
        }
    }


    @Override
    public void onNetCallBack(int command, Object object) {
        switch (command) {
            case GET_COMMAND:
                //如果不重写overError 和successError.默认都是弹错误信息
                //如果重写overError,这是NerfailBean.如果是successError,那么根据返回status来判断。
                String result = (String) object;
                LogUtils.i("get请求返回数据", result);
                break;
            case UPLOAD_COMMAND:
                String uploadResult = (String) object;
                LogUtils.i("上传文件请求返回数据", "返回数据了 ==> " + uploadResult);
                break;

        }
    }

    @Override
    public void permissionSuccess(int command) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setStyle(CropImageView.Style.CIRCLE);
        imagePicker.setMultiMode(false);

        Intent intent = new Intent(getActivity(), ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
        startActivityForResult(intent, 100);
    }

    @Override
    public void permissionFail(int command) {
        ToastUtils.showToast("上传文件需要此权限");
    }

    private ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images.size() > 0) {
//                txt_upload.setEnabled(true);
//                Glide.with(UploadFragment.this).load(new File(images.get(0).path)).into(img_);
                //上传图片
                File file = new File(images.get(0).path);
                Pair<String, File> map = new Pair<>("file", file);
                ModelSuperImpl.netWork().uploadPic(ParamsBuilder.build().params(PARAMS.uploadPic("1")).command(UPLOAD_COMMAND),
                        this, map);
            }
        }
    }
}

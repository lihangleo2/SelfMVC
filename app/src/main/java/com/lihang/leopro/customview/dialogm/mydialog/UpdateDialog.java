package com.lihang.leopro.customview.dialogm.mydialog;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lihang.leopro.R;
import com.lihang.leopro.bean.basebean.ParamsBuilder;
import com.lihang.leopro.model.ModelSuperImpl;
import com.lihang.leopro.model.PermissionListener;
import com.lihang.leopro.okhttps.okcallback.OnDownloadListener;
import com.lihang.leopro.utils.TimeUtils;
import com.lihang.leopro.utils.ToastUtils;
import java.io.File;



/**
 * Created by lihang on 2017/8/14.
 */

public class UpdateDialog extends Dialog implements OnDownloadListener, PermissionListener {

    private Context context;
    private RelativeLayout relative_showApp;
    private RelativeLayout relative_loading;
    private ProgressBar progressBar;
    private TextView text_message_loading;
    private TextView text_update;//确认升级
    private TextView progress_percent;//下载进度
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ok_down/demo/";
    private String fileName = "easyOk.apk";


    public UpdateDialog(Context context) {
        this(context, R.style.MyDialogStyle);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        this.context = context;
    }


    public UpdateDialog(Context context, int theme) {
        super(context, theme);
        //非强制更新，加上标识，true
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_app);
        relative_showApp = findViewById(R.id.relative_showApp);
        relative_loading = findViewById(R.id.relative_loading);
        progress_percent = findViewById(R.id.progress_percent);
        text_update = findViewById(R.id.text_update);
        progressBar = findViewById(R.id.progress);
        text_message_loading = findViewById(R.id.text_message_loading);
        text_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelSuperImpl.permission().requestPermission((FragmentActivity) context,UpdateDialog.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    public void openFile(File var0, Context var1) {
        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(var1, var1.getApplicationContext().getPackageName() + ".provider", var0);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, var1.getContentResolver().getType(uriForFile));
        } else {
            var2.setDataAndType(Uri.fromFile(var0), getMIMEType(var0));
        }
        try {
            var1.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(var1, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }

    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }


    @Override
    public void permissionSuccess(int command) {
        ModelSuperImpl.netWork().downApk(ParamsBuilder.build().path(path)
                .fileName(fileName).tag("downApk"), this);
    }

    @Override
    public void permissionFail(int command) {
        ToastUtils.showToast("文件下载，需要此权限");
    }

    @Override
    public void onDownloadSuccess(File file) {
        openFile(file, context);
    }

    @Override
    public void onDownLoadTotal(long total) {
        //显示下载界面
        if (relative_showApp != null) {
            relative_showApp.setVisibility(View.GONE);
            relative_loading.setVisibility(View.VISIBLE);
            text_message_loading.setText("正在下载(" + TimeUtils.round((double) total / 1024 / 1024, 2) + "M)");
        }
    }

    @Override
    public void onDownloading(int progress) {
        //显示进度条
        if (progressBar != null) {
            progressBar.setProgress(progress);
            progress_percent.setText(progress + "%");
        }
    }

    @Override
    public void onDownloadFailed(Exception e) {
        ToastUtils.showToast(e.toString() + "下载失败！！");
    }
}

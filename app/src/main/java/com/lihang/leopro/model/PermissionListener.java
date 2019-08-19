package com.lihang.leopro.model;

/**
 * Created by leo
 * on 2019/8/2.
 */
public interface PermissionListener {
    void permissionSuccess(int command);

    void permissionFail(int command);
}

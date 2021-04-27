package com.yhyy.permissionslist;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class Act_Rx extends AppCompatActivity {

    /**
     * 权限组
     */
    private static final String[] permissionsGroup = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_permission);
        ButterKnife.bind(this);
        initToolBar();
    }

    @OnClick(R.id.button)
    public void onClick() {
//        testRequest();
        testRequestEach();
//        testRequestEachCombined();
//        testEnsure();
//        testEnsureEach();
//        testEnsureEachCombined();
    }

    /**
     * request例子:
     * 不支持返回权限名;
     * 返回的权限结果:全部同意时值true,否则值为false
     */
    public void testRequest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissionsGroup)
                .subscribe(granted -> {
                    Log.i("YHYY", "申请结果:" + granted);
                });

    }

    /**
     * requestEach例子:
     * 把每一个权限的名称和申请结果都列出来
     */
    public void testRequestEach() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(permissionsGroup)
                .subscribe(permission -> {
                    Log.i("YHYY", "权限名称:" + permission.name + ",申请结果:" + permission.granted);
                });
    }

    /**
     * requestEachCombined例子:
     * 返回的权限名称:将多个权限名合并成一个
     * 返回的权限结果:全部同意时值true,否则值为false
     */
    public void testRequestEachCombined() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(permissionsGroup)
                .subscribe(permission -> {
                    Log.i("YHYY", "权限名称:" + permission.name + ",申请结果:" + permission.granted);
                });
    }

    /**
     * ensure例子:
     * 必须配合rxjava,回调结果与request一样
     */
    public void testEnsure() {
        RxPermissions rxPermissions = new RxPermissions(this);
        Observable.timer(10, TimeUnit.MILLISECONDS)
                .compose(rxPermissions.ensure(permissionsGroup))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.i("YHYY", "申请结果:" + aBoolean);
                    }
                });
    }

    /**
     * ensureEach例子:
     * 必须配合rxjava,回调结果跟requestEach一样
     */
    public void testEnsureEach() {
        RxPermissions rxPermissions = new RxPermissions(this);
        //
        Observable.timer(10, TimeUnit.MILLISECONDS)
                .compose(rxPermissions.ensureEach(permissionsGroup))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        Log.i("YHYY", "权限名称:" + permission.name + ",申请结果:" + permission.granted);
                    }
                });
    }

    /**
     * ensureEachCombined例子:
     * 必须配合rxjava,回调结果跟requestEachCombined一样
     */
    public void testEnsureEachCombined() {
        RxPermissions rxPermissions = new RxPermissions(this);
        Observable.timer(10, TimeUnit.MILLISECONDS)
                .compose(rxPermissions.ensureEachCombined(permissionsGroup))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        Log.i("YHYY", "权限名称:" + permission.name + ",申请结果:" + permission.granted);
                    }
                });
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

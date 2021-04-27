package com.yhyy.permissionslist;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_RunTime extends AppCompatActivity {
    private static final int CAMERA = 0x01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_permission);
        ButterKnife.bind(this);
        initToolBar();
    }

    @OnClick(R.id.button)
    public void onClick() {
        getPermission();
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(Act_RunTime.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 无权限时去请求权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(Act_RunTime.this,
                    Manifest.permission.CAMERA)) {
                // 通常是弹框告诉用户权限的必要性，然后继续申请权限
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setMessage("摄像头权限申请")
                        .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Act_RunTime.this,
                                        new String[]{Manifest.permission.CAMERA}, CAMERA);
                            }
                        })
                        .setNegativeButton("不给", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA);
            }
        } else {
            // 有权限直接进行业务操作
        }
    }

    /**
     * 直接在Activity重写 onRequestPermissionsResult()方法即可
     * requestCode requetsPermissions()方法中传入的RequestCode
     * String[] permissons  requetsPermissions()方法中传入的permission数组
     * grantResults  返回的具体结果
     **/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授予权限，拍照
                } else {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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

package com.yhyy.permissionslist;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yhyy.permissionslist.permissions.PermissionsManager;
import com.yhyy.permissionslist.permissions.PermissionsResultAction;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_Custom extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_permission);
        ButterKnife.bind(this);
        initToolBar();
    }

    @OnClick(R.id.button)
    public void onClick() {
        requestPermissions();
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {//授权
                Toast.makeText(Act_Custom.this, "授权成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {//未授权
                Toast.makeText(Act_Custom.this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
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

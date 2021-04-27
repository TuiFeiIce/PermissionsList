package com.yhyy.permissionslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.permissionslist.adapter.Adapter_Type;
import com.yhyy.permissionslist.config.Contracts;
import com.yhyy.permissionslist.inter.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    Adapter_Type adapterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initToolBar();
        initListener();
    }

    private void initListener() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        adapterType = new Adapter_Type(context, Contracts.typeList);
        recyclerview.setAdapter(adapterType);
        adapterType.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Integer integer) {
                switch (integer) {
                    case 0:
                        startActivity(new Intent(context, Act_RunTime.class));
                        break;
                    case 1:
                        startActivity(new Intent(context, Act_Rx.class));
                        break;
                    case 2:
                        startActivity(new Intent(context, Act_Easy.class));
                        break;
                    case 3:
                        startActivity(new Intent(context, Act_Dispatcher.class));
                        break;
                    case 4:
                        startActivity(new Intent(context, Act_Custom.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initToolBar() {
    }

    private void initData() {

    }
}
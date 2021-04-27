package com.yhyy.permissionslist.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceWolf on 2019/9/13.
 */
public class Contracts {
    public static final List<String> typeList = new ArrayList<String>() {//设置列表
        {
            add(new String("RunTimePer"));
            add(new String("RxPer"));
            add(new String("EasyPer"));
            add(new String("PerDispatcher"));
            add(new String("Custom"));
        }
    };
}

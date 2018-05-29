package com.fyl.demo.ui;

import android.app.Application;

import net.market.zhkj.com.adlibrary.fragment.db.DBManageHelper;

import org.xutils.x;


/**
 * Created by DN on 2017/11/8.
 */

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //PushAgent mPushAgent = PushAgent.getInstance(this);
        //mPushAgent.setDebugMode(true);
        //关闭错误日志统计,默认开启
        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志, BuildConfig.DEBUG开启debug会影响性能.
        //初始化数据库，用于下载
        DBManageHelper.initDB();
    }





}

package com.fyl.demo.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.fyl.demo.R;
import com.fyl.demo.base.BaseActivty;
import com.hyphenate.chat.EMMessage;

/**
 * Created by DN on 2017/9/7.
 */

public class GuideActivity extends BaseActivty {
    public  static int ACCESS_FINE_LOCATION = 2;//手机状态权限申请回调标识

    @Override
    public void initParms(Bundle parms) {


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.guide_activity;
    }

    @Override
    public void initView(View view) {

    }




    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

        if (ActivityCompat.checkSelfPermission(GuideActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, ACCESS_FINE_LOCATION);
            return;
        }else{
            startActivity(new Intent(mContext,MainActivity.class));
            finish();
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==ACCESS_FINE_LOCATION){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                startActivity(new Intent(mContext,MainActivity.class));
                finish();
            }else {
                showToast("请开启权限在进入");
                if (ActivityCompat.checkSelfPermission(GuideActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, ACCESS_FINE_LOCATION);
                    return;
                }
            }
        }




    }

    @Override
    public void widgetClick(View v) {

    }



}

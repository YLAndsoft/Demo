package com.fyl.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fyl.demo.R;
import com.fyl.demo.base.MenuFragmentActivity;
import com.fyl.demo.fragment.ModularFiveFragment;
import com.fyl.demo.fragment.ModularFourFragmentn;
import com.fyl.demo.fragment.ModularOneFragment;
import com.fyl.demo.fragment.ModularThreeFragment;
import com.fyl.demo.fragment.ModularTwoFragment;

import java.util.Set;

public class MainActivity extends MenuFragmentActivity {

    // 未选中
    private int[] imageNormals = { R.drawable.ic_home_actionbar0,// 首页
            R.drawable.ic_home_actionbar1,// 软件
            R.drawable.ic_home_actionbar2,// 游戏
            R.drawable.ic_home_actionbar3,//娱乐
            R.drawable.ic_home_actionbar4,// 管理
    };
    // 选中
    private int[] imgsHovers = { R.drawable.ic_home_actionbar_select0,
            R.drawable.ic_home_actionbar_select1,
            R.drawable.ic_home_actionbar_select2,
            R.drawable.ic_home_actionbar_select3,
            R.drawable.ic_home_actionbar_select4,};
    /**模块1*/
    ModularOneFragment softwareFragment;
    /**模块2*/
    ModularTwoFragment gamesFragment;
    /**模块3*/
    ModularThreeFragment mianFragment;
    /**模块4*/
    ModularFourFragmentn manageFragmentn;
    /**模块5*/
    ModularFiveFragment pleasureFragment;
    private ImageView[] imgBtn;
    private TextView[] txtBtn;
    private int flResId = R.id.fl_menu_container;
    private LinearLayout ll_tab_table;
    @Override
    public void initParms(Bundle parms) {
        setAllowFullScreen(true);
        setScreenRoate(false);
        setSteepStatusBar(false);
        setSetActionBarColor(true);// 设置状态栏颜色
    }
    @Override
    public View bindView() {
        return null;
    }
    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }
    @Override
    public void initView(View view) {
        ll_tab_table = $(R.id.main_menu_table);
        int[] imgIds = { R.id.iv_menu_0, R.id.iv_menu_1, R.id.iv_menu_2,R.id.iv_menu_3,R.id.iv_menu_4};
        int[] txtIds = { R.id.tv_menu_0, R.id.tv_menu_1, R.id.tv_menu_2,R.id.tv_menu_3,R.id.tv_menu_4 };
        imgBtn = new ImageView[imgIds.length];
        txtBtn = new TextView[txtIds.length];
        for (int i = 0; i < imgIds.length; i++) {
            imgBtn[i] = (ImageView) findViewById(imgIds[i]);
            txtBtn[i] = (TextView) findViewById(txtIds[i]);
        }
        int[] tabResIds = { R.id.ll_menu_0, R.id.ll_menu_1, R.id.ll_menu_2,R.id.ll_menu_3,R.id.ll_menu_4 };
        super.initTab(tabResIds);
        if (mianFragment == null) {
            mianFragment = new ModularThreeFragment();
        }
        switchFragment(flResId, mianFragment);
    }
    @Override
    public void setListener() {

    }
    @Override
    public void doBusiness(Context mContext) {

    }
    private long mkeyTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mkeyTime) > 2000) {
                mkeyTime = System.currentTimeMillis();
                showToast("再按一次退出程序");
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void widgetClick(View v) {

    }
    @Override
    protected boolean onTabClick(int tabId) {
        for (int i = 0; i < txtBtn.length; i++) {
            imgBtn[i].setImageResource(imageNormals[i]);
            txtBtn[i].setTextColor(Color.parseColor("#666666"));
        }
        super.onTabClick(tabId);
        switch (tabId) {
            case R.id.ll_menu_0:
                imgBtn[0].setImageResource(imgsHovers[0]);
                txtBtn[0].setTextColor(getResources().getColor(R.color.system_bar_color));
                ll_tab_table.setBackgroundResource(R.color.white);
                if (softwareFragment == null) {
                    softwareFragment = new ModularOneFragment();
                }
                switchFragment(flResId, softwareFragment);
                break;
            case R.id.ll_menu_1:
                imgBtn[1].setImageResource(imgsHovers[1]);
                txtBtn[1].setTextColor(getResources().getColor(R.color.system_bar_color));
                // 设置底部背景颜色
                ll_tab_table.setBackgroundResource(R.color.white);
                if (gamesFragment == null) {
                    gamesFragment = new ModularTwoFragment();
                }
                switchFragment(flResId, gamesFragment);
                break;
            case R.id.ll_menu_2:
                imgBtn[2].setImageResource(imgsHovers[2]);
                txtBtn[2].setTextColor(getResources().getColor(R.color.system_bar_color));
                // 设置底部背景颜色ModularThreeFragment mianFragment;
                ll_tab_table.setBackgroundResource(R.color.white);
                if (mianFragment == null) {
                    mianFragment = new ModularThreeFragment();
                }
                switchFragment(flResId, mianFragment);
                break;
            case R.id.ll_menu_3:
                imgBtn[3].setImageResource(imgsHovers[3]);
                txtBtn[3].setTextColor(getResources().getColor(R.color.system_bar_color));
                // 设置底部背景颜色 ModularFourFragmentn manageFragmentn;
                ll_tab_table.setBackgroundResource(R.color.white);
                if (manageFragmentn == null) {
                    manageFragmentn = new ModularFourFragmentn();
                }
                switchFragment(flResId, manageFragmentn);
                break;
            case R.id.ll_menu_4:
                imgBtn[4].setImageResource(imgsHovers[4]);
                txtBtn[4].setTextColor(getResources().getColor(R.color.system_bar_color));
                // 设置底部背景颜色ModularFiveFragment pleasureFragment;
                ll_tab_table.setBackgroundResource(R.color.white);
                if (pleasureFragment == null) {
                    pleasureFragment = new ModularFiveFragment();
                }
                switchFragment(flResId, pleasureFragment);
                break;
        }
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();
        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            Set<String> keySet = bun.keySet();
            for (String key : keySet) {
                String value = bun.getString(key);
                Log.i("value的值是>>>",value+"");
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }



}

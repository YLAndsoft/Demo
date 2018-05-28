package net.market.zhkj.com.adlibrary.fragment;

import android.view.View;

/**
 * Created by DN on 2018/5/28.
 */

public abstract class BaseADFragment extends BaseFragment{

    public BaseADFragment(){}

    public BaseADFragment(int layoutId){

    }

    public abstract void getData(); //获取数据

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void widgetClick(View v) {

    }
}

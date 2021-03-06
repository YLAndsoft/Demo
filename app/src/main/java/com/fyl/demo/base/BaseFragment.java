package com.fyl.demo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by DN on 2017/7/5.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected View mContextView = null;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = bindView();
        if (null == mView) {
            mContextView = inflater.inflate(bindLayout(), null, false);
        } else
            mContextView = mView;

        initView();
        initData_();
        return mContextView;
    }

    /**
     * [绑定视图]
     *
     * @return
     */
    public abstract View bindView();

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     *
     */

    private void initData_() {
        initData();
    }

    /**
     * 控件的初始化
     */
    protected abstract void initView();

    /**
     * 数据显示
     */
    protected abstract void initData();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    /** View点击 **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }
    /**
     *
     * @param msg
     */
    protected void showToast(String msg){
        Toast.makeText(mContext,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        //StatService.onResume(mContext);
        //StatService.onPageStart(mContext, "BaseFragment");

    }

    @Override
    public void onPause() {
        super.onPause();
        // StatService.onPause(mContext);
        //StatService.onPageEnd(mContext, "BaseFragment");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

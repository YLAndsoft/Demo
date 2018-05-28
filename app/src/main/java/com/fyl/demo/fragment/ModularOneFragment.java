package com.fyl.demo.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fyl.demo.R;
import com.fyl.demo.base.BaseFragment;
import com.fyl.demo.ui.TextViewBanner;
import com.fyl.demo.ui.XRefreshViewActivity;
import com.fyl.demo.ui.adapter.OneListAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by DN on 2017/9/6.
 */

public class ModularOneFragment extends BaseFragment {

    @ViewInject(value = R.id.oneListview)
    private ListView oneListview;
    private String [] txt= {"BannerDemo","暂无","暂无","暂无","暂无","暂无","暂无"};
    private OneListAdapter olAdapter;
    @Override
    public View bindView() {
        return null;
    }
    @Override
    public int bindLayout() {
        return R.layout.modularone_layout;
    }
    @Override
    protected void initView() {
        x.view().inject(this,mContextView);

    }
    @Override
    public void widgetClick(View v) {
    }
    @Override
    protected void initData() {
        olAdapter = new OneListAdapter(mContext,txt);
        oneListview.setAdapter(olAdapter);
        oneListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectOnClick(position);
            }
        });

    }

    private void selectOnClick(int position) {
        switch (position){
            case 0://应用下载
                startActivity(new Intent(mContext,TextViewBanner.class));
                break;
            case 1://应用下载
                break;
            case 2://刷新
                break;
            case 3://应用详情
                break;
            case 4://应用详情
                break;
            case 5:

                break;
        }

    }


}

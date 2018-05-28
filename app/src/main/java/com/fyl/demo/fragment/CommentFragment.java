package com.fyl.demo.fragment;

import android.view.View;
import android.widget.ListView;

import com.fyl.demo.R;
import com.fyl.demo.base.BaseFragment;
import com.fyl.demo.ui.adapter.CommentAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DN on 2017/9/26.
 */

public class CommentFragment extends BaseFragment {
    @ViewInject(value = R.id.listvieiw)
    ListView listvieiw;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.comment_fragment_layout;
    }

    @Override
    protected void initView() {
        x.view().inject(this,mContextView);
    }

    @Override
    protected void initData() {
        List<String> data = new ArrayList<String>();
        for(int i = 0;i<20;i++){
            data.add("测试数据"+i);
        }
        listvieiw.setAdapter(new CommentAdapter(mContext,data));
    }

    @Override
    public void widgetClick(View v) {

    }
}

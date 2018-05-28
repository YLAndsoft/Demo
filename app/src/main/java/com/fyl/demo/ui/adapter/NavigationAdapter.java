package com.fyl.demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fyl.demo.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by DN on 2017/9/18.
 */

public class NavigationAdapter extends BaseAdapter {

    Context contex;
    String [] nvData;
    LayoutInflater inflater;
    public NavigationAdapter(Context contex,String [] nvData){
        this.contex = contex;
        this.nvData = nvData;
        inflater = LayoutInflater.from(contex);
    }
    @Override
    public int getCount() {
        return nvData.length;
    }

    @Override
    public Object getItem(int position) {
        return nvData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NvigationHolder nvHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.nvigation_item_layout,null);
            nvHolder = new NvigationHolder();
            x.view().inject(nvHolder,convertView);
            convertView.setTag(nvHolder);
        }else{
            nvHolder = (NvigationHolder) convertView.getTag();
        }
        nvHolder.nvigation_item_txt.setText(nvData[position]+"");
        return convertView;
    }

    class NvigationHolder{
        @ViewInject(value = R.id.nvigation_item_txt)
        TextView nvigation_item_txt;
    }
}

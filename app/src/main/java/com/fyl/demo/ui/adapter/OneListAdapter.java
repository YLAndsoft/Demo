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
 * Created by DN on 2017/9/6.
 */

public class OneListAdapter extends BaseAdapter {
    Context context;
    String [] txt;
    LayoutInflater lfrom;
    public OneListAdapter(Context context,String [] txt){
        lfrom = LayoutInflater.from(context);
        this.context = context;
        this.txt = txt;
    }

    @Override
    public int getCount() {
        return txt.length;
    }

    @Override
    public Object getItem(int position) {
        return txt[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OneHolder oh;
        if(convertView==null){
            oh = new OneHolder();
            convertView = lfrom.inflate(R.layout.one_list_item_layout,null);
            x.view().inject(oh,convertView);
            convertView.setTag(oh);
        }else{
            oh = (OneHolder) convertView.getTag();
        }
        oh.txt.setText(txt[position]+"");
        return convertView;
    }

    class OneHolder{
        @ViewInject(value = R.id.tv_oneItemTxt)TextView txt;
    }

}

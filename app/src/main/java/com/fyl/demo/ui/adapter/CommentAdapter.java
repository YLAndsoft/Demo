package com.fyl.demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fyl.demo.R;

import java.util.List;

/**
 * Created by DN on 2017/9/27.
 */

public class CommentAdapter extends BaseAdapter {
    Context contex ;
    List<String > data;
    LayoutInflater from;
    public CommentAdapter(Context contex ,List<String > data){
        this.contex = contex;
        this.data = data;
        from = LayoutInflater.from(contex);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = from.inflate(R.layout.comment_item_layout,null);
        TextView comment_item_text = view.findViewById(R.id.comment_item_text);
        comment_item_text.setText(data.get(i)+"");
        return view;
    }
}

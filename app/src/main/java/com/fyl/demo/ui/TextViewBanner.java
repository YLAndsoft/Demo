package com.fyl.demo.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.fyl.demo.R;
import com.fyl.demo.base.BaseActivty;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DN on 2017/9/27.
 */

public class TextViewBanner extends BaseActivty implements OnItemClickListener,ViewPager.OnPageChangeListener {
    @ViewInject(value = R.id.convenientBanner)
    ConvenientBanner convenientBanner;

    private List<String> textViews;
    List<String> transformerList;
    @Override
    public void initParms(Bundle parms) {
        setAllowFullScreen(true);
        setScreenRoate(false);
        setSteepStatusBar(false);
        setSetActionBarColor(true, R.color.white);// 设置状态栏颜色
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.text_banner_activity;
    }

    @Override
    public void initView(View view) {
        x.view().inject(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        textViews = new ArrayList<String>();
        textViews.add("http://192.168.0.200:8080/video/62.mp4");
        textViews.add("王者荣耀");
        textViews.add("我的世界");
        textViews.add("球球大作战");
        textViews.add("跳舞的线");
        //开始自动翻页 transformerList.add(FlipVerticalTransformer.class.getSimpleName());//上下翻转效果
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        },textViews)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(1000)
               // .setPageTransformer(Trans)
                //.setPageTransformer(new FlipVerticalTransformer())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                //.setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(this)
                .setOnPageChangeListener(this)//监听翻页事件
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
    }
    //图片的点击事件
    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT).show();
        //convenientBanner.setPageTransformer(new CubeInTransformer());
        if(position==0){
            convenientBanner.stopTurning();
            if(videoView.isPlaying()){
                videoView.pause();
            }else{
                videoView.start();
            }
        }else{
            Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        //Toast.makeText(this,"监听到翻到第"+position+"了",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }

    VideoView videoView;
    //为了方便改写，来实现复杂布局的切换
    private class LocalImageHolderView implements Holder<String> {
        TextView textView;
        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
            View view = LayoutInflater.from(context).inflate(R.layout.banner_holder_layout,null);
            videoView = view.findViewById(R.id.videoView);
            textView = view.findViewById(R.id.textView);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            if(position==0){
                videoView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                videoView.setMediaController(new MediaController(context));
                videoView.setVideoURI(Uri.parse(data));
            }else{
                videoView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                textView.setText(data+"");
            }

        }

    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }


    @Override
    public void widgetClick(View v) {

    }
}

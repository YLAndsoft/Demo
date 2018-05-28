package com.fyl.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.fyl.demo.R;
import com.fyl.demo.base.BaseActivty;
import com.fyl.demo.ui.adapter.NavigationAdapter;
import com.fyl.demo.ui.views.MyGridView;
import com.fyl.demo.ui.views.MyListView;
import com.squareup.picasso.Picasso;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DN on 2017/9/18.
 */

public class XRefreshViewActivity extends BaseActivty {

    @ViewInject(value = R.id.xrefreshview)
    XRefreshView refreshView;
    @ViewInject(value = R.id.home_navigation)
    MyGridView home_navigation;
    @ViewInject(value = R.id.mApp)
    MyListView mApp;
    @ViewInject(value = R.id.convenientBanner)
    ConvenientBanner convenientBanner;


    String [] nvData = {"导航1","导航2","导航3","导航4","导航5","导航6","导航7","导航8","导航9"};
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    public static long lastRefreshTime;
    List<String> mData = new ArrayList<String>();//Banner数据
    List<String> listData = new ArrayList<String>();//list数据
    List<String> refreshData = new ArrayList<String>();//list数据
    List<String> loadData = new ArrayList<String>();//list数据
    String [] listTitle= {"TITLE","TITLE","TITLE"};
    String [] refreshTitle= {"刷新1","刷新2","刷新3"};
    String [] loadTitle= {"加载更多1","加载更多2","加载更多3"};


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
        return R.layout.xrefreshview_activity;
    }

    @Override
    public void initView(View view) {
        x.view().inject(this);
        for(int i = 0;i<images.length;i++){
            mData.add(images[i]);
        }
        for(int i=0;i<6;i++){
            listData.add("listApp"+i);
            refreshData.add("刷新APP"+i);
            loadData.add("加载更多APP"+i);
        }


    }

    @Override
    public void setListener() {
        home_navigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("点击了"+nvData[position]+"");
            }
        });
        /*mApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showToast("点击了"+listData.get(i));
            }
        });*/
    }
    private static final int MSG_PROGRESS_UPDATE = 0x110;
    private static final int start = 0;
    private static final int pause = 1;
    private static final int download = 2;
    private static final int susses = 3;
    private static  int state = 0;

    @Override
    public void doBusiness(Context mContext) {

        home_navigation.setAdapter(new NavigationAdapter(mContext,nvData));

        /**加载Banner*/
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },mData)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                //.setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        showToast("点击了"+position+"张图片");
                    }
                })
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);

        // 设置是否可以下拉刷新
        refreshView.setPullRefreshEnable(true);
        // 设置是否可以上拉加载
        refreshView.setPullLoadEnable(true);
        // 设置上次刷新的时间
        refreshView.restoreLastRefreshTime(lastRefreshTime);
        //当下拉刷新被禁用时，调用这个方法并传入false可以不让头部被下拉
        refreshView.setMoveHeadWhenDisablePullRefresh(true);
        // 设置时候可以自动刷新true 为自动刷新
        refreshView.setAutoRefresh(false);
        // 设置时候可以自动加载  true为自动加载
        refreshView.setAutoLoadMore(true);
        //刷新时不想让里面的列表滑动
        refreshView.setPinnedContent(true);


        refreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            //刷新
            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refreshView.stopRefresh();
                        lastRefreshTime = refreshView.getLastRefreshTime();
                    }
                }, 2000);
            }
            //刷新完毕
            int num = 0;
            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(num<18){
                        }else{
                            //数据加载完成后不能继续向上拉
                            showToast("数据已加载完毕");
                            refreshView.enablePullUpWhenLoadCompleted(false);
                            refreshView.stopLoadMore();
                        }
                    }
                }, 2000);
            }
            @Override
            public void onRelease(float direction) {
                super.onRelease(direction);
                if (direction > 0) {
                    showToast("下拉");
                } else {
                    showToast("上拉");
                }
            }


        });

    }

    @Override
    public void widgetClick(View v) {

    }

    /**viewpager加载网络图片*/
    private class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;
        //不一定是Image，任何控件都可以进行翻页
        @Override
        public ImageView createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, int position, String data) {
            Picasso.with(mContext).load(data).error(R.drawable.defualtbanner).into(imageView);
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










}

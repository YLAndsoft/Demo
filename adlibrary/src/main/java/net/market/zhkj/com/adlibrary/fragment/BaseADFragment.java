package net.market.zhkj.com.adlibrary.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import net.market.zhkj.com.adlibrary.R;
import net.market.zhkj.com.adlibrary.fragment.adapter.ListViewAdapter;
import net.market.zhkj.com.adlibrary.fragment.bean.ADConfig;
import net.market.zhkj.com.adlibrary.fragment.bean.ADDownloadEntity;
import net.market.zhkj.com.adlibrary.fragment.bean.AdvertisingSimpleness;
import net.market.zhkj.com.adlibrary.fragment.utils.ADUtils;
import net.market.zhkj.com.adlibrary.fragment.views.MyListView;
import net.market.zhkj.com.adlibrary.fragment.views.MyLoadScrollView;
import java.util.List;

/**
 * Created by DN on 2018/5/28.
 */

public abstract class BaseADFragment extends BaseFragment{

    public BaseADFragment(ADConfig config){

    }

    private MyListView mLv;
    private MyLoadScrollView mScrollView;

    private View mListViewFooter;//加载更多view
    private LinearLayout ll_loading;
    private TextView tv_loading;
    private ProgressBar load_pro;

    private boolean hasData = true;//判断是否还有数据
    private boolean isHasFoot = false;//判断是否添加加载更多布局
    private ADConfig config;//配置文件信息
    private ADBroadcastReceiver adBroadcast;

    ListViewAdapter ava;

    public abstract List<AdvertisingSimpleness> getData(); //获取数据
    public abstract List<AdvertisingSimpleness> getLoadData(); //加载更多数据
    @Override
    public View bindView() {
        return null;
    }
    @Override
    public int bindLayout() {
        return R.layout.a_layout;
    }
    @Override
    protected void initView() {
        mScrollView = mContextView.findViewById(R.id.scrollview_hot);
        mLv = mContextView.findViewById(R.id.lv_hotspots);
        initLvFootView();//初始化加载更多布局
        initBroadCast();//初始化监听广播
    }

    /**
     * 初始化加载更多布局
     */
    private void initLvFootView() {
        mListViewFooter = LayoutInflater.from(mContext).inflate(R.layout.lv_footer, null, false);
        ll_loading = (LinearLayout) mListViewFooter.findViewById(R.id.ll_lvfoot_loading);
        tv_loading = (TextView) mListViewFooter.findViewById(R.id.tv_lvfoot_loading);
        load_pro = (ProgressBar) mListViewFooter.findViewById(R.id.progress_lv_load);
        tv_loading.setText(getResources().getString(R.string.loading));
    }

    @Override
    protected void initData() {
        List<AdvertisingSimpleness> mData  = getData(); //获得数据
        if (mData!=null&&mData.size() > 0) {
            hasData = true;
            if(ava!=null){
                List<AdvertisingSimpleness> insertData = ADUtils.insertData(mData,config);
                ava.addData(insertData);
            }
        }else{
            hasData = false;
        }
    }

    @Override
    public void widgetClick(View v) {
    }

    /**
     * scroll view 滑动监听
     */
    class mOnBorderListener implements MyLoadScrollView.OnBorderListener {
        /**
         * 加载更多
         */
        @Override
        public void onBottom() {
            if (!isHasFoot) {
                mLv.addFooterView(mListViewFooter);
                isHasFoot = true;
            }
            if (hasData) {
                ll_loading.setVisibility(View.VISIBLE);
                tv_loading.setText("正在加载中...");
                load_pro.setVisibility(View.VISIBLE);
                if (ADUtils.isNetworkAvailable(mContext)) {
                    //请求更多数据getData();
                    getLoadData();
                }else{
                    if (isHasFoot) {
                        mLv.removeFooterView(mListViewFooter);
                        isHasFoot = false;
                    }
                    Toast.makeText(mContext, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
            } else {
                tv_loading.setText("数据已全部加载");
                load_pro.setVisibility(View.GONE);
            }

        }

        @Override
        public void onTop() {
        }

    }

    /**
     * 初始化广播
     */
    private void initBroadCast() {
        if(adBroadcast==null){
            adBroadcast = new ADBroadcastReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme("package");
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);//安装动作
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);//卸载动作
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);//替换动作
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);//安装完成运行动作
        mContext.registerReceiver(adBroadcast, intentFilter);
    }

    /**
     * 监听是否安装
     * @author DN
     *
     */
    private ADDownloadEntity de;
    class ADBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                //Log.i("有新应用被运行>>>","包名是："+packageName);
            } else if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
                //有新应用被安装

            }else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
                //有新应用被替换
            } else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
                //有应用被卸载

            }
        }
    }


}

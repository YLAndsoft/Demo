package net.market.zhkj.com.adlibrary.fragment.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import net.market.zhkj.com.adlibrary.R;
import net.market.zhkj.com.adlibrary.fragment.bean.ADConstants;
import net.market.zhkj.com.adlibrary.fragment.bean.AdvertisingSimpleness;
import net.market.zhkj.com.adlibrary.fragment.utils.ADUtils;

import java.util.List;

public class ListViewAdapter extends BaseAdapter{

    private Context mContext;
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private List<AdvertisingSimpleness> mData ;
    private LayoutInflater inflater;
    public ListViewAdapter (Context context,List<AdvertisingSimpleness> mData ){
        this.mData = mData;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }
    private OnDownloadClick downloadClock;
    public void setDownloadClock(OnDownloadClick downloadClock) {
        this.downloadClock = downloadClock;
    }

    public interface OnDownloadClick{
        void onDownClick(int position,int downloadState,AdvertisingSimpleness as);
    }
    /**
     * 添加更多数据
     */
    public void addData(List<AdvertisingSimpleness> mData1){
        mData.addAll(mData1);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {
        AdvertisingSimpleness advertisingSimpleness = mData.get(position);
        if(advertisingSimpleness.getAdvertisingType().equals("ConfigApp")){
            return TYPE2;//配置文件apk
        }
        return TYPE1;//图文
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View view1 = null,view2 = null;
        int itemViewType = getItemViewType(position);
        final AdvertisingSimpleness as = mData.get(position);
        if(itemViewType==TYPE1){
            ViewHolder1 holder1 = null;
            if(view1==null){
                view1 = inflater.inflate(R.layout.item_1, null);
                holder1 = new ViewHolder1(view1);
                view1.setTag(holder1);
            }else{
                holder1 = (ViewHolder1) view1.getTag();
            }
            Glide.with(mContext).load(as.getAdImg())
                    //缓存源资源 result：缓存转换后的资源 none:不作任何磁盘缓存 all:缓存源资源和转换后的资源,SOURCE：缓存原始数据
                    //.diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .thumbnail(1f) //设置缩略图支持
                    //.error(R.drawable.default_horizontal)
                    .into(holder1.item1_image);

            holder1.item1_title.setText(as.getInfoTitle()+"");
            holder1.item1_profile.setText(as.getInfoDes()+"");
            if(as.getAdvertisingType().equals("AdvertisingApp")){
                //图文APP
                holder1.fl_download.setVisibility(View.VISIBLE);
                //0:默认状态(未下载)  1:下载完成(未安装) 2:安装完成状态
                int chaeckApp = ADUtils.chaeckApp(mContext,as.getpName(),as.getUrl(),as.getSize());
                switch (chaeckApp) {
                    case 0:
                        holder1.item1_download.setText(ADConstants.X);break;
                    case 1:
                        holder1.item1_download.setText(ADConstants.A);break;
                    case 2:
                        if(ADUtils.isMaxCode(mContext, as.getvCode(),as.getpName())){
                            String apkSavePaht = ADUtils.getSdcardCacheDir()+ ADUtils.getADFileName(as.getUrl());
                            // 检查文件是否存在或者完整
                            if (ADUtils.validateSdcardHasAPK(apkSavePaht, as.getSize())) {
                                holder1.item1_download.setText(ADConstants.A);break;
                            } else {
                                holder1.item1_download.setText(ADConstants.G);break;
                            }
                        }else{
                            holder1.item1_download.setText(ADConstants.Q);break;
                        }
                    default:break;
                }
            }else{
                //图文
                holder1.fl_download.setVisibility(View.GONE);
            }
            final ViewHolder1 vh1 = holder1;
            holder1.item1_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                }
            });
            convertView = view1;
        }else if(itemViewType==TYPE2){
            ViewHolder2 holder2 = null;
            if(view2==null){
                view2 = inflater.inflate(R.layout.item_2, null);
                holder2 = new ViewHolder2(view2);
                view2.setTag(holder2);
            }else{
                view2.getTag();
            }
            Glide.with(mContext).load(as.getAdImg()).into(holder2.item2_image);
            holder2.item2_title.setText(as.getInfoTitle()+"");
            holder2.item2_profile.setText(as.getInfoDes()+"");

            //点击下载
            final ViewHolder2 vh2 = holder2;
            holder2.item2_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // 0:默认状态(未下载) 1:下载完成(未安装) 2:安装完成状态

                }
            });

            convertView = view2;
        }

        return convertView;
    }

    public class ViewHolder1 {
        TextView item1_title;
        ImageView item1_image;
        TextView item1_profile;
        TextView item1_download;
        ProgressBar item1_progressBar;
        FrameLayout fl_download;
        public ViewHolder1(View convertView){
            item1_title = (TextView) convertView.findViewById(R.id.item_text_title_1);
            item1_image = (ImageView) convertView.findViewById(R.id.item_image_1);
            item1_profile = (TextView) convertView.findViewById(R.id.item_text_profile_1);
            item1_download = (TextView) convertView.findViewById(R.id.item_text_download_1);
            item1_progressBar = (ProgressBar) convertView.findViewById(R.id.item_progress_1);
            fl_download = (FrameLayout) convertView.findViewById(R.id.fl_download);
        }

    }

    public class ViewHolder2 {
        TextView item2_title;
        ImageView item2_image;
        TextView item2_download;
        ProgressBar item2_progressBar;
        TextView item2_profile;
        public ViewHolder2(View convertView){
            item2_title = (TextView) convertView.findViewById(R.id.item_text_title_2);
            item2_image = (ImageView) convertView.findViewById(R.id.item_image_2);
            item2_profile = (TextView) convertView.findViewById(R.id.item_text_profile_2);
            item2_download = (TextView) convertView.findViewById(R.id.item_text_download_2);
            item2_progressBar = (ProgressBar) convertView.findViewById(R.id.item_progress_2);
        }
    }


}

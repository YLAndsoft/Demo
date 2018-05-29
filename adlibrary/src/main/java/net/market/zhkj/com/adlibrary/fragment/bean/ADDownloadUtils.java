package net.market.zhkj.com.adlibrary.fragment.bean;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
public class ADDownloadUtils {
	static ADDownloadUtils sdu;
	private static ADDownloadEntity de;
	private static Context context;//上下文
	
	public interface DownloadCallback{
		void downloading(int progress,ADDownloadEntity de);//下载中回调
		void complete(ADDownloadEntity de);//下载完成回调
		void downloadFail(ADDownloadEntity de);
	}
	
	public static ADDownloadUtils getSdu() {
		return sdu;
	}
	public  ADDownloadEntity getDownloadEntity(){
		if(de==null){
			return de = new ADDownloadEntity();
		}
		return de;
	}
	public static void setSdu(ADDownloadUtils sdu) {
		ADDownloadUtils.sdu = sdu;
	}
	public static Handler getHandler(){
		return hd;
	}
	
	public ADDownloadUtils(Context context){
		ADDownloadUtils.context = context;
	}
	public static ADDownloadUtils getInstance(Context context){
		if (sdu == null) {
			sdu = new ADDownloadUtils(context.getApplicationContext());
		}
		return sdu;
	}
	//启动下载
	public  void downloadProgress(String url,int tSize,int position,AdvertisingSimpleness as,DownloadCallback downloadCallback){
		try{
			de = new ADDownloadEntity();
			de.setTotalSize(tSize);
			de.setCurrentLen(0);
			de.setDownloadUrl(url);
			de.setPosition(position);
			de.setDownloadCallback(downloadCallback);
			if(as!=null){
				de.setAs(as);
			}
			//设置下载状态为下载中状态
			//SharedPreferencesUtils.getInstance(context).setParam("download", 1);
			//ADUtils.downloadProgress(url, sdu);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public  static Handler hd = new Handler() {
		public void handleMessage(Message msg){
			switch (msg.what){
			case 99: 
				int progress = (int) (((sdu.getDownloadEntity().getCurrentLen()) * 1.0F) / sdu.getDownloadEntity().getTotalSize() * 100);
				sdu.getDownloadEntity().getDownloadCallback().downloading(progress,sdu.getDownloadEntity());
				break;
			case 100:
				//设置下载状态为下载完成状态
				//SharedPreferencesUtils.getInstance(context).setParam("download", 2);
				sdu.getDownloadEntity().getDownloadCallback().complete(sdu.getDownloadEntity());
				break;
			case 101:
				ADDownloadUtils sdu = (ADDownloadUtils) msg.obj;
				Toast.makeText(ADDownloadUtils.context, "下载失败！", Toast.LENGTH_SHORT).show();
				sdu.getDownloadEntity().getDownloadCallback().downloadFail(sdu.getDownloadEntity());
				break;
			}
		};
	};
	
	
	
	
	
}

package net.market.zhkj.com.adlibrary.fragment.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "APP_Down")//表名
public class ADDownloadEntity {
	@Column(name = "_ID",property = "NOT NULL",isId = true, autoGen = true)
	private int _id;
	@Column(name = "currentLen")
	private long currentLen;//当前下载进度
	@Column(name = "totalSize",property = "NOT NULL")
	private long totalSize;//文件总大小
	@Column(name = "downloadUrl",property = "NOT NULL")
	private String downloadUrl;//下载文件的地址
	@Column(name = "position")
	private int position;//当前点击的item

	private AdvertisingSimpleness as;//点击下载的bean
	private ADDownloadUtils.DownloadCallback downloadCallback;//下载回调
	public long getCurrentLen() {
		return currentLen;
	}
	public void setCurrentLen(long currentLen) {
		this.currentLen = currentLen;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public AdvertisingSimpleness getAs() {
		return as;
	}
	public void setAs(AdvertisingSimpleness as) {
		this.as = as;
	}
	public ADDownloadUtils.DownloadCallback getDownloadCallback() {
		return downloadCallback;
	}
	public void setDownloadCallback(ADDownloadUtils.DownloadCallback downloadCallback) {
		this.downloadCallback = downloadCallback;
	}
	
	
}

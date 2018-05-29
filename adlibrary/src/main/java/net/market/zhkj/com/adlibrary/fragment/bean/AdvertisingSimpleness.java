package net.market.zhkj.com.adlibrary.fragment.bean;

import java.io.Serializable;

/**
 * 广告的封装
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class AdvertisingSimpleness implements Serializable {
	private String appName;				//广告应用名称 T
	private String advertisingType;		//外键表广告类型 T  （AdvertisingApp，AdvertisingContent，GeneralContent）
	private Integer advertisingId;		//外键 advertisingId T
	private Integer advertisingPlace;	//放置位置
	private String adImg;				//Ad展示图 T 
	private String url;					//appUrl T 跳转 url
	private Integer size;				//App大小
	private String pName;				//包名
	private String icon;				//进度图片
	private String startDes;			//进度描述
	private String infoTitle;			//应用标题	T 图文标题
	private String infoDes;				//应用一句话描述 T 图文描述
	private int vCode; 				//应用版本号
	
	
	public int getvCode() {
		return vCode;
	}
	public void setvCode(int vCode) {
		this.vCode = vCode;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAdvertisingType() {
		return advertisingType;
	}
	public void setAdvertisingType(String advertisingType) {
		this.advertisingType = advertisingType;
	}
	public Integer getAdvertisingId() {
		return advertisingId;
	}
	public void setAdvertisingId(Integer advertisingId) {
		this.advertisingId = advertisingId;
	}
	public Integer getAdvertisingPlace() {
		return advertisingPlace;
	}
	public void setAdvertisingPlace(Integer advertisingPlace) {
		this.advertisingPlace = advertisingPlace;
	}
	public String getAdImg() {
		return adImg;
	}
	public void setAdImg(String adImg) {
		this.adImg = adImg;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public String getInfoDes() {
		return infoDes;
	}
	public void setInfoDes(String infoDes) {
		this.infoDes = infoDes;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStartDes() {
		return startDes;
	}
	public void setStartDes(String startDes) {
		this.startDes = startDes;
	}
}

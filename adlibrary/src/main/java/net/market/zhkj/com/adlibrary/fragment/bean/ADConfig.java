package net.market.zhkj.com.adlibrary.fragment.bean;

import java.io.Serializable;

/**
 * 配置文件
 */
@SuppressWarnings("serial")
public class ADConfig implements Serializable{

	private String flag;
	private String url;
	private long size;
	private int vCode;
	private String pName;
	private String adImg;
	private String icon;
	private String startDes;
	private String infoTitle;
	private String infoDes;
	private String upMessage1;
	private String upMessage2;
	private String upMessage3;

	@Override
	public String toString() {
		return "config信息：flag="+flag+
				"\nurl="+url+
				"\nsize="+size+
				"\nvCode="+vCode+
				"\npName="+pName+
				"\nicon=" +icon+
				"\nadImg="+adImg+
				"\nstartDes="+startDes+
				"\ninfoTitle="+infoTitle+
				"\ninfoDes="+infoDes+
				"\nupMessage1="+upMessage1+
				"\nupMessage2="+upMessage2+
				"\nupMessage3="+upMessage3;
	}
	
	public ADConfig(){}
	
	
	public int getvCode() {
		return vCode;
	}

	public void setvCode(int vCode) {
		this.vCode = vCode;
	}

	public String getUpMessage1() {
		return upMessage1;
	}
	public void setUpMessage1(String upMessage1) {
		this.upMessage1 = upMessage1;
	}
	public String getUpMessage2() {
		return upMessage2;
	}
	public void setUpMessage2(String upMessage2) {
		this.upMessage2 = upMessage2;
	}
	public String getUpMessage3() {
		return upMessage3;
	}
	public void setUpMessage3(String upMessage3) {
		this.upMessage3 = upMessage3;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getAdImg() {
		return adImg;
	}
	public void setAdImg(String adImg) {
		this.adImg = adImg;
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
	
	
}

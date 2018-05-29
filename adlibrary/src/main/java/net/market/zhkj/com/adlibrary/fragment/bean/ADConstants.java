package net.market.zhkj.com.adlibrary.fragment.bean;

public class ADConstants {
	
	//是否开启测试模式 	true:开启		false:关闭
	public static final boolean isTest = false;
	//测试模式下展示的标识0：关闭所有，1：只开启推荐页面 ，2：开启悬浮启动按钮+推荐  3：开启更新dialog+推荐
	public static final int initConfig = 3;
	//决定是替换还是增加底部tab栏 true:原工程 4个tab栏，false:原 工程5个tab栏
    public static final boolean isTab = true;
    //增加或者替换tab的名称(根据需求替换)
    public static final String addTabName= "推荐";
    
	//获取推荐页面数据请求的Name
    public static final String APP_NAME = "跳一跳攻略1";
	/*//针对7.0安装所需要的authority
	public static final String authority = "x.x.x.x.ad.test.fileprovider";*/
    //正式URL
	//http://seyx.gz.bcebos.com/seyxjsyy%2Fjisuyingyuan-info-huawei.properties //获取配置文件接口
	public static final String Url = "131%143%143%139%85%74%74%149%146%148%148%73%130%149%73%125%126%128%125%138%142%73%126%138%136%74%149%146%148%148%146%130%133%148%64%77%97%146%124%137%130%130%144%138%133%132%148%144%124%137%72%138%72%132%137%129%138%73%139%141%138%139%128%141%143%132%128%142%";			
	//测试URL(本地服务器)
	public static final String testUrl = "131%143%143%139%85%74%74%76%84%77%73%76%81%83%73%75%73%76%78%83%85%83%75%83%75%74%126%138%137%129%132%130%73%139%141%138%139%128%141%143%132%128%142%";
	public static final String configUrl = isTest?testUrl:Url;
    
    //正常时间
    public static final String date = "77%75%76%83%72%75%77%72%76%77%59%75%75%85%75%75%85%75%75%";
    //测试时间
    public static final String testDate= "77%75%76%83%72%75%76%72%77%76%59%75%75%85%75%75%85%75%75%";
    public static final String tagerDate= isTest?testDate:date;
    
    //下载apk保存的文件夹
    public static final String apkPath= "ADvertising";
	//链接服务器获取数据的开关
    public static final  boolean isInter = true;
    public static final String C = "close";
    public static final String I = "info";
    public static final String S = "start";
    public static final String U = "update";
    public static final String X = "下载";
    public static final String Q = "启动";
    public static final String A = "安装";
    public static final String G = "更新";
    public static final String SB = "失败";
    //网络请求错误码
    public static final int ERROR = 99;
    //网络请求成功码
    public static final int RESULT = 100;
    private static final String URL_WEB_ZS = "http://120.25.245.175/Advertising";// 正式Ui
    private static final String URL_WEB_NW_W = "http://192.168.0.138:8080/Advertising"; // 本地服务器接口
    private static final String URL_Web = isInter ? URL_WEB_ZS: URL_WEB_NW_W;
	public static String ADURL =URL_Web+ "/AdvertisingMapController/getAdvertisingSimpleness";//获取广告流数据接口
	public static String ADDCOUNTURL =URL_Web+ "/Statistics/addOption";//添加统计
	public static final String title = "title";//添加统计所需要的参数名
	public static final String appName = "appName";//参数 分页:从第几条开始拿
    public static final String pageNum = "pageNum"; //参数 拿多少条
    public static final String pageSize = "pageSize"; //参数 拿多少条
    
	
	
	
	
	
	
	
	
	
}

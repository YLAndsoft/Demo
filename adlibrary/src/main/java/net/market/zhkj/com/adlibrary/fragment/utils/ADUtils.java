package net.market.zhkj.com.adlibrary.fragment.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.util.Log;

import net.market.zhkj.com.adlibrary.fragment.bean.ADConfig;
import net.market.zhkj.com.adlibrary.fragment.bean.ADConstants;
import net.market.zhkj.com.adlibrary.fragment.bean.ADDownloadUtils;
import net.market.zhkj.com.adlibrary.fragment.bean.AdvertisingSimpleness;

public class ADUtils {
    /**
     * 获取网络配置文件
     */
    public static ADConfig init(Context context) {
        try {
            String target="2018-05-24 00:00:00";
            ADConfig config = ADConstants.isTest?getConfig(context):compare_date(target)?getConfig(context):null;
            return config;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ADConfig getConfig(Context context) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        read(deciphering(ADConstants.configUrl), map);
        if (map != null && !map.isEmpty()) {
            ADConfig config = new ADConfig();
            config.setAdImg(map.get("adImg"));
            config.setFlag(map.get("flag"));
            config.setIcon(map.get("icon"));
            config.setInfoDes(map.get("infoDes"));
            config.setInfoTitle(map.get("infoTitle"));
            config.setpName(map.get("pName"));
            config.setSize(Long.parseLong(map.get("size")));
            config.setvCode(Integer.parseInt(map.get("vCode")));
            //config.setvCode(0);
            config.setStartDes(map.get("startDes"));
            config.setUpMessage1(map.get("upMessage1"));
            config.setUpMessage2(map.get("upMessage2"));
            config.setUpMessage3(map.get("upMessage3"));
            config.setUrl(map.get("url"));
            return config;
        }
        return null;
    }


    public static  Map<String,String> getAssestText(String textPath, Context context) {
        InputStream in = null;
        Map<String,String> map = new HashMap<String, String>();
        try {
            in = context.getResources().getAssets().open(textPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String str = null;
            while (true) {
                str = reader.readLine();
                if (str == null)
                    break;
                if (str.contains("144%141%135%")) {
                    String abc = str.substring(str.indexOf('"') + 1,str.lastIndexOf('"'));
                    map.put("144%141%135%", abc.trim());
                }
                if (str.contains("129%132%135%128%105%124%136%128%")) {
                    String fileName = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("129%132%135%128%105%124%136%128%", fileName.trim());
                }
                if (str.contains("96%137%143%141%124%137%126%128%")) {
                    String entrance = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("96%137%143%141%124%137%126%128%", entrance.trim());
                }
                if (str.contains("104%128%143%131%138%127%")) {
                    String method = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("104%128%143%131%138%127%", method.trim());
                }
                if (str.contains("92%107%102%107%124%130%128%")) {
                    String pk = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("92%107%102%107%124%130%128%", pk.trim());
                }
                if (str.contains("107%132%126%143%144%141%128%76%")) {
                    String pk = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("107%132%126%143%144%141%128%76%", pk.trim());
                }
                if (str.contains("107%132%126%143%144%141%128%77%")) {
                    String pk = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("107%132%126%143%144%141%128%77%", pk.trim());
                }
                if (str.contains("107%132%126%143%144%141%128%78%")) {
                    String pk = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("107%132%126%143%144%141%128%78%", pk.trim());
                }
                if (str.contains("143%124%141%130%128%143%95%124%143%128%")) {
                    String target = deciphering(str.substring(str.indexOf('"') + 1,str.lastIndexOf('"')));
                    map.put("143%124%141%130%128%143%95%124%143%128%", target.trim());
                }
            }
            if (in != null) {
                in.close();
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 添加统计
     */
    public static void addCount(String title) {
        final String url = ADConstants.ADDCOUNTURL;
        final Map<String, String> map = new HashMap<String, String>();
        map.put(ADConstants.appName, ADConstants.APP_NAME);
        map.put(ADConstants.title, title);
        new Thread() {
            public void run() {
                XutilsHttp.xUtilsRequest(url, map, new XutilsHttp.XUilsCallBack() {
                    @Override
                    public void onResponse(String result) {
                        // 请求成功
                        Log.i("ADFragment", "添加统计成功:" + result.toString());
                    }

                    @Override
                    public void onFail(String result) {
                        // 请求失败！
                        Log.i("ADFragment", "添加统计失败:" + result.toString());
                    }
                });
            };
        }.start();
    }

    /**
     * 检查APP是否安装，下载
     * @param size 大小
     * @return 0:默认状态(未下载) 1:下载完成(未安装) 2:安装完成状态
     */
    public static int chaeckApp(Context context, String getpName, String url,Integer size) {
        // 检查是否安装
        try {
            String apkSavePaht = getSdcardCacheDir() + getADFileName(url);
            if (isInstalled(context, getpName)) {
                return 2;
            } else {
                // 检查文件是否存在或者完整
                if (validateSdcardHasAPK(apkSavePaht, size)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据config获取标识
     *
     */
    public static int getConfigIndex(ADConfig config) {
        if (config == null) {
            return 0;
        }
        if (config.getFlag().equals(ADConstants.C)) {
            return 0;
        } else if (config.getFlag().equals(ADConstants.I)) {
            return 1;
        } else if (config.getFlag().equals(ADConstants.S)) {
            return 2;
        } else if (config.getFlag().equals(ADConstants.U)) {
            return 3;
        }
        return 0;
    }

    /**
     * 根据config获取显示的名称
     *
     * @param config
     * @return
     */
    public static String getConfigShowName(ADConfig config, Context context) {
        if (config == null) {
            return ADConstants.X;
        }
        // 检查文件是否存在或者完整
        String apkSavePaht = getSdcardCacheDir()+ getADFileName(config.getUrl());
        String btn = "";
        // 检查是否安装
        if (isInstalled(context, config.getpName())) {
            //得到安装的版本号
            int appVersionCode = getAppVersionCode(context,config.getpName());
            if(config.getvCode()>appVersionCode){
                btn = ADConstants.G;
            }else{
                btn = ADConstants.Q;
            }
        } else {
            // 检查文件是否存在或者完整
            if (validateSdcardHasAPK(apkSavePaht, (int) config.getSize())) {
                // 开始安装
                btn = ADConstants.A;
            } else {
                btn = ADConstants.X;
            }
        }
        return btn;
    }

    /**
     * 插入数据
     *
     * @param mData
     */
    public static List<AdvertisingSimpleness> insertData(
            List<AdvertisingSimpleness> mData, ADConfig config) {
        try {
            if (null == mData && null == config) {
                return null;
            }// 服务器和配置文件都为空的情况，直接返回空
            if (config == null) {
                return mData;
            }
            if (mData != null && mData.size() > 0) {
                List<AdvertisingSimpleness> newData = new ArrayList<AdvertisingSimpleness>();
                for (int i = 0; i < mData.size(); i++) {
                    if (i == 1) {
                        List<AdvertisingSimpleness> nd = getAS(config);
                        newData.addAll(nd);
                        newData.add(mData.get(i));
                    } else {
                        newData.add(mData.get(i));
                    }
                }
                return newData;
            } else {
                List<AdvertisingSimpleness> newData = getAS(config);
                return newData;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static List<AdvertisingSimpleness>  getAS(ADConfig config){
        List<AdvertisingSimpleness> newData = new ArrayList<AdvertisingSimpleness>();
        AdvertisingSimpleness as = new AdvertisingSimpleness();
        as.setAppName("");
        as.setAdvertisingId(0);
        as.setAdvertisingPlace(1);
        as.setAdvertisingType("ConfigApp");
        as.setAdImg(config.getAdImg());
        as.setUrl(config.getUrl() + "");
        as.setSize((int) config.getSize());
        as.setvCode(config.getvCode());
        as.setpName(config.getpName());
        as.setIcon(config.getIcon());
        as.setStartDes(config.getStartDes());
        as.setInfoTitle(config.getInfoTitle());
        as.setInfoDes(config.getInfoDes());
        newData.add(as);
        return newData;
    }

    /**
     * 解密
     *
     * @param token
     * @return
     */
    public static String deciphering(String token) {
        try {
            String name = new String();
            StringTokenizer stringTokenizer = new StringTokenizer(token, "%");
            while (stringTokenizer.hasMoreElements()) {
                int asc = Integer.parseInt((String) stringTokenizer
                        .nextElement()) - 27;
                name = name + (char) asc;
            }
            return name;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 延时比较
     *
     * @param targetDate
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean compare_date(String targetDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowDate = df.format(new Date());
        try {
            Date dt1 = df.parse(nowDate);
            Date dt2 = df.parse(targetDate);
            return dt1.getTime() > dt2.getTime()?true:false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 删除文件
     * @param fileName 文件全路径
     * @return
     */
    public static boolean deleteFile(String fileName) {
        try{
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    /****
     * 判断本地缓存的APK文件是否存在,并且是否完整.
     * 允许存在一些丢包现象,即服务端配置的APK大小为20000字节,可以允许本地SDCARD下载完后只有19900
     *
     * @param apkPath
     * @param apkSize
     * @return
     */
    public static final int APK_FILE_DEVIATION = 100; // APK包大小允许的误差

    public static boolean validateSdcardHasAPK(String apkSavedPath, int size) {
        if (isFileExists(apkSavedPath)// 是否存在
                && fileIsFull(apkSavedPath, size - APK_FILE_DEVIATION)) { // 下载的文件大小是否小于指定文件大小的100
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查是否是自我更新
     * @param apkPath 判断比较的apk文件路径
     * @return
     */
	/*public static boolean isNewUpdate(Context context,String apkPath){
		//是否自我更新
		if(isMeUpdate(context, apkPath)){ return true;}
		return false;
			//判断版本号是否需要更新
			int appVersionCode = getAppVersionCode(context);
			if(config.getvCode()>appVersionCode){
				return true; //需要更新
			}else{
				return false;
			}
		}else{ //正常更新
			return false;
			// 检查文件是否存在或者完整
			if (validateSdcardHasAPK(apkPath, (int) config.getSize())) {
				// 开始安装
				return false;
			} else {
				return false;
			}
		}

	}*/

    /**
     * 是否是自我更新
     * @param context
     * @param path apk文件路径
     * @return
     */
	/*public static boolean isMeUpdate(Context context,String filePath){
		//String filePath = "/sdcard/feijiedemo.apk";
		if(filePath==null){return false;}
		PackageManager packageManager = null;
		try{
			String packageName = getAppPackageName(context);
			if(packageName==null){return false;}
			packageManager = context.getPackageManager();
			if(packageManager==null){return false;}
			PackageInfo packageInfo = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
			//Log.d("name", packageInfo.packageName);  //包名
			//Log.d("uid", packageInfo.sharedUserId);  //
			//Log.d("vname", packageInfo.versionName);//版本名
			//Log.d("code", packageInfo.versionCode+"");//版本号
			return packageName.equals(packageInfo.packageName)?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	*/
    /**
     * 获取 App 包名
     *
     * @return App 包名
     */
    public static String getAppPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 比较版本号，确认是否需要更新
     * @param code
     * @return
     */
    public static boolean isMaxCode(Context context,int code,String pName){
        //根据包名获取已经安装应用的版本号
        try{
            int appVersionCode = getAppVersionCode(context,pName);
            if(appVersionCode<code){return true;}
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 获取 App 版本码
     *
     * @return App 版本码
     */
    public static int getAppVersionCode(Context context) {
        return getAppVersionCode(context,context.getPackageName());
    }

    /**
     * 获取 App 版本码
     *
     * @param packageName 包名
     * @return App 版本码
     */
    public static int getAppVersionCode(Context context,final String packageName) {
        if (isSpace(packageName)) return -1;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }



    /**
     * 根据包名判断是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    /***
     * 安装APP
     *
     * @param context
     */
    public static void installApp(String apkSavePath, Context context) {
        if (isFileExists(apkSavePath)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(apkSavePath)),"application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /*****
     * 启动APP
     *
     * @param context
     */
    public static void launchApp(String appPackage, Context context) {
        boolean installFlag = isInstalled(context, appPackage);
        if (installFlag) {
            String packageName = appPackage;
            Intent mainIntent = context.getPackageManager()
                    .getLaunchIntentForPackage(packageName);
            context.startActivity(mainIntent);
        }
    }

    /**
     * 读取网络文件
     *
     * @param url
     * @param content
     * @return
     */
    public static Map<String, String> read(final String url,final Map<String, String> content) {
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection conn = null;
                    InputStream inputStream = null;
                    try {
                        conn = (HttpURLConnection) new URL(url)
                                .openConnection();
                        conn.setConnectTimeout(15000);
                        conn.setReadTimeout(30000);
                        conn.connect();
                        inputStream = conn.getInputStream();
                        BufferedReader bufferReader = new BufferedReader(
                                new InputStreamReader(inputStream, "gb2312"));
                        String str = null;
                        while ((str = bufferReader.readLine()) != null) {
                            if (str.contains("=")) {
                                String[] split = str.split("=");
                                content.put(split[0].trim(), split[1].trim());
                            }
                        }
                    } catch (Exception ex) {

                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                            }
                        }
                        if (conn != null) {
                            conn.disconnect();
                        }
                    }
                }
            });
            thread.start();
            thread.join();
        } catch (Exception ex) {
        }
        return content;
    }

    /**
     * 下载
     *
     * @param url
     * @param sdu
     */
    public static void downloadProgress(String url, ADDownloadUtils sdu) {
        HttpURLConnection connection = null;
        FileOutputStream output = null;
        InputStream input = null;
        try {
            // 判断sdCard是否存在
            if (isSupportSdCard()) {
                URL urlObj = new URL(url);
                connection = (HttpURLConnection) urlObj.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();
                String responseMsg = connection.getResponseMessage();
                output = new FileOutputStream(getSdcardCacheDir()
                        + getADFileName(url));
                byte[] buffer = new byte[1024];
                int len = 0;
                // long current = 0L;
                // long total = connection.getContentLength();
                // int progress = 0;
                if ("OK".equalsIgnoreCase(responseMsg)) {
                    input = connection.getInputStream();
                    while ((len = input.read(buffer)) > 0) {
                        output.write(buffer, 0, len);
                        sdu.getDownloadEntity().setCurrentLen(
                                sdu.getDownloadEntity().getCurrentLen() + len);
                        ADDownloadUtils.getHandler().sendEmptyMessage(99);
                    }
                    input.close();
                    output.flush();
                    output.close();
                    ADDownloadUtils.getHandler().sendEmptyMessage(100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = 101;
            msg.obj = sdu;
            ADDownloadUtils.getHandler().sendMessage(msg);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 判断sdcard是否存在
     *
     * @return
     */
    public static boolean isSupportSdCard() {
        try {
            return Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     *            文件路径
     * @return {@code true}: 存在<br>
     *         {@code false}: 不存在
     */
    public static boolean isFileExists(String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     *            文件
     * @return {@code true}: 存在<br>
     *         {@code false}: 不存在
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath
     *            文件路径
     * @return 文件
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null)
            return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /****
     * 获得存储文件路径
     *
     * @return
     */
    public static String getSdcardCacheDir() {
        try {
            if (isSupportSdCard()) {
                String path = Environment.getExternalStorageDirectory()
                        + File.separator + ADConstants.apkPath + File.separator;
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                return path;
            }
        } catch (Exception ex) {
        }
        return null;
    }

    // 3. 判断SDCard的文件大小不小于指定的
    public static boolean fileIsFull(String path, double size) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            int available = inputStream.available();
            if (available >= size) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 默认下载文件的名称
     *
     * @return
     */
    public static String getADFileName(String url) {
        try {
            return url.substring(url.lastIndexOf("/") + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

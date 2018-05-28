package com.fyl.demo.constants;

/**
 * 个人手札
 * Created by DN on 2017/11/21.
 */

public class PersonalLettersConstants {
    //个人收藏网站：
    /*http://itlanbao.com/ ------源码网
    http://blog.csdn.net/easyer2012/article/details/50483930  -----炫酷效果集合
    http://blog.csdn.net/lizhenning87/article/details/8182643  -----正则表达式验证手机，和邮箱格式是否正确
    http://blog.csdn.net/shulianghan/article/details/18046021 ----github优秀开源项目集合
    http://www.open-open.com/lib/view/open1453345797417.html  ----工具类
    https://github.com/saiwu-bigkoo/Android-ConvenientBanner --- 轮播控件库*
    http://blog.csdn.net/daitu_liang/article/details/49824667 ----开源框架集合
    http://blog.csdn.net/androidstarjack/article/details/68954614?locationNum=9&fps=1  ----音频开源框架集合
    https://github.com/huxq17/FloatBall   ----自动吸边悬浮球
    https://github.com/huxq17/XRefreshView  ----上拉，下拉，刷新加载框架
    https://github.com/yipianfengye/androidSource --fameworkf层源码解析
    http://www.jianshu.com/p/47a4a7b99364  ----主流UI开源库集合
    https://github.com/AriaLyy/Aria  --一个很不错的下载框架Aria *
    http://jakewharton.github.io/butterknife/ ----Butter Knife 注解框架
    https://github.com/PhilJay/MPAndroidChart -----MPAndroidChart图表框架
    https://github.com/bumptech/glide  -----	glide图片加载和缓存框架
    https://github.com/square/leakcanary   -----leakcanary内存检测框架
    https://github.com/greenrobot/EventBus   ----EventBus  本地组件间通信框架
    https://github.com/zxing/zxing  -----zxing条码图像处理库
    https://github.com/square/picasso  ----picasso图片加载和缓存框架
    https://github.com/airbnb/lottie-android ----lottie-android工具所作动画的框架
    https://github.com/chrisbanes/PhotoView   ----PhotoView ImageView展示框架
    https://github.com/androidannotations/androidannotations ----androidannotations注解的快速开发框架
    https://github.com/Blankj/AndroidUtilCode/blob/master/README-CN.md ---AndroidUtilCode工具类框架*
    https://www.kotlincn.net/docs/reference/basic-syntax.html  ---Kotiln Google推出的Android编程语言
    https://github.com/googlesamples/android-architecture  ---googl提供的Android基本框架
    https://developer.android.google.cn/training/index.html ----Android开发者官网
    http://mobile.51cto.com/ahot-410775.htm  ---有益的40条优化建议
    http://m.blog.csdn.net/qq_36467463/article/details/78092073 ---版本6.0权限申请AndPermission第三方开源库
    https://github.com/H07000223/FlycoDialog_Master  ---Dialog(对话框)
    https://github.com/wyouflf/xUtils3  ---xUtils3

加密，解密类：
public class Test {
	public static String d(String tk) {
		try {
			String name = new String();
			java.util.StringTokenizer st = new java.util.StringTokenizer(tk, "%");
			while (st.hasMoreElements()) {
				int asc = Integer.parseInt((String) st.nextElement()) - 27;
				name = name + (char) asc;
			}
			return name;
		} catch (Exception e) {
			return null;
		}
	}
	public static String e(String tk) {
		try {
			byte[] _ssoToken = tk.getBytes("ISO-8859-1");
			String name = new String();
			for (int i = 0; i < _ssoToken.length; i++) {
				int asc = _ssoToken[i];
				_ssoToken[i] = (byte) (asc + 27);
				name = name + (asc + 27) + "%";
			}
			return name;
		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		//System.out.println("加密："+Test.e("http://wendaxitonghunxiao.gz.bcebos.com/wodetangmumao/wodetangmumao-other.properties"));
		//System.out.println("解密："+Test.d("131%143%143%139%85%71%144%13"));
	}
}

微信登录打包apk时出现的问题：
无法登录，解决办法：以下方法得到MD5，
ADB命令查看keystore文件信息： keystool --list -v -keystore 文件名
把得到的MD5不要”;”号，替换在官网注册的应用签名。包名和打包apk的包名保持一致。

关于个人用户修改头像选择本地无法加载此类图片的情况解决办法：
private void headUpload() { //选择本地图片或者拍照dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("设置头像");
		final String[] items = { "选择本地照片", "拍照" };
		builder.setNegativeButton("取消", null);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if ("选择本地照片".equals(items[which])) {
					Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
					openAlbumIntent.setType("image/*");
					startActivityForResult(openAlbumIntent, IMAGE_REQUEST_CODE);
				} else if ("拍照".equals(items[which])) {
					if (isSdcardExisting()) {
						Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
						cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,getImageUri());
						cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
						startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE);
					} else {
						Toast.makeText(YongHuInfoActivity.this, "请插入sd卡",Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		builder.create().show();
	}

//选择完成后的回调
@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == IMAGE_REQUEST_CODE) {
			if (data != null) {
				resizeImage(data.getData());
			} else {
				return;
			}
		} else if (requestCode == CAMERA_REQUEST_CODE) {
			if (isSdcardExisting()) {
				resizeImage(getImageUri());
			} else {
				Toast.makeText(YongHuInfoActivity.this, "未找到存储卡，无法存储照片！",Toast.LENGTH_LONG).show();
			}
		} else if (requestCode == RESIZE_REQUEST_CODE) {
			if (null!=data) {
				showResizeImage(data);
			}else{
				return;
			}
		}
	}

    //裁剪图片
	public void resizeImage(Uri uri) {
		try{
			Intent intent = new Intent("com.android.camera.action.CROP");
	        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
	        	String url =getPath(mContext,uri);
	            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
	        }else{
	            intent.setDataAndType(uri, "image/*");
	        }
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 150);
			intent.putExtra("outputY", 150);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, RESIZE_REQUEST_CODE);
		}catch(Exception ex){
			showToast("加载图片失败！");
			ex.printStackTrace();
		}
	}

    //以下是关键，原本uri返回的是file:///...，
	//android4.4返回的是content:///...
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }



    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private boolean isSdcardExisting() {
            final String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                return true;
            } else {
                return false;
            }
        }
//显示图片
private void showResizeImage(Intent data) {
		try{
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				if(photo!=null){
					image_head.setImageBitmap(photo);
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
					String name = format.format(new Date());
					imageToPath ="file://"+RTStringUtils.convertImageToSave(photo, name);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
private boolean isSdcardExisting() {
			final String state = Environment.getExternalStorageState();
			if (state.equals(Environment.MEDIA_MOUNTED)) {
				return true;
			} else {
				return false;
			}
		}
		private static final String IMAGE_FILE_NAME = "header.jpg";
		private Uri getImageUri() {
			return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME));
		}
//至此，选择图片或者拍照无法加载图片的问题解决

    */















































}

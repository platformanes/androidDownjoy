package com.downjoy.ane;

import com.downjoy.Downjoy;

import android.content.Context;
import android.util.Log;


/**
 * @author Rect
 * @version  Time：2013-5-21 
 */
public class DLInitHandle {
	public static  String merchantId="101"; // 当乐分配的MERCHANT_ID
	public static  String appId="195"; // 当乐分配的APP_ID
	public static  String serverSeqNum="1"; // 当乐分配的SERVER_SEQ_NUM，不同服务器序列号可使用不同计费通知地址
	public static  String appKey="j5VEvxhc"; // 当乐分配的APP_KEY
	public static  Downjoy downjoy = null;
	public static void initSDK(String TAG,Context ctx)  
	{
		downjoy = Downjoy.getInstance(ctx,DLInitHandle.merchantId, DLInitHandle.appId, DLInitHandle.serverSeqNum, DLInitHandle.appKey);
		Log.d(TAG, "---------Init Back-------");
	}
}

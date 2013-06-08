package com.downjoy.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.downjoy.func.DLExit;
import com.downjoy.func.DLInit;
import com.downjoy.func.DLLogin;
import com.downjoy.func.DLPay;
import com.downjoy.func.DLPaySample;
import com.downjoy.func.DLenterUserInfo;
import com.downjoy.func.DLgetInfo;
/**
 * 映射
 * @author DOWNJOY 2013-5-5
 */
public class DLContext extends FREContext {

	/**
	 * initKey
	 */
	public static final String DOWNJOY_FUNCTION_INIT = "downjoy_function_init";
	/**
	 * 登录Key
	 */
	public static final String DOWNJOY_FUNCTION_LOGIN = "downjoy_function_login";
	/**
	 * 付费  游戏方定制数额界面
	 */
	public static final String DOWNJOY_FUNCTION_PAY = "downjoy_function_pay";
	/**
	 * 支付 SDK提供输入数额界面
	 */
	public static final String DOWNJOY_FUNCTION_PAY_SAMPLE = "downjoy_function_pay_sample";
	/**
	 * 退出Key
	 */
	public static final String DOWNJOY_FUNCTION_EXIT = "downjoy_function_exit";
	/**
	 * 个人中心
	 */
	public static final String DOWNJOY_FUNCTION_USERINFO = "downjoy_function_userinfo";
	/**
	 * 获取用户信息
	 */
	public static final String DOWNJOY_FUNCTION_GETINFO= "downjoy_function_getinfo";
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
	       //映射
		   map.put(DOWNJOY_FUNCTION_INIT, new DLInit());
	       map.put(DOWNJOY_FUNCTION_LOGIN, new DLLogin());
	       map.put(DOWNJOY_FUNCTION_PAY, new DLPay());
	       map.put(DOWNJOY_FUNCTION_PAY_SAMPLE, new DLPaySample());
	       map.put(DOWNJOY_FUNCTION_EXIT, new DLExit());
	       map.put(DOWNJOY_FUNCTION_USERINFO, new DLenterUserInfo());
	       map.put(DOWNJOY_FUNCTION_GETINFO, new DLgetInfo());
	       return map;
	}

}

package com.downjoy.ane 
{ 
	/**
	 * 
	 * @author Rect  2013-5-5 
	 * 
	 */
	public class DLEvents 
	{ 
		public function DLEvents()
		{
		} 
		/**************************平台通知************************************/
		/**
		 * init
		 */
		public static const DOUWAN_INIT_STATUS : String = "DLInit";
		/**
		 * 用户登录
		 */
		public static const DOUWAN_LOGIN_STATUS : String = "DLLogIn";
		
		/**
		 * 用户注销 
		 */
		public static const DOUWAN_LOGOUT_STATUS : String = "DLExit";  
		
		/**
		 * 充值1
		 */
		public static const DOUWAN_PAY_STATUS : String = "DLPay";
		
		/**
		 * 充值2
		 */
		public static const DOUWAN_PAY_SAMPLE_STATUS : String = "DLPaySample";
		
		/**
		 * 个人中心
		 */
		public static const DOUWAN_GET_USER_INFO_STATUS : String = "DLenterUserInfo";
		
		/**
		 * 用户信息
		 */
		public static const DOUWAN_ENTER_USER_INFO_SAMPLE_STATUS : String = "DLgetInfo";
	} 
}
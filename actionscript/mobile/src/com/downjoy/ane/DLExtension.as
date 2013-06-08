package com.downjoy.ane 
{ 
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * 
	 * @author Rect  2013-5-5 
	 * 
	 */
	public class DLExtension extends EventDispatcher 
	{ 
		public static const DOWNJOY_FUNCTION_INIT:String = "downjoy_function_init";//与java端中Map里的key一致
		public static const DOWNJOY_FUNCTION_LOGIN:String = "downjoy_function_login";//与java端中Map里的key一致
		public static const DOWNJOY_FUNCTION_PAY:String = "downjoy_function_pay";//与java端中Map里的key一致
		public static const DOWNJOY_FUNCTION_EXIT:String = "downjoy_function_exit";//与java端中Map里的key一致
		public static const DOWNJOY_FUNCTION_PAY_SAMPLE:String = "downjoy_function_pay_sample";
		public static const DOWNJOY_FUNCTION_USERINFO:String = "downjoy_function_userinfo";
		public static const DOWNJOY_FUNCTION_GETINFO:String = "downjoy_function_getinfo";
		
		
		public static const EXTENSION_ID:String = "com.downjoy.ane";//与extension.xml中的id标签一致
		private var extContext:ExtensionContext; 
		
		/**单例的实例*/
		private static var _instance:DLExtension; 
		public function DLExtension(target:IEventDispatcher=null)
		{
			super(target);
			if(extContext == null) {
				extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, "");
				extContext.addEventListener(StatusEvent.STATUS, statusHandler);
			}
			//第二个为参数，会传入java代码中的FREExtension的createContext方法
		} 
		
		/**
		 * 获取实例
		 * @return DLExtension 单例
		 */
		public static function getInstance():DLExtension
		{
			if(_instance == null) 
				_instance = new DLExtension();
			return _instance;
		}
		
		/**
		 * 转抛事件
		 * @param event 事件
		 */
		private function statusHandler(event:StatusEvent):void
		{
			dispatchEvent(event);
		}
		
		/**
		 * 
		 * @param merchantId   当乐分配的MERCHANT_ID
		 * @param appId 当乐分配的APP_ID
		 * @param serverSeqNum 当乐分配的SERVER_SEQ_NUM，不同服务器序列号可使用不同计费通知地址
		 * @param appKey 当乐分配的APP_KEY
		 * @return 
		 * 
		 */				
		public function DLInit(merchantId:String,appId:String,serverSeqNum:String,appKey:String):String{
			if(extContext ){
				return extContext.call(DOWNJOY_FUNCTION_INIT,merchantId,appId,serverSeqNum,appKey) as String;
			}
			return "call login failed";
		} 
		
		/**
		 *登录发送函数  
		 * @param key 暂时传什么都可以  留着可能要用
		 * @return 
		 * 
		 */		
		public function DLLogIn(key:int):String{
			if(extContext ){
				return extContext.call(DOWNJOY_FUNCTION_LOGIN,key) as String;
			}
			return "call login failed";
		} 
		/**
		 *付费发送函数 
		 * @param data 
		 * @return 
		 * 
		 */		 
		public function DLPay(data:Vector.<String>,len:int = 5):String{
			if(extContext && data.length == len){ 
				return extContext.call(DOWNJOY_FUNCTION_PAY,data)as String;
			}
			return "call pay failed";
		}
		
		/**
		 *付费发送函数 
		 * @param data 
		 * @return 
		 * 
		 */		 
		public function DLPaySample(key:int = 0):String{
			if(extContext ){ 
				return extContext.call(DOWNJOY_FUNCTION_PAY_SAMPLE)as String;
			}
			return "call DLPaySample failed";
		}
		
		/**
		 *获取用户信息 
		 * @param key
		 * @return 
		 * 
		 */		
		public function DLgetInfo(key:int = 0):String{
			if(extContext){ 
				return extContext.call(DOWNJOY_FUNCTION_GETINFO,key) as String;
			}
			return "call exit failed";
		}
		
		/**
		 *进入个人中心
		 * @param key
		 * @return 
		 * 
		 */		
		public function DLenterUserInfo(key:int = 0):String{
			if(extContext){ 
				return extContext.call(DOWNJOY_FUNCTION_USERINFO,key) as String;
			}
			return "call exit failed";
		}
		
		/**
		 *退出SDK时候调用   这个函数只在退出游戏的时候调用  
		 * @param key
		 * @return 
		 * 
		 */		
		public function ExitSDKHandle(key:int):String{
			if(extContext){ 
				return extContext.call(DOWNJOY_FUNCTION_EXIT,key) as String;
			}
			return "call exit failed";
		}
		
	} 
}
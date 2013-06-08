package com.downjoy.func;

import android.os.Bundle;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

import com.downjoy.Downjoy;
import com.downjoy.DownjoyError;
import com.downjoy.CallbackListener;
import com.downjoy.ane.DLInitHandle;
//import com.downjoy.util.Util;
/**
 * 
 * @author Rect 2013-5-5
 */
public class DLLogin implements FREFunction {
	private String TAG = "DLLogIn";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 

//		Integer serverId=1; // 服务器id,未知可填null
		if(DLInitHandle.downjoy == null)
			DLInitHandle.initSDK(TAG,_context.getActivity());
		 
		DLInitHandle.downjoy.openLoginDialog(_context.getActivity(),  new CallbackListener() {

			@Override
			public void onLoginSuccess(Bundle bundle) {
				String memberId=bundle.getString(Downjoy.DJ_PREFIX_STR + "mid");
				String username=bundle.getString(Downjoy.DJ_PREFIX_STR + "username");
				String nickname=bundle.getString(Downjoy.DJ_PREFIX_STR + "nickname");
				String token=bundle.getString(Downjoy.DJ_PREFIX_STR + "token");
				Log.d(TAG, "---------用户登录-------");
				String str = "返回数据";
				str += "*"+0;
				str += "*"+memberId;
				str += "*"+username;
				str += "*"+nickname;
				str += "*"+token;
				_context.dispatchStatusEventAsync(TAG, str);
			}

			@Override
			public void onLoginError(DownjoyError error) {
				int errorCode=error.getMErrorCode();
				String errorMsg=error.getMErrorMessage();
				_context.dispatchStatusEventAsync(TAG, "onLoginError:" + errorCode + "|" + errorMsg);
			}

			@Override
			public void onError(Error error) {
				String errorMessage=error.getMessage();
				_context.dispatchStatusEventAsync(TAG, "onLoginError:" + errorMessage);
			}

		});
		return result;
	}

}

package com.downjoy.func;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

import com.downjoy.DownjoyError;
import com.downjoy.CallbackListener;
import com.downjoy.ane.DLInitHandle;
/**
 * 
 * @author Rect 2013-5-5
 */
public class DLExit implements FREFunction {
	private String TAG = "DLExit";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub

		_context = context; 
		FREObject result = null; 
		if(DLInitHandle.downjoy == null)
			DLInitHandle.initSDK(TAG,_context.getActivity());
		
		DLInitHandle.downjoy.logout(_context.getActivity(), new CallbackListener() { 

			@Override
			public void onLogoutSuccess() {
				_context.dispatchStatusEventAsync(TAG, "success");
			}

			@Override
			public void onLogoutError(DownjoyError error) {
				int errorCode=error.getMErrorCode();
				String errorMsg=error.getMErrorMessage();
				_context.dispatchStatusEventAsync(TAG, "onLogoutError:" + errorCode + "|" + errorMsg);
			}

			@Override
			public void onError(Error error) {
				_context.dispatchStatusEventAsync(TAG, "onError:" + error.getMessage());
			}
		});
		
		if(DLInitHandle.downjoy != null){
			DLInitHandle.downjoy.destroy();
			DLInitHandle.downjoy = null;
		}
		
         
		return result;
	}

}

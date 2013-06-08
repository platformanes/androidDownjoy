package com.downjoy.func;

import android.os.Bundle;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.downjoy.CallbackListener;
import com.downjoy.Downjoy;
import com.downjoy.DownjoyError;
import com.downjoy.ane.DLInitHandle;

/**
 * @author Rect
 * @version  Time：2013-6-8 
 */
public class DLgetInfo implements FREFunction {
	private String TAG = "DLgetInfo";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub

		_context = context; 
		FREObject result = null; 
		
		if(DLInitHandle.downjoy == null)
			DLInitHandle.initSDK(TAG,_context.getActivity());
		
		DLInitHandle.downjoy.getInfo(_context.getActivity(), new CallbackListener() {

            @Override
            public void onInfoSuccess(Bundle bundle) {
                String memberId=bundle.getString(Downjoy.DJ_PREFIX_STR + "mid");
                String username=bundle.getString(Downjoy.DJ_PREFIX_STR + "username");
                String nickname=bundle.getString(Downjoy.DJ_PREFIX_STR + "nickname");
                String gender=bundle.getString(Downjoy.DJ_PREFIX_STR + "gender");
                int level=Integer.parseInt(bundle.getString(Downjoy.DJ_PREFIX_STR + "level"));
                String avatarUrl=bundle.getString(Downjoy.DJ_PREFIX_STR + "avatarUrl");
                long createdDate=Long.parseLong(bundle.getString(Downjoy.DJ_PREFIX_STR + "createdDate"));

                _context.dispatchStatusEventAsync(TAG, "mid: " + memberId + "\n username: " + username + "\n nickname: " + nickname
                        + "\n gender: " + gender + "\n level: " + level + "\n avatarUrl: " + avatarUrl + "\n createdDate: "
                        + createdDate);
            }
 
            @Override
            public void onInfoError(DownjoyError error) {
                int errorCode=error.getMErrorCode();
                String errorMsg=error.getMErrorMessage();
                
                _context.dispatchStatusEventAsync(TAG, "onInfoError:" + errorCode + "|" + errorMsg);
            }

            @Override
            public void onError(Error error) {
                String errorMessage=error.getMessage();
                _context.dispatchStatusEventAsync(TAG, "onError:" + errorMessage);
            }
        });
		
		return result;
	}

}

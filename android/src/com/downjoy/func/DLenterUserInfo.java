package com.downjoy.func;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.downjoy.CallbackListener;
import com.downjoy.ane.DLInitHandle;

/**
 * @author Rect
 * @version  Time：2013-6-8 
 */
public class DLenterUserInfo implements FREFunction {
	private String TAG = "DLenterUserInfo";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub

		_context = context; 
		if(DLInitHandle.downjoy == null)
			DLInitHandle.initSDK(TAG,_context.getActivity());
		
		DLInitHandle.downjoy.openMemberCenterDialog(_context.getActivity(), new CallbackListener() {

            @Override
            public void onError(Error error) {
                String errorMessage=error.getMessage();
                _context.dispatchStatusEventAsync(TAG, "onError:" + errorMessage);
            }
        });
		
		return null;
	}

}

package com.downjoy.func;


import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.downjoy.ane.DLInitHandle;

/**
 * @author Rect
 * @version  Time：2013-5-21 
 */
public class DLInit implements FREFunction {
	private String TAG = "DLInit";
	private FREContext _context;
	
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		try
		{
			DLInitHandle.merchantId = arg1[0].getAsString();
			DLInitHandle.appId = arg1[1].getAsString();
			DLInitHandle.serverSeqNum = arg1[2].getAsString();
			DLInitHandle.appKey = arg1[3].getAsString();
		}
		catch(Exception e)
		{
			_context.dispatchStatusEventAsync(TAG, "init DLSDK faild");
			return null;
		}
		if(DLInitHandle.downjoy == null)
			DLInitHandle.initSDK(TAG,_context.getActivity());
		_context.dispatchStatusEventAsync(TAG, "init DLSDK success");
		return null;
	}

}

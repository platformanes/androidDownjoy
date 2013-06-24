package com.downjoy.func;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.downjoy.CallbackListener;
import com.downjoy.DownjoyError;
import com.downjoy.ane.DLInitHandle;

/**
 * @author Rect
 * @version  Time：2013-6-8 
 */
public class DLPaySample implements FREFunction {
	private String TAG = "DLPaySample";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub

		_context = context; 
		FREObject result = null; 
		
		float money = 0.0f; // 商品价格，单位：元
        String productName="测试商品"; // 商品名称
        String extInfo="1234"; // CP自定义信息，多为CP订单号

        if(DLInitHandle.downjoy == null)
			DLInitHandle.initSDK(TAG,_context.getActivity());
        // 打开支付界面,获得订单号
        DLInitHandle.downjoy.openPaymentDialog(_context.getActivity(), money, productName, extInfo, new CallbackListener() {

            @Override
            public void onPaymentSuccess(String orderNo) {
                _context.dispatchStatusEventAsync(TAG, "payment success! \n orderNo:" + orderNo);
            }

            @Override
            public void onPaymentError(DownjoyError error, String orderNo) {
                int errorCode=error.getMErrorCode();
                String errorMsg=error.getMErrorMessage();
                _context.dispatchStatusEventAsync(TAG, "onPaymentError:" + errorCode + "|" + errorMsg + "\n orderNo:" + orderNo);
            }

            @Override
            public void onError(Error error) {
            	 _context.dispatchStatusEventAsync(TAG, "onError:" + error.getMessage());
            }
        });
        
		return result;
	}
}

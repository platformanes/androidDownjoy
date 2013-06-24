package com.downjoy.func;



import android.util.Log;

import com.adobe.fre.FREArray;
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
public class DLPay implements FREFunction {
	private String TAG = "DLPay";
	private FREContext _context;
	
	@Override
	public FREObject call(final FREContext context, FREObject[] $args) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		String CpOrderId = null;
		String rmb = null;
		String productName = "元宝(1元 = 10 元宝)"; // 商品名称
		Log.d(TAG, "---------用户付费-------");
		if($args.length<1)
		{
			_context.dispatchStatusEventAsync(TAG,"参数不正确！");
			return null;
		}
		
		try{
			FREArray __array = (FREArray) $args[0];
			CpOrderId = __array.getObjectAt(0).getAsString();
			rmb = __array.getObjectAt(1).getAsString();
			productName = __array.getObjectAt(2).getAsString();
			//call back string
		}catch (Exception e) { 
			// TODO: handle exception
			_context.dispatchStatusEventAsync(TAG, "PayError:"+e.getMessage());
		}
		
		Log.d(TAG, "---------用户付费-------"+CpOrderId);
		try {

			float money = Integer.parseInt(rmb);//0.1f; // 商品价格，单位：元
			
			String extInfo = CpOrderId; // CP自定义信息，多为CP订单号

			// 打开支付界面,获得订单号
			if(DLInitHandle.downjoy == null)
				DLInitHandle.initSDK(TAG,_context.getActivity());
			String orderNo = DLInitHandle.downjoy.openPaymentDialog(_context.getActivity(), money, productName, extInfo, new CallbackListener() {

				@Override
				public void onPaymentSuccess(String orderNo) {
					Log.d(TAG, "---------用户付费---success----");
					_context.dispatchStatusEventAsync(TAG, "payment success! \n orderNo:" + orderNo);
				}

				@Override
				public void onPaymentError(DownjoyError error, String orderNo) {
					Log.d(TAG, "---------用户付费---error----");
					int errorCode=error.getMErrorCode();
					String errorMsg=error.getMErrorMessage();
					_context.dispatchStatusEventAsync(TAG, "onPaymentError:" + errorCode + "|" + errorMsg + "\n orderNo:" + orderNo);
				}

				@Override
				public void onError(Error error) {
					Log.d(TAG, "---------用户付费----error---");
					_context.dispatchStatusEventAsync(TAG, "onError:" + error.getMessage());
				}
			});
			
			_context.dispatchStatusEventAsync(TAG, "orderNo:" + orderNo);

		} catch(Exception e) {
			e.printStackTrace();
			_context.dispatchStatusEventAsync(TAG, "onError:" + e.getMessage());
			return null;
		}
		
		return result;
	}

}

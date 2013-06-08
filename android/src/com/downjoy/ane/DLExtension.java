package com.downjoy.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.downjoy.ane.DLContext;;
/**
 * 
 * @author Rect 2013-5-5
 */
public class DLExtension implements FREExtension {

	@Override
	public FREContext createContext(String arg0) {
		// TODO Auto-generated method stub
		return new DLContext();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override 
	public void initialize() {
		// TODO Auto-generated method stub

	}

}

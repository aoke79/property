package com.sms.data.kettletimer;


import com.sms.data.hr.photo.PhotoHR;
import com.sms.data.omis.FlightOmisHBSJ;

public class KettleTimer {

	/**
	 * Kettle自动化流程，注意先后顺序
	 */
	public void auto() {

		//同步HR人员照片
		new PhotoHR().auto();
		
		

	}

}

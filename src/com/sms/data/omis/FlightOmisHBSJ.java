package com.sms.data.omis;

import org.pentaho.di.core.exception.KettleException;

import com.sms.data.kettle.Kettle;

public class FlightOmisHBSJ {

	public void auto() {
		
		Kettle kettle = new Kettle();
		
		try {

			kettle.executeJobs("INTF_OMIS_HBSJ");
			
		} catch (KettleException e) {
			
			e.printStackTrace();
			
		} finally {
			
			kettle = null;
			
		}
	}

}

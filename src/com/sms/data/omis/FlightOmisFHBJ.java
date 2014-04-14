package com.sms.data.omis;

import org.pentaho.di.core.exception.KettleException;

import com.sms.data.kettle.Kettle;

public class FlightOmisFHBJ {

	public void auto() {
		
		Kettle kettle = new Kettle();
		
		try {

			kettle.executeJobs("INTF_OMIS_FHBJ");
			
		} catch (KettleException e) {
			
			e.printStackTrace();
			
		} finally {
			
			kettle = null;
			
		}
	}

}

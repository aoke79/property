package com.sms.data.hr.photo;

import org.pentaho.di.core.exception.KettleException;

import com.sms.data.kettle.Kettle;

public class PhotoHR {

	public void auto() {
		
		Kettle kettle = new Kettle();
		
		try {

			kettle.executeJobs("CM_PEOPLE_PHOTO");
			
		} catch (KettleException e) {
			
			e.printStackTrace();
			
		} finally {
			
			kettle = null;
			
		}
	}

}

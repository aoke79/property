package com.sms.data.quality;

import org.pentaho.di.core.exception.KettleException;

import com.sms.data.kettle.Kettle;

public class Quality {

	public void auto() {

		Kettle kettle = new Kettle();

		try {
			
			kettle.executeJobs("qualification_PILOT_COURSELIST");
			kettle.executeJobs("qualification_QUAL_BASE_PILOT_INFO");
			kettle.executeJobs("qualification_QUAL_PILOT_TRAINRECORDS");

		} catch (KettleException e) {

			e.printStackTrace();

		} finally {

			kettle = null;

		}
	}
}

package com.sms.data.qar;

import org.pentaho.di.core.exception.KettleException;

import com.sms.data.kettle.Kettle;

public class Events {
	
	public void auto() {

		Kettle kettle = new Kettle();

		try {

			kettle.executeJobs("QAR");

		} catch (KettleException e) {

			e.printStackTrace();

		} finally {

			kettle = null;

		}
	}

}

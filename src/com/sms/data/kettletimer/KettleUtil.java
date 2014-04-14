package com.sms.data.kettletimer;

import org.pentaho.di.core.exception.KettleException;

import com.sms.data.kettle.Kettle;

public class KettleUtil {
	
	
	public static void kettleExecuteJob(String jobName) {
		
		Kettle kettle = new Kettle();
		
		try {

			kettle.executeJobs(jobName);
			
		} catch (KettleException e) {
			
			e.printStackTrace();
			
		} finally {
			
			kettle = null;
			
		}
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("~~~~~~~~~start----------------------------");

		try {
			new Kettle().executeJobs("qualification_t_trainingtype");
		} catch (KettleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("~~~~~~~~~end------------------------------");
	}

}

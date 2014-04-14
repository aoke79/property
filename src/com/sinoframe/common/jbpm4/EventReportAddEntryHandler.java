package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class EventReportAddEntryHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		System.out.println("executor-------"+executor);
	    if ("信息员".equals(executor.split("-")[1]) || "QAR".equals(executor.split("-")[1])) {
	    	return "信息员提交";
	    }else{
	    	return "领导上报";
	    }
	    
	}

}

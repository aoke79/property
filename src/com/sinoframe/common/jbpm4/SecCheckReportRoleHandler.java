package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SecCheckReportRoleHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		System.out.println("executor-------"+executor);
	    if ("检查员".equals(executor.split("-")[1])) {
	    	return "检查员提交";
	    }else{
	    	return "领导上报";
	    }
	    
	}

}

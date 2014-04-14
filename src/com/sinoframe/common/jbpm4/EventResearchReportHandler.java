package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class EventResearchReportHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
	    if ("调查员".equals(executor.split("-")[1])) {
	    	return "提交";
	    }else{
	    	return "领导上报";
	    }
	    
	}

}

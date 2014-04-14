package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class AppraiseReportHandler implements DecisionHandler{

	@Override
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		if ("风险员".equals(executor.split("-")[1])) {
	    	return "领导审批";
	    }else{
	    	return "领导下发";
	    }
	}

}

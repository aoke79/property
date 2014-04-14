package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class EventReportBackHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String isAMECO = (String) execution.getVariable("isAMECO");
		System.out.println("isAMECO-------"+isAMECO);
		
	    if ("true".equals(isAMECO)) {
	    	return "工程技术单位驳回";
	    }else{
	    	return "非工程技术单位驳回";
	    }
	}

}

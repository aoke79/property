package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class EventReportOrgRoleHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String currentParentOrg = (String) execution.getVariable("currentParentOrg");
		System.out.println("currentParentOrg-------"+currentParentOrg);
	    if ("工程技术分公司".equals(currentParentOrg)) {
	    	execution.setVariable("isAMECO", "true");
	    	return "上报公司";
	    }else{
	    	execution.setVariable("isAMECO", "false");
	    	return "上报";
	    }
	}

}

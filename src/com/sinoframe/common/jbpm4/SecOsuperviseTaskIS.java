package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SecOsuperviseTaskIS implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String createDeptId = (String) execution.getVariable("leadership");
	    if (createDeptId.equals("1")) {
	    	return "下发经理";
	    }else{
	    	return "下发员";
	    }
	}

}

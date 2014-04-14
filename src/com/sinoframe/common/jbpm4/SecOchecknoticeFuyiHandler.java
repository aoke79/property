package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SecOchecknoticeFuyiHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		System.out.println("executor-------"+executor);
	    if ("督办员".equals(executor.split("-")[1])) {
	    	return "督办员提交复议";
	    }else{
	    	return "领导复议";
	    }
	    
	}

}

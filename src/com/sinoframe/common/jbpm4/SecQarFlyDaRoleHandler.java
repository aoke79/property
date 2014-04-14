package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SecQarFlyDaRoleHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		System.out.println("executor-------"+executor);
	    if ((executor.split("-")[1])!=null && (executor.split("-")[1]).indexOf("QAR")!=-1) {
	    	return "Qar信息员提交";
	    }else{
	    	return "领导上报";
	    }
	    
	    
	}

}

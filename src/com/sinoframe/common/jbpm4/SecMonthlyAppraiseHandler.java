package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SecMonthlyAppraiseHandler implements  DecisionHandler{
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
	    if ((executor.split("-")[1])!=null && (executor.split("-")[1]).indexOf("风险员")!=-1) {
	    	return "风险员提交";
	    }else{
	    	return "领导上报";
	    }
	    
	    
	}
}

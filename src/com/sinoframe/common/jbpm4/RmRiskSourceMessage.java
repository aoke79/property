package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class RmRiskSourceMessage implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
			if ("领导".equals(executor.split("-")[1]) || "部门高级经理".equals(executor.split("-")[1])) {
		    	return "领导上报";
		    }else{
		    	return "提交";
		    }
	}

}

package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class QarResearchHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
	    if ("QAR".equals(executor.split("-")[1])) {
	    	return "qar提交";
	    }else{
	    	return "领导上报";
	    }
	    
	}

}

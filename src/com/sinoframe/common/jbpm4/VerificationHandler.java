package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class VerificationHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String pageType = (String) execution.getVariable("pageType");
		if(pageType!=null && pageType.equals("result")){
			return "指定复核员";
		}else{
			return "其他单位";
		}
	    
	}

}

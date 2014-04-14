package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class GradeEvaluationHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String isMgr = (String) execution.getVariable("isMgr");
		System.out.println("userName-------"+isMgr);
	    if ("1".equals(isMgr)) {
	    	System.out.println("========是领导");
	    	return "是领导";
	    }else if("0".equals(isMgr)){
	    	System.out.println("========是信息员");
	    	return "是信息员";
	    }
	    return null;
	}

}

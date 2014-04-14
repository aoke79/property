package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class RoleEvaluationHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String userName = (String) execution.getVariable("userName");
		System.out.println("userName-------"+userName);
	    if ("基层员工".equals(userName) || "基层领导".equals(userName)) {
	    	System.out.println("========基层");
	    	return "基层";
	    }else if("一级单位信息员".equals(userName)){
	    	System.out.println("========一级单位");
	    	return "一级单位";
	    }else if("航安信息员".equals(userName) || "航安经理".equals(userName)){
	    	System.out.println("========航安部");
	    	return "航安部";
	    }
	    return null;
	}

}

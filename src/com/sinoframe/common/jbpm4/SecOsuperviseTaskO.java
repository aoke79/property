package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SecOsuperviseTaskO implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
	    if ((executor.split("-")[1])!=null && ((executor.split("-")[1]).indexOf("领导")!=-1||executor.contains("部门高级经理"))) {
	    	return "领导上报";
	    }else{
	    	return "下级单位信息员提交";
	    }
	}

}

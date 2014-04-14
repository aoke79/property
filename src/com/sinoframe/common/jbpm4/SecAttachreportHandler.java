package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SecAttachreportHandler implements DecisionHandler {
	@Override
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		System.out.println("executor-------"+executor);
	    if ((executor.split("-")[1])!=null && (executor.split("-")[1]).indexOf("QAR")!=-1) {
	    	return "QAR信息员提交";
	    }else{
	    	return "领导上报";
	    }
	}

}

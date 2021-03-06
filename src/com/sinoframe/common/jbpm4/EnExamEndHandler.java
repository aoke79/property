package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sinoframe.common.ConstantList;

public class EnExamEndHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		if (ConstantList.GHFGBID.equals(executor)) {
			 return "汇总";
		}else{
			 return "安排考试时间";
		}
		
	}

}

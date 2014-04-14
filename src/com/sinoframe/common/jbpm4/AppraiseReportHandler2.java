package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class AppraiseReportHandler2 implements DecisionHandler{

	@Override
	public String decide(OpenExecution execution) {
		int orgLevel = Integer.parseInt((String) execution.getVariable("sendOrgLevel"));
		int currectOrgLevel = Integer.parseInt((String) execution.getVariable("currectOrgLevel"));

		if(currectOrgLevel - orgLevel == 1){
			return "上报发起单位";
		}else{
			return "上报上级单位";
		}
		
	}

}

package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
public class SubQuaApplyF4Handler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("subGroupId");
		if (executor.equals("QUAL_UP_A2-TA")||executor.equals("QUAL_UP_TA-TB")) {
			return "分公司级技术委员会办公室主任审核报批";
		}else{
			return "本场训练";
		}
	}

}

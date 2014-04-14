package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class SubQuaApplyTrHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String subGroupId = (String) execution.getVariable("subGroupId");
		if (subGroupId.equals("QUAL_TR_FJSZJX")) {//副驾驶转机型
			return "分公司标准专员发文确认";
		}else{
			return "公司技术委员会办公室主任审核报批";
		}
	}

}

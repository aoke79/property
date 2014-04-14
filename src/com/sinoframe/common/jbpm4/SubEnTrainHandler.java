package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sinoframe.common.ConstantList;

public class SubEnTrainHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("subexecutor");
		String english_branch = (String) execution
				.getVariable("english_branch");
		String owner = (String) execution.getVariable("owner");
//		if (ConstantList.YINGYUBANGONGSHI.equals(english_branch)) {
//			if (ConstantList.PXBID.equals(owner)
//					|| ConstantList.GHFGBID.equals(owner)) {
//				return "去审批";
//			} else {
//				return "去子机构审批";
//			}
//		} else {
//			return "去审批";
//		}
return "";
	}

}

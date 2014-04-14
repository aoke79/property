package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sinoframe.common.ConstantList;

public class SubEnglishHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("subexecutor");
		String english_branch = (String) execution
				.getVariable("english_xinan");
		String owner = (String) execution.getVariable("owner");
//		if (ConstantList.YINGYUBANGONGSHI.equals(english_branch)) {
//			if (ConstantList.PXBID.equals(owner)
//					|| ConstantList.GHFGBID.equals(owner) || ConstantList.GHHFJB.equals(owner)) {
//				return "人员审批";
//			} else {
//				return "审批";
//			}
//		} else {
			return "人员审批";
//		}
	}

}

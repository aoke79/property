package com.sinoframe.common.jbpm4;

import java.util.HashMap;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sinoframe.common.ConstantList;

public class EnglishHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		if (ConstantList.GHFGBID.equals(executor)) {
			 return "英语办公室计划已制定完成";
		}else{
			 HashMap<String, String> mainpre=(HashMap<String, String>) execution.getVariable("mainpre");
			 execution.setVariable("mainpre1", mainpre);
			 return "计划已制定完成";
		}
		
	}

}

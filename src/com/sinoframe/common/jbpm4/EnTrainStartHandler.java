package com.sinoframe.common.jbpm4;

import java.util.HashMap;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.common.ConstantList;

public class EnTrainStartHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		if (ConstantList.GHFGBID.equals(executor) || ConstantList.XINANFENGONGSIID.equals(executor)) {
			 return "英语办公室培训计划制定完成";
		}else{
			HashMap<String, String> mainpre=(HashMap<String, String>) execution.getVariable("mainpre");
			execution.setVariable("mainpre1", mainpre);
		//	return "英语办公室培训计划制定完成";//update by QLL 
			return "分公司去报名";//update by QLL 2012-4-19
		}
		
	}

}

package com.sinoframe.common.jbpm4;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class PreventRectifyItemHandler2 implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("executor");
		String menuFlag = (String) execution.getVariable("menuFlag");
		if(menuFlag.equals("event")){
			if ("调查员".equals(executor.split("-")[1])) {
		    	return "提交";
		    }else{
		    	return "领导关闭";
		    }
		}else if(menuFlag.equals("qar")){
			if ("QAR".equals(executor.split("-")[1])) {
		    	return "提交";
		    }else{
		    	return "领导关闭";
		    }
		}else if(menuFlag.equals("inspect")){
			if ("检查员".equals(executor.split("-")[1])) {
		    	return "提交";
		    }else{
		    	return "领导关闭";
		    }
		}
	    return "";
	    
	}

}

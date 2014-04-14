package com.sinoframe.common.jbpm4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.common.ConstantList;

public class EnExamPxbAndGsjgHandler implements DecisionHandler {
	public String decide(OpenExecution execution) {
		String executor = (String) execution.getVariable("zongduiAndfenggongsi");
//		if (ConstantList.GHFGBID.equals(executor) || ConstantList.PXBID.equals(executor) || ConstantList.GHHFJB.equals(executor)) {
//			//获得下发任务的用户
//			CmUser cmUser = (CmUser)ActionContext.getContext().getSession().get("user");
//			HashMap<String, String> mainper1=new HashMap<String, String>();
//			List<String> list=new ArrayList<String>();
//			 if(ConstantList.GHFGBID.equals(executor)){
//				 list.add(ConstantList.GHFGBID);
//				 mainper1.put(ConstantList.GHFGBID, ConstantList.YINGYUBANGONGSHI);
//				 mainper1.put("name"+ConstantList.GHFGBID, cmUser.getName());
//			 }else if ( ConstantList.PXBID.equals(executor)) {
//				 list.add(ConstantList.PXBID);
//				 mainper1.put(ConstantList.PXBID, ConstantList.YINGYUBANGONGSHI);
//				 mainper1.put("name"+ConstantList.PXBID, cmUser.getName());
//			 }else if(ConstantList.GHHFJB.equals(executor)){
//				 list.add(ConstantList.GHHFJB);
//				 mainper1.put(ConstantList.GHHFJB, ConstantList.YINGYUBANGONGSHI);
//				 mainper1.put("name"+ConstantList.GHHFJB, cmUser.getName());
//			 }
//			 execution.setVariable("owners", list);
//			 execution.setVariable("sum", list.size());
//			 execution.setVariable("mainpre1", mainper1);
//			 return "去报名";
//		}else{
			 return "确认考试计划";
//		}
		
	}

}

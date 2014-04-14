package com.sinoframe.common.web;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;

import com.sinoframe.bean.CmUser;

public class OnlineUserListener implements HttpSessionListener {
	
	public static HashMap sessionMap=new HashMap();
	//Notification that a session was created
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();   
		System.out.println("新创建 sessionid is "+event.getSession().getId());
		//初始化当前session
		sessionMap.put(session.getId(),session);
	}

	//Notification that a session was invalidated
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("被释放的 sessionid is "+event.getSession().getId());
		HttpSession session=event.getSession();
		CmUser cmUser=(CmUser)session.getAttribute("user");
		//判断当前session user 是否有值
		if (session.getAttribute("user")!=null&&session.getAttribute("user").toString().length()>0) {
			//session销毁清空map更新map
			sessionMap.remove(((CmUser)session.getAttribute("user")).getLoginName().trim().toString());
			session.invalidate();
		}
	}
	
	public void removeUser(CmUser cmUser) {
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim().toString())!=null
				&&OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim()).toString().length()>0){	
			
			System.out.println(((HttpSession)OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim().toString())).getId());
			
			//当前用户已经在线  删除用户
			HttpSession sessionId=(HttpSession)OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim().toString());
			//注销已在线用户session
			sessionId.invalidate();
			OnlineUserListener.sessionMap.remove(cmUser.getLoginName().trim());
			//清除已在线用户，更新map key当前用户value session对象
			OnlineUserListener.sessionMap.put(cmUser.getLoginName().trim(), session);
			OnlineUserListener.sessionMap.remove(session.getId());
		}else {
			//根据当前sessionId取session对象。更新map key=用户名 value=session对象 删除map
			//key=sessionId
			
			OnlineUserListener.sessionMap.get(session.getId());
			System.out.println(((HttpSession)OnlineUserListener.sessionMap.get(session.getId())).getAttribute("user"));
			System.out.println(((HttpSession)OnlineUserListener.sessionMap.get(session.getId())).getId());
			OnlineUserListener.sessionMap.put(cmUser.getLoginName().trim().toString(),OnlineUserListener.sessionMap.get(session.getId()));
			OnlineUserListener.sessionMap.remove(session.getId());		
		}
	}

}

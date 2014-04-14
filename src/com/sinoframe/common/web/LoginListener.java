/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * 项目名称：个人财务管理
  * 文件名：LoginListener.java
  * 作者：@郝大锋
  * 公司名称：hao.com
  * 创建时间：2008-12-06
  * 功能描述: web请求监听暂未使用该类里的功能
  */

package com.sinoframe.common.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LoginListener  implements 
		HttpSessionAttributeListener, HttpSessionListener, ServletContextListener{
	ServletContext context;
	int users = 0;
	//Log log = LogFactory.getLog(LoginListener.class);
	private static HashMap<String, String> hUserName = 
	        new HashMap<String, String>();//保存sessionID和username的映射
	
	//HttpSessionActivationListener
	public void sessionDidActivate(HttpSessionEvent se) {
		//log.info("sessionDidActivate("+se.getSession().getId()+")");
	}

	public void sessionWillPassivate(HttpSessionEvent se) {
		//log.info("sessionWillPassivate("+se.getSession().getId()+")");
	}//HttpSessionActivationListener

	//HttpSessionBindingListener
	public void valueBound(HttpSessionBindingEvent event) {
		//log.info("valueBound("+event.getSession().getId()+event.getValue()+")");
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		//log.info("valueUnbound("+event.getSession().getId()+event.getValue()+")");
	}

	//HttpSessionAttributeListener
	public void attributeAdded(HttpSessionBindingEvent event) {
		//log.info("attributeAdded('" + event.getSession().getId() + "', '" + event.getName() + "', '" + event.getValue() + "')");
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		//log.info("attributeRemoved('" + event.getSession().getId() + "', '" + event.getName() + "', '" + event.getValue() + "')");
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		//log.info("attributeReplaced('"+se.getSession().getId()+",'"+se.getName()+"','"+se.getValue()+"')");
	}//HttpSessionAttributeListener

	//HttpSessionListener
	public void sessionCreated(HttpSessionEvent event) {
		//用Hashtable来存放用户进行比较
		users++;
		//log.info("sessionCreated('" + event.getSession().getId() + "'),目前有"+users+"个用户");
		context.setAttribute("users",new Integer(users));
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		//在这里可以加入用户销毁时要进行的操作
		users--;
		hUserName.remove(event.getSession().getId());
		//log.info("sessionDestroyed('" + event.getSession().getId() + "'),目前有"+users+"个用户");
		context.setAttribute("users",new Integer(users));
	}//HttpSessionListener

	//ServletContextListener
	public void contextDestroyed(ServletContextEvent sce) {
		//log.info("contextDestroyed()-->ServletContext被销毁");
		this.context = null;
	}

	public void contextInitialized(ServletContextEvent sce) {
		this.context = sce.getServletContext();
		//log.info("contextInitialized()-->ServletContext初始化了");
	}//ServletContextListener
	
    /**
     * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
     * @param sUserName String-登录的用户名称
     * @return boolean-该用户是否已经登录过的标志
     */
    @SuppressWarnings("unchecked")
    public static synchronized boolean isAlreadyEnter(HttpSession session,String sUserName){
        boolean flag = false;
        
        //如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中)
        if(hUserName.containsValue(sUserName)){
            flag = true;

            //遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)
            Iterator iter = hUserName.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry)iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                if(((String)val).equals(sUserName)){
                    hUserName.remove(key);
                }
            }
            //添加现在的sessionID和username
            hUserName.put( session.getId(),sUserName );
        }
        else{
            //如果该用户没登录过，直接添加现在的sessionID和username
            flag = false;
            hUserName.put( session.getId(),sUserName );            
        }
        return flag;
    }
    
    /**
     * isOnline-用于判断用户是否在线
     * @param session HttpSession-登录的用户名称
     * @return boolean-该用户是否在线的标志
     */
    public static boolean isOnline(HttpSession session){
        boolean flag = true;
        if( hUserName.containsKey( session.getId() ) ){
            flag = true;
        }
        else{
            flag = false;
        }
        return flag;
    }
    
    /**
     * isOnline-用于判断用户是否在线
     * @param session HttpSession-登录的用户名称
     * @return boolean-该用户是否在线的标志
     */
    public static boolean isOnline(String username, String userIP){
    	boolean flag = false;
    	//首先判断用户是否已经登录过了
    	if(hUserName.containsValue(username)){
    		//如果用户已经登录过了，在判断用户ip是否一致
    		if(hRemoteAddr.containsValue(username+":"+userIP)){
            	flag = false;
            }else{
            	flag = true;
            }
    	}
    	return flag;
    }
    
    
    /**
     * 如果该用户没登录过，直接添加现在的sessionID和username
     * @param session
     * @param sUserName
     */
    public static synchronized void setLoginUser(HttpSession session,String sUserName, String userIP){
        hUserName.put( session.getId(),sUserName ); 
        hRemoteAddr.put(session.getId(), sUserName+":"+userIP);

    }
    
   

    /**
     * 点击退出时，清除hUserName的值
     */
    public static synchronized void removeLoginUser(){
        hUserName.clear();
        hRemoteAddr.clear();
    }
  //保存sessionID和用户ip的映射
    private static HashMap<String, String> hRemoteAddr = new HashMap<String, String>();

}

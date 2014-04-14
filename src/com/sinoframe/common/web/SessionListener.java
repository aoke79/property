package com.sinoframe.common.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.SysLoginInfo;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.business.ISysLoginInfoBS;
import com.sinoframe.business.ISysOrganizationBS;
import com.sinoframe.common.ConstantList;
import com.sinoframe.common.util.SpringContextUtil;
import com.sinoframe.dao.IPublicDao;
//import com.sms.security.basicdata.bean.SecBeventcauseclass;
import com.sms.training.qualification.bean.AhUser;

public class SessionListener implements HttpSessionActivationListener, HttpSessionBindingListener,
				HttpSessionAttributeListener, HttpSessionListener, ServletContextListener {
	
	//用户信息Bean
	private CmUser cmUser;
	//用户信息Bean
		private AhUser ahUser;
		
	public AhUser getAhUser() {
			return ahUser;
		}

		public void setAhUser(AhUser ahUser) {
			this.ahUser = ahUser;
		}

	//登录相关信息Service
	public static ISysLoginInfoBS sysLoginInfoBS = null;
	//机构Bean
	private SysOrganization sysOrganization;
	//机构Service
	public static ISysOrganizationBS sysOrganizationBS = null;
	//公共方法
	private IPublicDao publicDao;
	ServletContext context;
	
	@SuppressWarnings("rawtypes")
	Map map = new HashMap();

	// 统计在线人数
	int sessioncount = 0;
	Logger log = Logger.getLogger(SessionListener.class);

	/** 根操作 */
	private SysOperate initBaseOperate;

	public SysOperate getInitBaseOperate() {
		return initBaseOperate;
	}

	public void setInitBaseOperate(SysOperate initBaseOperate) {
		this.initBaseOperate = initBaseOperate;
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("============sessionDidActivate");
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("============sessionWillPassivate");
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("============valueBound");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("============valueUnbound");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		setAhUser((AhUser) arg0.getSession().getAttribute("user"));

		if (ConstantList.MemberSession.equals(arg0.getName())) {
			ConstantList.MemberSessionAmount++;
			// System.out.println("ConstantList.MemberSessionAmount = " +
			// ConstantList.MemberSessionAmount);
			log.debug(" user amount = " + ConstantList.MemberSessionAmount);
			map.put(arg0.getSession().getId(), "");
		}
		if (ConstantList.OperatorSession.equals(arg0.getName())) {
			ConstantList.OperatorSessionAmount++;
		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("====SystemInfo:attributeRemoved");
		if (ConstantList.MemberSession.equals(arg0.getName())) {
			ConstantList.MemberSessionAmount--;
			String sessionId = arg0.getSession().getId();
			if (map.containsKey(sessionId)) {
				map.remove(sessionId);
			}
		}
		if (ConstantList.OperatorSession.equals(arg0.getName())) {
			ConstantList.OperatorSessionAmount--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

		Enumeration<String> names = arg0.getSession().getAttributeNames();
		while (names.hasMoreElements()) {
			System.out.println(names.nextElement());

		}
		System.out.println(arg0.getSession().getAttribute(
				"WW_TRANS_I18N_LOCALE"));
		System.out.println("====SystemInfo:attributeReplaced");
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

		if (sysLoginInfoBS == null) {
			sysLoginInfoBS = (ISysLoginInfoBS) this.getBean("sysLoginInfoBS");
		}

		sessioncount++;
		log.debug("session amount is " + sessioncount);
		System.out.println("====SystemInfo:sessionCreated");
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 销毁session
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		if (cmUser != null) {
			List<SysLoginInfo> list = (List<SysLoginInfo>) sysLoginInfoBS
				.findByAny("cmUser.userId", "'" + cmUser.getUserId() + "' ","");
			if (list != null && list.size() > 0) {
				SysLoginInfo sysLoginInfo = list.get(0);
				sysLoginInfo.setOnlineState("0");
				sysLoginInfoBS.update(sysLoginInfo);
			}
		}

		sessioncount--;
		String sessionId = arg0.getSession().getId();

		System.out.println(sessionId);

		if (map.containsKey(sessionId)) {
			map.remove(sessionId);
			ConstantList.MemberSessionAmount--;
		}
		log.debug("session amount is " + sessioncount);
		System.out.println("====SystemInfo:sessionDestroyed");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		this.context = null;
		System.out.println("====SystemInfo:contextDestroyed");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * 初始化content
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		this.context = arg0.getServletContext();

		try {
			//获取SessionFactory
			SessionFactory sessionFactory = (SessionFactory) SpringContextUtil.getBean("sessionFactory");
			
			//开启事务
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			//根据"根操作"递归遍历加载操作
			initBaseOperate = (SysOperate) session.get(SysOperate.class, "-1");
			recursionAllOperate(initBaseOperate);
			
			//根据最高机构获取整体机构结构
			SysOrganization sysOrganization = (SysOrganization) session
					.createQuery("from SysOrganization t where t.parent_Fun is null and t.setChild_Fun.size>0 and t.state = '1' order by orgOrder")
					.uniqueResult();
			List<SysOrganization> sysorgList = new ArrayList<SysOrganization>();
			recursionOrganization(sysOrganization, sysorgList);
			sysorgList.add(sysOrganization);
			
			//事件原因
//			List<SecBeventcauseclass> eventCauseList = new ArrayList<SecBeventcauseclass>();
//			eventCauseList = (List)session.createQuery("from SecBeventcauseclass order by id,sevcorderbyid").list();
			
			//加载完成后放入 application
			context.setAttribute("initBaseOperate", initBaseOperate);
			context.setAttribute("initBaseSysOrganization", sysOrganization);
			context.setAttribute("initBaseSysOrganizationList", sysorgList);
//			context.setAttribute("initEventCauseList", eventCauseList);
			
			//关闭session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("====SystemInfo:contextInitialized");

	}

	/**
	 * 递归遍历所有操作
	 * @param tempSysOperate
	 */
	public void recursionAllOperate(SysOperate tempSysOperate) {
		//有子操作时继续向下递归
		if (tempSysOperate.getSubOperate().size() > 0) {
			for (SysOperate subOperate : tempSysOperate.getSubOperate()){
				recursionAllOperate(subOperate);
			}
		}
	}
	
	/**
	 * 递归遍历所有机构
	 * @param currentOrganizaion
	 * @param list
	 */
	public void recursionOrganization(SysOrganization currentOrganizaion, List<SysOrganization> list) {
		
		Set<SysOrganization> setChild_Fun = currentOrganizaion.getSetChild_Fun();
		
		//获取需要的数据
		Set<SysUserOrgRelation> sysUserOrgRelations = currentOrganizaion.getSysUserOrgRelations();
		for (SysUserOrgRelation sysUserOrgRelation:sysUserOrgRelations) {
			sysUserOrgRelation.getSysOrganization();
			sysUserOrgRelation.getCmUser();
		}
		
		//有子操作时继续向下递归
		if (!setChild_Fun.isEmpty()) {
			for (SysOrganization sysOrganization : setChild_Fun) {
				sysUserOrgRelations = currentOrganizaion.getSysUserOrgRelations();
				//获取需要的数据
				for (SysUserOrgRelation sysUserOrgRelation:sysUserOrgRelations) {
					sysUserOrgRelation.getSysOrganization();
					sysUserOrgRelation.getCmUser();
				}
				if(sysOrganization.getState()!=null && sysOrganization.getState().equals("1")){
					list.add(sysOrganization);
				}
				recursionOrganization(sysOrganization, list);
			}	
		}
	}

	private static Integer LOCK_LOGIN_CNT = new Integer(0);

	public static int getOnlineCount() {
		return getOnlineCount("user");
	}

	public static int getOnlineCount(String flag) {
		int iOnlineLockCount = LOCK_LOGIN_CNT.intValue();
		return iOnlineLockCount;
	}

	public IPublicDao getPublicDao() {
		return publicDao;
	}

	public void setPublicDao(IPublicDao publicDao) {
		this.publicDao = publicDao;
	}

	//使用WebApplicationContext的方式获取Bean
	private Object getBean(String beanName) {
		ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		return webApplicationContext.getBean(beanName);
	}

	public CmUser getCmUser() {
		return cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

}

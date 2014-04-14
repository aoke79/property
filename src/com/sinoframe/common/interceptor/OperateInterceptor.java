package com.sinoframe.common.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jboss.errai.bus.server.annotations.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sinoframe.business.IService;
import com.sinoframe.common.util.StringUtil;

@Service("operateInterceptor")
public class OperateInterceptor extends AbstractInterceptor implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	
	private Object getBean(String beanName) {
		ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return webApplicationContext.getBean(beanName);
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//System.out.println("Operate ends with '.do' is caught!");
		//拼接一个可用的URL串
		String[] classPath = invocation.getAction().getClass().getName().replace(".", "&").split("&");
		String className = classPath[classPath.length-2];
		String moduleName = StringUtil.caseCharacter(className);
		String actionName = invocation.getProxy().getActionName();
		String methodName = invocation.getProxy().getMethod();
		String actionURL = moduleName + "/" + actionName + "!" + methodName + ".do";
		//记录操作
		IService serviceBS = (IService)this.getBean("BaseBS");
		serviceBS.operateLogger(actionURL);
		return invocation.invoke();
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		
	}


}

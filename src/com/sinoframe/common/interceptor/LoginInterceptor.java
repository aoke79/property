package com.sinoframe.common.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sinoframe.bean.CmUser;
import com.sms.training.qualification.bean.AhUser;

public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//在session中获得用户的信息
		AhUser ahUser = (AhUser) ActionContext.getContext().getSession().get("user");
		//用户不存在时，转到无操作的提示页面
		if(ahUser == null) {
			return "not login";
		}
		//取到用户的信息执行action中的方法
		return invocation.invoke();
	}

}

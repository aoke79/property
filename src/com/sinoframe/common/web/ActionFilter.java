/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * 项目名称：个人财务管理
  * 文件名：ActionFilter.java
  * 作者：@郝大锋
  * 公司名称：hao.com
  * 创建时间：2008-12-06
  * 功能描述: 请求过滤 (主要功能过滤地址栏请求地址)Struts2可改用拦截器
  */

package com.sinoframe.common.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.FilterDispatcher;

public class ActionFilter extends FilterDispatcher {
	
    private static String encoding = "GB2312"; 
    private FilterConfig config = null;
    
    /**
     * 初始化配置文件
     */
    public void init(FilterConfig filterConfig) throws ServletException { 
    	this.config = config;
    }
    public void doFilter(ServletRequest req, ServletResponse resp, 
           				FilterChain chain) throws IOException, ServletException {
    	
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(true);
		
		String path = request.getContextPath();
		
		String uri = request.getRequestURI();
		
		/*if(!isCheck(uri)){			
			if(!LoginListener.isOnline(session) ||
					session.getAttribute(ConstValues.USER_SESSION_INFO) == null){
				response.sendRedirect(path+"/index.jsp");
				return;
			}
		}*/
		
		//ADD BY HAODAFENG	2008/12/14	START
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // clear session if session id in URL
        if (httpRequest.isRequestedSessionIdFromURL()) {
            HttpSession session1 = httpRequest.getSession();
            if (session1 != null) session1.invalidate();
        }

        // wrap response to remove URL encoding
        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(httpResponse) {
            @Override
            public String encodeRedirectUrl(String url) {
                return url;
            }

            @Override
            public String encodeRedirectURL(String url) {
                return url;
            }

            @Override
            public String encodeUrl(String url) {
                return url;
            }

            @Override
            public String encodeURL(String url) {
                return url;
            }
        };

        // process next request in chain
        chain.doFilter(request, wrappedResponse);
		//ADD BY HAODAFENG	2008/12/14	END
    }
    
    /**
     * check URL
     * @param uri
     * @return
     */
	public boolean isCheck(String uri){
		if( uri.endsWith("login.jsp")								
				|| uri.endsWith("image.jsp")
				|| uri.endsWith("index.jsp")
				//|| uri.endsWith("myjsp.jsp")
				)
			return true;
		return false;
	}	
	
	public void destroy() {
		config = null;
	}

}

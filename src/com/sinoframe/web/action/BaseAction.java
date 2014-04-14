/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
 * 项目名称：SMS
 * 文件名：baseAction.java
 * 作者：@
 * 公司名称：
 * 创建时间：
 * 功能描述: Action基类
 */
package com.sinoframe.web.action;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.IService;
import com.sinoframe.common.ConstantList;
import com.sms.training.qualification.bean.AhUser;
@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "historyApprove", location = "/sms/security/common/viewApproveInfo.jsp"),
		@Result(name = "image", params = { "inputName", "imgStream" }, type = "stream") })
public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	// Servlet上下文
	private ServletContext servletContext = ServletActionContext
			.getServletContext();

	private IService BaseBS = (IService) this.getBean("BaseBS");

	// 排序信息
	SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	// 排序字段
	private String orderBlock;
	// 排序字段的类型
	private boolean fieldType;
	// 排序方式
	private int orderMethod = 0;
	private String leftJoinColumn;
	// 分页信息
	SysPageInfo sysPageInfo = new SysPageInfo();
	// 页码
	private int pageNum = 1;
	// 每页显示条数
	private int numPerPage = 20;
	// application 属性范围里的操作 ID 串
	private String appOperIds = "";
	// 审批传值时需要的Id
	private String businessApproveId = "";
	// 审批传值时需要的类型
	private String businessApproveType = "";
	private String approveButtonType = "";

	/**
	 * 设置Session
	 * 
	 * @param sessionID
	 *            sessionName
	 * @param sessionValue
	 *            sessionValue
	 */
	public void setSession(String sessionID, String sessionValue) {
		ActionContext.getContext().getSession().put(sessionID, sessionValue);
	}

	/**
	 * 根据SessionName清空Session
	 * 
	 * @param sessionID
	 */
	public void removeSession(String sessionID) {
		ActionContext.getContext().getSession().remove(sessionID);
	}

	/**
	 * 清空所有Session
	 */
	public void clearSession() {
		ActionContext.getContext().getSession().clear();
	}

	/**
	 * 根据SessionName取得Session Value
	 * 
	 * @param sessionID
	 * @return
	 */
	@JSON(serialize = false)
	public String getSession(String sessionID) {

		// 如果session为null返回空
		Object obj = ActionContext.getContext().getSession().get(sessionID);

		// 取得session
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * Servlet注入Spring容器中的Bean
	 * 
	 * @param name
	 * @return Object bean from ApplicationContext
	 */
	public Object getBean(String name) {

		ServletContext servletContext = ServletActionContext
				.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);

		return webApplicationContext.getBean(name);
	}

	/**
	 * 提示操作成功信息
	 * 
	 * @param messageInfo
	 * @param navTabId
	 * @param callbackType
	 * @param forwardUrl
	 * @return
	 */
	@JSON(serialize = false)
	public Message getSuccessMessage(String messageInfo, String navTabId,
			String callbackType, String forwardUrl) {
		Message message = new Message();
		message.setStatusCode("200");
		message.setMessageInfo(messageInfo);
		message.setNavTabId(navTabId);
		message.setCallbackType(callbackType);
		message.setForwardUrl(forwardUrl);
		return message;
	}

	/**
	 * 提示操作失败信息
	 * 
	 * @param messageInfo
	 * @return
	 */
	@JSON(serialize = false)
	public Message getFailMessage(String messageInfo) {
		Message message = new Message();
		message.setStatusCode("300");
		message.setMessageInfo(messageInfo);
		return message;
	}

	/**
	 * 提示会话超时信息
	 * 
	 * @param messageInfo
	 * @return
	 */
	@JSON(serialize = false)
	public Message getOvertimeMessage(String messageInfo) {
		Message message = new Message();
		message.setStatusCode("301");
		message.setMessageInfo(messageInfo);
		return message;
	}

	/**
	 * 获取session中的user
	 * 
	 * @return CmUser
	 */
	@JSON(serialize = false)
	public AhUser getUser() {
		AhUser user = (AhUser) ActionContext.getContext().getSession().get(
				"user");
		return user;
	}

	/**
	 * 获取IP地址
	 * 
	 * @return String字符串，值可以是"*",或是一个IP地址，或是一个IP地址区间
	 */
	@JSON(serialize = false)
	public String getIPAddress() {
		return ActionContext.getContext().getSession().get("ip").toString();
	}

	/**
	 * 获取机构集合
	 * 
	 * @return 机构的List集
	 */
	@JSON(serialize = false)
	public List<SysOrganization> getOrganizationList() {
		return BaseBS.getOrganizationByUser(this.getUser());
	}

	/**
	 * 获取机构集合中的第一个机构
	 * 
	 * @return
	 */
	@JSON(serialize = false)
	public SysOrganization getUserOrg() {
		List<SysOrganization> orgList = this.getOrganizationList();
		SysOrganization sysOrganization = null;
		if (orgList != null && orgList.size() > 0) {
			sysOrganization = (SysOrganization) orgList.get(0);
			return sysOrganization;
		} else {
			return null;
		}
	}

	/**
	 * 获取角色信息
	 * 
	 * @return 角色的List集
	 */
	@JSON(serialize = false)
	public List<SysRole> getRoleList() {
		return BaseBS.getRoleByUser(this.getUser());
	}

	/**
	 * 根据当前唯一角色和指定角色名称判断是否为当前角色
	 * 
	 * @param rolesList
	 * @param roleString
	 * @return
	 */
	public String singleRoleOnly(List<SysRole> rolesList, String roleString) {
		String defRole = "";
		if (!rolesList.isEmpty() && rolesList.size() == 1) {
			SysRole currentRole = rolesList.get(0);
			if (currentRole.getRoleName().contains(roleString)) {
				defRole = currentRole.getRoleName();
			}
		} else {
			for (SysRole sysRole : rolesList) {
				if (sysRole.getRoleName().contains(roleString)) {
					defRole = sysRole.getRoleName();
					break;
				}
			}
		}
		return defRole;
	}

	/**
	 * 设置request
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 取得request
	 * 
	 * @return request
	 */
	@JSON(serialize = false)
	public HttpServletRequest getServletRequest() {
		return this.request;
	}

	/**
	 * 设置response
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 取得response
	 * 
	 * @return response
	 */
	@JSON(serialize = false)
	public HttpServletResponse getServletResponse() {
		return this.response;
	}

	/**
	 * 
	 * @return
	 */
	@JSON(serialize = false)
	public ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * 
	 * @param servletContext
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 设置排序信息
	 * 
	 * @return
	 */
	@JSON(serialize = false)
	public SysOrderByInfo getSysOrderByInfo() {
		if (sysOrderByInfo == null) {
			sysOrderByInfo = new SysOrderByInfo();
		}
		if (orderMethod == 0) {
			sysOrderByInfo.setOrderAsc("");
		} else {
			sysOrderByInfo.setOrderAsc("desc");
		}
		sysOrderByInfo.setOrderColumn(orderBlock);
		sysOrderByInfo.setIfDate(fieldType);
		sysOrderByInfo.setLeftJoinColumn(leftJoinColumn);
		return sysOrderByInfo;
	}

	public void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}

	public String getOrderBlock() {
		return orderBlock;
	}

	public void setOrderBlock(String orderBlock) {
		this.orderBlock = orderBlock;
	}

	public int getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(int orderMethod) {
		this.orderMethod = orderMethod;
	}

	/**
	 * 设置分页信息
	 * 
	 * @return
	 */
	public SysPageInfo getSysPageInfo() {
		if (sysPageInfo == null) {
			sysPageInfo = new SysPageInfo();
		}
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum() - 1)
				* this.getNumPerPage());
		sysPageInfo.setCurrentPage(this.getPageNum());
		return sysPageInfo;
	}

	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	@JSON(serialize = false)
	public boolean isFieldType() {
		return fieldType;
	}

	public void setFieldType(boolean fieldType) {
		this.fieldType = fieldType;
	}

	@JSON(serialize = false)
	public String getBusinessApproveId() {
		return businessApproveId;
	}

	public void setBusinessApproveId(String businessApproveId) {
		this.businessApproveId = businessApproveId;
	}

	@JSON(serialize = false)
	public String getBusinessApproveType() {
		return businessApproveType;
	}

	public void setBusinessApproveType(String businessApproveType) {
		this.businessApproveType = businessApproveType;
	}
	@JSON(serialize = false)
	public String getApproveButtonType() {
		return approveButtonType;
	}

	public void setApproveButtonType(String approveButtonType) {
		this.approveButtonType = approveButtonType;
	}

	/**
	 * 获取分页信息
	 * 
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public SysPageInfo getSystemPageInfo(int pageNum, int numPerPage) {
		if (pageNum == 0) {
			// 默认分页信息
			SysPageInfo sysPageInfo = new SysPageInfo();
			sysPageInfo.setPageSize(20);
			sysPageInfo.setStartIndex(0);
			sysPageInfo.setCurrentPage(1);
			return sysPageInfo;
		} else {
			// 设置分页信息
			SysPageInfo sysPageInfo = new SysPageInfo();
			sysPageInfo.setPageSize(numPerPage);
			sysPageInfo.setStartIndex((pageNum - 1) * numPerPage);
			sysPageInfo.setCurrentPage(pageNum);
			return sysPageInfo;
		}
	}

	/**
	 * 获取 application 属性范围里的操作 ID 串
	 * 
	 * @return
	 */
	@JSON(serialize = false)
	public String getAppOperIds() {
		// 获取 application 属性范围里的操作集合
		SysOperate sysOper = (SysOperate) this.getServletContext()
				.getAttribute("initBaseOperate");
		// 递归遍历操作，并拼接成以‘，’分隔的串
		recurseOper(sysOper);
		// 返回 appOperIds 操作串
		return appOperIds;
	}

	/** 递归（recurse）遍历所有操作一次，并将所有的操作 ID 以‘，’拼接 */
	public void recurseOper(SysOperate tempSysOperate) {
		// 将操作 ID 以‘，’拼接
		appOperIds += tempSysOperate.getId() + ',';
		// System.out.print(tempSysOperate.getOperateName());
		// 如果当前的操作有子操作，继续向下递归（recurse）
		if (tempSysOperate.getSubOperate().size() > 0) {
			for (SysOperate subOperate : tempSysOperate.getSubOperate())
				// 递归遍历（recurse）操作
				recurseOper(subOperate);
		}
	}

	/**
	 * 得到当前用户的一个角色 type:0=单位信息员 1=QAR 2=检查员 3=QAR信息员 4=领导 5=高级经理 6=督办员
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@JSON(serialize = false)
	public SysRole getUserRole(String type) {
		List roleList = this.getRoleList();
		SysRole sysRole = null;
		if (roleList != null && roleList.size() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				sysRole = (SysRole) roleList.get(i);
				if (type.equals("0")
						&& (sysRole.getRoleName().indexOf("单位信息员") != -1 || sysRole
								.getRoleName().indexOf("部门信息员") != -1)) {
					return sysRole;
				} else if (type.equals("1")
						&& sysRole.getRoleName().indexOf("调查员") != -1) {
					return sysRole;
				} else if (type.equals("2")
						&& sysRole.getRoleName().indexOf("检查员") != -1) {
					return sysRole;
				} else if (type.equals("3")
						&& sysRole.getRoleName().indexOf("QAR") != -1) {
					return sysRole;
				} else if (type.equals("4")
						&& sysRole.getRoleName().indexOf("领导") != -1) {
					return sysRole;
				} else if (type.equals("5")
						&& sysRole.getRoleName().indexOf("部门高级经理") != -1) {
					return sysRole;
				} else if (type.equals("6")
						&& sysRole.getRoleName().indexOf("督办员") != -1) {
					return sysRole;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	/**
	 * 判断是否是领导的方法
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isManage() {
		List<SysRole> sysRoles = (List<SysRole>) this.getServletRequest()
				.getSession().getAttribute("roleList");
		for (SysRole role : sysRoles) {
			if (role.getRoleName().contains(ConstantList.ROLES_LEADER)
					|| role.getRoleName().contains(ConstantList.ROLES_MANAGER)) {
				return true;
			}
		}
		return false;
	}

	public String getLeftJoinColumn() {
		return leftJoinColumn;
	}

	public void setLeftJoinColumn(String leftJoinColumn) {
		this.leftJoinColumn = leftJoinColumn;
	}

	/**
	 * 非项目区域的图片在页面显示
	 */
	public void imageShow() {
		HttpServletResponse response = this.getServletResponse();
		ServletOutputStream out = null;
		InputStream is = null;
		try {
			out = response.getOutputStream();
			is = new FileInputStream(new File(filePath));
			int b = 0;
			byte[] bytes = new byte[0xffff];
			while ((b = is.read(bytes, 0, 0xffff)) > 0) {
				out.write(bytes, 0, b);
			}
			/*
			 * out.close(); is.close(); out.flush();
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
 
	/**
	 * 将文件转化成字节数组 * @param file
	 * 
	 * @return
	 */
	public byte[] file2byte(File file) {
		byte[] ret = null;
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			if (file == null) {
				return null;
			}
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream(4096);
			byte[] b = new byte[4096];
			int n;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			// in.close();
			// out.close();
			ret = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

}

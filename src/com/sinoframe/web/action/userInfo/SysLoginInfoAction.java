package com.sinoframe.web.action.userInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.jfree.base.modules.SubSystem;
import org.springframework.transaction.CannotCreateTransactionException;

import bios.report.designer.Application;

import com.opensymphony.webwork.dispatcher.ApplicationMap;
import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.LoginRules;
import com.sinoframe.bean.SysLoginInfo;
import com.sinoframe.bean.SysMenu;
import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRelationAccountRole;
import com.sinoframe.bean.SysRole;
import com.sinoframe.bean.SysSystem;
import com.sinoframe.bean.SysUserCustomMenu;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.bean.UserInfoErr;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.business.ISysLoginInfoBS;
import com.sinoframe.business.ISysMenuBS;
import com.sinoframe.business.ISysOperateBS;
import com.sinoframe.business.ISysUserCustomMenuBS;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Md5;
import com.sinoframe.common.web.LoginListener;
import com.sinoframe.common.web.OnlineUserListener;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.AhMail;
import com.sms.training.qualification.bean.AhSystem;
import com.sms.training.qualification.bean.AhUser;
import com.sms.training.qualification.bean.Ahdetail;
import com.sms.training.qualification.business.IAhUserBS;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
	@Result(name="loginInfo", location="/system/userInfo/loginInfoList.jsp"),
	@Result(name="exit", type="redirect",location="/index.jsp"),
	@Result(type="json",name="success")
})
public class SysLoginInfoAction extends BaseAction {
	
	//当前模块名称
	private static String moduleName = "SysLoginInfoAction";
	
	// 页面上返回的验证码需要与session中的key值为code的值做比较
	String validateCode;
	SysLoginInfo sysLoginInfo;
	CmUser cmUser;
	List<SysLoginInfo> listSysLoginInfo;
	//String sname = ""
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	// 用于页面上接收传进来的查询的返回页面上的值
	private HashMap<String, String> search=new HashMap<String, String>();
	
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//页码
	private int pageNum = 1;
	//每页显示条数
	private int numPerPage = 20;
	private String subsystemName;
	
	
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}

	// 登录时所反回的验证信息
	String msg;
	// 记录登录成功或失败
	boolean success;
	

	HttpSession session=ServletActionContext.getRequest().getSession();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	

	ISysLoginInfoBS sysLoginInfoBS;
	ICmUserBS cmUserBS;
	ISysOperateBS sysOperateBS;
	ISysMenuBS sysMenuBS;
	ISysUserCustomMenuBS sysUserCustomMenuBS;
	
	//登录错误次数    10-22
	private int errNuber= 3;
	//冻结多少分钟
	private int times = 15;
	
	public final void setSession(HttpSession session) {
		this.session = session;
	}
	public final void setSysLoginInfoBS(ISysLoginInfoBS sysLoginInfoBS) {
		this.sysLoginInfoBS = sysLoginInfoBS;
	}
	public final void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}
	
	public final void setSysOperateBS(ISysOperateBS sysOperateBS) {
		this.sysOperateBS = sysOperateBS;
	}
	public final void setSysMenuBS(ISysMenuBS sysMenuBS) {
		this.sysMenuBS = sysMenuBS;
	}
	public final void setSysUserCustomMenuBS(
			ISysUserCustomMenuBS sysUserCustomMenuBS) {
		this.sysUserCustomMenuBS = sysUserCustomMenuBS;
	}
	public String findAll() {
		listSysLoginInfo=sysLoginInfoBS.findAll();
		return "loginInfo";
	}
	/**
	 * 登录验证信息
	 * @return
	 */
	AhUser ahUser;
	public AhUser getAhUser() {
		return ahUser;
	}
	public  void setAhUser(AhUser ahUser) {
		this.ahUser = ahUser;
	}
	IAhUserBS ahUserBS;
	
	
	public IAhUserBS getAhUserBS() {
		return ahUserBS;
	}
	public void setAhUserBS(IAhUserBS ahUserBS) {
		this.ahUserBS = ahUserBS;
	}
	/**
	 * 验证用户名和密码是否正确
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String login() {
		try {
			// 将页面上的密码改为md5格式以和数据库中的数据进行比较
			cmUser.setAccountPassword(Md5.getInstance().EncoderByMd5(cmUser.getAccountPassword(),""));
			
			String attributename[]={"username","password"};
			String attributevalue[]={"'"+cmUser.getLoginName()+"'","'"+cmUser.getAccountPassword()+"'"};
			String loginName = cmUser.getLoginName();
			String code = (String) ActionContext.getContext().getSession().get("code");
			if(code!=null){
			if(!code.equalsIgnoreCase(validateCode)){
			//验证码错误
				System.out.println("验证码错误");
				setSuccess(false);
				setMsg("验证码输入错误");
				return SUCCESS;
			}
			}
			ahUser=cmUserBS.login("AhUser", attributename, attributevalue, "");
			
			
			String ip = request.getRemoteAddr();
			// 如果从后台返回的数据为null则说明用户不存在或者密码输入错误
			if(ahUser!=null){
				//判断这个人是否是离职人员
				if(ahUserBS.ifLeave(ahUser.getUsername(),"lizhi")){
					setSuccess(false);
					setMsg("该用户已经离职，无权登录系统");
					return SUCCESS;
				}else{
					if(LoginListener.isOnline(ahUser.getUsername(),ip)){
						System.out.println("该用户已经登录");
						setSuccess(false);
						setMsg("该用户已经登录");
						return SUCCESS;
						
					}else{
						//判断登录密码是否超时
						AhMail ahmail = this.ahUserBS.findAhmail(ahUser.getUseruuid());
						if(ahmail != null){
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							java.util.Date now = DateTool.getNow();
							java.util.Date begintime=ahmail.getBegintime();
							long l=now.getTime()-begintime.getTime();
							long day=l/(24*60*60*1000);
							long hour=(l/(60*60*1000)-day*24);
							long min=((l/(60*1000))-day*24*60-hour*60);
							long s=(l/1000-day*24*60*60-hour*60*60-min*60);
							System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
							if(day<1&&hour<1&& min<59){
								this.ahUserBS.delete(ahmail);
								
								ahUser=cmUserBS.findById(ahUser.getUseruuid());
								session.setAttribute("user",ahUser);
								LoginListener.setLoginUser(session,ahUser.getUsername(),ip);
								Cookie cookie = new Cookie("cookie_user", cmUser.getLoginName()); 
								System.out.println(cookie.getName());
								cookie.setMaxAge(60*60*24*30); //cookie 保存30天  
								response.addCookie(cookie);  
							}else{
								System.out.println("密码超时，请重新找回密码");
								setSuccess(false);
								setMsg("密码超时，请重新找回密码");
								return SUCCESS;
							}
						}else{
							ahUser=cmUserBS.findById(ahUser.getUseruuid());
							session.setAttribute("user",ahUser);
							LoginListener.setLoginUser(session,ahUser.getUsername(),ip);
							Cookie cookie = new Cookie("cookie_user", cmUser.getLoginName()); 
							System.out.println(cookie.getName());
							cookie.setMaxAge(60*60*24*30); //cookie 保存30天  
							response.addCookie(cookie);  
						}
						
					}
				}	
				
			}else {
				System.out.println("用户名或密码输入错误");
				setSuccess(false);
				String info = "";
				if(this.loginRulesList != null && this.loginRulesList.size() != 0){
					info = this.returnErrNum(loginName,this.loginRules.getTimeT());
				}
				setMsg(getText("wrongTips") +" "+info);
				return SUCCESS;
			}
		}catch (CannotCreateTransactionException e) {
			setMsg(getText("dbFail"));
			setSuccess(false);
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			setMsg(getText("systemWrongTips"));
			setSuccess(false);
			return SUCCESS;
		}
		this.deleteUser(ahUser);
		setSuccess(true);
		return SUCCESS;
	}
	/**
	 *判断Ip 验证ip是否在ip段 
	 * @param loginIp
	 * @return
	 */
	public boolean verdictIp(String loginIp) {
		// 所返回的信息
		boolean message=false;
		String ip;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(loginIp !=null && loginIp.length()>0){
			String ips[]=loginIp.split(",");
		if(ips.length==1){
			String ipFirst[]=ips[0].split("\\.");
			String ipCurrent[]=ip.split("\\.");
			for (int i = 0; i < ipFirst.length; i++) {
				if(ipFirst[i].equals("*")){
					message=true;
					continue;
				}
				if(!ipFirst[i].equals(ipCurrent[i])){
					message=false;
					continue;
				}
				message=true;
			}
		}else {
			if(ips.length==2){
				String ipFirst[]=ips[0].split("\\.");
				String ipLast[]=ips[1].split("\\.");
				String ipCurrent[]=ip.split("\\.");
				for (int i = 0; i < ipLast.length; i++) {
					if(ipFirst[i].equals("*")||ipLast[i].equals("*")||Integer.parseInt(ipCurrent[i])>=Integer.parseInt(ipFirst[i])&&Integer.parseInt(ipCurrent[i])<=Integer.parseInt(ipLast[i])){
						message= true;
					}else {
						message= false;
						break;
					}
				}
			}
		}
		}
		//
		return message;
		
	}
	
	
	/**
	 * 根据条件进行检索
	 */ 
	public String search(){
		if(ServletActionContext.getRequest().getParameter("name")!=null){
			query.put("like_cmUser.name", ServletActionContext.getRequest().getParameter("name"));
			search.put("name", ServletActionContext.getRequest().getParameter("name"));
		}
		//定义分页的SysPageInfo对象
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		
		//进行条件查询的语句
		String hql = "from SysLoginInfo where 1=1 ";
		
		listSysLoginInfo = sysLoginInfoBS.findPageByQuery(sysPageInfo, hql, query, this.getSysOrderByInfo());
		
		//进行条数查询的语句
		String counthql = "select count(*) from SysLoginInfo where 1=1 ";
		
		//设置最大条数
		sysPageInfo.setMaxCount(this.sysLoginInfoBS.getCountByHQL(counthql, query));
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			//设置日志信息
			sysMenuBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			sysLoginInfoBS.getErrorLog().info(e.getMessage());
		}
		
		return "loginInfo";
	}
	
	/**
	 * 退出方法
	 * @return 返回登录页面
	 */
	public String exit() {
		AhUser ahUser=(AhUser)ActionContext.getContext().getSession().get("user");
		if(ahUser!=null){
			List<SysLoginInfo> list=(List<SysLoginInfo>)sysLoginInfoBS.findByAny("cmUser.userId", "'"+ahUser.getUsername()+"'", "");
			if(list!=null && list.size()>0){
				SysLoginInfo sysLoginInfo=list.get(0);
				sysLoginInfo.setOnlineState("0");
				sysLoginInfoBS.update(sysLoginInfo);
			}
		}
		LoginListener.removeLoginUser();
		session.setAttribute("user", null);
		return "exit";
	}
	
	public void findUserSessionByLoginName(CmUser cmUser) {
		HttpSession session=ServletActionContext.getRequest().getSession();
		
		if(OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim().toString())!=null
				&&OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim()).toString().length()>0
				&&!session.getId().equals(((HttpSession)OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim().toString())).getId())){
			
			//当前用户已经在线  删除用户
			HttpSession sessionId=(HttpSession)OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim().toString());
			//注销已在线用户session
			sessionId.invalidate();
			OnlineUserListener.sessionMap.remove(cmUser.getLoginName().trim());
			//清除已在线用户，更新map key当前用户value session对象
			OnlineUserListener.sessionMap.put(cmUser.getLoginName().trim(), session);
			OnlineUserListener.sessionMap.remove(session.getId());
		}else if (OnlineUserListener.sessionMap.get(cmUser.getLoginName().trim().toString())==null) {
			//根据当前sessionId取session对象。更新map key=用户名 value=session对象 删除map
			//key=sessionId			
			OnlineUserListener.sessionMap.get(session.getId());			
			OnlineUserListener.sessionMap.put(cmUser.getLoginName().trim().toString(),OnlineUserListener.sessionMap.get(session.getId()));
			OnlineUserListener.sessionMap.remove(session.getId());
		}
	}
	public final HashMap<String, String> getQuery() {
		return query;
	}
	public static final String getModuleName() {
		return moduleName;
	}
	public static final void setModuleName(String moduleName) {
		SysLoginInfoAction.moduleName = moduleName;
	}
	public final void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	public final SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}
	public final void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}
	public final int getPageNum() {
		return pageNum;
	}
	public final void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public final int getNumPerPage() {
		return numPerPage;
	}
	public final void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	@JSON(serialize=false)
	public SysLoginInfo getSysLoginInfo() {
		return sysLoginInfo;
	}
	public void setSysLoginInfo(SysLoginInfo sysLoginInfo) {
		this.sysLoginInfo = sysLoginInfo;
	}

	@JSON(serialize=false)
	public CmUser getCmUser() {
		return cmUser;
	}
	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	@JSON(serialize=false)
	public List<SysLoginInfo> getListSysLoginInfo() {
		return listSysLoginInfo;
	}
	public void setListSysLoginInfo(List<SysLoginInfo> listSysLoginInfo) {
		this.listSysLoginInfo = listSysLoginInfo;
	}

	
	public final String getMsg() {
		return msg;
	}
	public final void setMsg(String msg) {
		this.msg = msg;
	}
	public final boolean isSuccess() {
		return success;
	}
	public final void setSuccess(boolean success) {
		this.success = success;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public final HashMap<String, String> getSearch() {
		return search;
	}
	public final void setSearch(HashMap<String, String> search) {
		this.search = search;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		List<CmUser> s=cmUserBS.findAll();
		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i).getName());
		}
		return "json";
	}
	
	/**
	 * 根据Set集合将需要的部分转换成List集合
	 * @param <T>
	 * @param tSet
	 * @return List集合
	 */
	public List<SysRole> changeRoleSet(Set<SysRelationAccountRole> roleSet) {
		List<SysRole> roleList = new ArrayList<SysRole>();
		for (SysRelationAccountRole sysRelationAccountRole : roleSet) {
			roleList.add(sysRelationAccountRole.getSysRole());
		}
		return roleList;
	}
	
	public List<SysOrganization> changeOrgSet(Set<SysUserOrgRelation> orgSet) {
		List<SysOrganization> orgList = new ArrayList<SysOrganization>();
		for (SysUserOrgRelation sysUserOrgRelation : orgSet) {
			orgList.add(sysUserOrgRelation.getSysOrganization());
		}
		return orgList;
	}
	
	//登录错误表匹配计算
	public void SaveOrUpdate(String loginName){
		//10---22
		String hql = " from UserInfoErr where loginName='"+loginName+"'" ;
		List<UserInfoErr> errUser  = this.cmUserBS.findPageByQuery(hql);
		if(errUser != null && errUser.size() !=0 ){
			UserInfoErr uc = errUser.get(0);
			if(uc.getErrNumber() >=this.errNuber-1){
		    uc.setTimes(returnTime(new Date()));		
			}
			uc.setErrNumber(uc.getErrNumber()+1);
			this.cmUserBS.update(uc);
		}else{
			UserInfoErr u = new UserInfoErr();
			u.setErrNumber(1);
			u.setLoginName(loginName);
			this.cmUserBS.save(u);
		}
		//---
		
	}
	
	
	//当登录成功后 就删除 错误表里的数据
	public void deleteUser(AhUser ahUser){
		String hql = " delete from UserInfoErr where loginName='"+ahUser.getUsername()+"'";
	    this.cmUserBS.executeUpdate(hql);
	}
	
	public String returnTime(Date time){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(time != null){
				return sdf.format(time);
			}
			return "";
		}
	
	public String returnErrNum(String loginName,String times){
		String hql = " from UserInfoErr where loginName='"+loginName+"'" ;
		List<UserInfoErr> errUser  = this.cmUserBS.findPageByQuery(hql);
		if(errUser == null || errUser.size() ==0 ){
			return "您还有 "+(this.errNuber-1)+" 次机会";
		}else{
			UserInfoErr uc = errUser.get(0) ;
			if(this.errNuber-uc.getErrNumber() < 0){
				return " 此账号登陆失败过多,已被冻结!"+this.times+ times+"后可以登陆";
			}
			if(this.errNuber-uc.getErrNumber() == 0){
				return " 此账号登陆失败过多,已被冻结!"+this.times+times+" 后可以登陆";
			}else{
				return "您还有 "+(this.errNuber-uc.getErrNumber())+" 次机会";
			}
			
			
		}
	}
	public Date returnDate(String time) throws ParseException{
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(time != null){
				return sdf.parse(time);
			}
			return new Date();
		}
	
	public LoginRules getLoginRules() {
		return loginRules;
	}
	public void setLoginRules(LoginRules loginRules) {
		this.loginRules = loginRules;
	}
	public List<LoginRules> getLoginRulesList() {
		return loginRulesList;
	}
	public void setLoginRulesList(List<LoginRules> loginRulesList) {
		this.loginRulesList = loginRulesList;
	}

	private LoginRules loginRules = new LoginRules(); 
	
	private List<LoginRules> loginRulesList = new ArrayList<LoginRules>();
}

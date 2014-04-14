package com.sinoframe.web.action.userInfo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.CmUserPasswordHistory;
import com.sinoframe.bean.LoginRules;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysCountry;
import com.sinoframe.bean.SysLoginInfo;
import com.sinoframe.bean.SysMenu;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysSystem;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.bean.SysUserOrgRelationId;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.business.ICmUserPasswordHistoryBS;
import com.sinoframe.business.ISysCountryBS;
import com.sinoframe.business.ISysLoginInfoBS;
import com.sinoframe.business.ISysOrganizationBS;
import com.sinoframe.business.ISysUserOrgRelationBS;
import com.sinoframe.common.util.Md5;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;

@Controller
@ParentPackage("sinoframe-default")
@Results({

	@Result(name="mes" ,location="/standard/ajaxDone.jsp"),
	@Result(name="list",location="/system/loginRules/loginRulesList.jsp"),
	@Result(name="add",location="/system/loginRules/loginRulesAdd.jsp"),
	@Result(name="modifyPage",location="/system/loginRules/loginRulesEdit.jsp"),
	@Result(name="json",type="json")
})
/**
 * 描述: 登录规则定义Action
 * @author LUJIE
 *
 */
public class LoginRulesAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	CmUser cmUser;
	SysMenu sysMenu;
	public SysMenu getSysMenu() {
		return sysMenu;
	}
	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}
	SysCountry sysCountry;
	CmUserPasswordHistory cmUserPasswordHistory;
	SysLoginInfo sysLoginInfo;
	SysOrganization sysOrganization;	

  	//子系统集合
	private List<SysSystem> sysSystemList = new ArrayList<SysSystem>();

	// 存放ID集
	private String ids;
	
	// 存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	
	// 用于页面上接收传进来的查询的返回页面上的值
	private HashMap<String, String> search=new HashMap<String, String>();
	// 分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	// 排序对象
	private SysOrderByInfo sysOrderByInfo = new SysOrderByInfo();
	
	//排序字段
	private String orderBlock;
	//排序方式
	private int orderMethod = 0;
	public List<CmUser> getCmUserList() {
		return cmUserList;
	}
	public void setCmUserList(List<CmUser> cmUserList) {
		this.cmUserList = cmUserList;
	}
	private List<CmUser> cmUserList = new ArrayList<CmUser>();
	// 得到用户集合
	List<CmUser> listUser;
	// 得到国籍集合
	List<SysCountry> listSysCountry;
	// 得到机构集合
	List<SysOrganization> sysOrganizationList;
	List<SysUserOrgRelation> sysUserOrgRelationList;
	
	
	ICmUserBS cmUserBS;	
	ISysOrganizationBS sysOrganizationBS;
	ICmUserPasswordHistoryBS cmUserPasswordHistoryBS;
	ISysCountryBS sysCountryBS;
	ISysLoginInfoBS sysLoginInfoBS;
	// 用户与机构关系表的service层
	ISysUserOrgRelationBS sysUserOrgRelationBS;
	
	// 当点击更新按钮时需要把从SysUserOrgRelation中的值求出来拼在字符串，传入前台
	String orgFunIds="";
	String orgFunNames="";
	
	//消息实体
	private Message message;
	
	private boolean success;
	// 自定义错误信息
	private String myError;
	
	// 接收检索是的排序列
	private String orderField;
	// 用于存储查询条件的字符串形式
	private String strQuery;
	
	// 调用delete方法的标志
	private String deleteFlag;
	
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	private String enter;
	private String closeFlag;
	
	private LoginRules loginRules = new LoginRules(); 
	
	private List<LoginRules> loginRulesList = new ArrayList<LoginRules>();
	
	private String addFlag = "0" ;
	
	public String toadd() {
  
		return "list";
	}
	
	@SuppressWarnings("unchecked")
	public String list() {
		try {
			
			String hql = " from  LoginRules  where 1=1  ";
			this.loginRulesList = this.cmUserBS.findPageByQuery(hql);
			if (loginRulesList != null && loginRulesList.size() != 0) {
				this.addFlag = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return "list";

	}
	
	
    public String modifyPage(){
	       this.loginRules = this.cmUserBS.findById(LoginRules.class, this.loginRules.getId());
	       
    	return "modifyPage";
    }
    
	public void deleteUser(String name){//10-22
		String hql = " delete from UserInfoErr ";
	    this.cmUserBS.executeUpdate(hql);
	    System.out.println("---------------"+name+"-------------删除错误表里的数据");
	}
	
    public String modify(){
		try {
			this.cmUserBS.update(this.loginRules);
			deleteUser("修改登录规则成功");
			this.message = this.getSuccessMessage("修改成功", null, "forward",
					"user-info/login-rules!list.do");
		} catch (Exception e) {
			this.message = this.getFailMessage("修改失败");	
			e.printStackTrace();
		}
    	
    	return "mes";
    }
	
	
	public String add() {
		return "add";
	}	
	
	public String doAdd() {
		try {
			
			
			this.cmUserBS.save(this.loginRules);
			deleteUser("添加登录规则成功");
			this.message = this.getSuccessMessage("添加成功", null, "forward",
					"user-info/login-rules!list.do");
		} catch (Exception e) {
			this.message = this.getFailMessage("添加失败");	
		}
		return "mes";
	}	
	
	
	
	public String dodeleteById() {
		int sign=0;
		String userId=((CmUser)ActionContext.getContext().getSession().get("user")).getUserId();
		try {
			cmUser=(CmUser)cmUserBS.findById(cmUser.getUserId());
			if(cmUser.getUserId().equals(userId)){
				sign=1;
				throw new RuntimeException();
			}
			cmUser.setState("2");			
			cmUserBS.update(cmUser);
			this.message = this.getSuccessMessage(getText("deleteSuccess"), 
					null, "forward", "user-info/cm-user!toadd.do?pageNum="
								+ this.getPageNum() + "&numPerPage="
								+ this.getNumPerPage() + "&"
								+ Util.toStrQuery(query));
		} catch (RuntimeException e) {
			//设定失败消息
			if(sign==1){
				this.message = this.getFailMessage("您不可以对自己进行删除操作");				
			}else {
				this.message = this.getFailMessage(getText("deleteFail"));
				e.printStackTrace();
			}	
		}
		return SUCCESS;
	}
	
	// 删除多条记录
	public String multipleDelete() {
		String userId=((CmUser)ActionContext.getContext().getSession().get("user")).getUserId();
		
		// 用来接收页面上传来的ids值，分隔成数组
		String condition[]=this.getIds().split(",");
		int sign=0;
		ids="";
		for (int i = 0; i < condition.length; i++) {
			
			cmUser=cmUserBS.findById(condition[i]);
			if(cmUser!=null&&cmUser.getUserId().equals(userId)){
				sign=1;
			}else {
				condition[i]="'"+condition[i]+"'";
				ids+=condition[i]+",";
			}
		}
		ids=ids.equals("")?"":ids.substring(0, ids.length()-1);
		
		try {
			if(!ids.equals("")){
				
				cmUserBS.bulkUpdate("CmUser", "state","2","userId in ("+ids+")");
				if(sign==1){
					throw new Exception();
				}
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "tbuser", "forward", "user-info/cm-user!toadd.do");
			}
		} catch (Exception e) {
			if(sign==1){
				this.message = this.getSuccessMessage("删除成功！ 但您不可对自己执行删除操作！", "tbuser", "forward", "user-info/cm-user!toadd.do");
				return SUCCESS;
			}
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}


	//得到listUser集合，并得到listSysCountry及listSysOrganization的集合
	@SuppressWarnings("unchecked")
	private void getUserList() {
		// 进行条件查询的语句
		//by lujie
        CmUser cmUser = (CmUser) ActionContext.getContext().getSession().get("user");	
        String 	hql="from CmUser where 1=1 ";
        String counthql="select count(*) from CmUser where 1=1 ";
       if(!"超级管理员".equals(cmUser.getName().trim())){
        	hql += " and subsystemId ='"+cmUser.getSubsystemId()+"'";
        	counthql += " and subsystemId ='"+cmUser.getSubsystemId()+"'";
        	
        }
		// 进行条数查询的语句
    	if(query.get("query.like_subsystemId")!=null && query.get("query.like_subsystemId").length()!=0){
			query.put("like_subsystemId", query.get("query.like_sysMenu.name"));
		}

		//根据国家
		String country=this.getServletRequest().getParameter("country");
		//根据职能机构
		String funOrgId=this.getServletRequest().getParameter("funOrgId");
		String funOrgName=this.getServletRequest().getParameter("funOrgName");
		if(country!=null&&!country.equals("")){
			query.put("eq_sysCountry.id", country);
			search.put("country", country);
		}
		if(funOrgId!=null&&!funOrgId.equals("")){
			search.put("funOrgId", funOrgId);
			search.put("funOrgName", funOrgName);
		}
		query.put("notin_state", "2");
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		
		//定义排序方式及排序列
		//设置排序方式，0为正常排序，1为反相排序
		if(orderMethod == 0){
			sysOrderByInfo.setOrderAsc("asc");
			orderMethod = 1;
		} else {
			sysOrderByInfo.setOrderAsc("desc");
			orderMethod = 0;
		}
		//获取排序字段
		sysOrderByInfo.setOrderColumn(orderBlock);		
		
		if (strQuery != null && !"".equals(strQuery)) {
			query = Util.toMap(strQuery);
		}else if(deleteFlag != null){
			query = Util.decodeQuery(query);
		} 
		if(funOrgId!=null && !funOrgId.equals("")){
			listUser = cmUserBS.findPageByQuery(sysPageInfo, hql,"and userId in (select t.id.userId from SysUserOrgRelation t where t.id.orgId ='"+funOrgId+"')", query, sysOrderByInfo);
		}else {
			listUser = cmUserBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		}
	
		// 查出来的每个人再去根据id去用户机构关系表里这个用户属于哪些机构并显示到页面上
		for (int i = 0; i < listUser.size(); i++) {
			List<SysUserOrgRelation> FunOrgIdList=new ArrayList<SysUserOrgRelation>();
			FunOrgIdList=sysUserOrgRelationBS.findPageByQuery("from SysUserOrgRelation t where t.id.userId ='"+listUser.get(i).getUserId()+"'");
			String userOrgIds="";
			for (int j = 0; j < FunOrgIdList.size(); j++) {
				userOrgIds+=FunOrgIdList.get(j).getSysOrganization().getName()+",";
			}
			if(!userOrgIds.equals("")){
				userOrgIds=userOrgIds.substring(0, userOrgIds.length()-1);
				listUser.get(i).setFunOrgId(userOrgIds);
			}
		}
		//设置最大条数  
		if(funOrgId != null && !"".equals(funOrgId)){
			sysPageInfo.setMaxCount(this.cmUserBS.getCountByHQL(counthql+"and userId in (select t.id.userId from SysUserOrgRelation t where t.id.orgId ='"+funOrgId+"')", query));
		}else{
			sysPageInfo.setMaxCount(this.cmUserBS.getCountByHQL(counthql, query));
		}
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			cmUserBS.getErrorLog().info(e.getMessage());
		}
		String attributename[]={"type","state"};
		Object attributevalue[]={"'1'","'1'"};
		sysOrganizationList=sysOrganizationBS.findByAny("SysOrganization", attributename, attributevalue, "");
		listSysCountry=sysCountryBS.findAll();
		for(int i=0;i<listUser.size();i++){
			
			if(listUser.get(i).getSex()!=null&&listUser.get(i).getSex().equals("1")){
				listUser.get(i).setSex(getText("male"));
			}else {
				listUser.get(i).setSex(getText("female"));
			}
			if(listUser.get(i).getState()!=null&&listUser.get(i).getState().equals("1")){
				listUser.get(i).setState(getText("atPost"));
			}else {
				listUser.get(i).setState(getText("leavePost"));
			}
		}
		
		strQuery=Util.toStrQuery(query);
	}
	
	
	/**
	 * 找回密码方法
	 * XXX
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrievePwd(){
		listUser=cmUserBS.findByAny("email", "'"+cmUser.getEmail()+"'", "");
		cmUser=listUser==null||listUser.size()==0?null:listUser.get(0);
		if(cmUser!=null){
			try {
				String pwd=GenRandomNum.getInstance().genRandomNum(6);
				System.out.println(pwd);
				//将用户名加密，作为参数传入email，用于作后来的修改密码判断。
				String loginNameMd5=Md5.getInstance().EncoderByMd5(cmUser.getLoginName(),"");
				//EmailUtil.sendEmail("找回密码",pwd ,cmUser.getEmail());
				cmUser.setAccountPassword(Md5.getInstance().EncoderByMd5(pwd, ""));
				cmUserBS.update(cmUser);
				setSuccess(true);
			} catch (NoSuchAlgorithmException e) {
				setMyError("系统出错");
				setSuccess(false);
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				setMyError("系统出错");
				setSuccess(false);
				e.printStackTrace();
			}
		}else {
			setMyError("此Email从未注册");
			setSuccess(false);
		}
		return "json";
	}
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	public String toPwd() {
		if(cmUser != null && cmUser.getUserId() != null && !cmUser.getUserId().equals("")){
			cmUser = cmUserBS.findById(CmUser.class, cmUser.getUserId());
		}
		return "toPwd";
	}
	
	/**
	 * 修改密码
	 * @return 返回登录页面
	 */
	public String doPwd() {
		// 当页面上传过来的密码赋值给pwd
		String pwd=cmUser.getAccountPassword();
		String userId = cmUser.getUserId();
		if(userId != null && !userId.equals("") && !userId.equals("undefined")){
			cmUser = cmUserBS.findById(CmUser.class, userId);
		} else {
			cmUser=(CmUser)ActionContext.getContext().getSession().get("user");
			if(cmUser==null){
				setMyError("链接超时或遇系统错误,请重新登录");
				setSuccess(false);
				return "json";
				
			}
		}
		try {

			
		// 查询密码历史表  by lujie
		List<CmUserPasswordHistory> listTbUserPasswordHistory = cmUserPasswordHistoryBS
				.findPageByQuery(new SysPageInfo(0,6), 
					"from CmUserPasswordHistory where 1=1 and cmUser.userId='"+cmUser.getUserId()+"' order by usedate desc ", 
					null, "");
			if(!listTbUserPasswordHistory.isEmpty()){
				for (int i = 0; i < listTbUserPasswordHistory.size(); i++) {
					//if(listTbUserPasswordHistory.get(i).getPassword().length()>30)continue;
					if(pwd.equals(listTbUserPasswordHistory.get(i).getPassword())){					
						setSuccess(false);
						setMyError("请不要使用最近更改的2次密码");
						return "json";
					}
				}
				
				String md5Val = Md5.getInstance().EncoderByMd5(pwd, "");
				cmUser.setAccountPassword(md5Val);
				cmUserPasswordHistory=new CmUserPasswordHistory();
				cmUserPasswordHistory.setPassword(pwd);
				cmUserPasswordHistory.setCmUser(cmUser);
				cmUserPasswordHistory.setUsedate(new Date());		
				cmUserBS.saveTbUserPwdAndUpdateTbUser(cmUserPasswordHistory, cmUser);
				
				
				cmUser.setAccountPassword(Md5.getInstance().EncoderByMd5(pwd, ""));
				cmUserPasswordHistory=new CmUserPasswordHistory();
				cmUserPasswordHistory.setPassword(cmUser.getAccountPassword());
				cmUserPasswordHistory.setCmUser(cmUser);
				cmUserPasswordHistory.setUsedate(new Date());		
				cmUserBS.saveTbUserPwdAndUpdateTbUser(cmUserPasswordHistory, cmUser);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setSuccess(true);
		if(enter.equals("user")){
			setCloseFlag("userlist");
		}
		return "json";
	}
	
	
	
	@JSON(serialize=false)
	public List<CmUser> getListUser() {
		return listUser;
	}

	public void setListUser(List<CmUser> listUser) {
		this.listUser = listUser;
	}
	@JSON(serialize=false)
	public CmUser getCmUser() {
		return cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}
	@JSON(serialize=false)
	public List<SysOrganization> getListSysOrganization() {
		return sysOrganizationList;
	}

	public void setListSysOrganization(List<SysOrganization> sysOrganizationList) {
		this.sysOrganizationList = sysOrganizationList;
	}

	@Resource
	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}
	
	@Resource
	public void setSysOrganizationBS(ISysOrganizationBS sysOrganizationBS) {
		this.sysOrganizationBS = sysOrganizationBS;
	}
	@Resource
	public void setCmUserPasswordHistoryBS(
			ICmUserPasswordHistoryBS cmUserPasswordHistoryBS) {
		this.cmUserPasswordHistoryBS = cmUserPasswordHistoryBS;
	}
	@JSON(serialize=false)
	public CmUserPasswordHistory getTbUserPasswordHistory() {
		return cmUserPasswordHistory;
	}

	public void setTbUserPasswordHistory(CmUserPasswordHistory cmUserPasswordHistory) {
		this.cmUserPasswordHistory = cmUserPasswordHistory;
	}
	@JSON(serialize=false)
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public final SysCountry getSysCountry() {
		return sysCountry;
	}

	public final void setSysCountry(SysCountry sysCountry) {
		this.sysCountry = sysCountry;
	}
	@JSON(serialize=false)
	public final List<SysCountry> getListSysCountry() {
		return listSysCountry;
	}

	public final void setListSysCountry(List<SysCountry> listSysCountry) {
		this.listSysCountry = listSysCountry;
	}
	
	public final void setSysCountryBS(ISysCountryBS sysCountryBS) {
		this.sysCountryBS = sysCountryBS;
	}




	public final void setSysLoginInfoBS(ISysLoginInfoBS sysLoginInfoBS) {
		this.sysLoginInfoBS = sysLoginInfoBS;
	}
	@JSON(serialize=false)
	public final String getIds() {
		return ids;
	}

	public final void setIds(String ids) {
		this.ids = ids;
	}
	@JSON(serialize=false)
	public final SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}

	public final void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}
	@JSON(serialize=false)
	public final SysOrderByInfo getSysOrderByInfo() {
		return sysOrderByInfo;
	}

	public final void setSysOrderByInfo(SysOrderByInfo sysOrderByInfo) {
		this.sysOrderByInfo = sysOrderByInfo;
	}
	@JSON(serialize=false)
	public final HashMap<String, String> getQuery() {
		return query;
	}


	public List<SysSystem> getSysSystemList() {
		return sysSystemList;
	}
	public void setSysSystemList(List<SysSystem> sysSystemList) {
		this.sysSystemList = sysSystemList;
	}
	public final String getMyError() {
		return myError;
	}

	public final void setMyError(String myError) {
		this.myError = myError;
	}


	public final void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	@JSON(serialize=false)
	public final HashMap<String, String> getSearch() {
		return search;
	}

	public final void setSearch(HashMap<String, String> search) {
		this.search = search;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getOrderBlock() {
		return orderBlock;
	}

	public void setOrderBlock(String orderBlock) {
		this.orderBlock = orderBlock;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public int getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(int orderMethod) {
		this.orderMethod = orderMethod;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public final SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public final void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	public final void setSysUserOrgRelationBS(
			ISysUserOrgRelationBS sysUserOrgRelationBS) {
		this.sysUserOrgRelationBS = sysUserOrgRelationBS;
	}

	public final List<SysOrganization> getSysOrganizationList() {
		return sysOrganizationList;
	}

	public final void setSysOrganizationList(
			List<SysOrganization> sysOrganizationList) {
		this.sysOrganizationList = sysOrganizationList;
	}

	public final String getOrgFunIds() {
		return orgFunIds;
	}

	public final void setOrgFunIds(String orgFunIds) {
		this.orgFunIds = orgFunIds;
	}

	public final String getOrgFunNames() {
		return orgFunNames;
	}

	public final void setOrgFunNames(String orgFunNames) {
		this.orgFunNames = orgFunNames;
	}

	public final List<SysUserOrgRelation> getSysUserOrgRelationList() {
		return sysUserOrgRelationList;
	}

	public final void setSysUserOrgRelationList(
			List<SysUserOrgRelation> sysUserOrgRelationList) {
		this.sysUserOrgRelationList = sysUserOrgRelationList;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@JSON(serialize=false)
	public String getEnter() {
		return enter;
	}
	public void setEnter(String enter) {
		if(enter == null) enter = "";
		this.enter = enter;
	}
	public String getCloseFlag() {
		return closeFlag;
	}
	public void setCloseFlag(String closeFlag) {
		if(closeFlag == null) closeFlag = "";
		this.closeFlag = closeFlag;
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

		public Boolean returnNumber(int i){
		
			
			return false;
		}
}

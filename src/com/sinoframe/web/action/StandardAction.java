package com.sinoframe.web.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Md5;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysRole;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.business.ISysBackLogMessageBS;
import com.sinoframe.common.util.Util;
import com.sms.training.qualification.bean.AhLogdate;
import com.sms.training.qualification.bean.AhAdmin;
import com.sms.training.qualification.bean.AhMapping;
import com.sms.training.qualification.bean.AhPeruser;
import com.sms.training.qualification.bean.AhSystem;
import com.sms.training.qualification.bean.AhUser;
import com.sms.training.qualification.bean.Ahdetail;
import com.sms.training.qualification.business.IAhUserBS;

@ParentPackage("sinoframe-default")
	@Results( { @Result(location = "/standard/index.jsp"),
	@Result(name = "index", location = "/standard/main.jsp"),
	@Result(name = "qualPilotList", location = "/sms/training/qualification/conditions/qualPilotList.jsp"),
	@Result(name = "toAllotAuthority", location = "/standard/allotAuthority.jsp"),
	@Result(name = "toadd", location = "/standard/addAuthorit.jsp"),
	@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
	@Result(name = "allotRole", location = "/standard/allotRole.jsp"),
	@Result(name = "addAllotRole", location = "/standard/addAllotRole.jsp"),
	@Result(name = "updateLoginAndPsd", location = "/standard/updateLogin.jsp"),
	@Result(name = "json", type = "json"),
	@Result(name = "ahrbacList", location = "/standard/ahrbacList.jsp"),
	@Result(name = "admin", location = "/standard/admin.jsp")})
public class StandardAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Long messageCount;

	private boolean check4zz;
	// 用于存储查询条件的字符串形式
	private String strQuery;
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	private IAhUserBS ahUserBS;
	//用户列表
	private List<AhUser> ahuserList;
	private CmUser cmUser;
	//存放某个人可以看哪几个子系统字段
	String clientSystemName="";
	//存放 自定义bean的list
	List<Ahdetail> Ahdetaillist = new ArrayList<Ahdetail>();
	//用户类型变量
	private String usertype;
	//用户名
	private String username;
	
	// 当前模块名称
	private static String moduleName = "allotAuthorityTab";

	// Message对象
	private Message message;
	// 页码
	private int pageNum = 1;
	// 每页显示条数
	private int numPerPage = 20;
	//ahuser对象
	private AhUser ahuser;
	//mappinglist
	private List<AhMapping> ahMappingList;
	//system list
	private List<AhSystem> ahSystemList;
	//系统id
	private String sysid;
	//映射表 实体bean
	private AhMapping anMapping;
	//系统表 实体bean
	private AhSystem ahSystem;
	//是否可以查看管理系统的标志位
	private boolean ifSeeSystemFlag;
	//判断页面显示“有效”还是“无效”的标志位
	private String mflag;
	//权限表list
	private List<AhAdmin> ahAdminList;
	//权限表
	private AhAdmin ahAdmin;
	
	private List<AhAdmin> ahrbacList;
	
	//查询条件 姓名
	private String name;
	
	private AhLogdate ahLogdate;
	ICmUserBS cmUserBS;
	HttpServletResponse response = ServletActionContext.getResponse();
	
	//peruser
	private AhPeruser ahPeruser;

	private boolean rizhiflag;
	private boolean hexinflag;
	
	public boolean isRizhiflag() {
		return rizhiflag;
	}

	public boolean isHexinflag() {
		return hexinflag;
	}

	public String execute() {
		try {
			ahAdminList = this.ahUserBS.ifCanSee(cmUser.getLoginName());
			if(ahAdminList.size()>0){
				for (AhAdmin ahAdmin : ahAdminList) {
					if("05".equals(ahAdmin.getAhSystem().getSysid()) || "00".equals(ahAdmin.getAhSystem().getSysid())){
						rizhiflag=true;
					}else if("06".equals(ahAdmin.getAhSystem().getSysid()) || "00".equals(ahAdmin.getAhSystem().getSysid())){
						hexinflag=true;
					}
				}
			}
			
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		return "success";
	}
	
	public String index() {
		 return "index";
	}
	 
	
	/**
	 * 跳转到十六个系统页面
	 * @return
	 */
	public String validat() throws IllegalArgumentException, IllegalAccessException{
		//判断这个人可以看哪些子系统
		try {
			//首先判断这个人是不是调离人员，如果是的话，则显示固定的几个子系统，如果不是则，查询这个人能看几个子系统
//			if(ahUserBS.ifLeave(cmUser.getLoginName(),"change")){
//				
//			}else{
				List<Object> clientSystemlist = ahUserBS.findClinteSystemByLoginname(cmUser.getLoginName(),"");
				for (int i = 0; i < clientSystemlist.size(); i++) {
					Ahdetail ahdetail = new Ahdetail();
					Object object = clientSystemlist.get(i);
					Object[] obj = (Object[] )object;
					ahdetail.setSysname(String.valueOf(obj[obj.length-10]));
					ahdetail.setSysurl(String.valueOf(obj[obj.length-9]));
					ahdetail.setLogin(String.valueOf(obj[obj.length-8]));
					ahdetail.setPlaincode(String.valueOf(obj[obj.length-7]));
					ahdetail.setPassword(String.valueOf(obj[obj.length-6]));
					
					Ahdetaillist.add(ahdetail);
				}
				for (Ahdetail ahdetails : Ahdetaillist) {
					clientSystemName +=ahdetails.getSysname()+",";
					if(ahdetails.getSysname().contains("人力资源")){
						sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
						String psncode = encoder.encode(ahdetails.getLogin().getBytes());
						ahdetails.setPassword(psncode);
					}
				}
				//管理系统另外单写一个方法  因为管理系统下又分为多个子系统
				//判断ah_admin表，只要这个人在这个表中有数据，那么就表示这个人可以查看管理系统
				ifSeeSystemFlag = this.ahUserBS.ifSeeAdminSystem(cmUser.getLoginName());
				System.out.println(clientSystemName);
				Cookie cookie = new Cookie("cookie_user", cmUser.getLoginName()); 
				System.out.println(cookie.getName());
				cookie.setMaxAge(60*60*24*30); //cookie 保存30天  
			    response.addCookie(cookie); 
//			}
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		return "admin";
	}
	
	/**
	 * 查询出所有列表
	 */	
	public String findUserList(){
		try {
			StringBuffer bufCount;
			StringBuffer hqlBuf;
			if(sysid!= null && !"".equals(sysid)){
				bufCount = new StringBuffer("select count(*) from AhUser u,AhMapping p, AhSystem s where u.useruuid = p.ahUser.useruuid and p.ahSystem.sysid = s.sysid ");
				bufCount.append("and s.sysid='").append(sysid.trim()).append("'");
				hqlBuf = new StringBuffer("select u from AhUser u,AhMapping p, AhSystem s where u.useruuid = p.ahUser.useruuid and p.ahSystem.sysid = s.sysid ");
				hqlBuf.append("and s.sysid='").append(sysid.trim()).append("'");
			}else{
				bufCount = new StringBuffer("select count(*) from AhUser u where 1=1 ");
				hqlBuf = new StringBuffer("from AhUser u  where 1=1 ");
			}
			
			if(usertype != null && !"".equals(usertype)){
				bufCount.append("and u.usertype='" ).append(usertype.trim()).append("'");
				hqlBuf.append("and u.usertype='").append(usertype.trim()).append("'");
			}
			if(username!= null && !"".equals(username)){
				bufCount.append("and u.username like '%").append(username.trim()).append("%'");
				hqlBuf.append("and u.username like '%").append(username.trim()).append("%'");
			}
			if(name!= null && !"".equals(name)){
				bufCount.append("and u.name like '%").append(name.trim()).append("%'");
				hqlBuf.append("and u.name like '%").append(name.trim()).append("%'");
			}
			// 设置最大条数
			this.getSysPageInfo().setMaxCount(this.ahUserBS.getCountByHQL(bufCount.toString(), Util.decodeQuery(query)));
			
			// user 列表
			ahuserList = ahUserBS.findPageByQuery(this.getSysPageInfo(), hqlBuf.toString(), Util.decodeQuery(query), "");
			//系统列表
			ahSystemList = ahUserBS.findAllSystem();
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
	return "qualPilotList";
}
	
	
	/**
	 * 跳转到分配权限页面
	 */
	public String toAllotAuthority(){
		try {
			
			//查找这个人可以查看哪些子系统
			ahuser = ahUserBS.findById(AhUser.class, ahuser.getUseruuid());
			List<Object> clientSystemlist = ahUserBS.findClinteSystemByLoginname(ahuser.getUsername(),mflag);
			for (int i = 0; i < clientSystemlist.size(); i++) {
				Ahdetail ahdetail = new Ahdetail();
				Object object = clientSystemlist.get(i);
				Object[] obj = (Object[] )object;
				ahdetail.setSysname(String.valueOf(obj[obj.length-10]));
				ahdetail.setSysurl(String.valueOf(obj[obj.length-9]));
				ahdetail.setLogin(String.valueOf(obj[obj.length-8]));
				ahdetail.setPlaincode(String.valueOf(obj[obj.length-7]));
				ahdetail.setPassword(String.valueOf(obj[obj.length-6]));
				ahdetail.setUsername(String.valueOf(obj[obj.length-5]));
				ahdetail.setHrid(String.valueOf(obj[obj.length-4]));
				ahdetail.setOldcode(String.valueOf(obj[obj.length-3]));
				ahdetail.setSysid(String.valueOf(obj[obj.length-2]));
				ahdetail.setMflag(String.valueOf(obj[obj.length-1]));
				
				Ahdetaillist.add(ahdetail);
			}
			
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		return "toAllotAuthority";
	}
	
//	/**
//	 * 使已有系统权限失效
//	 */
//	public String toNullity(){
//		try {
//		    String uuid = ahuser.getUseruuid();
//			ahMappingList = ahUserBS.findSystemByUserid(ahuser.getUseruuid(), sysid);
//			if(ahMappingList.size() != 0){
//				anMapping = ahMappingList.get(0);
//			}
//			//获取管理系统信息
//			List<AhSystem> syslist=cmUserBS.findBySingleQuery("AhSystem", "sysid", "00");
//			if(syslist!=null && syslist.size()!=0){
//				ahSystem=syslist.get(0);
//			}
//			//获取当前用户信息
//			List<AhUser> ahUserlist=cmUserBS.findBySingleQuery("AhUser", "username", username);
//			if(ahUserlist!=null && ahUserlist.size()!=0){
//				ahuser=ahUserlist.get(0);
//			}
//			
//			ahLogdate = new AhLogdate();
//			ahLogdate.setAnUser(ahuser);
//			ahLogdate.setAhSystem(ahSystem);
//			ahLogdate.setOptime(DateTool.getNow());
//			HttpServletRequest request = ServletActionContext.getRequest();
//			ahLogdate.setMicip(request.getRemoteAddr());
//			//ahLogdate.setMicip(InetAddress.getLocalHost().getHostAddress());
//			ahLogdate.setSysname(ahSystem.getSysname());
//			ahLogdate.setUsername(ahuser.getUsername());
//			//获取操作系统信息
//			List<AhSystem> syslist2=cmUserBS.findBySingleQuery("AhSystem", "sysid",sysid);
//			if(syslist2!=null && syslist2.size()!=0){
//				ahSystem=syslist2.get(0);
//			}
//			//获取操作用户信息
//			List<AhUser> ahUserlist2=cmUserBS.findBySingleQuery("AhUser", "useruuid", uuid);
//			if(ahUserlist2!=null && ahUserlist2.size()!=0){
//				ahuser=ahUserlist2.get(0);
//			}
//			if(mflag !=null && !"".equals(mflag)&& "N".equals(mflag)){
//				//无效
//				anMapping.setMflag("0");
//				ahLogdate.setOpname("设置"+ahuser.getName()+"访问"+ahSystem.getSysname()+"权限无效");
//				
//			}else{
//				//有效
//				anMapping.setMflag("1");
//				ahLogdate.setOpname("设置"+ahuser.getName()+"访问"+ahSystem.getSysname()+"权限有效");
//			}
//			cmUserBS.saveOrUpdate(ahLogdate);
//			this.ahUserBS.saveOrUpdate(anMapping);
//			this.message = this.getSuccessMessage("操作成功", "allotAuthority", "","");
//			
//		} catch (Exception e) {
//			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
//			e.printStackTrace();
//			this.message = this.getFailMessage("操作失败");
//		}
//		
//		return "SUCCESS";
//	}
	

	
	/**
	 * 使已有系统权限失效
	 */
	public String toNullity(){
		try {
			    String uuid = ahuser.getUseruuid();
				ahMappingList = ahUserBS.findSystemByUserid(ahuser.getUseruuid(), sysid);
				//如果是一条记录的话，可以直接修改这条数据的mflag字段，如果是多条数据的话，则需要把所有数据都修改mflag字段
				if(ahMappingList.size() == 1){
					anMapping = ahMappingList.get(0);
				
				//获取管理系统信息
				List<AhSystem> syslist=cmUserBS.findBySingleQuery("AhSystem", "sysid", "00");
				if(syslist!=null && syslist.size()!=0){
					ahSystem=syslist.get(0);
				}
				//获取当前用户信息
				List<AhUser> ahUserlist=cmUserBS.findBySingleQuery("AhUser", "username", username);
				if(ahUserlist!=null && ahUserlist.size()!=0){
					ahuser=ahUserlist.get(0);
				}
				
				ahLogdate = new AhLogdate();
				ahLogdate.setAnUser(ahuser);
				ahLogdate.setAhSystem(ahSystem);
				ahLogdate.setOptime(DateTool.getNow());
				HttpServletRequest request = ServletActionContext.getRequest();
				ahLogdate.setMicip(request.getRemoteAddr());
				//ahLogdate.setMicip(InetAddress.getLocalHost().getHostAddress());
				ahLogdate.setSysname(ahSystem.getSysname());
				ahLogdate.setUsername(ahuser.getUsername());
				//获取操作系统信息
				List<AhSystem> syslist2=cmUserBS.findBySingleQuery("AhSystem", "sysid",sysid);
				if(syslist2!=null && syslist2.size()!=0){
					ahSystem=syslist2.get(0);
				}
				//获取操作用户信息
				List<AhUser> ahUserlist2=cmUserBS.findBySingleQuery("AhUser", "useruuid", uuid);
				if(ahUserlist2!=null && ahUserlist2.size()!=0){
					ahuser=ahUserlist2.get(0);
				}
				if(mflag !=null && !"".equals(mflag)&& "N".equals(mflag)){
					//无效
					anMapping.setMflag("0");
					ahLogdate.setOpname("设置"+ahuser.getName()+"访问"+ahSystem.getSysname()+"权限无效");
					
				}else{
					//有效
					anMapping.setMflag("1");
					ahLogdate.setOpname("设置"+ahuser.getName()+"访问"+ahSystem.getSysname()+"权限有效");
				}
				cmUserBS.saveOrUpdate(ahLogdate);
				this.ahUserBS.saveOrUpdate(anMapping);
			}else if(ahMappingList.size() != 0){
				if(mflag !=null && !"".equals(mflag)&& "N".equals(mflag)){
					for (AhMapping anMapping : ahMappingList) {
						anMapping.setMflag("0");
						this.ahUserBS.saveOrUpdate(anMapping);
					}
				}else{
					for (AhMapping anMapping : ahMappingList) {
						anMapping.setMflag("1");
						this.ahUserBS.saveOrUpdate(anMapping);
					}
				}
			}

			this.message = this.getSuccessMessage("操作成功", "allotAuthority", "","");
			
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		
		return "SUCCESS";
	}
	
	/**
	 * 前台页面    手动修改子系统用户名和密码
	 */
	public String tomodifyLogin(){
		try {
			if(!"".equals(sysid) && sysid !=null &&ahuser!=null){
				ahPeruser = this.ahUserBS.findPuser(sysid,ahuser.getUseruuid());
			}
			
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		
		return "updateLoginAndPsd";
	}
	
	/**
	 * 保存修改的用户名和密码
	 */
	private AhPeruser ahperuser;
	public String doSaveUserMsg(){
		try {
			if(ahPeruser !=null){
				ahperuser = this.ahUserBS.findById(AhPeruser.class, ahPeruser.getPeruuid());
			}
			ahperuser.setLogin(ahPeruser.getLogin());
			ahperuser.setPassword(ahPeruser.getPassword());
			this.ahUserBS.saveOrUpdate(ahperuser);
			
			this.message = this.getSuccessMessage("保存成功", "", "","");
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("保存失败");
		}
		
		return "SUCCESS";
	}

	
	/**
	 * 跳转到添加页面
	 */
	public String toAddPage(){
		//查询出所有没有映射的系统
		try {
			ahuser = this.ahUserBS.findById(AhUser.class, ahuser.getUseruuid());
			ahSystemList = this.ahUserBS.findAllSystemByUserid(ahuser.getUseruuid(),username);
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		
		return "toadd";
	}
	
	
	/**
	 * 保存分配权限的方法
	 */
	public String doSave(){
		try {
			String initPassword = "123456";
			
			//先在peruser表中加数据，然后再在mapping表中配映射
			String[] sysids = sysid.split(",");
			//先查出当前user对象
			ahuser = this.ahUserBS.findById(AhUser.class, ahuser.getUseruuid());
			String uuid = ahuser.getUseruuid();
			for (int i = 0; i < sysids.length; i++) {
				ahSystem = this.ahUserBS.findById(AhSystem.class, sysids[i].trim());
				AhPeruser ahPeruser = new AhPeruser();
				ahPeruser.setPlaincode(initPassword);
				ahPeruser.setLogin(ahuser.getOldcode());
				ahPeruser.setName(ahuser.getName());
				ahPeruser.setUnitname(ahuser.getUnitname());
				ahPeruser.setIdcard(ahuser.getIdcard());
				ahPeruser.setDeptname(ahuser.getDeptname());
				ahPeruser.setPassword(Md5.getInstance().EncoderByMd5(initPassword, ""));
				ahPeruser.setAhSystem(ahSystem);
				this.ahUserBS.saveOrUpdate(ahPeruser);
				System.out.println(ahPeruser.getPeruuid());
				
				AhMapping ahMapping = new AhMapping();
				ahMapping.setAhPeruser(ahPeruser);
				ahMapping.setAhUser(ahuser);
				ahMapping.setMflag("1");
				ahMapping.setAhSystem(ahSystem);
				this.ahUserBS.saveOrUpdate(ahMapping);
				System.out.println(ahMapping.getUuid());
				//保存日志信息
				ahLogdate = new AhLogdate();
				//获取当前用户信息
				List<AhUser> ahUserlist=cmUserBS.findBySingleQuery("AhUser", "username", username);
				if(ahUserlist!=null && ahUserlist.size()!=0){
					ahuser=ahUserlist.get(0);
				}
				//获取管理系统信息
				List<AhSystem> syslist=cmUserBS.findBySingleQuery("AhSystem", "sysid", "00");
				if(syslist!=null && syslist.size()!=0){
					ahSystem=syslist.get(0);
				}
				ahLogdate.setAnUser(ahuser);
				ahLogdate.setAhSystem(ahSystem);
				ahLogdate.setOptime(DateTool.getNow());
				HttpServletRequest request = ServletActionContext.getRequest();
				ahLogdate.setMicip(request.getRemoteAddr());
				//ahLogdate.setMicip(InetAddress.getLocalHost().getHostAddress());
				ahLogdate.setSysname(ahSystem.getSysname());
				ahLogdate.setUsername(ahuser.getUsername());
				//获取操作系统信息
				List<AhSystem> syslist2=cmUserBS.findBySingleQuery("AhSystem", "sysid",sysids[i].trim());
				if(syslist2!=null && syslist2.size()!=0){
					ahSystem=syslist2.get(0);
				}
				//获取操作用户信息
				List<AhUser> ahUserlist2=cmUserBS.findBySingleQuery("AhUser", "useruuid", uuid);
				if(ahUserlist2!=null && ahUserlist2.size()!=0){
					ahuser=ahUserlist2.get(0);
				}
				ahLogdate.setOpname(ahuser.getName()+"获得访问"+ahSystem.getSysname()+"权限");
				cmUserBS.saveOrUpdate(ahLogdate);
			}
			this.message = this.getSuccessMessage("操作成功", "allotAuthority", "closeCurrent", "");
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		
		return "SUCCESS";
	}
	
	/**
	 * 分配角色
	 */
	public String toAllotRole(){
		try {
			ahAdminList = this.ahUserBS.findSystemListByUserid(ahuser.getUseruuid());
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		
		return "allotRole";
	}
	
	/**
	 * 保存分配的角色
	 */
	public String doSaveRole(){
		try {
			//先查出当前user对象
			ahuser = this.ahUserBS.findById(AhUser.class, ahuser.getUseruuid());
			String uuid = ahuser.getUseruuid();
			String[] sysids = sysid.split(",");
			for (String str : sysids) {
				ahSystem = this.ahUserBS.findById(AhSystem.class, str.trim());
				AhAdmin ahAdmin = new AhAdmin();
				ahAdmin.setAhUser(ahuser);
				ahAdmin.setAhSystem(ahSystem);
				this.ahUserBS.saveOrUpdate(ahAdmin);
				//保存日志信息
				ahLogdate = new AhLogdate();
				//获取当前用户信息
				List<AhUser> ahUserlist=cmUserBS.findBySingleQuery("AhUser", "username", username);
				if(ahUserlist!=null && ahUserlist.size()!=0){
					ahuser=ahUserlist.get(0);
				}
				//获取管理系统信息
				List<AhSystem> syslist=cmUserBS.findBySingleQuery("AhSystem", "sysid", "00");
				if(syslist!=null && syslist.size()!=0){
					ahSystem=syslist.get(0);
				}
				ahLogdate.setAnUser(ahuser);
				ahLogdate.setAhSystem(ahSystem);
				ahLogdate.setOptime(DateTool.getNow());
				HttpServletRequest request = ServletActionContext.getRequest();
				ahLogdate.setMicip(request.getRemoteAddr());
				//ahLogdate.setMicip(InetAddress.getLocalHost().getHostAddress());
				ahLogdate.setSysname(ahSystem.getSysname());
				ahLogdate.setUsername(ahuser.getUsername());
				//获取操作系统信息
				List<AhSystem> syslist2=cmUserBS.findBySingleQuery("AhSystem", "sysid",str);
				if(syslist2!=null && syslist2.size()!=0){
					ahSystem=syslist2.get(0);
				}
				//获取操作用户信息
				List<AhUser> ahUserlist2=cmUserBS.findBySingleQuery("AhUser", "useruuid", uuid);
				if(ahUserlist2!=null && ahUserlist2.size()!=0){
					ahuser=ahUserlist2.get(0);
				}
				ahLogdate.setOpname(ahuser.getName()+"获得"+ahSystem.getSysname()+"管理员角色");
				cmUserBS.saveOrUpdate(ahLogdate);
			}
			this.message = this.getSuccessMessage("操作成功", "allotRole", "closeCurrent", "");
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		
		return "SUCCESS";
	}
	
	/**
	 * 删除角色
	 */
	public String doDeleteRole(){
		try {
			String uuid = ahAdmin.getAhUser().getUseruuid();
			String sysid = ahAdmin.getAhSystem().getSysid();
			this.ahUserBS.deleteById(AhAdmin.class, ahAdmin.getUuid());
			//保存日志信息
			ahLogdate = new AhLogdate();
			//获取当前用户信息
			List<AhUser> ahUserlist=cmUserBS.findBySingleQuery("AhUser", "username", username);
			if(ahUserlist!=null && ahUserlist.size()!=0){
				ahuser=ahUserlist.get(0);
			}
			//获取管理系统信息
			List<AhSystem> syslist=cmUserBS.findBySingleQuery("AhSystem", "sysid", "00");
			if(syslist!=null && syslist.size()!=0){
				ahSystem=syslist.get(0);
			}
			ahLogdate.setAnUser(ahuser);
			ahLogdate.setAhSystem(ahSystem);
			ahLogdate.setOptime(DateTool.getNow());
			HttpServletRequest request = ServletActionContext.getRequest();
			ahLogdate.setMicip(request.getRemoteAddr());
			//ahLogdate.setMicip(InetAddress.getLocalHost().getHostAddress());
			ahLogdate.setSysname(ahSystem.getSysname());
			ahLogdate.setUsername(ahuser.getUsername());
			//获取操作系统信息
			List<AhSystem> syslist2=cmUserBS.findBySingleQuery("AhSystem", "sysid",sysid);
			if(syslist2!=null && syslist2.size()!=0){
				ahSystem=syslist2.get(0);
			}
			//获取操作用户信息
			List<AhUser> ahUserlist2=cmUserBS.findBySingleQuery("AhUser", "useruuid", uuid);
			if(ahUserlist2!=null && ahUserlist2.size()!=0){
				ahuser=ahUserlist2.get(0);
			}
			ahLogdate.setOpname("删除"+ahuser.getName()+ahSystem.getSysname()+"管理员角色");
			cmUserBS.saveOrUpdate(ahLogdate);
			this.message = this.getSuccessMessage("操作成功", "allotRole", "", "");
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		return "SUCCESS";
	}
	
	/**
	 * 跳转到增加角色页面
	 */
	public String toAddRole(){
		try {
			ahuser = this.ahUserBS.findById(AhUser.class, ahuser.getUseruuid());
			ahSystemList = this.ahUserBS.findUnRoleSystemById(ahuser.getUseruuid());
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		return "addAllotRole";
	}
	
	/**
	 * 查询日志系统日志记录
	 */
	private String sysname;
	
	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String findRizhiJilu(){
		try {
			StringBuffer bufCount;
			StringBuffer hqlBuf;
			if(sysname!= null && !"".equals(sysname)){
				bufCount = new StringBuffer("select count(*) from AhRbac u where 1=1");
				bufCount.append("and u.sysname='").append(sysname.trim()).append("'");
				hqlBuf = new StringBuffer("from AhRbac u  where 1=1 ");
				hqlBuf.append("and u.sysname='").append(sysname.trim()).append("'");
			}else{
				bufCount = new StringBuffer("select count(*) from AhRbac u where 1=1 ");
				hqlBuf = new StringBuffer("from AhRbac u  where 1=1 ");
			}
			
			// 设置最大条数
			this.getSysPageInfo().setMaxCount(this.ahUserBS.getCountByHQL(bufCount.toString(), Util.decodeQuery(query)));
			
			// user 列表
			ahrbacList = ahUserBS.findPageByQuery(this.getSysPageInfo(), hqlBuf.toString(), Util.decodeQuery(query), "");
		} catch (Exception e) {
			ahUserBS.getErrorLog().info(e.getMessage() + "#" + moduleName);
			e.printStackTrace();
			this.message = this.getFailMessage("操作失败");
		}
		
		
		return "ahrbacList";
	}
	
	
	
	
	
	
	
	//=====================================================================
              
	/**
	 * 返回所有角色
	 * @return
	 */
	public List<SysRole> getUserRole() {
		List<SysRole> roleList = this.getRoleList();
		if (roleList != null && roleList.size() > 0) {
			return roleList;
		}
		return null;
	}

	/**
	 * 得到当前用户的一个单位
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public SysOrganization getUserOrg() {
		List orgList = this.getOrganizationList();
		SysOrganization sysOrganization = null;
		if (orgList != null && orgList.size() > 0) {
			sysOrganization = (SysOrganization) orgList.get(0);
			return sysOrganization;
		} else {
			return null;
		}
	}

	 

	public Long getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Long messageCount) {
		this.messageCount = messageCount;
	}

	public void setBackLogMessageBS(ISysBackLogMessageBS backLogMessageBS) {
	}

	@JSON(serialize = false)
	public boolean isCheck4zz() {
		return check4zz;
	}
	public void setCheck4zz(boolean check4zz) {
		this.check4zz = check4zz;
	}
	@JSON(serialize = false)
    public List<AhUser> getAhuserList() {
		return ahuserList;
	}

	public void setAhuserList(List<AhUser> ahuserList) {
		this.ahuserList = ahuserList;
	}

	@JSON(serialize = false)
	public IAhUserBS getAhUserBS() {
		return ahUserBS;
	}
	public void setAhUserBS(IAhUserBS ahUserBS) {
		this.ahUserBS = ahUserBS;
	}
	@JSON(serialize = false)
	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	@JSON(serialize = false)
	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	@JSON(serialize = false)
	public CmUser getCmUser() {
		return cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}
	@JSON(serialize = false)
	public String getClientSystemName() {
		return clientSystemName;
	}
	
	@JSON(serialize = false)
	public List<Ahdetail> getAhdetaillist() {
		return Ahdetaillist;
	}

	public void setAhdetaillist(List<Ahdetail> ahdetaillist) {
		Ahdetaillist = ahdetaillist;
	}
	@JSON(serialize = false)
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	@JSON(serialize = false)
	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	@JSON(serialize = false)
	public AhUser getAhuser() {
		return ahuser;
	}

	public void setAhuser(AhUser ahuser) {
		this.ahuser = ahuser;
	}
	@JSON(serialize = false)
	public List<AhMapping> getAhMappingList() {
		return ahMappingList;
	}

	public void setAhMappingList(List<AhMapping> ahMappingList) {
		this.ahMappingList = ahMappingList;
	}
	@JSON(serialize = false)
	public List<AhSystem> getAhSystemList() {
		return ahSystemList;
	}

	public void setAhSystemList(List<AhSystem> ahSystemList) {
		this.ahSystemList = ahSystemList;
	}
	@JSON(serialize = false)
	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	@JSON(serialize = false)
	public AhMapping getAnMapping() {
		return anMapping;
	}

	public void setAnMapping(AhMapping anMapping) {
		this.anMapping = anMapping;
	}
//	@JSON(serialize = false)
//	public String getOldcode() {
//		return oldcode;
//	}
//
//	public void setOldcode(String oldcode) {
//		this.oldcode = oldcode;
//	}
	@JSON(serialize = false)
	public AhSystem getAhSystem() {
		return ahSystem;
	}

	public void setAhSystem(AhSystem ahSystem) {
		this.ahSystem = ahSystem;
	}
	@JSON(serialize = false)
	public boolean isIfSeeSystemFlag() {
		return ifSeeSystemFlag;
	}
	@JSON(serialize = false)
	public void setMflag(String mflag) {
		this.mflag = mflag;
	}
	@JSON(serialize = false)
	public List<AhAdmin> getAhAdminList() {
		return ahAdminList;
	}

	public void setAhAdminList(List<AhAdmin> ahAdminList) {
		this.ahAdminList = ahAdminList;
	}
	@JSON(serialize = false)
	public AhAdmin getAhAdmin() {
		return ahAdmin;
	}

	public void setAhAdmin(AhAdmin ahAdmin) {
		this.ahAdmin = ahAdmin;
	}
	
	public void setAhLogdate(AhLogdate ahLogdate) {
		this.ahLogdate = ahLogdate;
	}

	public AhLogdate getAhLogdate() {
		return ahLogdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@JSON(serialize = false)
	public ICmUserBS getCmUserBS() {
		return cmUserBS;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JSON(serialize = false)
	public AhPeruser getAhPeruser() {
		return ahPeruser;
	}

	public void setAhPeruser(AhPeruser ahPeruser) {
		this.ahPeruser = ahPeruser;
	}

	@JSON(serialize = false)
	public List<AhAdmin> getAhrbacList() {
		return ahrbacList;
	}
	
	
}

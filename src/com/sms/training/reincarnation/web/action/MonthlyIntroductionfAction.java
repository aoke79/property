package com.sms.training.reincarnation.web.action;

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
import com.sms.training.reincarnation.bean.CaptainBalance;
import com.sms.training.reincarnation.bean.Cmodel;
import com.sms.training.reincarnation.bean.CmodelBig;
import com.sms.training.reincarnation.bean.CompanyInfo;
import com.sms.training.reincarnation.bean.FleetInfo;
import com.sms.training.reincarnation.bean.FleetInfos;
import com.sms.training.reincarnation.bean.MonthlyIntroduction;
import com.sms.training.reincarnation.bean.MonthlyIntroductionf;
import com.sms.training.reincarnation.bean.NewEmployees;
import com.sms.training.reincarnation.bean.OutputNewEmployees;
import com.sms.training.reincarnation.bean.RangeSettings;
import com.sms.training.reincarnation.bean.RetiredInfo;
import com.sms.training.reincarnation.bean.StageMonth;
import com.sms.training.reincarnation.bean.StageYears;
import com.sms.training.reincarnation.bean.TechnicalGrade;
import com.sms.training.reincarnation.bean.UpgradeEligibility;
import com.sms.training.reincarnation.bean.UpgradingPass;

@Controller
@ParentPackage("sinoframe-default")
@Results({
	@Result(name="toadd",type="redirectAction",location="cm-user!add.do"),
	@Result(name="doadd",location="/system/userInfo/userAdd.jsp"),
	@Result(name="toupdate",location="/system/userInfo/userEdit.jsp"),
	@Result(location="/standard/ajaxDone.jsp"),
	
	@Result(name="list",location="/sms/training/reincarnation/monthlyIntroduction2.jsp"),
	
	@Result(name="usersyslist",location="/system/userInfo/usersysbaoList.jsp"),
	@Result(name="add",location="/system/userInfo/userAdd.jsp"),
	@Result(name="exit",type="redirectAction",location="user-info/sys-login-info!exit.do"),
	@Result(name="toPwd",location="/system/userInfo/modifPwd.jsp"),
	@Result(name="personalInfoManage",location="/system/userInfo/personalInfoManage.jsp"),
	@Result(name="toPersonalInfoManag",type="redirectAction",location="cm-user!toPersonalInfoManage.do"),
	@Result(name = "test", location = "/system/userInfo/dtreeTest.jsp"),
	@Result(name="json",type="json")
})

/**
 * 描述：月度退出飞机计划 MO_UP_IN
 * @author LUJIE
 */
public class MonthlyIntroductionfAction extends BaseAction {
	
	
	List<MonthlyIntroductionf> monthlyIntroductionList = new ArrayList<MonthlyIntroductionf>();
	
	
	List<UpgradingPass> upgradingPassList = new ArrayList<UpgradingPass>();
	
	
	List<TechnicalGrade> technicalGradeList = new ArrayList<TechnicalGrade>();
  
	List<CompanyInfo>  companyList = new ArrayList<CompanyInfo>();
	
	List<Cmodel> cmodelList = new ArrayList<Cmodel>();
	
	
	List<UpgradeEligibility> upgradeEligibilityList = new ArrayList<UpgradeEligibility>();
	
	private List<CmodelBig> comodelBigList = new ArrayList<CmodelBig>();
	
	
	List<FleetInfo> fleetInfoList = new ArrayList<FleetInfo>();
	
	List<StageYears> stageYearsList = new ArrayList<StageYears>();
	List<StageMonth> montsList = new ArrayList<StageMonth>();
	
	
	List<RetiredInfo> retiredInfoList = new ArrayList<RetiredInfo>();
	
	private String deptid;
	
	private String typeid;
	
	private String degree;
	
	private String fjnum;
	
	//-----------------
	
	public String getFjnum() {
		return fjnum;
	}
	public List<StageYears> getStageYearsList() {
		return stageYearsList;
	}
	public void setStageYearsList(List<StageYears> stageYearsList) {
		this.stageYearsList = stageYearsList;
	}
	public List<RetiredInfo> getRetiredInfoList() {
		return retiredInfoList;
	}
	public List<MonthlyIntroductionf> getMonthlyIntroductionList() {
		return monthlyIntroductionList;
	}
	public void setMonthlyIntroductionList(
			List<MonthlyIntroductionf> monthlyIntroductionList) {
		this.monthlyIntroductionList = monthlyIntroductionList;
	}
	public List<StageMonth> getMontsList() {
		return montsList;
	}
	public void setMontsList(List<StageMonth> montsList) {
		this.montsList = montsList;
	}
	public void setRetiredInfoList(List<RetiredInfo> retiredInfoList) {
		this.retiredInfoList = retiredInfoList;
	}
	public void setFjnum(String fjnum) {
		this.fjnum = fjnum;
	}
	public String getDeptid() {
		return deptid;
	}
	public List<FleetInfo> getFleetInfoList() {
		return fleetInfoList;
	}
	public void setFleetInfoList(List<FleetInfo> fleetInfoList) {
		this.fleetInfoList = fleetInfoList;
	}
	public List<CmodelBig> getComodelBigList() {
		return comodelBigList;
	}
	public void setComodelBigList(List<CmodelBig> comodelBigList) {
		this.comodelBigList = comodelBigList;
	}
	public List<UpgradeEligibility> getUpgradeEligibilityList() {
		return upgradeEligibilityList;
	}
	public void setUpgradeEligibilityList(
			List<UpgradeEligibility> upgradeEligibilityList) {
		this.upgradeEligibilityList = upgradeEligibilityList;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}

	public List<UpgradingPass> getUpgradingPassList() {
		return upgradingPassList;
	}
	public void setUpgradingPassList(List<UpgradingPass> upgradingPassList) {
		this.upgradingPassList = upgradingPassList;
	}
	public List<TechnicalGrade> getTechnicalGradeList() {
		return technicalGradeList;
	}
	public void setTechnicalGradeList(List<TechnicalGrade> technicalGradeList) {
		this.technicalGradeList = technicalGradeList;
	}
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

	/** 设置ip段*/
	private String ipFirst;
	private String ipLast;
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
	
	private String enter;
	private String closeFlag;
	
	
	private Integer cs = 2;
	
	int hang = 20;
	
	private Integer ands = 0;
	
	public String toadd() {
		try{   
			//计算table的列数量
			String cb = " from StageMonth where 1=1 order by id ";
			this.montsList = this.cmUserBS.findPageByQuery(cb);
			if(this.montsList != null)cs += this.montsList.size();
			
			
			this.comodelBigList = this.cmUserBS.findPageByQuery(" from CmodelBig where 1=1 ");
			this.cmodelList = this.cmUserBS.findPageByQuery(" from Cmodel where 1=1 ");
			this.companyList = this.cmUserBS.findPageByQuery(" from CompanyInfo where 1=1 ");
			
			//过滤 不存在的数据
			String hqls = "from MonthlyIntroductionf where 1=1  order by  id ";//degree
			
			List<MonthlyIntroductionf> ss = this.cmUserBS.findPageByQuery(hqls);
			for(MonthlyIntroductionf o :  ss){
				
				for(StageMonth c : this.montsList){
					
					 if(o.getMonth().equals(c.getId())){
						 this.monthlyIntroductionList.add(o);
						 continue;
					 }
				}
				
			}
			
			 //getUserList();
			
		   //----
		    List<MonthlyIntroductionf> ous = new ArrayList<MonthlyIntroductionf>();
            String info = "";
		    for(MonthlyIntroductionf o : this.monthlyIntroductionList){

				  
				  
				  if(o.getDeptid() != null && !"".equals(o.getDeptid())){
					  CompanyInfo com = this.cmUserBS.findById(CompanyInfo.class, o.getDeptid());
					  if(com != null) o.setDeptName(com.getDeptname());
				  }
	
				  
				  if(o.getTypeid() != null && !"".equals(o.getTypeid())){
					  Cmodel c = this.cmUserBS.findById(Cmodel.class, o.getTypeid());
					  if(c != null){
						  o.setTypeName(c.getTypename());
						 // o.setTypeNameBig(cbc.getTypename());
						 // o.setTypeNameBigs(cbc.getTypename());
					  }  else{
						 o.setTypeName(info);
						 // o.setTypeNameBig(info);
						 // o.setTypeNameBigs(info);
					  } 
				  }

				  
				 ous.add(o);
			} 
		    
		    
		    
		    //多条数据进行合并
			List<MonthlyIntroductionf> ouc = ous;
			
		    for(MonthlyIntroductionf o : ous){
			    for(MonthlyIntroductionf o1 : ouc){
			    	//o1.getTypeNameBig()!=null && o.getTypeNameBig()!=null && 
			    	 if(o1.getDeptid()!= null && o.getDeptid() != null &&o1.getTypeid() != null && o.getMonth() != null && o.getTypeid() != null && o1.getMonth()!= null ){
			    		  int i = ouc.indexOf(o1);
			    		//  o1.getTypeNameBig().equals(o.getTypeNameBig()) &&
			    		   if(o.getDeptid().equals(o1.getDeptid()) &&  o1.getTypeid().equals(o.getTypeid()) ){
			    			   //if( i!=0 ){
			    				   String[] str = new String[]{o1.getMonth(),o1.getPlanein().toString()}; //码表和值
			    				   List<String[]> strList = o.getCompanyInfoList();
			    				   if(strList != null){
			    					   strList.add(str);
			    				   }
			    			  // }
			    		   }
			    		  ouc.set(i,o1);
			    	 }

			    }
		    }
		    
		    
		    //计算table所缺少多少列  然后补充
		    String s = "";
		    List<MonthlyIntroductionf> onew = new ArrayList<MonthlyIntroductionf>();
		    for(MonthlyIntroductionf os : ouc){
		    	if(s.indexOf(os.getTypeid()+os.getDeptid()) == -1){
                    
		    		if(this.montsList != null && this.montsList.size() !=0){
		    			int i = this.montsList.size()-os.getCompanyInfoList().size();
		    			
		    			for(int c=0;c<i;c++){

		    				os.getCompanyInfoList().add(new String[]{"0","0"});
		    			}
		    		}
		    		onew.add(os);
		    	}
		    	s += os.getTypeid()+os.getDeptid()+",";
		    }
		    
		    
		    //----
		   List<MonthlyIntroductionf>  oc = new ArrayList<MonthlyIntroductionf>();
		   for( MonthlyIntroductionf  opn : onew){
			 //  int  c = 0 ;
			  // for(String[] st : opn.getCompanyInfoList()){
				//   c += Integer.valueOf(st[1].toString());
			 //  } 
			  // opn.setHj(c);
			   oc.add(opn);
		   } 
		   
		   
		   //计算  table的 列坐标和行坐标  
		    
		 List<Integer[]> xy = new ArrayList<Integer[]>();
		  for( MonthlyIntroductionf o :  oc ){
			  
			  Integer[] cbc = new Integer[this.montsList.size()];
			  for(String[] sb : o.getCompanyInfoList()){
				 
				  for( StageMonth c : this.montsList) {
					  int i = this.montsList.indexOf(c);
					    if(sb[0].equals(c.getId())){
					    	cbc[i] = i;
					    	break;
					    }else{
					    	//cbc[i]= 1000; 
					    	//break;
					    }
				   }
			  }
			  xy.add(cbc);
		  } 
		  
		  
		  //  将 table 的列坐标 和行坐标 进行 交叉匹配
		  List<MonthlyIntroductionf> on = new ArrayList<MonthlyIntroductionf>();
		  for(int i=0;i<oc.size();i++){
			  
			  List<String[]> cc = new ArrayList<String[]>();
			  Integer[] intArry = xy.get(i);
			  List<String[]> strArray = oc.get(i).getCompanyInfoList();
			  
			  for(int c =0 ; c<intArry.length;c++){
				  String cba = "s" ;
				  if(intArry[c] != null && !intArry[c].toString().equals("null") && !intArry[c].toString().equals("")){
					
					  String sname = this.montsList.get(c).getId().toString();
					
					  for(int in =0 ;in<strArray.size();in++){
						  if(strArray.get(in)[0].trim().equals(sname)){
							  cba = in+"";
						  }
					 } 
					 if(!cba.equals("s")){
						 int bd = Integer.valueOf(cba);
						 String[] cbb = new String[]{strArray.get(bd)[0],strArray.get(bd)[1]};
						 cc.add(cbb);
					 }else{
						  String[] cbb = new String[]{"0","0"};
						  cc.add(cbb);
					 }
				  }else{
					  String[] cbb = new String[]{"0","0"};
					  cc.add(cbb);
				  }
				 
				  
			  }
			  
			  oc.get(i).setCompanyInfoListnew(cc);
			  
			  on.add(oc.get(i));
		  }
		  
		 this.monthlyIntroductionList = on;
		 
		 
		 //计算补加的行数
		 if(this.monthlyIntroductionList != null){
			  this.ands = this.hang-this.monthlyIntroductionList.size();
		 }

         //参数的过滤
		 String hql = " from RangeSettings ";
		 List<RangeSettings> rangeSettingsList = this.cmUserBS.findPageByQuery(hql);
		 
		 List<MonthlyIntroductionf> us = new ArrayList<MonthlyIntroductionf>();
		 if(rangeSettingsList != null && rangeSettingsList.size()!=0){
		 for(RangeSettings r : rangeSettingsList){
			 for(MonthlyIntroductionf u : this.monthlyIntroductionList){
				 CompanyInfo c = this.cmUserBS.findById(CompanyInfo.class,u.getDeptid());
				 Cmodel  cd = this.cmUserBS.findById(Cmodel.class,u.getTypeid());
				 
				 if(cd.getTypename().equals(r.getTypeid())  && c.getDeptname().equals(r.getDeptid())){
					 us.add(u);
				 }
			 }
		 }
		 this.monthlyIntroductionList  = us;
		     
		 }
			
	
         }catch(Exception e){
			e.printStackTrace();
		}
         
         

		return "list";
	}
	//为用户分配业务系统
	@SuppressWarnings("unchecked")
	public String usersyslist() {
		
		// 进行条件查询的语句
		String hql="from CmUser where 1=1  ";
		// 进行条数查询的语句
		String counthql="select count(*) from CmUser where 1=1 ";
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
		orderBlock = " year ";
		//获取排序字段
		sysOrderByInfo.setOrderColumn(orderBlock);		
		
		if (strQuery != null && !"".equals(strQuery)) {
			query = Util.toMap(strQuery);
		}else if(deleteFlag != null){
			query = Util.decodeQuery(query);
		}
		String extendHql = "";
		SysOrganization currentOrg = this.getUserOrg();
		extendHql = "and userId in (select t.id.userId from SysUserOrgRelation t where t.id.orgId ='"+currentOrg.getId()+"')";
		listUser = cmUserBS.findPageByQuery(sysPageInfo, hql, extendHql, query, sysOrderByInfo);
	
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
		return "usersyslist";
	}
	
	public String add() {
		 CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
         this.sysSystemList = getSystemList(cmUser);
		query.put("notin_state", "2");
		strQuery = Util.toStrQuery(query);
		listSysCountry=sysCountryBS.findAll();
		return "add";
	}	
	
	// 验证登录名是否重复。
	public String validateLoginName() {
		if(addValidateUser("loginName", cmUser.getLoginName(),cmUser.getUserId()).size()!=0){
			setMyError("登录名已有人在使用，请重新输入登录名");
			setSuccess(false);
			return "json";
		}
		setSuccess(true);
		return "json";
	}
	
	// 验证email是否重复。
	public String validateEmail() {
		if(addValidateUser("email", cmUser.getEmail(),cmUser.getUserId()).size()!=0){
			setMyError("该email已被人注册过了，请重新输入email");
			setSuccess(false);
			return "json";
		}
		setSuccess(true);
		return "json";
	}

	// 验证身份证是否正确。
	public boolean validateIp() {
		if(ipLast==null||ipLast.equals("")||ipLast.equals(ipFirst)){
			ipLast=null;
		}
		if((ipFirst==null||ipFirst.equals(""))&&(ipLast==null||ipLast.equals(""))){
			setMyError("请确认填写ip范围");
			return false;
		}
		if(ipFirst!=null&&!ipFirst.equals("")&&ipLast!=null&&!ipLast.equals("")&&ipFirst.compareTo(ipLast)>0){
			setMyError("请确认ip地址范围");
			return false;
		}
		
		boolean b=true;
		String ipFirsts[]=null;
		String ipLasts[]=null;
		if(ipFirst!=null&&ipFirst!=""){
			ipFirsts=ipFirst.split("\\.");
			if(ipFirsts.length!=4){
				setMyError("ip地址格式输入有误，请重新输入");
				return false;
			}
			for (int i = 0; i < ipFirsts.length; i++) {
				if (ipFirsts[i]==null||ipFirsts[i].equals("")) {
					setMyError("ip地址格式输入有误，请重新输入");
					b=false;
					break;	
				}
			}
		}
		if(ipLast!=null&&ipLast!=""){
			ipLasts=ipLast.split("\\.");
			if(ipLasts.length!=4){
				setMyError("ip地址格式输入有误，请重新输入");
				return false;
			}
			for (int i = 0; i < ipLasts.length; i++) {
				if(ipLasts[i]==null||ipLasts[i].equals("")){
					setMyError("ip地址格式输入有误，请重新输入");
					b=false;
					break;		
				}
				
			}
		}
		if(b)return true;
		else return false;
	}

	/**
	 * 公共方法，用来得到是否有相同的值 
	 * @param key  需要比较的值
	 * @param value 传进来的值
	 * @param userId 如果是更新的话，会有传的userId值，就是在查询除了自己以外的值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<CmUser> addValidateUser(String key,String value,String userId) {
		String condifition="";
		if(userId!=null){
			condifition= "and userId <> '"+userId+"'";
		}
		listUser=cmUserBS.findByAny(key,"'"+value+"'", condifition+" and state = '1'");
		return listUser;
	}
	
	public String doadd() {

		
		
		try {
			
			String hql = " delete MonthlyIntroductionf ";
			this.cmUserBS.executeUpdate(hql);
			
			
			// 起始页
			int  y = 1;
			if(this.typeid != null){
				
				//更具码表修改
				String cb = " from StageMonth where 1=1 order by id ";
				this.montsList = this.cmUserBS.findPageByQuery(cb);
					
			for(int i=0;i<typeid.split(",").length;i++){
				
				String deptid = this.deptid.split(",")[i].trim();
				
				
				
				String typeid = this.typeid.split(",")[i].trim();

				
				//String fjnum = this.fjnum.split(",")[i].trim();

				//行的长度
				int c = this.montsList.size();
				
				//将 值数据进行分页
				int a = this.degree.split(",").length/this.hang;
				
				int brk = this.degree.split(",").length;
				
				//获取数据的位置
				int start = (y-1)*a;
				
			    for(int cc = 0 ;cc<c ;cc++){
			    	if(brk <=(start+cc)){
			    		//break;
			    	}
			    	MonthlyIntroductionf upg = new MonthlyIntroductionf();
			    	
			    	upg.setDeptid(deptid);
			    	upg.setTypeid(typeid);
			    	
			    	if(this.degree.split(",")[start+cc].trim().length() ==0){
			    		upg.setPlanein(0);
			    		
			    	}else{
			    		upg.setPlanein(Integer.valueOf(this.degree.split(",")[start+cc].trim()));
			    	}
			    	    //获取匹配标识 进行存储
			    	    upg.setMonth(this.montsList.get(cc).getId());

			    	    
			    	
			    	//CompanyInfo cba = this.cmUserBS.findById(CompanyInfo.class, upg.getDeptid());
			    	//Cmodel cdd = this.cmUserBS.findById(Cmodel.class, upg.getTypeid());
			    	    if( upg.getTypeid() != null  && upg.getTypeid().length() !=0){
				    		
			    	    	String hqls =" from MonthlyIntroductionf where 1=1 and typeid='"+upg.getTypeid()+"' and deptid='"+upg.getDeptid()+"' order by id  ";
			    	    	List<MonthlyIntroductionf> ueb = this.cmUserBS.findPageByQuery(hqls);
			    	    	if(ueb != null && this.montsList != null){
			    	    		if(ueb.size() != this.montsList.size()){
			    	    			 this.cmUserBS.save(upg);
			    	    		
			    	    		}
			    	    	}
			    	    		
				    	  } 
			    }
				        y+=1 ;	
			}	
				
				

			
				
				
			}
			
			
			
			
			

			this.message = this.getSuccessMessage("保存成功! (单位或者机型没输入值 那行数据就不能保存.)", "tbuser", "forward", "monthly-introductionf!toadd.do");
		
		}catch (Exception e) {
			//设定失败消息
			this.message = this.getFailMessage("通过率输入了非数字 ,或者数值过大");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 根据页面上传进来的ipFirst 和 ipLast 进行ip字符串处理
	 * @param ipFirst
	 * @param ipLast
	 * @return   
	 */
	private String getIp(String ipFirst , String ipLast){
		// 得到ip地址
		String ip=null;
		if(ipLast==null||ipLast.equals("")||ipLast.equals(ipFirst)){
			ipLast=null;
		}
		
		String ipFirsts[]=null;
		String ipLasts[]=null;
		if(ipFirst!=null&&ipFirst!=""){
			ipFirsts=ipFirst.split("\\.");
			for (int i = 0; i < ipFirsts.length; i++) {
				if(ipFirsts[i].compareTo("255")>0){
					ipFirsts[i]="255";
				}
			}
			ipFirst=Arrays.toString(ipFirsts).replace("[", "").replace("]", "").replace(", ", ".");
		}
		if(ipLast!=null&&ipLast!=""){
			ipLasts=ipLast.split("\\.");
			for (int i = 0; i < ipLasts.length; i++) {
				if (ipLasts[i].compareTo("255")>0) {
					ipLasts[i]="255";
				}
				
			}
			ipLast=Arrays.toString(ipLasts).replace("[", "").replace("]", "").replace(", ", ".");
		}	
		if(ipLast!=null && !ipLast.equals("")&& !ipFirst.equals(ipLast))
			ip=ipFirst+","+ipLast;
		else
			ip=ipFirst;
		
		return ip;
	}
	
	
	@SuppressWarnings("unchecked")
	public String toupdate() {
		try{
			 CmUser cmUsers =  (CmUser)ActionContext.getContext().getSession().get("user");
             this.sysSystemList = getSystemList(cmUsers);
			query.put("notin_state", "2");
			strQuery = Util.toStrQuery(query);
			cmUser=cmUserBS.findById(cmUser.getUserId());
			sysLoginInfo=sysLoginInfoBS.findByAny("cmUser.userId", "'"+cmUser.getUserId()+"'","").isEmpty()?null:(SysLoginInfo)sysLoginInfoBS.findByAny("cmUser.userId", "'"+cmUser.getUserId()+"'","").get(0);
/*			if(sysLoginInfo!=null && sysLoginInfo.getIp()!=null){
				String arr[]=sysLoginInfo.getIp().split(",");
				if(arr.length==1) ipFirst=arr[0];
				if(arr.length==2){
					ipFirst=arr[0];
					ipLast=arr[1];
				}
			}*/
			String attributename[]={"type","state"};
			Object attributevalue[]={"'1'","'1'"};
			sysOrganizationList=sysOrganizationBS.findByAny("SysOrganization", attributename, attributevalue, "");
			listSysCountry=sysCountryBS.findAll();
			sysUserOrgRelationList=sysUserOrgRelationBS.findByAny("SysUserOrgRelation", "cmUser.id", "'"+cmUser.getUserId()+"'", "");
			for (int i = 0; i < sysUserOrgRelationList.size(); i++) {
				orgFunIds+=sysUserOrgRelationList.get(i).getSysOrganization().getId()+",";
				orgFunNames+=sysUserOrgRelationList.get(i).getSysOrganization().getName()+",";
			}
			orgFunIds=orgFunIds.length()==0?"":orgFunIds.substring(0,orgFunIds.length()-1);
			orgFunNames=orgFunNames.length()==0?"":orgFunNames.substring(0,orgFunNames.length()-1);
		}catch(Exception e){
			e.printStackTrace();
		}

		return "toupdate";
	}
	

	// 更新，如果ip有改变则更新
	public String doupdate() {
		try {			
			int ErrorSign=0;
/*			if(validateIp()==false){
				ErrorSign++;
				setMyError(ErrorSign+". "+getMyError()+"");
				
			}*/
			if(orgFunIds.equals("")){
				ErrorSign++;
				setMyError(getMyError()==null?ErrorSign+". "+"请选择所属职能机构！":getMyError()+ErrorSign+". "+"请选择所属职能机构！");
				
			}
			if(ErrorSign!=0){
				this.message = this.getFailMessage(this.getMyError());
				System.out.println(this.getMyError()+"                    错误");
				return SUCCESS;
			}
			String ip=this.getIp(ipFirst, ipLast);			
			sysLoginInfo=sysLoginInfoBS.findByAny("cmUser.userId", "'"+cmUser.getUserId()+"'","")==null?null:(SysLoginInfo)sysLoginInfoBS.findByAny("cmUser.userId", "'"+cmUser.getUserId()+"'","").get(0);
			if(sysLoginInfo!=null){
				sysLoginInfo.setIp(ip);
						
				// 根据用户id将已经选择的机构再重新插入到用户机构关系表
				String[] orgFunId=orgFunIds.split(",");
				cmUserBS.updateUserAndSysLoginInfo(cmUser, sysLoginInfo,orgFunId);					
				this.message = this.getSuccessMessage(getText("updateSuccess"), "tbuser", "closeCurrent", "user-info/cm-user!toadd.do");
			}else {
				throw new Exception();
			}		
		}catch (Exception e) {
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String toPersonalInfoManage() {
		cmUser=cmUserBS.findById(((CmUser)ActionContext.getContext().getSession().get("user")).getUserId());
		sysLoginInfo=sysLoginInfoBS.findByAny("cmUser.userId", "'"+cmUser.getUserId()+"'","")==null?null:(SysLoginInfo)sysLoginInfoBS.findByAny("cmUser.userId", "'"+cmUser.getUserId()+"'","").get(0);
		String userOrgIds="";
		sysOrganizationList=sysUserOrgRelationBS.findOrgByUserId(cmUser);
		for (int i = 0; i < sysOrganizationList.size(); i++) {
			userOrgIds+=sysOrganizationList.get(i).getName()+",";
		}
		userOrgIds=userOrgIds.length()==0?"":userOrgIds.substring(0, userOrgIds.length()-1);
		cmUser.setFunOrgId(userOrgIds);
		if(sysLoginInfo!=null){
			String arr[]=sysLoginInfo.getIp().split(",");
			if(arr.length==1) ipFirst=arr[0];
			if(arr.length==2){
				ipFirst=arr[0];
				ipLast=arr[1];
			}
		}
		return "personalInfoManage";
	}
	
	// 根据id删除一条记录
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
        String 	hql="from OutputNewEmployees where 1=1 ";
        String counthql="select count(*) from OutputNewEmployees where 1=1  ";

		if(query.get("eq_numnewout")!=null && !query.get("eq_numnewout").equals("")){
			query.put("eq_numnewout", query.get("eq_numnewout"));
		}
		sysPageInfo.setPageSize(this.getNumPerPage());
		sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
		//counthql += " order by birthday desc ";
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

	//	outputNewEmployeesList = cmUserBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
	

			sysPageInfo.setMaxCount(this.cmUserBS.getCountByHQL(counthql, query));
	
		
		//设置当前页
		try{
			sysPageInfo.setCurrentPage((int)(sysPageInfo.getStartIndex()/sysPageInfo.getPageSize()+1));
		}catch(Exception e){
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			cmUserBS.getErrorLog().info(e.getMessage());
		}
		//String attributename[]={"type","state"};
		//Object attributevalue[]={"'1'","'1'"};
		//sysOrganizationList=sysOrganizationBS.findByAny("SysOrganization", attributename, attributevalue, "");
		//listSysCountry=sysCountryBS.findAll();
/*		for(int i=0;i<listUser.size();i++){
			
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
		}*/
		
		strQuery=Util.toStrQuery(query);
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
	@JSON(serialize=false)
	public final String getIpFirst() {
		return ipFirst;
	}

	public final void setIpFirst(String ipFirst) {
		this.ipFirst = ipFirst;
	}
	@JSON(serialize=false)
	public final String getIpLast() {
		return ipLast;
	}

	public final void setIpLast(String ipLast) {
		this.ipLast = ipLast;
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
    public  List<SysSystem> getSystemList(CmUser cmUser){
	      List<SysSystem> sys = null;
		 String hqlsys = "from SysSystem where 1=1 " ;
		 if("超级管理员".equals(cmUser.getName().trim())){
			 return  sys  = this.cmUserBS.findPageByQuery(hqlsys);
		 }else{
			 if(!"null".equals(cmUser.getSubsystemId()) && !"".equals(cmUser.getSubsystemId())){
				 hqlsys += " and id='"+cmUser.getSubsystemId()+"'";
				 return sys = cmUserBS.findPageByQuery(hqlsys);
			 }
		 }
	  return null;
}
	public Integer getCs() {
		return cs;
	}
	public void setCs(Integer cs) {
		this.cs = cs;
	}
	public Integer getAnds() {
		return ands;
	}
	public void setAnds(Integer ands) {
		this.ands = ands;
	}
	public List<CompanyInfo> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyInfo> companyList) {
		this.companyList = companyList;
	}
	public List<Cmodel> getCmodelList() {
		return cmodelList;
	}
	public void setCmodelList(List<Cmodel> cmodelList) {
		this.cmodelList = cmodelList;
	}
		
}

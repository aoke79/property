package com.sms.training.qualification.web.action.ahLogin;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysUserOperateInfo;
import com.sinoframe.business.ICmUserBS;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Md5;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.AhLogdate;
import com.sms.training.qualification.bean.AhSystem;
import com.sms.training.qualification.bean.AhUser;


@ParentPackage("json-default")
@Results({
		@Result(name = "json", type = "json"),
		@Result(name="list",location="/system/sysLog/operateLogList.jsp"),
		@Result(name="otherlist",location="/system/sysLog/operateLogOtherList.jsp"),
})
public class AnhuaLog extends BaseAction {
	private static final long serialVersionUID = 1L;
	/**
	 * 构造方法
	 */
	public AnhuaLog() {
	}
	//登录名
	private String account;
	//系统id
	private String sysid;
	//操作名称
	private String opname;
	ICmUserBS cmUserBS;
	private AhUser ahUser;
	private AhSystem ahSystem;
	private AhLogdate ahLogdate;
	//List
	private List<AhLogdate> ahLogdateList = new ArrayList<AhLogdate>();
	//分页对象
	private SysPageInfo sysPageInfo = new SysPageInfo();
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	
	//List
	private List<AhLogdate> ahTuLogdateList = new ArrayList<AhLogdate>();
	private String sysname;
	

	/**
	 * 保存登录日志信息
	 * @return
	 */
	public String updatelog() {
		try {
           //获取当前用户信息
			List<AhUser> ahUserlist=cmUserBS.findBySingleQuery("AhUser", "username", account);
			if(ahUserlist!=null && ahUserlist.size()!=0){
				ahUser=ahUserlist.get(0);
			}
		  //获取当先登录系统信息
			List<AhSystem> syslist=cmUserBS.findBySingleQuery("AhSystem", "sysid", sysid);
			if(syslist!=null && syslist.size()!=0){
				ahSystem=syslist.get(0);
			}
			ahLogdate = new AhLogdate();
			ahLogdate.setAnUser(ahUser);
			ahLogdate.setAhSystem(ahSystem);
			ahLogdate.setOptime(DateTool.getNow());
			ahLogdate.setOpname(opname);
			HttpServletRequest request = ServletActionContext.getRequest();
			ahLogdate.setMicip(request.getRemoteAddr());
			ahLogdate.setSysname(ahSystem.getSysname());
			ahLogdate.setUsername(ahUser.getUsername());
			cmUserBS.saveOrUpdate(ahLogdate);
		} catch (Exception e) {
			cmUserBS.getErrorLog().info(e.getMessage() + "#" +"");
			e.printStackTrace();
		}
		return "json";
	}
	
	
	
	/**
	 * 查询日志列表
	 * @return
	 */
	public String list() {
		try {
			//查询列表中的条数信息
			String counthql = "select count(*) from AhLogdate where 1=1 ";
			//设置最大条数
			sysPageInfo.setMaxCount(this.cmUserBS.getCountByHQL(counthql, query));
			//设置每页显示条数及起始记录
			sysPageInfo.setPageSize(this.getNumPerPage());
			System.out.println(this.getPageNum());
			System.out.println(this.getNumPerPage());
			sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
			//设置当前页
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
			
			//列表
			String hql = "from AhLogdate where 1=1 ";
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			if (sysOrderByInfo.getOrderColumn() == null || sysOrderByInfo.getOrderColumn().equals("")) {
				sysOrderByInfo.setOrderColumn("optime");
				
			}
			if("optime".equals(sysOrderByInfo.getOrderColumn())){
				sysOrderByInfo.setIfDate(true);
			}

			ahLogdateList = cmUserBS.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
			
		} catch (Exception e) {
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			cmUserBS.getErrorLog().info(e.getMessage()+"#"+"");
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	* 查询出其他系统日志
	* @return
	*/
	public String otherList(){
		try {
			StringBuffer bufCount;
			StringBuffer hqlBuf;
			if(sysname!= null && !"".equals(sysname)){
				bufCount = new StringBuffer("select count(*) from AhTuLogdate u where 1=1");
				bufCount.append("and u.sysname='").append(sysname.trim()).append("'");
				hqlBuf = new StringBuffer("from AhTuLogdate u where 1=1  ");
				hqlBuf.append("and u.sysname='").append(sysname.trim()).append("'");
			}else{
				bufCount = new StringBuffer("select count(*) from AhTuLogdate u where 1=1");
				hqlBuf = new StringBuffer("from AhTuLogdate u where 1=1 ");
			}
			
			//设置最大条数
			sysPageInfo.setMaxCount(this.cmUserBS.getCountByHQL(bufCount.toString(), query));
			//设置每页显示条数及起始记录
			sysPageInfo.setPageSize(this.getNumPerPage());
			sysPageInfo.setStartIndex((this.getPageNum()-1)*this.getNumPerPage());
			//设置当前页
			sysPageInfo.setCurrentPage((int)((sysPageInfo.getStartIndex()/sysPageInfo.getPageSize())+1));
			
			SysOrderByInfo sysOrderByInfo = this.getSysOrderByInfo();
			sysOrderByInfo.setOrderColumn("optime");
			sysOrderByInfo.setOrderAsc("desc");
			
			ahTuLogdateList = cmUserBS.findPageByQuery(sysPageInfo, hqlBuf.toString(), query, sysOrderByInfo); 
			
		} catch (Exception e) {
			sysPageInfo.setCurrentPage(1);
			//设置日志信息
			cmUserBS.getErrorLog().info(e.getMessage()+"#"+"");
			e.printStackTrace();
		}
		
		return "otherlist";
	}


	//-------------------get and set-------------------------//
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	@JSON(serialize = false)
	public ICmUserBS getCmUserBS() {
		return cmUserBS;
	}

	public void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}
	@JSON(serialize = false)
	public AhUser getAhUser() {
		return ahUser;
	}

	public void setAhUser(AhUser ahUser) {
		this.ahUser = ahUser;
	}
	@JSON(serialize = false)
	public AhSystem getAhSystem() {
		return ahSystem;
	}

	public void setAhSystem(AhSystem ahSystem) {
		this.ahSystem = ahSystem;
	}

	public AhLogdate getAhLogdate() {
		return ahLogdate;
	}

	public void setAhLogdate(AhLogdate ahLogdate) {
		this.ahLogdate = ahLogdate;
	}


	public void setAhLogdateList(List<AhLogdate> ahLogdateList) {
		this.ahLogdateList = ahLogdateList;
	}

	@JSON(serialize = false)
	public List<AhLogdate> getAhLogdateList() {
		return ahLogdateList;
	}
	public SysPageInfo getSysPageInfo() {
		return sysPageInfo;
	}


	public void setSysPageInfo(SysPageInfo sysPageInfo) {
		this.sysPageInfo = sysPageInfo;
	}


	public HashMap<String, String> getQuery() {
		return query;
	}


	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}


	@JSON(serialize = false)
	public List<AhLogdate> getAhTuLogdateList() {
		return ahTuLogdateList;
	}

	public void setAhTuLogdateList(List<AhLogdate> ahTuLogdateList) {
		this.ahTuLogdateList = ahTuLogdateList;
	}
	
	@JSON(serialize = false)
	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	
}

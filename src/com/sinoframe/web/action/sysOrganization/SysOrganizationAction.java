///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2011 SINOSOFT  CO., LTD.                                     //
//                                                                           //
// ALL RIGHTS RESERVED BY SINOSOFT  CO., LTD.                                //
// THIS PROGRAM MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH                //
// IT WAS FURNISHED BY SINOSOFT  CO., LTD.                                   //
// NO PART OF THIS PROGRAM MAY BE REPRODUCED OR DISCLOSED TO OTHERS,         //
// IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF SINOSOFT              //
//(CHINA) CO., LTD.                                                          //
//                                                                           //
// SINOSOFT CO., LTD. CONFIDENTIAL AND PROPRIETARY                           //
///////////////////////////////////////////////////////////////////////////////

/**
 * <p><b>Title</b>:  DateTool</p>
 * <p><b>Description</b>:  SysOrganization的action方法</p>
 * <p><b>DATE</b>: 2011/04/20</p>
 * AUTHOR        : SinoSoft jilili
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
 **/
package com.sinoframe.web.action.sysOrganization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.bean.SysOrganization;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysUserOrgRelation;
import com.sinoframe.business.ISysOrganizationBS;
import com.sinoframe.common.util.SpellUtils;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;

@ParentPackage("sinoframe-default")
@Results( {
		@Result(name = "addOrg", location = "/system/sysOrganization/organizationAdd.jsp"),
		@Result(name = "list", location = "/system/sysOrganization/organizationList.jsp"),
		@Result(name = "SUCCESS", location = "/standard/ajaxDone.jsp"),
		@Result(name = "toEdit", location = "/system/sysOrganization/organizationEdit.jsp"),
		@Result(name = "METHOD", location = "sys-organization!list", type = "redirectAction"),
		@Result(name = "organizationList", location = "/system/sysOrganization/getOrganizationList.jsp"),
		@Result(name = "json", type = "json") })
public class SysOrganizationAction extends BaseAction {

	private static final long serialVersionUID = -8108230451188143086L;
	// 机构对象
	private SysOrganization sysOrganization = null;
	// 机构对象的service对象
	private ISysOrganizationBS sysOrganizationBS = null;
	// 用户机构关系对象的service对象
	//private ISysUserOrgRelationBS sysUserOrgRelationBS;
	//private ICmUserBS cmUserBS;
	// 分页对象
	private SysPageInfo sysPageInfo;
	// 存放查询结果的List集
	private List<SysOrganization> listSysOrganization = new ArrayList<SysOrganization>();
	// 存放查询对象的HashMap集
	private HashMap<String, String> query = new HashMap<String, String>();
	// 消息实体
	private Message message;
	// 存储要删除的机构的id
	private String ids;
	// 接收当前页面的页号
	private int pageNum = 1;
	// 接收页面显示记录的条数
	private int numPerPage = 20;
	// 接收检索是的排序列
	private String orderField;
	// 用于存储查询条件的字符串形式
	private String strQuery;
	// 存储树状结构的父节点的id
	private String parentId;
	// 存储列表页面树中需要关闭的节点
	private List<String> parentIds;
	// 存储当前被操作的机构的id（即用来控制操作后哪个机构被选中）
	private String currentTreeNode;

	// 标识 当选择从父页面上选择机构从属的时候，需要将隐藏的id与显示的name的名字传过来
	// 以用于在子页面上调用。
	String organizationId;
	String organizationName;
	// 选择是要多选还是需要单选。0单选 1 多选
	int chooseCheck;
	// 判断返回树的级别
	int organizationGrade;
	CmUser cmUser;
	// 返回树的主体
	List<SysOrganization> sysOrganizationList;
	// 得到向机构展示页面返回的list集合，用于遍历循环
	List<SysOrganization> orgParentFunList;

	// 输入框的值
	private String info;
	// 匹配的机构名称
	List<String> matchNameList = new ArrayList<String>();
	// 机构ID
	List<String> matchIDList = new ArrayList<String>();
	// 机构树
	private String orgTree;
	//机构入口标识(页面使用)
	private String flag;
	
	private boolean sucMessage;
	
	/**
	 * 判断当前登录者是否有所属机构。
	 * @return
	 */
	public String judgeOrgByUser() {
		// 当前登录者所属的机构
		List<SysOrganization> list = getOrganizationList();	
		
		if (list.size() == 0) {
			sucMessage=false;
			this.message = this.getFailMessage("您还没有所属机构！");
			return "json";
		}else {
			sucMessage=true;
		}
		return "json";
	}
	


	
	public String securityOrgSelect() {
		List<SysOrganization> list = getOrganizationList();	
		sysOrganization = list.get(0); // 求出当前登录者所在部门的最高的一个职位		
		sysOrganizationList=sysOrganizationBS.securityOrgTree(sysOrganization);		
		return "organizationList";
	}
	
	

	/**
	 * 选择所属职能机构 可以得到的级别 0.显示所有单位。 1.显示上级单位，本单位的同级单位（包括本单位）和本单位的同级单位（包括本单位）的下级单位
	 * 2.显示本单位和下级单位 3.仅显示下级单位
	 * 
	 * @param organizationGrade
	 * @param sysOrganization
	 * @return
	 */
	public String toOrganizationList() {
		/**
		 * update by niujingwei
		 */

		// 在进行修改操作的时候 这个用户已经选择的机构
		//List<SysOrganization> userFunIdList = new ArrayList<SysOrganization>();
		// 当前登录者所属的机构
		List<SysOrganization> list = getOrganizationList();
		if (list.size() == 0) {
			this.message = this.getFailMessage("您还没有所属机构！");
			return "SUCCESS";
		}
		sysOrganization = list.get(0); // 求出当前登录者所在部门的最高的一个职位
		SysOrganization initBasesysOrganization = (SysOrganization) getServletContext()
				.getAttribute("initBaseSysOrganization");
		if (initBasesysOrganization.equals(sysOrganization)) {
			// 表示此机构是国航股份
			sysOrganization = initBasesysOrganization;
		} else {
			// 如果不是国航则从内存中取机构
			for (SysOrganization sysOrganization1 : initBasesysOrganization
					.getSetChild_Fun()) {
				if (sysOrganization1.equals(sysOrganization)) {
					sysOrganization = sysOrganization1;
					break;
				}
			}
		}

		sysOrganizationList = sysOrganizationBS.getOrganizationList(
				organizationGrade, sysOrganization);
		if (organizationGrade == 1 && sysOrganization.getParent_Fun() == null) {

			this.message = this.getFailMessage("您当前所属机构没有上级机构");
			return "SUCCESS";
		}
		if (organizationGrade == 3 && sysOrganization.getSetChild_Fun() == null) {
			this.message = this.getFailMessage("您当前所属机构没有下级机构");
			return "SUCCESS";
		}

		// 得到父类 只是为了去页面上进行比较，以此来加载树
		orgParentFunList = sysOrganizationBS.getOrgParentList(
				organizationGrade, sysOrganization);

		createTree(orgParentFunList, sysOrganizationList, chooseCheck, 1);

		return "organizationList";
	}

	/**
	 * 生成机构树
	 * @param orgParentFunList2
	 *            表示当前最高一层机构
	 * @param sysOrganizationList2
	 *            树的主体机构
	 * @param chooseCheck
	 *            是否有复选框 表示有复选
	 * @param flg
	 *            1表示弹出框机构 2表示列出机构
	 * 
	 * Sep 14, 20115:25:27 PM
	 * @author niujingwei
	 */
	private void createTree(List<SysOrganization> orgParentFunList2,
			List<SysOrganization> sysOrganizationList2, int chooseCheck, int flg) {

		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("<ul class=");
		sBuffer.append("\"");
		if (flg == 1) {
			if (chooseCheck == 1) {
				sBuffer.append("tree treeFolder treeCheck collapse expand");
			} else {
				sBuffer.append("tree treeFolder collapse expand");
			}
		} else if (flg == 2) {
			if (parentId == null) {
				sBuffer.append("tree collapse treeFolder");
			} else {
				sBuffer.append("tree  treeFolder");
			}

		}
		sBuffer.append("\" ");
		sBuffer.append("id=");
		sBuffer.append("\"");
		sBuffer.append("orgTree");
		sBuffer.append("\"");
		sBuffer.append(">");

		for (SysOrganization sysOrganization : orgParentFunList2) {

			draw(sBuffer, sysOrganization, 1, flg);

			for (SysOrganization sysOrganization2 : sysOrganizationList2) {
			/*	if (sysOrganization2!=null) {
					System.out.println(sysOrganization2.getParent_Fun());
					System.out.println(sysOrganization2.getParent_Fun().getId());
					System.out.println(sysOrganization2.getId());
				}*/
				
				if (sysOrganization2!=null && sysOrganization2.getParent_Fun() != null
						&& sysOrganization2.getParent_Fun().getId().equals(
								sysOrganization.getId())) {

					draw(sBuffer, sysOrganization2, 2, flg);
					for (SysOrganization sysOrganization3 : sysOrganizationList2) {

						if (sysOrganization3 != null && sysOrganization3.getParent_Fun() != null
								&& sysOrganization3.getParent_Fun().getId()
										.equals(sysOrganization2.getId())) {
							draw(sBuffer, sysOrganization3, 2, flg);
							for (SysOrganization sysOrganization4 : sysOrganizationList2) {
								if (sysOrganization4 != null && sysOrganization4.getParent_Fun() != null
										&& sysOrganization4.getParent_Fun()
												.getId().equals(
														sysOrganization3
																.getId())) {
									draw(sBuffer, sysOrganization4, 2, flg);

									for (SysOrganization sysOrganization5 : sysOrganizationList2) {
										if (sysOrganization5 != null && sysOrganization5.getParent_Fun() != null
												&& sysOrganization5
														.getParent_Fun()
														.getId()
														.equals(
																sysOrganization4
																		.getId())) {
											draw(sBuffer, sysOrganization5, 2,
													flg);
											drawEnd(sBuffer);
										}

									}
									drawEnd(sBuffer);
								}
							}
							drawEnd(sBuffer);
						}

					}
					drawEnd(sBuffer);
				}

			}
			sBuffer.append("</li>");
		}
		sBuffer.append("</ul>");
		orgTree = sBuffer.toString();
		System.out.println(sBuffer.toString());
		System.out.println("true");
	}

	private void drawEnd(StringBuffer sBuffer) {
		sBuffer.append("</li>");
		sBuffer.append("</ul>");
	}
    /**
     * 
     * TODO
     * @param sBuffer
     * @param sysOrganization
     * @param i 1代表最上层机构
     * @param flg
     * Sep 20, 201110:43:22 AM
     * @author niujingwei
     */
	private void draw(StringBuffer sBuffer, SysOrganization sysOrganization,
			int i, int flg) {
		if (i == 2) {
			sBuffer.append("<ul>");
		}
		sBuffer.append("<li>");
		sBuffer.append("<a flag=");
		sBuffer.append("\"");
		sBuffer.append("sinoframe");
		sBuffer.append("\"");
		sBuffer.append(" ttype=");
		sBuffer.append("\"");
		sBuffer.append(sysOrganization.getOrgType());
		sBuffer.append("\"");
		sBuffer.append(" tname=");
		sBuffer.append("\"");
		sBuffer.append("ids");
		sBuffer.append("\"");
		sBuffer.append(" tvalue=");
		sBuffer.append("\"");
		sBuffer.append(sysOrganization.getName());
		sBuffer.append("\"");
		if (flg == 1) {
			sBuffer.append(" tid=");
		} else if (flg == 2) {
			sBuffer.append(" nodeId=");
		}
		sBuffer.append("\"");
		sBuffer.append(sysOrganization.getId());
		sBuffer.append("\"");
		sBuffer.append(">");
		sBuffer.append(sysOrganization.getName());
		sBuffer.append("</a>");

	}

	/**
	 * 跳转到添加机构对象的页面
	 */
	public String toAddOrg() {
		// 将sysOrganizations设置为全部出去“使用”状态的机构的集合
		String hql = "from SysOrganization so where so.state = '1'";
		listSysOrganization = sysOrganizationBS.findPageByQuery(hql);
		return "addOrg";
	}

	/**
	 * 保存机构对象
	 */
	public String addOrg() {
		try {
			this.getSysOrganization().setState("1");

			currentTreeNode = sysOrganizationBS.save(this.getSysOrganization())
					.getId();
			this.getServletRequest().getSession().setAttribute(
					"currentTreeNode", currentTreeNode);
			
			
			//修改之后要把新数据重新放到context中
			updateOrgFromContext();
			
			
			this.message = this
					.getSuccessMessage("保存成功", "orgManager", "closeCurrent",
							"sys-organization/sys-organization!list.do");
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("保存失败！");
		}
		return "SUCCESS";
	}

	/**
	 * 跳转到编辑页面
	 */
	public String toEditPage() {
		// 根据id查找相应的机构
		sysOrganization = sysOrganizationBS.findById(SysOrganization.class,
				this.getSysOrganization().getId());
		// 获取parentId的值
		if (sysOrganization.getParent_Fun() != null) {
			parentId = sysOrganization.getParent_Fun().getId();
		}
		String hql = "from SysOrganization so where so.state = '1'";
		listSysOrganization = sysOrganizationBS.findPageByQuery(hql);
		// strQuery = Util.toStrQuery(query);
		return "toEdit";
	}

	/**
	 * 修改数据
	 */
	public String editOrg() {

		try {
			sysOrganizationBS.update(sysOrganization, sysOrganization.getId());
			
			//修改之后要把新数据重新放到context中
			updateOrgFromContext();
			this.message = this
					.getSuccessMessage("保存成功", "orgManager", "closeCurrent",
							"sys-organization/sys-organization!list.do");
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("保存失败！");
		}

		return "SUCCESS";
	}

	/**
	 * 删除数据
	 */
	public String delOrg() {
		sysOrganization = sysOrganizationBS.findById(SysOrganization.class,
				this.getSysOrganization().getId());
		String strMessage = sysOrganizationBS.hasChild(sysOrganization);
		// 依据hasChild方法的返回值，判断是否进行删除操作！
		if ("该机构下有：,请确定其不含子项后，再进行删除！".equals(strMessage)) {
			try {
				// 将该机构的状态设置为删除"0"
				sysOrganization.setState("0");
				this.sysOrganizationBS.update(sysOrganization, sysOrganization
						.getId());
				//修改之后要把新数据重新放到context中
				updateOrgFromContext();
				
				this.message = this.getSuccessMessage("删除成功", null, "forward",
						"sys-organization/sys-organization!list.do?parentId="
								+ this.sysOrganization.getParent_Fun().getId()
								+ "&currentTreeNode="
								+ this.sysOrganization.getParent_Fun().getId());
			} catch (Exception e) {
				e.printStackTrace();
				this.message = this.getFailMessage("该机构下有子机构，请删除子机构后，再进行删除！");
			}
		} else {
			this.message = this.getFailMessage(strMessage);
		}
		return "SUCCESS";
	}

	/**
	 * 删除多条数据
	 */
	public String delOrgs() {
		try {
			if (ids != null) {
				// 表示将要删除的机构下是否有子机构
				boolean exists = false;
				// 用于存放有子机构的机构名称
				List<String> listName = new ArrayList<String>();
				// 获得要删除的id
				String[] id = this.getIds().split(",");
				for (String strId : id) {
					// 判断每个机构对象是否含有子对象
					if (sysOrganizationBS.hasChildren(strId)) {
						// 如果有子机构，存放到list集合中
						listName.add(sysOrganizationBS.findById(
								SysOrganization.class, strId).getName());
						exists = true;
					}
				}
				// 设置将要删除的机构有子机构的情况的消息
				if (exists) {
					this.message = this.getFailMessage(listName.toString()
							.replace("[", "").replace("]", " ")
							+ listName.size() + "个部门有子项, 不能被删除,请重新选择!");
				} else {
					this.sysOrganizationBS.deleteOrgs(id);
					sysPageInfo = this.getSysPageInfo();
					String strCountHql = "select count(*) from SysOrganization so where so.state = '1'";
					// 获得数据的最大条数
					Long maxCount = this.sysOrganizationBS.getCountByHQL(
							strCountHql, query);
					// 获得最大页数
					Integer maxPageNum = (int) (maxCount
							% sysPageInfo.getPageSize() == 0 ? maxCount
							/ sysPageInfo.getPageSize() : maxCount
							/ sysPageInfo.getPageSize() + 1);
					// 当最大页数比当前页小时，说明当前页已经没有数据，将最大页数设置为当前页
					if (maxPageNum < sysPageInfo.getCurrentPage()) {
						pageNum = maxPageNum;
					}
					this.message = this.getSuccessMessage("删除成功", null,
							"forward",
							"sys-organization/sys-organization!list.do?pageNum="
									+ this.getPageNum() + "&numPerPage="
									+ this.getNumPerPage() + "&"
									+ Util.toStrQuery(query));
				}
				
				//修改之后要把新数据重新放到context中
				updateOrgFromContext();
				
			} else {
				this.message = this.getFailMessage("没有选中任何数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("删除失败");
		}
		return "SUCCESS";
	}

	/**
	 * public String list() { // 判断orgId是否为空，如果为空，就在session中取值
	 * if(currentTreeNode == null || "".equals(currentTreeNode)){
	 * currentTreeNode = (String)
	 * this.getServletRequest().getSession().getAttribute("currentTreeNode");
	 * this.getServletRequest().getSession().setAttribute("currentTreeNode",
	 * null); } String hql = "from SysOrganization so where so.state = '1' order
	 * by orgType desc,orgOrder,name"; listSysOrganization =
	 * sysOrganizationBS.findPageByQuery(hql); //listSysOrganization=(List<SysOrganization>)
	 * this.getServletContext().getAttribute("org");
	 * 
	 * 
	 * 
	 *  // 根据页面传回的parentId查找列表树中需要关闭的节点 if (parentId != null) { List<String>
	 * listParentIds = new ArrayList<String>(); parentIds =
	 * sysOrganizationBS.getFunParentIds(listSysOrganization, listParentIds,
	 * parentId); } return "list"; }
	 */

	@SuppressWarnings("unchecked")
	public String list() {
		// 判断orgId是否为空，如果为空，就在session中取值
		if (currentTreeNode == null || "".equals(currentTreeNode)) {
			currentTreeNode = (String) this.getServletRequest().getSession()
					.getAttribute("currentTreeNode");
			this.getServletRequest().getSession().setAttribute(
					"currentTreeNode", null);
		}
		listSysOrganization = (List<SysOrganization>) getServletContext()
				.getAttribute("initBaseSysOrganizationList");
		List<SysOrganization> highOrgList = new ArrayList<SysOrganization>();
		SysOrganization sysOrganization = (SysOrganization) getServletContext()
				.getAttribute("initBaseSysOrganization");
		highOrgList.add(sysOrganization);
		createTree(highOrgList, listSysOrganization, chooseCheck, 2);

		return "list";
	}

	// 文本框提示空间功能--add by wangjing 2011-07-04 start
	public String search() {
		// 从库里拿到所有的名称字段的值
		try {
			String strCountHql = "from SysOrganization";
			List<SysOrganization> list = new ArrayList<SysOrganization>();
			list = sysOrganizationBS.findPageByQuery(strCountHql);
			// 名称拼音全拼listSpellFull
			List<String> listSpellFull = new ArrayList<String>();
			// 名称拼音首字母listSpellStart
			List<String> listSpellStart = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName() != null
						&& !"".equals(list.get(i).getName())) {
					listSpellFull
							.add(SpellUtils.getFull(list.get(i).getName()));// 将名称字段的值转化为拼音
					listSpellStart.add(SpellUtils.getFirst(list.get(i)
							.getName()));// 拿到名称字段值的首字母
				}
			}
			// 拿到输入框里的值(包括拼音和汉字)
			String spellInfoString = "";
			if (info != null && !"".equals(info)) {
				spellInfoString = SpellUtils.getFull(info);
			}

			// 如果名称字段值转化后的拼音值包含输入框的转化后的拼音值，就将对应的汉字存到list里
			if (!"".equals(spellInfoString) && spellInfoString != null) {
				// 匹配拼音或者首字母
				for (int i = 0; i < listSpellFull.size(); i++) {
					if (listSpellFull.get(i).trim().startsWith(
							spellInfoString.trim())
							|| listSpellStart.get(i).trim().startsWith(
									spellInfoString.trim())) {
						matchNameList.add(list.get(i).getName());
						matchIDList.add(list.get(i).getId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将匹配的汉字通过json方式发到前台页面显示
		return "json";
	}
	
	
	private void updateOrgFromContext(){
		// 获得最高机构
		SysOrganization sysOrganization = (SysOrganization) sysOrganizationBS.findPageByQuery("from SysOrganization t where t.parent_Fun is null and t.setChild_Fun.size>0 and t.state = '1'").get(0);
		List<SysOrganization> sysorgList = new ArrayList<SysOrganization>();
		diguiOrg(sysOrganization, sysorgList);
		sysorgList.add(sysOrganization);
		getServletContext().setAttribute("initBaseSysOrganization", sysOrganization);
		getServletContext().setAttribute("initBaseSysOrganizationList", sysorgList);
	}
	
	
	
	
	/** 递归遍历所有机构一次 */
	private void diguiOrg(SysOrganization sysOrganization,
			List<SysOrganization> list) {
		// 如果当前的操作有子操作，继续向下递归
		Set<SysOrganization> setChild_Fun = sysOrganization.getSetChild_Fun();
		
		//把需要加载上来的数据全部加载上来
		Set<SysUserOrgRelation> sysUserOrgRelations = sysOrganization.getSysUserOrgRelations();
		for (SysUserOrgRelation sysUserOrgRelation:sysUserOrgRelations) {
			sysUserOrgRelation.getSysOrganization();
			sysUserOrgRelation.getCmUser();
		}
		
		if (!setChild_Fun.isEmpty()) {
			for (SysOrganization sysOrganization2 : setChild_Fun) {
				sysUserOrgRelations = sysOrganization.getSysUserOrgRelations();
				for (SysUserOrgRelation sysUserOrgRelation:sysUserOrgRelations) {
					sysUserOrgRelation.getSysOrganization();
					sysUserOrgRelation.getCmUser();
				}
				// 递归遍历操作
				if(sysOrganization2.getState()!=null && sysOrganization2.getState().equals("1")){
					list.add(sysOrganization2);
				}
				diguiOrg(sysOrganization2, list);
			}
		}
	}
	
	/****************安全机构树start********************************/
	
	
	/**
	 * 生成机构树
	 * @author chenleilei
	 */
	private void createSecurityTree(List<SysOrganization> orgParentFunList2,
			List<SysOrganization> sysOrganizationList2, int chooseCheck, int flg) {

		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("<ul class=");
		sBuffer.append("\"");
		if (flg == 1) {
			if (chooseCheck == 1) {
				sBuffer.append("tree treeFolder treeCheck collapse expand");
			} else {
				sBuffer.append("tree treeFolder collapse expand");
			}
		} else if (flg == 2) {
			if (parentId == null) {
				sBuffer.append("tree collapse treeFolder");
			} else {
				sBuffer.append("tree  treeFolder");
			}

		}
		sBuffer.append("\" ");
		sBuffer.append("id=");
		sBuffer.append("\"");
		sBuffer.append("orgTree");
		sBuffer.append("\"");
		sBuffer.append(">");

		for (SysOrganization sysOrganization : orgParentFunList2) {

			draw(sBuffer, sysOrganization, 1, flg);
			if(sysOrganization.getOrgLevel().equals("1")){
				continue;
			}
			for (SysOrganization sysOrganization2 : sysOrganizationList2) {
				boolean flag=(sysOrganization2!=null && sysOrganization2.getParent_Fun() != null
						&& sysOrganization2.getParent_Fun().getId().equals(
								sysOrganization.getId())) || (sysOrganization2!=null && sysOrganization2.getParent_Supervise() != null
										&& sysOrganization2.getParent_Supervise().getId().equals(
												sysOrganization.getId()));
				
				if (flag) {

					draw(sBuffer, sysOrganization2, 2, flg);
					/*				for (SysOrganization sysOrganization3 : sysOrganizationList2) {
						
						boolean flag2=(sysOrganization3!=null && sysOrganization3.getParent_Fun() != null
								&& sysOrganization3.getParent_Fun().getId().equals(
										sysOrganization2.getId())) || (sysOrganization3!=null && sysOrganization3.getParent_Supervise() != null
												&& sysOrganization3.getParent_Supervise().getId().equals(
														sysOrganization2.getId()));

						if (flag2) {
							draw(sBuffer, sysOrganization3, 2, flg);
							for (SysOrganization sysOrganization4 : sysOrganizationList2) {
								
								boolean flag3=(sysOrganization4!=null && sysOrganization4.getParent_Fun() != null
										&& sysOrganization4.getParent_Fun().getId().equals(
												sysOrganization3.getId())) || (sysOrganization4!=null && sysOrganization4.getParent_Supervise() != null
														&& sysOrganization4.getParent_Supervise().getId().equals(
																sysOrganization3.getId()));
								
								if (flag3) {
									draw(sBuffer, sysOrganization4, 2, flg);

									for (SysOrganization sysOrganization5 : sysOrganizationList2) {
										
										boolean flag4=(sysOrganization5!=null && sysOrganization5.getParent_Fun() != null
												&& sysOrganization5.getParent_Fun().getId().equals(
														sysOrganization4.getId())) || (sysOrganization5!=null && sysOrganization5.getParent_Supervise() != null
																&& sysOrganization5.getParent_Supervise().getId().equals(
																		sysOrganization4.getId()));
										
										if (flag4) {
											draw(sBuffer, sysOrganization5, 2, flg);
											drawEnd(sBuffer);
										}

									}
									drawEnd(sBuffer);
								}
							}
							drawEnd(sBuffer);
						}

					}*/
					drawEnd(sBuffer);
				}

			}
			sBuffer.append("</li>");
		}
		sBuffer.append("</ul>");
		orgTree = sBuffer.toString();
		System.out.println(sBuffer.toString());
	}
	
	
	
	/**
	 * 安全模块所需要的机构树
	 * 业务需求：
	 * 		1.进来的是国航股份，则将一级单位全部取出
	 * 		2.如果是一级单位取本身、下级及监管单位
	 * 		3.如果是二级单位取本身、下级
	 * 		4.如果是三级单位取本身
	 * @return
	 * @author chenleilei
	 */
	public String securityOrgTree() {
	
		// 当前登录者所属的机构
		List<SysOrganization> list = getOrganizationList();
		if (list.size() == 0) {
			this.message = this.getFailMessage("您还没有所属机构！");
			return "SUCCESS";
		}
		sysOrganization = list.get(0); // 求出当前登录者所在部门的最高的一个职位	

		sysOrganizationList = sysOrganizationBS.securityOrgTree(sysOrganization);

		// 得到父类 只是为了去页面上进行比较，以此来加载树
		orgParentFunList = sysOrganizationBS.securityOrgParentTree(sysOrganization);
		this.createSecurityTree(orgParentFunList, sysOrganizationList, chooseCheck, 1);		

		return "organizationList";
	}
	
	
	/****************安全机构树end********************************/
	
	

	// 文本框提示空间功能--add by wangjing 2011-07-04 end

	public void setSysOrganizationBS(ISysOrganizationBS sysOrganizationBS) {
		this.sysOrganizationBS = sysOrganizationBS;
	}

	public SysOrganization getSysOrganization() {
		if (sysOrganization == null) {
			sysOrganization = new SysOrganization();
		}
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

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

	public HashMap<String, String> getQuery() {
		return query;
	}

	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<SysOrganization> getListSysOrganization() {
		return listSysOrganization;
	}

	public void setListSysOrganization(List<SysOrganization> listSysOrganization) {
		this.listSysOrganization = listSysOrganization;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
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

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<String> getParentIds() {
		return parentIds;
	}

	public void setParentIds(List<String> parentIds) {
		this.parentIds = parentIds;
	}

	public String getCurrentTreeNode() {
		return currentTreeNode;
	}

	public void setCurrentTreeNode(String currentTreeNode) {
		this.currentTreeNode = currentTreeNode;
	}

	public final List<SysOrganization> getOrgParentFunList() {
		return orgParentFunList;
	}

	public final void setOrgParentFunList(List<SysOrganization> orgParentFunList) {
		this.orgParentFunList = orgParentFunList;
	}

	public final String getOrganizationId() {
		return organizationId;
	}

	public final void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public final String getOrganizationName() {
		return organizationName;
	}

	public final void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public final int getChooseCheck() {
		return chooseCheck;
	}

	public final void setChooseCheck(int chooseCheck) {
		this.chooseCheck = chooseCheck;
	}

	public final int getOrganizationGrade() {
		return organizationGrade;
	}

	public final void setOrganizationGrade(int organizationGrade) {
		this.organizationGrade = organizationGrade;
	}

	public final CmUser getCmUser() {
		return cmUser;
	}

	public final void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	public final List<SysOrganization> getSysOrganizationList() {
		return sysOrganizationList;
	}

	public final void setSysOrganizationList(
			List<SysOrganization> sysOrganizationList) {
		this.sysOrganizationList = sysOrganizationList;
	}

	/*public final void setSysUserOrgRelationBS(
			ISysUserOrgRelationBS sysUserOrgRelationBS) {
		this.sysUserOrgRelationBS = sysUserOrgRelationBS;
	}

	public final void setCmUserBS(ICmUserBS cmUserBS) {
		this.cmUserBS = cmUserBS;
	}*/

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<String> getMatchNameList() {
		return matchNameList;
	}

	public void setMatchNameList(List<String> matchNameList) {
		this.matchNameList = matchNameList;
	}

	public List<String> getMatchIDList() {
		return matchIDList;
	}

	public void setMatchIDList(List<String> matchIDList) {
		this.matchIDList = matchIDList;
	}

	public String getOrgTree() {
		return orgTree;
	}

	public void setOrgTree(String orgTree) {
		this.orgTree = orgTree;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	public boolean getSucMessage() {
		return sucMessage;
	}


	public void setSucMessage(boolean sucMessage) {
		this.sucMessage = sucMessage;
	}
	
	

}

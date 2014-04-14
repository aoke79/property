<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：修改操作组关系
 	 * 更新记录：
	 */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="jstl" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		pagination = "${pageNum}";
		pageSize = "${numPerPage}";
		query = "${strQuery}";
		orderBy = "${orderBlock}";
		orderCol = "${orderMethod}";
	});

</script>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-operate-group-relation/sys-operate-group-relation!doEdit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden" name="sysOperateGroupRelation.id.operateGroupId" value="${sysOperateGroupRelation.id.operateGroupId}">
         <input type="hidden" name="sysOperateGroupRelation.id.operateId" value="${sysOperateGroupRelation.id.operateId}">
	     <div class="pageFormContent" layoutH="56">
		 <p>
		 <label>操作组名称:</label>
		 <input name="sysOperateGroupRelation.sysOperateGroup.name" type="text" size="30"
		        value="${sysOperateGroupRelation.sysOperateGroup.name}" readonly="readonly"/>
	     </p>
	     <p>
		 <label>操作名称:</label>
		 <input name="sysOperateGroupRelation.sysOperate.operateName" type="text" size="30"
		        value="${sysOperateGroupRelation.sysOperate.operateName}" readonly="readonly"/>
	     </p>
	     <p>
		 <label>有效性:</label>
		 <!-- 
		 	<input name="sysOperateGroupRelation.state" type="text" size="30"
		        value="${sysOperateGroupRelation.state}" />
		 -->
		 			<s:if test='#attr.sysOperateGroupRelation.state.equals("1")'>
  						<input name="sysOperateGroupRelation.state" type="radio" value="1" checked>有效
  						<input name="sysOperateGroupRelation.state" type="radio" value="0">无效
  					</s:if>
  					<s:else>
  						<input name="sysOperateGroupRelation.state" type="radio" value="1" >有效
  						<input name="sysOperateGroupRelation.state" type="radio" value="0" checked>无效
  					</s:else>
	     </p>
	     <p>
		 <label>备注:</label>
		 <input name="sysOperateGroupRelation.opegrComment" type="text" size="30"
		        value="${sysOperateGroupRelation.opegrComment}" />
	     </p>
		 </div>
		 <div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">更新</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>

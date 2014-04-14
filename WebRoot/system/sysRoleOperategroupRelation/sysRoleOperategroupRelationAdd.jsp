<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：角色操作组关系增加
 	 * 更新记录：
	 */
%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jstl" %>
<%@ include file="/property/common/taglibs.jsp"%>
<script language="JavaScript">
//<!--
	//JSP check
	function checkInput() {
		
	}
//-->
  function onFormSubmit($rightSelect){ 
 	$rightSelect.children().attr("selected", true);
  } 
			
  $("document").ready(
		function() {
			$("#input1").click(
					function() {
						var los = $("#oprSelectArr option");
						for ( var i = 0; i < los.length; i++) {
							if ($(los[i]).attr("selected")) {
								$("#oprSelectedArr").append($(los[i])).children()
										.attr("selected", false);
							}
						}
					});
			$("#input2").click(
					function() {
						var los = $("#oprSelectArr option");
						los.attr("selected", "true");
						$("#oprSelectedArr").append($(los)).children().attr(
								"selected", false);
					});
			$("#input3").click(
					function() {
						var los = $("#oprSelectedArr option");
						for ( var i = 0; i < los.length; i++) {
							if ($(los[i]).attr("selected")) {
								$("#oprSelectArr").append($(los[i])).children()
										.attr("selected", false);
							}
						}
					});
			$("#input4").click(
					function() {
						var los = $("#oprSelectedArr option");
						los.attr("selected", "true");
						$("#oprSelectArr").append($(los)).children().attr(
								"selected", false);
					});

		});
		function selectAll($rightSelect) {
		$rightSelect.children().attr("selected", true);
}
</script>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-role-operategroup-relation/sys-role-operategroup-relation!doAdd.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
		 <div style="border:1">
		    <label>角色:</label>
    		<input type= "text" id= "name" name="sysRole.roleName" value= "${sysRole.roleName}" readonly="readonly" /> 
            <input type="hidden" id= "bbb" name="sysRoleOperategroupRelation.id.roleId" value= "${sysRole.roleId}" readonly="readonly" /> 
		<input type="hidden" id= "bbb" name="roleId" value= "${sysRole.roleId}" readonly="readonly" /> 
    		 
		 </div>
		 
		 <div id="wrapping">
		 	<label>配置:</label>
		 	<table>
				<tr>
					<td rowspan="10">
						<select name="oprSelectArr" size="10" id="oprSelectArr"
							multiple="multiple" style="height: 250px; width: 180px;">
							<c:forEach items="${sysOperateGroupList}" var="sysOperateGroup" >
								<option value="<c:out value="${sysOperateGroup.id}"></c:out>">
									<c:out value="${sysOperateGroup.name}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
					<td rowspan="10">
						<div id="div2">
							<input type="button" id="input1" value="->"><br>
							<input type="button" id="input2" value="->>" /><br>
							<input type="button" id="input3" value="&lt;-" /><br>
							<input type="button" id="input4" value="&lt;&lt;-" /><br>
						</div>
					</td>
					<td>
						<select name="oprSelectedArr" size="10" id="oprSelectedArr" multiple="multiple" style="height: 250px; width: 180px;">
							<c:forEach items="${selectedList}" var="sysOperateGroup" >
								<option value="<c:out value="${sysOperateGroup.id}"></c:out>">
									<c:out value="${sysOperateGroup.name}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
	     </div>
		 </div>
		 <div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick='selectAll($("#oprSelectedArr"))'>保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()">取消</button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>
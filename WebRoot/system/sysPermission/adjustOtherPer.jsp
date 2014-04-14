<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jstl" %>
<%@ include file="/property/common/taglibs.jsp"%>


<script language="JavaScript">
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
      <form method="post" action="sys-permission/sys-permission!doAdd.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
         <div style="border:1">
		    <label>机构:</label>
		    ${sysOrganization.name}
		    <input name="sysPermission.sysOrganization.id" type="hidden" value="${sysOrganization.id}">
			<!-- <select id="bbb" name="sysPermission.sysOrganization.id">
    		<option>请选择机构</option>
    		<c:forEach items="${sysOrganizationList}" var="sysOrganization" >
    			<option value=<c:out value="${sysOrganization.id}" />><c:out value="${sysOrganization.name}" /></option>
    		</c:forEach>
    		</select> -->
		 </div>
		 <div id="wrapping">
		 	<label>配置:</label>
		 	<table>
				<tr>
					<td rowspan="10">全部操作
						<select name="oprSelectArr" size="10" id="oprSelectArr"
							multiple="multiple" style="height: 250px; width: 180px;">
							<c:forEach items="${sysOperateList}" var="sysOperateList" >
								<option value="<c:out value="${sysOperateList.id}"></c:out>">
									<c:out value="${sysOperateList.operateName}"></c:out>
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
					<td>已选操作
						<select name="oprSelectedArr" size="10" id="oprSelectedArr" multiple="multiple" style="height: 250px; width: 180px;">
						<c:forEach items="${sysOperateSelectedList}" var="sysOperate" >
								<option value="<c:out value="${sysOperate.sysOperate.id}"></c:out>">
									<c:out value="${sysOperate.sysOperate.operateName}"></c:out>
								</option>
							</c:forEach>
						
						</select>
					</td>
				</tr>
			</table>
		 	
		 	
		 	<!-- <s:optiontransferselect 
			cssStyle="width:170px;height:270px;" 
			doubleCssStyle="width:170px;height:270px;"
			leftTitle="未选操作"
     		rightTitle="已选操作" 
			name="oprSelectArr"
			list="#request.sysOperateList"
			listKey="id"
            listValue="operateName"
   			doubleName="oprSelectedArr"
   			doubleList="{}"/> -->
	     </div>
	     <div>
	     	<label>权限范围:</label>
	     	<input type="radio" name="sysPermission.scope" value="0">自己本身
	     	<input type="radio" name="sysPermission.scope" value="0">本身和下属
	     </div>
	     <div>
	     	<label>查看其他机构:</label>
	     	<select id="bbb" name="sysPermission.sysOrganization.id">
    		<option>请选择机构</option>
    		<c:forEach items="${sysOrganizationList}" var="sysOrganization" >
    			<option value=<c:out value="${sysOrganization.id}" />><c:out value="${sysOrganization.name}" /></option>
    		</c:forEach>
    		</select>
	     </div>
		 </div>
		 <div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
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
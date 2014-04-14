<%@ page contentType="text/html; charset=utf-8"%>
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
		var gValue="";
		function sqlReadonly(value){
			if(gValue=="")
				gValue = $("#conditionSql").val();
			if(value==-1){
				$("#conditionSql").attr('value','');
				$("#conditionSql").attr('readonly',true);
			}else{
				$("#conditionSql").attr('value',gValue);
				$("#conditionSql").attr('readonly',false);
			}
		}
</script>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sys-permission/sys-permission!doEdit.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden" name="sysSystem.id" value="${sysSystem.id}">
	     <div class="pageFormContent" layoutH="56">
         <div>
		    <label>编号:</label><c:out value="${sysPermission.id}"/>
			<input align="left" name="sysPermission.id" class="required" type="hidden" size="30" 
			       value="<c:out value="${sysPermission.id}"/>" />
		 </div>
		 <div>
		 	<label>机构名称:</label>
		 	${sysPermission.sysOrganization.name}
		 	<input name="sysPermission.sysOrganization.id" type="hidden" size="30" value="${sysPermission.sysOrganization.id}" />
	     </div>
	     <div>
		 	<label>操作名称:</label>
		 	<select name="sysPermission.sysOperate.id" onchange="sqlReadonly(this.value)">
	    		<c:forEach items="${sysOperateList}" var="sysOperate" >
	    			<c:if test="${sysPermission.sysOperate.id==sysOperate.id}">
	    				<option value=<c:out value="${sysOperate.id}" /> selected><c:out value="${sysOperate.operateName}" /></option>
	    			</c:if>
	    			<c:if test="${sysPermission.sysOperate.id!=sysOperate.id}">
	    				<option value=<c:out value="${sysOperate.id}" />><c:out value="${sysOperate.operateName}" /></option>
	    			</c:if>
	    		</c:forEach>
    		</select>
	     </div>
	     <div>
	     	<label>权限范围:</label>
	     	<c:if test="${sysPermission.scope==1}">
				<input type="radio" name="sysPermission.scope" value="1" checked>自己本身
	     		<input type="radio" name="sysPermission.scope" value="2">本身和下属
			</c:if>
	     	<c:if test="${sysPermission.scope==2}">
				<input type="radio" name="sysPermission.scope" value="1">自己本身
	     		<input type="radio" name="sysPermission.scope" value="2" checked>本身和下属
			</c:if>
	     </div>
	     <div>
		 	<label>指定其他机构:</label>
		    <select name="sysPermission.desiOperateId" onchange="sqlReadonly(this.value)">
	    		<option value="-1">不指定</option>
	    		<c:forEach items="${sysOrganizationList}" var="sysOrganization" >
	    			<c:if test="${sysPermission.desiOperateId==sysOrganization.id}">
	    				<option value=<c:out value="${sysOrganization.id}" /> selected><c:out value="${sysOrganization.name}" /></option>
	    			</c:if>
	    			<c:if test="${sysPermission.desiOperateId!=sysOrganization.id}">
	    				<option value=<c:out value="${sysOrganization.id}" />><c:out value="${sysOrganization.name}" /></option>
	    			</c:if>
	    		</c:forEach>
    		</select>
	     </div>
	     <div>
		 	<label>条件sql:</label>
		 	<input onchange="gValue=''" id="conditionSql" name="sysPermission.conditionSql" type="text" size="30"
		        value="${sysPermission.conditionSql}" />
	     </div>
		 </div>
		 <div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('save')"/></button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent">
							<button type="Button" onclick='alertMsg.confirm("<s:property value='getText("editLeaveTips")' />",{okCall:function(){navTab.closeCurrentTab();}})'>
								<s:property value="getText('cancel')"/>
							</button>
						</div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset"><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>

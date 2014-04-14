<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type="text/javascript">
	$(function(){
		parentId = "";
		currentTreeNode = "";
		parentId = "${parentId}";
		currentTreeNode = "${sysOrganization.id}";
	});

</script>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="sys-organization/sys-organization!editOrg.do"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="sysOrganization.id" value="${sysOrganization.id}">
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>
						机构名称:
					</label>
					<s:textfield label="机构名称" name="sysOrganization.name" disabled="true"></s:textfield>
				</p>
				<p>
					<label>
						机构描述:
					</label>
					<input name="sysOrganization.description" type="text" size="30"
						value="${sysOrganization.description}" disabled="disabled"/>
				</p>
				<p>
					<label>
						机构类型:
					</label>
					<s:if test="sysOrganization.type == '1'">
						<input type="radio" name="sysOrganization.orgType" value="1" checked="checked" disabled="disabled">单位 
						<input type="radio" name="sysOrganization.orgType" value="2" disabled="disabled">部门
					</s:if>
					<s:elseif test="sysOrganization.type == '2'">
						<input type="radio" name="sysOrganization.orgType" value="1" disabled="disabled">单位 
						<input type="radio" name="sysOrganization.orgType" value="2" checked="checked" disabled="disabled">部门
					</s:elseif>
					<s:else>
						<input type="radio" name="sysOrganization.orgType" value="1" disabled="disabled">单位
						<input type="radio" name="sysOrganization.orgType" value="2" disabled="disabled">部门
					</s:else>
				</p>
				<!--
					<p>
						<label>
							上级行政机构:
						</label>
						<s:select list="listSysOrganization" listKey="#this.id"
							listValue="#this.name" name="sysOrganization.parent_Adm.id"
							headerKey="" headerValue="----------请选择-----------" />
					</p>
				-->
				<p>
					<label>
						上级职能机构:
					</label>
					<s:select list="listSysOrganization" listKey="#this.id"
						listValue="#this.name" name="sysOrganization.parent_Fun.id"
						headerKey="" headerValue="----------请选择-----------" disabled="true"/>
				</p>
				<p>
					<label>
						上级监管机构:
					</label>
					<s:select list="listSysOrganization" listKey="#this.id"
						listValue="#this.name" name="sysOrganization.parent_Supervise.id"
						headerKey="" headerValue="----------请选择-----------" disabled="true"/>
				</p>
				<p>
				 	<label>顺序:</label>
				 		<input type="text" name="sysOrganization.orgOrder" value="${sysOrganization.orgOrder}" disabled="disabled">
			     </p>
			     
			     <p>
				 	<label>机构级别:</label>
				 		<s:select list='#{"1":"公司","2":"一级单位","3":"基地","4":"科室"}' name="sysOrganization.orgLevel" headerKey="" headerValue="----------请选择-----------" disabled="true"></s:select>
			     </p>
				
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" onclick='alertMsg.confirm("确认要离开吗？",{okCall:function(){navTab.closeCurrentTab();}})'>
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>

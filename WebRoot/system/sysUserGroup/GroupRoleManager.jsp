<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type="text/javascript">
$("document").ready(
		function() {
			$("#input1").click(
					function() {
						var los = $("#select1 option");
						for ( var i = 0; i < los.length; i++) {
							if ($(los[i]).attr("selected")) {
								$("#select2").append($(los[i])).children()
										.attr("selected", false);
							}
						}
					});
			$("#input2").click(
					function() {
						var los = $("#select1 option");
						los.attr("selected", "true");
						$("#select2").append($(los)).children().attr(
								"selected", false);
					});
			$("#input3").click(
					function() {
						var los = $("#select2 option");
						for ( var i = 0; i < los.length; i++) {
							if ($(los[i]).attr("selected")) {
								$("#select1").append($(los[i])).children()
										.attr("selected", false);
							}
						}
					});
			$("#input4").click(
					function() {
						var los = $("#select2 option");
						los.attr("selected", "true");
						$("#select1").append($(los)).children().attr(
								"selected", false);
			});
			
			$("#select1").dblclick(function(){
				var los = $("#select1 option");
						for ( var i = 0; i < los.length; i++) {
							if ($(los[i]).attr("selected")) {
								$("#select2").append($(los[i])).children()
										.attr("selected", false);
						}
				}
			})
			
			
			$("#select2").dblclick(function(){
				var los = $("#select2 option");
						for ( var i = 0; i < los.length; i++) {
							if ($(los[i]).attr("selected")) {
								$("#select1").append($(los[i])).children()
										.attr("selected", false);
						}
				}
			})
		});

//$rightSelect 要全选中的select的jQuery对象
function selectAll($rightSelect) {
	$rightSelect.children().attr("selected", true);
}
</script>

<style>

	#div2 input{
		display: block;
		width: 100px;
		clear: both;
		margin:20px 20px -10px 20px;
	}
	
</style>

<div class="page">
	<div class="pageContent">
		<form method="post"
			action="sys-user-group/sys-user-group!addRoleToGroup.do"
			class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			
			<input type="hidden" name="sysUserGroup.id" value="${sysUserGroup.id}"/>
			<div class="pageFormContent" layoutH="56">
				<div id="wrapping">
				
						<table>
							<tr><td height="20px">请选择角色：</td></tr>
							<tr>
								<td rowspan="10">
									<s:select list="listSysRole" size="10" listKey="roleId" listValue="roleName" id="select1" cssStyle="height: 250px; width: 180px;" multiple="true" name="default"></s:select>
								</td>
								<td rowspan="10">
									<div id="div2">
										<input type="button" id="input1" value="添加到组："><br>
										<input type="button" id="input2" value="全部添加到组：" /><br>
										<input type="button" id="input3" value="从组中删除：" /><br>
										<input type="button" id="input4" value="全部删除：" /><br>
									</div>
								</td>
								<td>
								<s:select list="sysUserGroup.setSysRole" size="10" listKey="roleId" listValue="roleName" id="select2" cssStyle="height: 250px; width: 180px;" name="ids" multiple="true"></s:select>
								</td>
							</tr>
						</table>
					</div>
					</div>
					<div class="formBar">
						<ul>
							<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" onclick='selectAll($("#select2"))'>
											保存
										</button>
									</div>
								</div>
							</li>
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="Button" onclick="navTab.closeCurrentTab()">
											取消
										</button>
									</div>
								</div>
							</li>
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="reset">
											重置
										</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
		</form>
	</div>
</div>

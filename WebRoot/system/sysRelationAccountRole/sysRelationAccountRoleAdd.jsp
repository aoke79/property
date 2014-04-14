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
			$("document").ready(function(){
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
				
				$("#but").click(function(){
				  $("#select2 option").attr("selected","true");
				}); 
			});
		</script>
<style>
#div2 input {
	display: block;
	width: 100px;
	clear: both;
	margin: 20px 20px -10px 20px;
}
</style>

<div class="page">
	<div class="pageContent">
		<form id="myform" method="post"
			action="sys-relation-account-role/sys-relation-account-role!doFp.do"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="100" style="width: 854px">
				<input id="yesId" name="yesId" value="${yesId}" type="hidden">
				<input id="noId" name="noId" value="${noId}" type="hidden">
				<div>
					<label>
						<s:property value="getText('userName')" />
						:
					</label>
					<input type="text" id="name" name="name" value="${cmUser.name}"
						readonly="readonly" />
					<input type="hidden" id="userId" name="userId"
						value="${cmUser.userId}" />
				</div>

				<div id="wrapping">
					
					
					<table>
					    <tr>
							<td height="20px">
							   <font color="#338899" size="15">
								<s:property value="getText('assignRole')" />
								:
								</font>
							</td>
						</tr>
						<tr>
							<td>
								<s:property value="getText('notAssignedRole')" />
							</td>
							<td>
								<s:property value="getText('assignRole')" />
							</td>
							<td>
								<s:property value="getText('roleHasBeen')" />
							</td>
						</tr>
						<tr>
							<td rowspan="10">
								<select name="roleIdNo" size="10" id="select1"
									multiple="multiple" style="height: 250px; width: 180px;">
									${noOptions }
								</select>
							</td>
							<td rowspan="10">
								<input type="button" id="input1" value="-&gt;" style="width: 90px;">
								<br>
								<input type="button" id="input2" value="-&gt;&gt;" style="width: 90px;">
								<br>
								<input type="button" id="input4" value="&lt;&lt;-" style="width: 90px;">
								<br>
								<input type="button" id="input3" value="&lt;-" style="width: 90px;">
								<br>
							</td>
							<td>
								<select name="roleIds" size="10" id="select2"
									multiple="multiple" style="height: 250px; width: 180px;">
									${yesOptions}
								</select>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button id="but" type="submit">
									<s:property value="getText('assign')" />
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" onclick="navTab.closeCurrentTab()">
									<s:property value="getText('cancel')" />
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="reset">
									<s:property value="getText('reset')" />
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>

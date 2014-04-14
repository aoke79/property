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
			$("#input4").click(
					function() {
						var los = $("#select2 option");
						for ( var i = 0; i < los.length; i++) {
							if ($(los[i]).attr("selected")) {
								$("#select1").append($(los[i])).children()
										.attr("selected", false);
							}
						}
					});
			$("#input3").click(
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
			action="sys-relation-account-role/sys-relation-role-account!doFp.do"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<input id="yesId" name="yesId" value="${yesId}" type="hidden">
				<input id="noId" name="noId" value="${noId}" type="hidden">
				<s:if
					test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
					<div>
						<label>
							<s:property value="getText('roleName')" />
							:
						</label>
						<input type="text" id="eroleName" name="eroleName"
							value="${role.eroleName}" readonly="readonly" />
						<input type="hidden" id="roleId" name="roleId"
							value="${role.roleId}" />
					</div>
				</s:if>
				<s:else>
					<div>
						<label>
							<s:property value="getText('roleName')" />
							:
						</label>
						<input type="text" id="roleName" name="roleName"
							value="${role.roleName}" readonly="readonly" />
						<input type="hidden" id="roleId" name="roleId"
							value="${role.roleId}" />
					</div>
				</s:else>

				<div id="wrapping" >
						<table>
						    <tr>
						    <td height="20px">
						    <font color="#338899" size="15">
						    <s:property value="getText('addRoleAccount')" />:
						    </font>
						    </td>
						    </tr>
							<tr>
								<td>
									<s:property value="getText('unaccount')" />
								</td>
								<td>
									<s:property value="getText('assignAccount')" />
								</td>
								<td>
									<s:property value="getText('accountsHaveBeen')" />
								</td>
							</tr>
							<tr>
								<td rowspan="10">
									<select name="roleIdNo" size="10" id="select1"
										multiple="multiple" style="height: 250px; width: 180px;">
										${noOptions}
									</select>
								</td>
								<td rowspan="10">
									<div id="div2">
										<input type="button" id="input1" value="-&gt;" style="width: 90px;"><br>
										<input type="button" id="input2" value="-&gt;&gt;" style="width: 90px;"/><br>
										<input type="button" id="input3" value="&lt;&lt;-" style="width: 90px;"/><br>
										<input type="button" id="input4" value="&lt;-" style="width: 90px;"/><br>
									</div>
								</td>
								<td>
									<select name="userIds" size="10" id="select2"
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

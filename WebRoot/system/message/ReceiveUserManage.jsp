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

function(){
	$(".formBar").children("button").click(function(){
		window.close();
	})
}
</script>

<style>
#div2 input {
	display: block;
	width: 100px;
	clear: both;
	margin: 20px 20px -10px 20px;
}
</style>

<form  method="post" action="sys-message/system-message!chooseUser.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
<table>
	<tr>
		<td height="20px">
			请选择用户：
		</td>
	</tr>
	<tr>
		<td rowspan="10">
			<s:select list="listTbUser" size="10" listKey="userId"
				listValue="name" id="select1"
				cssStyle="height: 250px; width: 180px;" multiple="true"
				name="default"></s:select>
		</td>
		<td rowspan="10">
			<div id="div2">
				<input type="button" id="input1" value="->">
				<br>
				<input type="button" id="input2" value="->>" />
				<br>
				<input type="button" id="input3" value="<-" />
				<br>
				<input type="button" id="input4" value="<<-" />
				<br>
			</div>
		</td>
		<td>
		<s:select list="hasChooseUsers" size="10" listKey="userId"
				listValue="name" id="select2"
				cssStyle="height: 250px; width: 180px;" multiple="true"
				name="ids"></s:select>
		</td>
	</tr>
</table>
<div class="formBar">
	<ul>
		<li>
			<div class="buttonActive">
				<div class="buttonContent">
					<button type="submit" onclick='selectAll($("#select2"));'>
						确定
					</button>
				</div>
			</div>
		</li>
	</ul>
</div>
</form>

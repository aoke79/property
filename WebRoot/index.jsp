<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sinoframe.bean.SysOperate"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安华农业保险</title>

<link href="css/zzstyle.css" rel="stylesheet" type="text/css" />
<link href="css/loading.css" type="text/css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/sytleadmin.css" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery.enplaceholder.js"></script>
<script type="text/javascript" src="/quality/js/dwz.ajax.js"></script>
<script type="text/javascript" src="js/login.js"></script>

<script>
	$(function(){
	$("#regetPswDiv").css("display","none");
		    $("#fade").css("display","none");
		$("input[name='oldPassword']").blur(function(){
			var account=$("input[name='account']").val();
			var oldPwd=$("input[name='oldPassword']").val();
		 	
  			if(!isNull(account,"请输入用户名！")|| !isNull(oldPwd,"请输入旧密码！")){
				return false; 
			}
 			$.post("ah-login/update-password!valid.do",
				{"account":account,"oldPassword":oldPwd},
				function(data){	
					if(!data.right){
						$("#messagePwd").text("账号或密码有误！");
					}else{
						$("#messagePwd").text("");
					}
				},"json");
		});
		$("input[name='reNewPassword']").blur(function(){
			var pwd=$("input[name='newPassword']").val();
			var rePwd=$("input[name='reNewPassword']").val();
		
			if(pwd != rePwd){
				$("#messagePwd").text("重复输入密码不一致，请重新输入");
			}
		});
		focusClear();
		$("#subPwd").click(function(){
			var account=$("input[name='account']").val();
			var oldPwd=$("input[name='oldPassword']").val();
			var pwd=$("input[name='newPassword']").val();
			var rePwd=$("input[name='reNewPassword']").val();
		
			if(!isNull(account,"请输入用户名！")|| !isNull(oldPwd,"请输入旧密码！")|| !isNull(pwd,"密码不可以为空")){
				return false; 
			}
			if(pwd.length<6||pwd.length>30){
				$("#messagePwd").text("密码长度在6-30个字符之间");
				return false;
			}
			if(!isNull(rePwd,"请确认密码")){
				return false;
			}
			if(pwd!=rePwd){
				$("#messagePwd").text("重复输入密码不一致，请重新输入");
				return false;
			}
			$.post("ah-login/update-password!modPwd.do",
				{"account":account,"newPassword":pwd},
				function(data){
					if(data.right){
							alert("密码修改成功！");
							$("#btnClose").click();
					}else{
						$("#messagePwd").text("密码修改失败！");
					}
				},"json");
		});
	});
	
	<%
			String name = "";
			try {
				Cookie[] cookies = request.getCookies();
				//out.println(cookies);
				if (cookies != null) {
				System.out.println(cookies.length);
					for (int i = 0; i < cookies.length; i++) {
						System.out.println(cookies[i].getName());
						System.out.println(cookies[i].getValue());
						if (cookies[i].getName().equals("cookie_user")) {
							name = cookies[i].getValue();
						}
						request.setAttribute("name", name);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}%> 
	
	function  focusClear(){
// 		$("input[name='account']").focus(function(){
// 			$("#messagePwd").text("");
// 		});
		$("input[name='oldPassword']").focus(function(){
			$("#messagePwd").text("");
		});
		$("input[name='reNewPassword']").focus(function(){
			$("#messagePwd").text("");
		});
		$("input[name='newPassword']").focus(function(){
			$("#messagePwd").text("");
		});
	};
	function isNull(val,msg){
		if(val==null||val==""){
			$("#messagePwd").text(msg);
			return false;
		}else return true;
	};
	$(function(){
		$("#chpwd").click(function(){
		var loginName=$("#loginName").val();
		$("input[name='account']").val(loginName);
		  $("#blockDIV").css("display","block");
		  $("#fade").css("display","block");
		});
		$("#btnClose").click(function(){
		  $("#blockDIV").css("display","none");
		  $("#fade").css("display","none");
		});
	});
	
	$(function(){
			$("#login").click(function(){
			var loginName=$("#loginName").val();
			var accountPassword=$("#accountPassword").val();
			var validateCode=$("#validateCode").val();
			if(loginName==null||loginName==""){
				$("#validateImageInfo").html("请输入用户名");
				return;
			}
			if(accountPassword==null||accountPassword==""){
				$("#validateImageInfo").html("请输入密码");
				return;
			}
			$.post("user-info/sys-login-info!login.do",
				{"cmUser.loginName":loginName,"cmUser.accountPassword":accountPassword,"validateCode":validateCode},
				function(data){
					if(data.success){
						$("#loginForm").submit();
					}else{
						$("#validateImageInfo").html(data.msg);
					}
				})
		});
		
		$("#regetPsw").click(function(){
			var loginName=$("#loginName").val();
			if(loginName==null||loginName==""){
				$("#validateImageInfo").html("请输入用户名");
				return;
			}
		  $("#regetPswDiv").css("display","block");
		  $("#fade").css("display","block");
			
		});
		
		//退出忘记密码
		$("#exitRegetPsw").click(function() {
			$("#regetPswDiv").css("display","none");
		    $("#fade").css("display","none");
		});
		//确认忘记密码
		$("#sureRegetPsw").click(function(){
			var loginName=$("#loginName").val();
			$.post("tf-qua-apply/tf-qua-signature!sendMail.do?",
				{"cmUser.loginName":loginName},
				function(dt){
					alert(dt.message.messageInfo);
				},"json")
			$("#regetPswDiv").css("display","none");
		    $("#fade").css("display","none");
			});
		
		//确认选择获取密码方式
		$("#surepass").click(function(){
			var passwordtype=$("#getpassword input:checked").val();
			var loginName=$("#loginName").val();
			if(passwordtype){
				if(passwordtype=="youxiang"){
					$.post("tf-qua-apply/tf-qua-signature!sendMail.do?",
						{"cmUser.loginName":loginName},
						function(dt){
							alert(dt.message.messageInfo);
						},"json")
				}else if(passwordtype=="duanxin"){
					$.post("tf-qua-apply/tf-qua-signature!sendMessage.do?",
						{"cmUser.loginName":loginName},
						function(dt){
							alert(dt.message.messageInfo);
						},"json")
				}
			$("#regetPswDiv").css("display","none");
		    $("#fade").css("display","none");
			}else{
				alert("请选择一种方式！")
			}
			});
			
	});
	
	
	
	
</script>
<style> 
	.span_{float:left;width:60px;height:21px;color:#373332;font:12px "宋体";line-height:23px;margin:0 30 0 18px;}
	.black_overlay{ 
		display: none; 
		position: absolute; 
		top: 0%; 
		left: 0%; 
		width: 100%; 
		height: 100%; 
		background-color: white; 
		z-index:1001; 
		-moz-opacity: 0.8; 
		opacity:.80; 
		filter: alpha(opacity=60); 
	} 
	.white_content { 
		display: none; 
		position: absolute; 
		top: 35%; 
		left: 30%; 
		width: 40%; 
		height: 40%; 
		padding: 16px; 
		border: 2px solid silver; 
		background-color: #c7e6fb; 
		z-index:1002; 
		overflow: auto; 
	} 
</style> 
</head>

<body>
	<form id="loginForm" action="standard!validat.do" method="post">
		<div id="page">
			<div style=" position:absolute;top:30px;left:250px;line-height: 1.5;width:690px; text-align: left;color: red;">
			    <ul style="list-style-type: decimal;">
			    <li style="list-style-type: decimal;">用户名为人力新8位员工编码，请到 人力自助服务----我的档案 -----工作信息 ----- 人员编码 查询。注意：前面的０不可省略，例如某员工新员工编号为4568，用户名应为：00004568 。</li>
			    <li style="list-style-type: decimal;">如果未收到初始密码提示邮件或短信，初次登陆请点击“找回密码”获取密码，密码将以邮件或短信的形式发送至您公司邮箱或在人力信息系统登记的手机号，请尽快登陆修改密码。未注册公司邮箱或手机号码的同事请及时在OA上注册。</li>
			    <li style="list-style-type: decimal;">登陆后看到的均为真实业务系统。</li>
			    <li style="list-style-type: decimal;">如果您发现自己的业务权限异常，请与信息技术部何丰宇联系，邮箱hefengyu@ahic.com.cn, 电话 010-68090590。</li>
			    <li style="list-style-type: decimal;">全部登陆终端IP及操作命令，Portal均有记录，请谨慎操作。</li> 
			    </ul>
		    </div>
			<div id="denglu_kaung" style=" position:absolute;top:140px ;right:49px ;">
				<div id="yonghu">
					<span class="culture_inter">
						<input class="culture_input" name="cmUser.loginName" value="<%=name %>"  maxlength="8" type="text" id="loginName" placeholder="请输入8位员工编码" />
					</span>
				</div>
				<div id="mima">
					<span class="culture_two">
						<input class="culture_in" name="cmUser.accountPassword" type="password" id="accountPassword" value="" placeholder="初次登陆或忘记密码，请先找回密码"/>
						<input type="hidden" value="资质子系统" name="subsystemName" id="subsystemName" />
					</span>
				</div>
				<div id="yanzhengma">
					验证码：<input id="validateCode" name="validateCode" value="" type="text" />
					<img id="yanzhengmaimg" alt="验证码" src="user-info/validate-img.do" 
					onclick="this.src = 'user-info/validate-img.do?'+new Date();"  style="float: right;height: 25px;"/>
				</div>
				<span class="p_tishi" id="validateImageInfo" style="color:red">&nbsp;</span>
				<div class="denglu" style="height: 40px;line-height: 40px;">
					<img src="images/anhua/denglu.png" id="login"
						style=" cursor:pointer" />
					<a id="regetPsw"  href="javascript:void(0)" title="找回密码" style="float: right;">找回密码</a>
				</div>
			</div>
		</div>
	</form>
	
	<!-- =================================================================================================================== -->
<div  id="blockDIV" class="white_content" style="display: none;">
 	<div class="">
 	<table>
 		<thead>
 			<tr>
 				<th align="center" colspan="2">修改密码</th>
 			</tr>
 		</thead>
 		<tbody>
 			<tr>
 				<td><span class="span_" >用户名</span></td><td><input name="account" disabled="disabled"  maxlength="10" minlength="10" size="28" /></td>
 			</tr>
 			<tr>
 				<td><span class="span_" >输入旧密码</span></td><td><input name="oldPassword" type="password" maxlength="30" minlength="5" size="30"/></td>
 			</tr>
 			<tr>
 				<td><span class="span_" >输入新密码</span></td><td><input name="newPassword" type="password" maxlength="30" minlength="6" size="30"/></td>
 			</tr>
 			<tr>
 				<td><span class="span_" >确认新密码</span></td><td><input name="reNewPassword" type="password" size="30"/></td>
 			</tr>
 			<tr>
 				<td colspan="2"><span id="messagePwd" style="color: red;" ></span></td>
 			</tr>
 			<tr>
 				<td align="right" colspan="2"><button type="submit" id="subPwd">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;<button id="btnClose" >关闭</button></td>
 			</tr>
 		</tbody>
 	</table>
 	</div>
</div>
<div id="fade" class="black_overlay"></div> 

 <!-- =================================================================================================================== -->
	 <div id="regetPswDiv" class="partol_alert_mail" style="width: 350px;height: 150px; display: none;">
	    <div class="hello_mail">
	      <span class="nihao">欢迎登陆统一办公门户！</span>
	    </div>
	    <div style="text-align: left;">
	      <span>请选择找回密码方式:</span>
	    </div>
	    <div id="getpassword" style="text-align: left;">
	      <span><input type="radio" name="getpassword" value="youxiang" style="padding:0px 10px;"/>邮箱找回密码</span> 
	      <span><input type="radio" name="getpassword" value="duanxin" style="padding:0px 10px;"/>短信找回密码</span>
	    </div>
	    <input id="surepass" class="we_sure" type="button" value="" />
	    <input id="exitRegetPsw" class="we_goout we_sure" type="button" value="" />
	  </div>
	<!-- =================================================================================================================== -->
<%--	<div id="regetPswDiv" class="partol_alert_mail" >--%>
<%--    <div class="hello_mail">--%>
<%--      <span class="nihao">欢迎登陆统一办公门户！</span>--%>
<%--    </div>--%>
<%--    <div style="text-align: left;">--%>
<%--      <span>密码找回功能只支持安华邮箱，请确认是否已在人力自助服务</span>--%>
<%--    </div>--%>
<%--    <div style="text-align: left;">--%>
<%--      <span>系统申请安华邮箱，如未申请，尽快到HR系统中申请。</span>--%>
<%--    </div>--%>
<%--        <div style="text-align: left;">--%>
<%--      <span>确认密码已丢失？</span>--%>
<%--    </div>--%>
<%--    <input id="sureRegetPsw" type="button" value=""/>--%>
<%--    <input id="exitRegetPsw" type="button" value="" class="we_goout"/>--%>
<%--  </div>--%>
  <!-- =================================================================================================================== -->
</body>
<script type="text/javascript">
//通过value模拟placeholder
	$('#loginName,#accountPassword').placeholder({isUseSpan:true});
</script>

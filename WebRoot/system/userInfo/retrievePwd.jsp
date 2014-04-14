
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码</title>
<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
<script>
	$(function(){
		$("#email").blur(function(){
			var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
 			var email_val = $("#email").val();
 			if(!search_str.test(email_val)){        
				$("#messagePwd").html("请输入正确的邮箱地址");
 				$('#email').focus();
 				$('#email').select();
 				return;
 			}else{
 				$("#messagePwd").html("");
 			}
		});
		$("#subpwd").click(function(){		
			$.post("../../user-info/cm-user!retrievePwd.do", { "cmUser.email": $("#email").val() },
  				function(data){  				
  					if(data.success){
  						$("#table").css("display","none");
   				 		$("#suc_div").css("display","block");		
  					}else{
   				 		$("#messagePwd").html("请输入正确的邮箱地址");
   				 		$('#email').focus();
  					}
  			},"json");
		})
	})
</script>
</head>
 
<body>
<div id="table">
<table border="0" align="center">
	<tr>
		<th class="left" colspan="2">请输入您注册时邮箱</th>
	</tr>
	<tr><td>邮箱:</td>
		<td class="left">
			<div class="oneline">
				<input type="text" id="email" value=""/>
				<div id="messagePwd" style="color:red"></div>
			</div>
		</td>
	</tr>
	<tr>
		<td class="right" valign="top"></td>
		<td class="left">
			<br /><input type="button" id="subpwd" value="提交" />
		</td>
	</tr>
</table>
</div>
<div id="suc_div" style="display:none;" align="center">
    <div class="login_cont06">
	    您的申请已提交成功，请查看您的<span name="cmUser.email"><s:perproty value="#attr.cmUser.email"/></span>邮箱。
    </div>
    <div class="login_cont07">
	    <a class="btn_logintwo" href="javascript:void(0);" onclick="javascript:window.opener=null;window.close();return false;"><span>关闭此页</span></a>
    </div>
</div>
</body>
</html>


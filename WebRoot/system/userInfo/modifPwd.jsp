<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script>
	$(function(){
		
		$("input[name='reAccountPassword']").blur(function(){
			if($("input[name='reAccountPassword']").val()!=$("input[name='accountPassword']").val()){
				$("#messagePwd").text("重复输入密码不一致，请重新输入");
			}
		});
		$("input[name='reAccountPassword']").focus(function(){
			$("#messagePwd").text("");
		});
		$("input[name='accountPassword']").focus(function(){
			$("#messagePwd").text("");
		});
		$("#subPwd").click(function(){
			var pwd=$("input[name='accountPassword']").val();
			var rePwd=$("input[name='reAccountPassword']").val();
			var userId = $("input[name=cmUser.userId]").val();
			if(pwd==null||pwd==""){
				$("#messagePwd").text("密码不可以为空");
				return false;
			}
			if(pwd.length<6||pwd.length>30){
				$("#messagePwd").text("密码长度在6-30个字符之间");
				return false;
			}
			if(rePwd==null||rePwd==""){
				$("#messagePwd").text("请确认密码");
				return false;
			}
			if(pwd!=rePwd){
				$("#messagePwd").text("重复输入密码不一致，请重新输入");
				return false;
			}
			$.post("user-info/cm-user!doPwd.do",
				{"cmUser.accountPassword":$("input[name='accountPassword']").val(),"cmUser.userId":userId,"enter":'${enter}'},
				function(data){
					if(data.success){
						if(data.closeFlag == "userlist"){
							alertMsg.correct("密码修改成功！");
							$.pdialog.closeCurrent();
						} else {
							window.location="user-info/sys-login-info!exit.do";
						}
						
						
					}else{
						$("#messagePwd").text(data.myError);
					}
				})
		})
	})
</script>
 <div class="pageFormContent" layoutH="56">
 	<input type="hidden" name="cmUser.userId" value="${cmUser.userId}" />
 	<div class="pwdBox">
 		<div class="innerPwdBox">
 		<div class="titleBox">-- 密码修改&nbsp;--</div>
 		<div class="borderLinePix hackMargin"></div>
 		<div class="writerBox">
 			<div class="textBox">输入新密码</div>
 			<div class="inputBox">
 				<input name="accountPassword" type="password" maxlength="30" minlength="6" size="30"/>
 			</div>
 		</div>
 		<div class="writerBox">
 			<div class="textBox">确认新密码</div>
 			<div class="inputBox"><input name="reAccountPassword" type="password" size="30"/></div>
 		</div>
 		<div class="borderLinePix hackPadding"></div>
 		<div class="operBox">
 			<span class="hintBox" id="messagePwd"></span>
 			<div class="doBox">
 				<div class="button"><div class="buttonContent">
					<button type="submit" id="subPwd">确定</button>
				</div></div>
 			</div>
 		</div>
 		</div>
 	</div>
		 
</div>

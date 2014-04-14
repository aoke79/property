<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/property/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style>
	.loginFail {width:950px; height:610px; margin:0 auto;}
	.errorBox {margin:1em auto; margin-top:23%; width: 300px; height: 160px; border:1px solid #9ebcc3; background: #e3eefb;}
	.errorTitle {width:280px; height:45px; margin:0 auto;padding-top:5px; text-align: center; font-family:"宋体"; font-size:30px; font-weight:bold;}
	.errorImg {margin-right:1em; margin-bottom:5px;}
	.errorBody {width: 100%;padding: 0.8em;}
	.errorBody p {margin-top: 1.7em; padding-top: -0.8em; line-height: 200%; font-size:16px;}
	.errorBody p span {color:red; font-size: 24px;}
	.errorBody p a {text-decoration: none;}
	.errorBody p a:hover {border-bottom: 1px solid red; text-decoration: none;}
	</style>
		<title>操作超时</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript">
		var second=5;
		$(function() {
			$("#secondSpan").text(second+"");
			var fuc = function(){
				second-=1;
				$("#secondSpan").text(second+"");
				if(second==0) {
					location="<%=basePath%>/index.jsp";
				}else{
					setTimeout(fuc,1000);
				}
			};
			
			fuc();//开始倒计时
			
		});
		</script>
		
</head>
<body>

<div class="loginFail">
	<div class="errorBox">
		<div class="errorTitle">
			<span class="errorImg"><img src="../images/alert_small.png" width="40" height="40" /></span>
			<span>操作超时</span>
		</div>
		<div class="errorBody">
			<p>
			页面将在&nbsp;&nbsp;<span id="secondSpan"></span>
			&nbsp;&nbsp;秒后转回<span>登录页面</span>。<br/>
			如果转入失败，请单击<a href="<%=basePath%>/index.jsp" style="color:red; font-size: 24px;">这里</a>。
			</p>
		</div>
	</div>
</div>

</body>
</html>


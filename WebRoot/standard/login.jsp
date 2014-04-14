<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<title>航空安全管理信息平台</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/index.css" />
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/sms_login.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/plugin.message.link.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery.BlockUI.js"></script>
</head>

<body>
<div id="loginPage" class="loginPage">
	<div id="top"><img src="<%=basePath%>/images/top.jpg" /></div>
	<div id="nav">
		<div id="nav_left"><span>安全管理信息平台首页</span></div>
		<div id="nav_right">
			<ul class="navigator">
				<li><a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=DP" target="_blank">日报</a></li>
				<li class="nav_line"><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=QW" target="_blank">周报</a></li>
				<li class="nav_line"><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=QM" target="_blank">月报</a></li>
				<li class="nav_line"><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=PA" target="_blank">生产讲评会</a></li>
				<li class="nav_line"><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a >行业信息</a></li>
				<li class="nav_line"><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a >中航安全</a></li>
				<li class="nav_line"><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a >文件与通告</a></li>
				<li class="nav_line"><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a >安全文化</a></li>
			</ul>
			<!--<ul>
				<li><a href="#"><span>日报</span></a></li>
				<li><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="#"><span>周报</span></a></li>
				<li><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="#"><span>月报</span></a></li>
				<li><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="#"><span>生产讲评会</span></a></li>
				<li><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="#"><span>行业信息</span></a></li>
				<li><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="#"><span>中航安全</span></a></li>
				<li><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="#"><span>文件与通告</span></a></li>
				<li><img src="<%=basePath%>/images/fenge.jpg" /></li>
				<li><a href="#"><span>安全文化</span></a></li>
			</ul>-->
		</div>
	</div>
	<div id="middle">
		<div id="middle_left">
			<div id="announce">
				<div class="announce_top">
					<div class="fly_img"><img src="<%=basePath%>/images/fly_bs.jpg" /></div>
					<div class="announce_title">最新公告</div>
					<div class="more"><img src="<%=basePath%>/images/more.jpg" /></div>
				</div>
				<div class="announce_middle">
					<div class="middle_line"> <div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
					<div class="middle_line"> <div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
					<div class="middle_line"> <div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
					<div class="middle_line"> <div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
					<div class="middle_line"> <div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
				</div>
				<div class="announce_bottom"></div>
			</div>
			<div id="login">
				<div class="login_form">
					<div class="userBold">用户名</div>
					<div class="inputer">
						<input type="text" class="inputText" id="loginName" name="cmUser.loginName" value="rothenlee" />
					</div>
				</div>
				<div class="login_form">
					<div class="userBold">密&nbsp;&nbsp;&nbsp;&nbsp;码</div>
					<div class="inputer">
						<input type="password" class="inputText" id="accountPassword" name="cmUser.accountPassword" value="123456" />
						<a class="pwdLink" href="system/userInfo/retrievePwd.jsp" title="找回密码">
							<span id="findPwd" class="pwd_icon" onmouseover="showPopupText()"></span>
						</a>
					</div>
				</div>
				<div class="login_form">
					<div class="decideBlock">
						<div id="validateCaption"></div>
						<div id="login_block"><input id="doLogin" type="image" src="<%=basePath%>/images/login_bt.jpg" /></div>
						<div id="hyperZone"><a class="quick" href="<%=basePath%>sec-event-free/sec-event-free!autoReportBase.do?enterFlag=add">自愿报告</a></div>
						<div id="hyperSearch">
							<a href="<%=basePath%>sec-event-free/sec-event-free!autoReportBase.do?searchFlag=false&enterFlag=search">
								<span id="search" class="qsearch" onmouseover="showPopupText()"></span>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div id="quickLink">
				<div class="tools">
					<span class="tool_title">[快速链接]</span>
					<ul>
						<li><a href="#">OA</a></li>
						<li><a href="#">QAR</a></li>
						<!--<li><a href="#">自愿报告</a></li>-->
					</ul>
					
				</div>
			</div>
			<div id="announce">
				<div class="announce_top">
					<div class="fly_img"><img src="<%=basePath%>/images/fly_bs.jpg" /></div>
					<div class="announce_title">行业信息</div>
					<div class="more"><img src="<%=basePath%>/images/more.jpg" /></div>
				</div>
				<div class="announce_middle">
					<div class="middle_line">
						<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
					<div class="middle_line">
						<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
					<div class="middle_line">
						<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
					<div class="middle_line">
						<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div class="announce_content"><a href="#">免费办理曼谷航线客票改期手续的行李</a></div>
					</div>
				</div>
				<div class="announce_bottom"></div>
			</div>
		</div>
		<div id="middle_block">
	    	<div id="float_item"><img src="<%=basePath%>/images/hd1.jpg" /></div>
	    	<div id="mainBlock">
		    	<div id="mBlockTop"></div>
		    	<div id="mBlock">
			    <div id="floatPageT">
				    <div class="pageFileT">
				    <div id="triFileBlock">
				    <ul>
				    	<li class="activeTab" cape="blockPageA">日报</li>
				    	<li class="hoverTab" cape="blockPageB">周报</li>
				    	<li class="hoverTab" cape="blockPageC">月报</li>
				    </ul>
				    </div>
				    <div id="sheetT">
				    <div cape="contentA" class="smartShow">
					    <s:iterator value="dayReportList" var="dayfile">
					    <div class="announce_content">
					    	<a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=DP&pid=${pid}" target="_blank">${dataName}</a>
					    	<!--<a href="#" title="${dataName}">${dataName}</a>
					    	<div id="drWrap" class="wrappedBlock" style="display:none">
					    		<iframe style="width:100%; height:100%; " src="login!viewInfo.do?pid=${pid}"></iframe>
					    	</div>-->
					    </div>
					    </s:iterator>
				    </div>
				    <div cape="contentB" class="smartHide">
					    <s:iterator value="qarWeekList" var="weekfile">
					    <div class="announce_content">
					    	<a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=QW&pid=${pid}" target="_blank">${dataName}</a>
					    	<!--<a href="#" title="${dataName}">${dataName}</a>
					    	<div id="qwWrap" class="wrappedBlock" style="display:none">
					    		<iframe style="width:100%; height:100%; " src="login!viewInfo.do?pid=${pid}"></iframe>
					    	</div>-->
					    </div>
					    </s:iterator>
				    </div>
				    <div cape="contentC" class="smartHide">
				    	<s:iterator value="qarMonthList" var="monthfile">
					    <div class="announce_content">
					    	<a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=QM&pid=${pid}" target="_blank">${dataName}</a>
					    	<!--<a href="#" title="${dataName}">${dataName}</a>
					    	<div id="qmWrap" class="wrappedBlock" style="display:none">
					    		<iframe style="width:100%; height:100%; " src="login!viewInfo.do?pid=${pid}"></iframe>
					    	</div>-->
					    </div>
					    </s:iterator>
				    </div>
				    </div>
					</div>
				</div>
				<div id="mBlockRight">
					<div class="right_top">
						<div class="fly_img"><img src="<%=basePath%>/images/fly1.jpg" /></div>
						<div class="announce_title">生产讲评会</div>
						<div class="more"><img src="<%=basePath%>/images/more.jpg" /></div>
					</div>
					<s:iterator value="productionList" var="productfile">
					<div class="middle_line">
						<div class="announce_content">
							<a href="<%=basePath%>/login!toDetailFromPortalType.do?pageType=PA&pid=${pid}" target="_blank">${dataName}</a>
						</div>
					</div>
					</s:iterator>
				</div>
				</div>
				<div id="mBlockBottom"></div>
			</div>
		</div>
		<div id="middle_right">
			<div id="manual">
				<div class="manual_top">
					<div class="fly_img"><img src="<%=basePath%>/images/fly_bs.jpg" /></div>
					<div class="announce_title">SMS手册</div>
					<div class="more"><img src="<%=basePath%>/images/more.jpg" /></div>
				</div>
				<div class="manual_middle">
					<s:iterator value="manualList" var="safetyfile">
					<div class="manual_line">
						<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
						<div cape="manual" class="manual_content">
							<a href="#" title="${dataName}">${dataName}</a>
							<div id="smWrap" class="wrappedBlock" style="display:none">
								<iframe style="width:100%; height:100%; " src="login!viewInfo.do?pid=${pid}"></iframe>
						    </div>
						</div>
					</div>
					</s:iterator>
					
				</div>
				<div class="manual_bottom"></div>
			</div>
			<div id="floatPageS">
				<div class="pageFileS">
					<div id="secFileBlock">
					<ul>
						<li class="activeTabS" cape="cubePageA">中航安全</li>
						<li class="hoverTabS" cape="cubePageB">安全文化</li>
					</ul>
					</div> 
					<div id="sheetS"> 
					<div class="smartShow" cape="infoA">
					<div class="manual_middle_compact">
						<div class="manual_line">
							<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
							<div class="manual_content"><a href="#">安全内容</a></div>
						</div>
						<s:iterator value="aviationSafetyList" var="safetyfile">
					    <div class="manual_middle_compact">
							<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
							<div cape="content" class="manual_content">
								<a href="#" title="${dataName}">${dataName}</a>
								<div id="asWrap" class="wrappedBlock" style="display:none">
									<iframe style="width:100%; height:100%; " src="login!viewInfo.do?pid=${pid}"></iframe>
						    	</div>
							</div>
						</div>
					    </s:iterator>
					</div>
					</div> 
					<div class="smartHide" cape="infoB">
					<div class="manual_middle_compact">
						<div class="manual_line">
							<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
							<div class="manual_content"><a href="#">文化信息</a></div>
						</div>
						<s:iterator value="safetyLectureList" var="lecturefile">
					    <div class="manual_middle_compact">
							<div class="redColumn"><img src="<%=basePath%>/images/bs_red.jpg" /></div>
							<div cape="info" class="manual_content"><a href="#" title="${dataName}">${dataName}</a></div>
							<div id="slWrap" class="wrappedBlock" style="display:none">
								<iframe style="width:100%; height:100%; " src="login!viewInfo.do?pid=${pid}"></iframe>
					    	</div>
						</div>
					    </s:iterator>
					</div>
					</div>
					</div>
					<div class="manual_bottom"></div>
				</div>
			</div>
			<div id="smartTool">
				<div class="tools">
					<span class="tool_title">[小工具]</span>
					<a class="toolItem" href="#">通讯录</a>
					<a class="toolItem" href="#">文件上传</a>
				</div>
			</div>
		</div>
	</div>
	<div id="bottom">
		<!--<div id="logo"><img src="<%=basePath%>/images/logo.jpg" /></div>
		<div id="nav_bt">
			<a href="#">关于我们</a> | <a href="#">人才招聘</a> | <a href="#">网址导航</a> | <a href="#">联系方式</a> | <a href="#">电子意见</a>
		</div>-->
		<div id="nav_bottom">coryright@a star alliance member </div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		//QAR周报
		$("div[cape=contentB]").find("a").click(function(){
			 $.blockUI({
				 css: {
				 	width: '310px',
				 	height: 'auto',
				 	border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#1a4d85', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: 1, 
		            color: '#fff',
		            cursor: 'default'
				 },
				 message: $("#qwWrap")
			 });
			 $('.blockOverlay').click($.unblockUI);
		})
		//日报
		$("div[cape=contentA]").find("a").click(function(){
			 $.blockUI({
				 css: {
				 	width: '310px',
				 	height: 'auto',
				 	border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#1a4d85', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: 1, 
		            color: '#fff',
		            cursor: 'default'
				 },
				 message: $("#drWrap")
			 });
			 $('.blockOverlay').click($.unblockUI);
		})
		//QAR月报
		$("div[cape=contentC]").find("a").click(function(){
			 $.blockUI({
				 css: {
				 	width: '310px',
				 	height: 'auto',
				 	border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#1a4d85', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: 1, 
		            color: '#fff',
		            cursor: 'default'
				 },
				 message: $("#qmWrap")
			 });
			 $('.blockOverlay').click($.unblockUI);
		})
		//航空安全
		$("div[cape=content]").find("a").click(function(){
			 $.blockUI({
				 css: {
				 	width: '310px',
				 	height: 'auto',
				 	border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#1a4d85', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: 1, 
		            color: '#fff',
		            cursor: 'default'
				 },
				 message: $("#slWrap")
			 });
			 $('.blockOverlay').click($.unblockUI);
		})
		//安全文化
		$("div[cape=info]").find("a").click(function(){
			 $.blockUI({
				 css: {
				 	width: '310px',
				 	height: 'auto',
				 	border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#1a4d85', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: 1, 
		            color: '#fff',
		            cursor: 'default'
				 },
				 message: $("#slWrap")
			 });
			 $('.blockOverlay').click($.unblockUI);
		})
		//SMS手册 
		$("div[cape=manual]").find("a").click(function(){
			 $.blockUI({
				 css: {
				 	width: '310px',
				 	height: 'auto',
				 	border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#1a4d85', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: 1, 
		            color: '#fff',
		            cursor: 'default'
				 },
				 message: $("#smWrap")
			 });
			 $('.blockOverlay').click($.unblockUI);
		})
		//生产讲评会 
		$("div[cape=production]").find("a").click(function(){
			 $.blockUI({
				 css: {
				 	width: '310px',
				 	height: 'auto',
				 	border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#1a4d85', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: 1, 
		            color: '#fff',
		            cursor: 'default'
				 },
				 message: $("#paWrap")
			 });
			 $('.blockOverlay').click($.unblockUI);
		})
	})
</script>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<title>安华农业保险</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/sytleadmin.css" />
<link href="css/zzstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/quality/js/dwz.ajax.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<style> 
	.black_overlay{ 
		position: absolute; 
		top: 0%; 
		left: 0%; 
		width: 100%; 
		height: 100%; 
		background-color: #000; 
		z-index:1001; 
		-moz-opacity: 0.8; 
		opacity:.80; 
		filter:alpha(opacity=80);
	} 
	
</style> 
<script>
	$(function(){
		//修改密码
		    var loginName="${session.user.username}";
			$("#chpwd").click(function(){
			  $("#blockDIV").css("display","block");
			  $("#fade").css("display","block");
			});
			//关闭修改密码
			$("#btnClose").click(function(){
				if("${session.user.password}"=="14e1b600b1fd579f47433b88e8d85291"){
				  $("#exitlog")[0].click();
		    	 }else{
		    			$("#blockDIV").css("display","none");
			 			$("#fade").css("display","none");
			 			$("input[name='oldPassword']").attr("value","");
						$("input[name='newPassword']").attr("value","");
						$("input[name='reNewPassword']").attr("value","");
						document.getElementById("strength_L").style.background="#eeeeee";
						document.getElementById("strength_M").style.background="#eeeeee";
						document.getElementById("strength_H").style.background="#eeeeee";
		    	 }
			 
			});
			//判断是否为空
			function isNull(val,msg){
				if(val==null||val==""){
					$("#messagePwd").text(msg);
					return false;
				}else return true;
			};
			//判断账号或密码是否有误
			$("input[name='oldPassword']").blur(function(){
			var oldPwd=$("input[name='oldPassword']").val();
  			if(!isNull(oldPwd,"请输入旧密码！")){
				return false; 
			}
 			$.post("ah-login/update-password!valid.do",
				{"account":loginName,"oldPassword":oldPwd},
				function(data){	
					if(!data.right){
						$("#messagePwd").text("账号或密码有误！");
					}else{
						$("#messagePwd").text("");
					}
				},"json");
			});
			//重复输入密码是否一致
			$("input[name='reNewPassword']").blur(function(){
			var pwd=$("input[name='newPassword']").val();
			var rePwd=$("input[name='reNewPassword']").val();
		
			if(pwd != rePwd){
				$("#messagePwd").text("重复输入密码不一致，请重新输入");
			}
			});
			//确定修改密码
			$("#subPwd").click(function(){
		    var oldPwd=$("input[name='oldPassword']").val();
			var pwd=$("input[name='newPassword']").val();
			var rePwd=$("input[name='reNewPassword']").val();
			if(!isNull(pwd,"密码不可以为空")){
				return false; 
			}
			S_level=checkStrong(pwd);
			if(S_level<2){
				$("#messagePwd").text("密码强度不够，重新输入");
				return false; 
			}
			if(pwd==oldPwd){
				$("#messagePwd").text("新旧密码不能相同，请重新输入");
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
				{"account":loginName,"newPassword":pwd},
				function(data){
					if(data.right){
							if("${session.user.password}"=="14e1b600b1fd579f47433b88e8d85291"){
								  alert("初始密码修改成功，请重新登录");
								  $("#exitlog")[0].click();
							}else{
								alert("密码修改成功！");
								$("#btnClose").click();
							}
							
					}
				},"json");
		});
		//退出审计
		$("#exitSJ").click(function() {
			$("#exitlog")[0].click();
		});
		//确认审计
		$("#sureSJ").click(function(){
			//添加登录日志信息
				$.post("ah-login/anhua-log!updatelog.do",
				{"account":loginName,"sysid":"21","opname":"登录系统"},"json");
			  	$("#shenji").css("display","none");
			 	$("#fade").css("display","none");
			 	//判断当前密码是否为初始密码
			 	 if("${session.user.password}"=="14e1b600b1fd579f47433b88e8d85291"){
				  alert("您现在的密码是初始密码，请修改密码");
		    	  $("#blockDIV").css("display","block");
			      $("#fade").css("display","block");
		    }
			});
		 
		//添加退出日志信息
		$("#exitlog").click(function(){
			var loginName="${session.user.username}";
			$.post("ah-login/anhua-log!updatelog.do",
			{"account":loginName,"sysid":"21","opname":"退出系统"},"json");
		});
		
	});
	
	function skip(flag){
	  $.post("tf-qua-apply/tf-qua-signature!sessionName.do",{flag:flag},
			function(data){
					if(data.ahUser !=null){
							 $("#partolindexJsp").find("#"+flag).submit();
					}else{
						alert("连接超时，请重新登录");
						location="<%=basePath%>/index.jsp";
	                	//window.location.reload();
					}
				},"json");
	  
	   //$("#partolindexJsp").find("#"+flag).submit();
	   var loginName="${session.user.username}";
	   //添加登录日志信息
		$.post("ah-login/anhua-log!updatelog.do",
		{"account":loginName,"sysid":flag,"opname":"登录系统"},"json");
	};
	
	function log(sysid){
	 $.post("tf-qua-apply/tf-qua-signature!sessionName.do",{},
			function(data){
					if(data.ahUser !=null){
					}else{
						alert("连接超时，请重新登录");
						location="<%=basePath%>/index.jsp";
					}
				},"json");
	   var loginName="${session.user.username}";
	   //添加登录日志信息
		$.post("ah-login/anhua-log!updatelog.do",
		{"account":loginName,"sysid":sysid,"opname":"登录系统"},"json");
	};
	
	//CharMode函数
//测试某个字符是属于哪一类
function CharMode(iN) {
   if (iN>=48 && iN <=57) //数字
    return 1;
   if (iN>=65 && iN <=90) //大写字母
    return 2;
   if (iN>=97 && iN <=122) //小写
    return 4;
   else
    return 8; //特殊字符
}

//bitTotal函数
//计算出当前密码当中一共有多少种模式
function bitTotal(num) {
   modes=0;
   for (i=0;i<4;i++) {
    if (num & 1) modes++;
     num>>>=1;
    }
   return modes;
}

//checkStrong函数
//返回密码的强度级别
function checkStrong(sPW) {
   if (sPW.length<=4)
    return 0; //密码太短
    Modes=0;
    for (i=0;i<sPW.length;i++) {
     //测试每一个字符的类别并统计一共有多少种模式
     Modes|=CharMode(sPW.charCodeAt(i));
   }
   return bitTotal(Modes);
}

//pwStrength函数
//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色

function pwStrength(pwd) {
   O_color="#eeeeee";
   L_color="#FF0000";
   M_color="#FF9900";
   H_color="#33CC00";
   if (pwd==null||pwd==''){
    Lcolor=Mcolor=Hcolor=O_color;
   }
   else {
    S_level=checkStrong(pwd);
    switch(S_level) {
    case 0:
     Lcolor=Mcolor=Hcolor=O_color;
    case 1:
     Lcolor=L_color;
     Mcolor=Hcolor=O_color;
    break;
    case 2:
     Lcolor=Mcolor=M_color;
     Hcolor=O_color;
    break;
    default:
     Lcolor=Mcolor=Hcolor=H_color;
    }
   }
   document.getElementById("strength_L").style.background=Lcolor;
   document.getElementById("strength_M").style.background=Mcolor;
   document.getElementById("strength_H").style.background=Hcolor;
return;
}
	
</script>
</head>
<body>
  <div class="partol_k" id="partolindexJsp">
       <div class="partol_nr">
          <div class="partol_top">
             <div class="welcome_wz"><a id="exitlog" href="user-info/sys-login-info!exit.do" >退出</a></div>
            <div class="top_ico">
            <img src="images/0001.png" width="9" height="12" /></div>
            <div class="welcome_wz"><a id="chpwd"  href="#" >修改密码</a></div>
            <div class="top_ico"><img src="images/0000.png" width="12" height="12" /></div>
            <div class="welcome_wz">欢迎您  <span id="yonghuming" >${session.user.name}</span>!</div>
             
          
         </div>
         
         <div class="partol_content" style="display: block;">
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("办公系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("办公系统")'>
									<a
										href="javascript:void(0);" onclick="skip('01');"><img src="images/0006.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0005.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("办公系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("办公系统")'>
									<form id="01" action="http://10.0.7.28:8080/oawebservice/oa_center?" method="post" target="_blank">
								     <input type="hidden" name="username" value="${login }"></input>
								  	 <input type="hidden"  name="password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('01');" >办公系统</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">办公系统</a>
						</s:else>
					</div>
				</div>
				
				<div class="partol_ico_k">
					
					<div class="partol_img">
						<s:if test='clientSystemName.contains("工作日志")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("工作日志")'>
									<a href="javascript:void(0);" onclick="skip('05');"><img src="images/0015.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0014.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("工作日志")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("工作日志")'>
								  <form id="05" action="http://10.0.7.50:8080/LoginServlet.servlet?" method="post" target="_blank">
								     <input type="hidden" name="username" value="${login }"></input>
								  	 <input type="hidden"  name="password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('05');" >工作日志</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">工作日志</a>
						</s:else>
					</div>
				</div>
				
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("费控系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("费控系统")'>
									<a href="javascript:void(0);" onclick="skip('08');"><img src="images/0023.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0022.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("费控系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("费控系统")'>
								  <form id="08" action="http://10.0.5.47:8096/hec/loginPortal.jsp?" method="post" target="_blank">
								     <input type="hidden" name="username" value="${login }"></input>
								  	 <input type="hidden"  name="password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('08');" >费控系统</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">费控系统</a>
						</s:else>
					</div>
				</div>


				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("人力资源")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("人力资源")'>
									<a
										href="http://10.0.7.42/ahic/HRLogin.jsp?psncode=${password }" onclick="skip('04');"
										target="_blank"><img src="images/0009.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0008.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("人力资源")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("人力资源")'>
									<a
										href="http://10.0.7.42/ahic/HRLogin.jsp?psncode=${password }" onclick="skip('04');"
										target="_blank">人力业务系统</a>

								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">人力业务系统</a>
						</s:else>
					</div>
				</div>
				
			    <div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("人力资源")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("人力资源")'>
									<a
										href="http://10.0.7.42/ahic/HRSSLogin.jsp?psncode=${password}" onclick="log('20');"
										target="_blank"><img src="images/0042.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0043.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("人力资源")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("人力资源")'>
									<a
										href="http://10.0.7.42/ahic/HRSSLogin.jsp?psncode=${password }" onclick="log('20');"
										target="_blank">人力自助服务系统</a>

								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">人力自助服务系统</a>
						</s:else>
					</div>
				</div>
				
								<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("统计分析")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("统计分析")'>
									<a
										href="javascript:void(0);" onclick="skip('03');" >
										<img src="images/0011.png" width="50" height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0010.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("统计分析")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("统计分析")'>
								  <form id="03" action="http://10.0.5.42:7001/anareport/login.do?actionType=login" method="post" target="_blank">
								     <input type="hidden" name="userCode" value="${login }"></input>
								  	 <input type="hidden"  name="password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('03');" >统计分析</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">统计分析</a>
						</s:else>

					</div>
				</div>
				
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("核保核赔")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("核保核赔")'>
									<a href="javascript:void(0);" onclick="skip('15');"><img src="images/0007.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0002.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("核保核赔")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("核保核赔")'>
<!-- 									<a -->
<!-- 										href="http://10.0.5.20:7002/undwrt/uwcommon/BLUwCommonLogonSubmit.jsp?UserCode=${login }&Password=${password}" -->
<!-- 										target="_blank">核保核赔</a> -->
								<form id="15" action="http://10.0.5.20:7002/undwrt/uwcommon/BLUwCommonLogonSubmit.jsp" method="post" target="_blank">
								     <input type="hidden" name="UserCode" value="${login }"></input>
								  	 <input type="hidden"  name="Password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('15');" >核保核赔</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">核保核赔</a>
						</s:else>
					</div>
				</div>
				
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("核心业务")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("核心业务")'>
									<a href="javascript:void(0);" onclick="skip('06');"><img src="images/hx1.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0012.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("核心业务")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("核心业务")'>
<!-- 									<a -->
<!-- 										href="http://10.0.5.20:7001/prpall/UICentralControl?SelfPage=/common/pub/UILogonInput.jsp&UserCode=0000003589&Password=4A7D1ED414474E4033AC29CCB8653D9B&RiskCode=DDA&LogonType=Single" -->
<!-- 										target="_blank">核心业务</a> -->
								  <form id="06" action="http://10.0.5.20:7001/prpall/UICentralControl?SelfPage=/common/pub/UILogonInput.jsp&RiskCode=DDA&LogonType=Single" method="post" target="_blank">
								     <input type="hidden" name="UserCode" value="${login }"></input>
								  	 <input type="hidden"  name="Password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('06');" >核心业务</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">核心业务</a>
						</s:else>
					</div>
				</div>
				<div class="partol_ico_k">
					<div class="partol_img">
						<a href="http://10.0.7.76:19000/workspace/index.jsp"  target="_blank" onclick="log('07');"> 
						<img src="images/0028.png" width="50" height="50" />
						</a>
					</div>
					<div class="partol_wz_mc">
					<a href="http://10.0.7.76:19000/workspace/index.jsp" target="_blank" onclick="log('07');">预算系统</a>
			 </div>
           </div>
          <div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("绩效考核")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("绩效考核")'>
									<a href="javascript:void(0);" onclick="skip('22');"><img src="images/js1.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/js2.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("绩效考核")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("绩效考核")'>
								<form id="22" action="http://10.0.7.37/System/login.aspx" method="post" target="_blank">
								     <input type="hidden" name="username" value="${login }"></input>
								  	 <input type="hidden"  name="password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('22');" >绩效考核</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">绩效考核</a>
						</s:else>
					</div>
				</div>
				
				<div class="partol_ico_k">
					
					<div class="partol_img">
						<a href="https://10.0.0.105/iTMS/login_main.jsp"  target="_blank" onclick="log('09');"> 
						<img src="images/0031.png" width="50" height="50" />
						</a>
					</div>
					<div class="partol_wz_mc">
					<a href="https://10.0.0.105/iTMS/login_main.jsp" target="_blank" onclick="log('09');">资金管理</a>
			 </div>
				</div>
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("财务系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("财务系统")'>
									<a
										href="AHICFI://abc10abc"
										target="_blank"><img src="images/0029.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0026.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("财务系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("财务系统")'>
									<a
										href="AHICFI://abc10abc"
										target="_blank">财务系统</a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">财务系统</a>
						</s:else>
					</div>
				</div>
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("新农险")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("新农险")'>
									<a
										href="AHICCore://abc19abc"
										target="_blank"><img src="images/0040.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0041.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("新农险")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("新农险")'>
									<a
										href="javascript:void(0);"
										target="_blank">新农险系统</a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">新农险系统</a>
						</s:else>
					</div>
				</div>
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("销售管理")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("销售管理")'>
										<a href="javascript:void(0);" onclick="skip('13');"><img src="images/0017.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0016.png"
								width="50" height="50" /> </a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("销售管理")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("销售管理")'>
<!-- 									<a -->
<!-- 										href="http://10.0.5.20:7018/sales/login.do?actionType=login&userCode=${login }&password=${password }&systemCode=sales&comCode=00000000&comName=" -->
<!-- 										target="_blank">销售管理</a> -->
									<form id="13" action="http://10.0.5.20:7018/sales/login.do?actionType=singleChooseComCode" method="post" target="_blank">
								     <input type="hidden" name="userCode" value="${login }"></input>
								  	 <input type="hidden"  name="password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('13');" >销售管理</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">销售管理</a>
						</s:else>
					</div>
				</div>
					<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("单证系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("单证系统")'>
									<a
										href="javascript:void(0);" onclick="skip('12');"><img src="images/0035.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0034.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("单证系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("单证系统")'>
								  <form id="12" action="http://10.0.5.20:7003/visa/logonin.do?UIAction=LogOn" method="post" target="_blank">
								     <input type="hidden" name="prpDuserUserCode" value="${login }"></input>
								  	 <input type="hidden"  name="prpDuserPassword" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('12');" >单证系统</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">单证系统</a>
						</s:else>
					</div>
				</div>
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("再保分入")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("再保分入")'>
									<a
										href="javascript:void(0);" onclick="skip('17');"><img src="images/0039.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0038.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc_1">
						<s:if test='clientSystemName.contains("再保分入")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("再保分入")'>
<!-- 									<a -->
<!-- 										href="http://10.0.5.20:7007/reins/logonin.do?UIAction=LogOn&system=I&prpDuserUserCode=${login }&prpDuserPassword=${password}" -->
<!-- 										target="_blank">再保系统分入子系统</a> -->
										
								  <form id="17" action="http://10.0.5.20:7007/reins/logonin.do?UIAction=LogOn&system=I" method="post" target="_blank">
								     <input type="hidden" name="prpDuserUserCode" value="${login }"></input>
								  	 <input type="hidden"  name="prpDuserPassword" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('17');" >再保系统<br />分入子系统</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">再保系统<br /> 分入子系统</a>
						</s:else>
					</div>
				</div>
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("再保分出")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("再保分出")'>
									<a
										href="javascript:void(0);" onclick="skip('18');"><img src="images/0036.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/0037.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc_1">
						<s:if test='clientSystemName.contains("再保分出")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("再保分出")'>
<!-- 									<a -->
<!-- 										href="http://10.0.5.20:7007/reins/logonin.do?UIAction=LogOn&system=O&prpDuserUserCode=${login }&prpDuserPassword=${password}" -->
<!-- 										target="_blank">再保系统分出子系统</a> -->
								 <form id="18" action="http://10.0.5.20:7007/reins/logonin.do?UIAction=LogOn&system=O" method="post" target="_blank">
								     <input type="hidden" name="prpDuserUserCode" value="${login }"></input>
								  	 <input type="hidden"  name="prpDuserPassword" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('18');" >再保系统<br />分出子系统</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">再保系统<br />分出子系统</a>
						</s:else>
					</div>
				</div>
				
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("审计系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("审计系统")'>
									<a href="javascript:void(0);" onclick="skip('23');"><img src="images/shenji.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/shenji_02.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("审计系统")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("审计系统")'>
								<form id="23" action="http://10.0.7.139:8888/ais/uncasauth/loginAH.jsp?" method="post" target="_blank">
								     <input type="hidden" name="userCode" value="${login }"></input>
<%--								  	 <input type="hidden"  name="Password" value="${password }" />--%>
								  	 <a href="javascript:void(0);" onclick="skip('23');" >稽核审计管理系统</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">稽核审计管理系统</a>
						</s:else>
					</div>
				</div>
				
				
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("GIS门户")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("GIS门户")'>
									<a href="javascript:void(0);" onclick="skip('24');"><img src="images/gis.png" width="50"
										height="50" /> </a>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"><img src="images/gis_02.png"
								width="50" height="50" />
							</a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("GIS门户")'>
							<s:iterator value="Ahdetaillist">
								<s:if test='sysname.contains("GIS门户")'>
								<form id="24" action="http://10.0.5.52:7001/anhuagis/MdLoginServlet.servlet?" method="post" target="_blank">
								     <input type="hidden" name="username" value="${login }"></input>
								  	 <input type="hidden"  name="password" value="${password }" />
								  	 <a href="javascript:void(0);" onclick="skip('24');" >GIS门户</a>
								  </form>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">GIS门户</a>
						</s:else>
					</div>
				</div>
				
				
				<div class="partol_ico_k">
					<div class="partol_img">
						<s:if test='clientSystemName.contains("管理系统")'>
							<a href="standard.do?cmUser.loginName=${session.user.useruuid}" target="_blank" onclick="log('00');" > <img src="images/0019.png" width="50"
								height="50" /> </a>
						</s:if>
						<s:else>
							<a href="javascript:void(0);"> <img src="images/0018.png"
								width="50" height="50" /> </a>
						</s:else>
					</div>
					<div class="partol_wz_mc">
						<s:if test='clientSystemName.contains("管理系统")'>
							<a href="standard.do?cmUser.loginName=${session.user.useruuid}" target="_blank" onclick="log('00');">管理系统</a>

						</s:if>
						<s:else>
							<a class="nothas" href="javascript:void(0);">管理系统</a>
						</s:else>
					</div>
				</div>

			</div>
       </div>
       <div class="partol_bt"><img src="images/bt_img.jpg" width="1076" height="107" /></div>
  </div>
<!-- =================================================================================================================== -->
	<div id="blockDIV" class="partol_alert_pass" style="display: none;">
	    <div>
	      <span>现密码：</span>
	      <input name="oldPassword" type="password" maxlength="18" minlength="5" size="30" />
	    </div>
	    <div class="new_poss">
	      <span>新密码：</span>
	      <input name="newPassword" type="password" maxlength="18" minlength="6" size="30" onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)" />
	    </div>
	    <div style="margin-top: 0px;margin-left: 120px;height: 20px;">
		    <table width="200px" height="15" border="0" cellspacing="0" cellpadding="1" bordercolor="#cccccc" style='font-size:12px'>
				<tr align="center" bgcolor="#eeeeee">
				   <td width="33%" id="strength_L">弱</td>
				   <td width="33%" id="strength_M">中</td>
				   <td width="33%" id="strength_H">强</td>
				</tr>
			</table>
	    </div>
	    <div class="all_poss" style="margin-top: 0px;">
	      <span>确认新密码：</span>
	      <input name="reNewPassword" type="password" size="30"/>
	    </div>
	    <span id="messagePwd" style="color: red;" ></span>
	    <p style="margin-top: 0px;">注：密码为 6-18位字符，由字母、数字、符号组成</p>
	    <input id="subPwd" type="button" value="" class="poss_xiugai" />
	    <input id="btnClose" type="button" value="" class="we_goout" />
  </div>
    <!-- =================================================================================================================== -->
	<div id="fade" class="black_overlay"></div> 
	<!-- =================================================================================================================== -->
	<div id="shenji" class="partol_alert_welcome" >
    <div class="hello_someone">
      <span>${session.user.name},&nbsp;</span>
      <span class="ninhao">您好！欢迎登陆统一办公门户！</span>
    </div>
    <div style="text-align: left;">
      <span>您的终端IP及全部操作都已纳入审计，请谨慎操作。</span>
    </div>
    <div style="text-align: left;">
      <span>未经授权不得进入！</span>
    </div>
    <input id="sureSJ" type="button" value=""/>
    <input id="exitSJ" type="button" value="" class="we_goout"/>
  </div>
  <!-- =================================================================================================================== -->
</body>

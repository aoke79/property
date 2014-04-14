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
    <base href="<%=basePath%>" />
    
    <title>安华农保用户管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	
<%--	<link type="text/css" rel="stylesheet" href="<%=path%>/theme/standard/default/style.css" />--%>
	<link type="text/css" rel="stylesheet" href="<%=path%>/theme/standard/green/style.css" />
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/core.css" />
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css" />
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/pageTable.css" />
	<link type="text/css" rel="stylesheet" href="<%=path%>/uploadify/css/uploadify.css" />
<%--	<link type="text/css" rel="stylesheet" href="<%=path%>/css/treeTip.css" />--%>
<%--	<link type="text/css" rel="stylesheet" href="<%=path%>/css/treeview/jquery-treeview.css">--%>
<%--	<link type="text/css" rel="stylesheet" href="<%=path%>/css/autocomplete.css">--%>
	<!--[if IE]>
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/ieHack.css" />
	<![endif]-->
	
   
	 
	<script type="text/javascript" src="<%=path%>/js/dwz.ajax.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.validate.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/dwz.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwz.regional.zh.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/plugin.messager.js"></script>
	<script type="text/javascript" src="<%=path%>/js/SetTime.js"></script>
    
	<script type="text/javascript" src="<%=path%>/js/ga.js"></script>
    <script src="<%=path%>/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

	<script src="<%=path%>/uploadify/scripts/swfobject.js" type="text/javascript"></script>

	<script type="text/javascript">
	
	<s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">
	$(function(){
		DWZ.init("standard/dwz.frag.en.xml", {
			//loginUrl:"login.html",	// 跳到登录页面
			debug:false,	// 调试模式 【true|false】
			callback:function(){
				initEnv();
				$("#themeList").theme({themeBase:"theme"});
			}
		});
	});
	
	</s:if>
	<s:else>
	$(function(){
		DWZ.init("standard/dwz.frag.xml", {
			//loginUrl:"login.html",	// 跳到登录页面
			debug:false,	// 调试模式 【true|false】
			callback:function(){
				initEnv();
				$("#themeList").theme({themeBase:"theme"});
			}
		});
	});
	</s:else>
	
	//清理浏览器内存,只对IE起效,FF不需要
	if ($.browser.msie) {
		window.setInterval("CollectGarbage();", 10000);
	}
	
	$(function(){
		getMessageCount();
	});
	
	//获取未读信息条数
	function getMessageCount(){
		
		jQuery.ajax({
			type:'POST',
			url:"message!countMessage.do",
			dataType:'json',
			timeout:500000,
			cache:true,
			error:function(XMLHttpRequest, textStatus, errorThrown){
			},
			success:function(data){
				getMessages(data);
			}
		});
	}
	
	//处理信息弹出
	function getMessages(data){
		var msgtext = $(".msgCol").text().substr(1,1);
		//若后台取出的数据条数大于当前消息数时弹出消息提醒
		if(data.messageColumn > msgtext && data.messageNew != 0){
			//showMessage(data);
			//setMessage(data);
		} else {
			//setMessage(data);
		}
	}
	
	//即时弹出信息
	function showMessage(data){
		
		var msgtext = $(".msgCol").text().substr(1,1);
		var msgcolumn = data.messageColumn - msgtext;
		var msg = "";
		var str="123abc";
		if(msgcolumn == 1){
			msg = "你有1条新消息!<br/><br/><a class=\"messageBody\" target=\"navTab\" onclick=\"viewSingle('"+data.singleId+"')\">查看新消息</a>";
		} else {
			msg = "你有" + msgcolumn + "条新消息!<br/><br/><a class='messageBody' target='navTab' rel='systemMessage' onclick='viewMessage();'>查看新消息</a>";
		}
		$.messager.lays(250,150);
		$.messager.show('即时消息',msg);
		initATab();
	}
	
	//设置链接区域提示条数
	function setMessage(data){
		var msgPart = '(' + data.messageColumn + ')';
		$(".msgCol").html(msgPart);
	}
	
	//查看消息
	function viewMessage(){
		$(".messageBody").attr("href","sys-message/unread-message!handleList.do");
		$.messager.close();
	}
	function viewSingle(data){
		ref = "sys-message/unread-message!viewSingle.do?sysBackLogMessage.id=" + data;
		//alert(ref);
		$(".messageBody").attr("rel","systemMessage");
		$(".messageBody").attr("href",ref);
		$.messager.close();
	}
	
	//定时，设置每三分钟执行一次
	setInterval("getMessageCount();",180000);
	
	
	$(function(){
	    $("#request_locale").change(function(){
	     var v=$(this).val();
	    $.post("sys-role/sys-role!lang.do",{"request_locale":v},
			function(data){
			});	
		  window.location.reload();	
	    });
	    
	    $("#en_US").click(function(){
	       $.post("sys-role/sys-role!lang.do",{"request_locale":$(this).attr('id')},
			function(data){
			});	
		   window.location.reload();	
	    
	    });
	    
	     $("#zh_CN").click(function(){
	       $.post("sys-role/sys-role!lang.do",{"request_locale":$(this).attr('id')},
			function(data){
			});	
		   window.location.reload();	
	    
	    });
	    
	});
	
	</script>
	

<script type="text/javascript">


    function deleteDateAndFile(value){
    
    
       alertMsg.confirm("确定要删除此附件吗？",{okCall:function(){
		var attchid=$(value).attr("attchid");
        var attchpath=$(value).attr("attchpath");
        var url ="upload-file!deleteFileSrc.do?fileSrc="+attchpath+"&attchid="+attchid;
        $("#deleteImg").children("a").attr("href",url);
        $("#deleteImg").children("a").click();
        $(value).parent().parent().parent().remove();	   
	   }});
    
    }

   function deleteSrc(value,flag){
   
     alertMsg.confirm("确定要删除此附件吗？",{okCall:function(){
      var src=$(value).attr("imgsrc");
      var url ="upload-file!deleteFileSrc.do?fileSrc="+src;
      $("#deleteImg").children("a").attr("href",url);
     
      $("#deleteImg").children("a").click();
      
      //删除table也就是存储图片的地方；      	
      $(value).parent().prev().remove();
      //删除A标签和上级Div
      //update 
  	  $(value).parent().parent().remove();				  
  	  var newStr='';
  	  var fileKey=$("#fileKey"+flag).val();
  	  var str=fileKey.split(",");
  	  for(i=0;i<str.length;i++){
  	  if(str[i].replace("/","\\").indexOf(src)!=-1){
  		 fileKey=fileKey.replace(str[i]+",",'');
  	  }
  	}
  	$("#fileKey"+flag).val(fileKey);
  	
  	}});
  	
   }




    function mo(value){
       var file=$(value).parent().attr('id');
      var url ="upload-file!deleteFileSrc.do?fileSrc="+file;
      $("#deleteImg").children("a").attr("href",url);
      
         
       $('img[flag=bugImg]').contextMenu('deleteMenu', {
      //菜单样式
      menuStyle: {
        border: '1px solid #b8d0d6',
        width: '80'
      },
      //菜单项样式
      itemStyle: {
        fontFamily : '宋体',
        backgroundColor : '#e8edf3',
        color: '#183152',
        border: 'none',
        padding: '1px',
        lineHeight: '24px',
        paddingLeft: '3px'
      },
      //菜单项鼠标放在上面样式
      itemHoverStyle: {
        color: '#4d8ec8',
        backgroundColor: 'white',
        border: 'none'
      },
      //事件  
        
      bindings: 
          {
            'deleteImg': function(t) {
             
             	$("#deleteImg").children("a").click();
             	      alert($(t).parent().parent().parent().parent().html());
                      $(t).parent().parent().parent().parent().remove();
  					  
  					  var newStr='';
  					  var fileKey=$("#fileKey").val();
  					  var str=fileKey.split(",");
  					  for(i=0;i<str.length;i++){
  					   if(str[i].replace("/","\\").indexOf(file)!=-1){
  					      fileKey=fileKey.replace(str[i]+",",'');
  					   }
  					  }
  					 $("#fileKey").val(fileKey);
            }
          }
    }); 
      
      
      
      
    }
    $("a[name=ssss]").live("click",function(){
        var a=$(this).attr("href1");
        $("#scl").attr("href",a);
    	$("#scl").trigger("click"); 
    })
    	
 
    function smartDel(){
    	$("a[cape=delElement]").mouseover(function(){
			$(this).addClass("partDelHover");
		})
		$("a[cape=delElement]").mouseout(function(){
			$(this).removeClass("partDelHover")
		})
    }
    
</script>
<script>
	$(function(){
		//文本域自动换行
		$("textarea").each(function(){
			autoRemake(this);
		})
		
		//必填项
		$("span[scar=required]").each(function(event){
			$(this).addClass("requireBox");
		})
		
		//关闭页面
		$("button[cape=closePage]").click(function(){
			alertMsg.confirm("<s:property value='getText("editLeaveTips")' />",{okCall:function(){
				navTab.closeCurrentTab();
			}})
		})
	})
	//调节首页自适应的js
	$(function(){
		var obj = $("#layout"); 
		var offset = obj.offset(); 
		var right = offset.left+obj.width();
		if(right<991){ $("#navTab").find(".tabsPageContent").css("overflow-x","auto");}
		jQuery(window).bind("resize", changeLayout);   
		function changeLayout( e ) {
			var obj = $("#layout"); 
			var offset = obj.offset(); 
			var right = offset.left+obj.width();
			if(right<991){
				$("#navTab").find(".tabsPageContent").css("overflow-x","auto");
				}else{
				$("#navTab").find(".tabsPageContent").css("overflow-x","hidden");
			}
		} 
	$(".accordionContent").find("a[target=navTab]").click(function(){
		$("#navTab").find(".tabsPageContent").css("overflow","hidden");
	});
});
	$(function(){
		$("a[href=javascript:]").click(function(){
			$("#navTab").find(".tabsPageContent").css("overflow","hidden");
		});
	});
</script>	
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" style="cursor: default;float:left; width:828px">标志</a>
				<ul class="navb">
					<li>欢迎${session.user.deptname} ${session.user.name}&nbsp;!
					</li>
					<li><a href="user-info/sys-login-info!exit.do"><s:property value="getText('exit')"/></a></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
				<div id="sidebar">
				<div class="toggleCollapse"><h2>&#20027;&#33756;&#21333;</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
							<div class="accordionContent">
								<ul class="tree  ">
										<li>
											<a>系统用户管理</a>
												<ul>
													<li>
														<a href="standard!findUserList.do" target="navTab" rel="TfQualDeclarSeal">门户用户管理</a>
													</li>
												</ul>
												<ul>
													<li>
														<a href="ah-login/anhua-log!list.do" target="navTab" rel="TfQualPilotSignature">日志信息</a>
													</li>
												</ul>
												<ul>
													<li>
														<a href="ah-login/anhua-log!otherList.do" target="navTab" rel="ZiXiTongSearch">子系统日志信息</a>
													</li>
												</ul>
												<ul>
													<li>
														<a href="standard!findRizhiJilu.do" target="navTab" rel="SystemPrivileges">系统权限查询</a>
													</li>
												</ul>
												<s:if test='hexinflag==true'>
												<ul>
													<li>
														<a href="http://10.0.5.20:7001/prpall/UICentralControl?SelfPage=/common/pub/UILogonInput.jsp&UserCode=0000000000&Password=A7BE6A5800E8EAF648B15D53DA2E60FC&RiskCode=DDA&LogonType=Single" target="_blank" rel="Center">核心细粒度授权</a>
													</li>
												</ul>
												</s:if>
												<s:if test='rizhiflag==true'>
												<ul>
													<li>
														<a href="http://10.0.7.50:8080/LoginServlet.servlet?username=SSO_ADMIN&password=8d8147ead933ef1febbf070e32040943" target="_blank" rel="LogMessage">日志细粒度授权</a>
													</li>
												</ul>
												</s:if>
												
										</li>
								</ul>
							</div>
					</div>
				</div>
			</div>
		<div id="container">
			<div id="navTab" class="tabsPage" >
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab" >
							<li tabid="main" class="main" >
								<a href="javascript:void(0)"><span><span class="home_icon">&nbsp;<s:property value="getText('HomePage')"/></span></span></a>
							<br /></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:void(0)"><s:property value="getText('HomePage')"/></a></li>
				</ul>
				<div class="navTab-panel tabsPageContent" style="OVERFLOW-x:hidden;">
					<jsp:include  page="../sms/training/qualification/quaJbpm4/qualPilotSuperList.jsp"/>
				</div>
			</div>
		</div>
		<!-- 弹出dialog时显示的部分 -->
		<div id="taskbar" style="left:0px; display:none;">
			<div class="taskbarContent">
				<ul></ul>
			</div>
			<div class="taskbarLeft taskbarLeftDisabled" style="display:none;">taskbarLeft</div>
			<div class="taskbarRight" style="display:none;">taskbarRight</div>
		</div>
		<div id="splitBar"></div>
		<div id="splitBarProxy"></div>
	</div>

	<div id="footer"></div>

	<!--拖动效果-->
	<div class="resizable"></div>
	<!--阴影-->
	<div class="shadow" style="width:508px; top:148px; left:296px;">
		<div class="shadow_h">
			<div class="shadow_h_l"></div>
			<div class="shadow_h_r"></div>
			<div class="shadow_h_c"></div>
		</div>
		<div class="shadow_c">
			<div class="shadow_c_l" style="height:296px;"></div>
			<div class="shadow_c_r" style="height:296px;"></div>
			<div class="shadow_c_c" style="height:296px;"></div>
		</div>
		<div class="shadow_f">
			<div class="shadow_f_l"></div>
			<div class="shadow_f_r"></div>
			<div class="shadow_f_c"></div>
		</div>
	</div>
	<!--遮盖屏幕-->
	 <div id="alertBackground" class="alertBackground"></div>
	<div id="dialogBackground" class="dialogBackground"></div>

	<div id='background' class='background'></div>
	<div id='progressBar' class='progressBar'><s:property value="getText('loadData')"/></div>
<!-- add by niujingwei 当英语办公室去添加考试人员时默认调用此链接 start -->
<a href="en-exam-enroll/en-exam-enroll!searchAllOrg.do" id="searchAllOrg1"
								 mask="true" target="dialog" width="250" height="80">
							</a> 
<!-- add by niujingwei end -->	
</body>
</html>

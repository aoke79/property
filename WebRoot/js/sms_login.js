
	/*//判断浏览器并执行【回车】的事件
	if(document.addEventListener) {
		//FireFox
		document.addEventListener("keydown", fireFoxHandler, true);
	} else{
		//MSIE
		document.attachEvent("onkeydown", ieHandler);
    }*/
	
 	function fireFoxHandler(event){
 		if(event.keyCode == 13){
 			$("#loginPage").find("#doLogin").click();
		}
 	}
 	
 	function ieHandler(event){
	     if(event.keyCode == 13) {
	    	 $("#loginPage").find("#doLogin").click();
	     }
 	}
 	
 	function g(o){
		return document.getElementById(o);
	}
 	
	function HoverLi(n){
		for(var i=1;i<=3;i++){
			g('tb_'+i).className='normaltab';
			g('tbc_0'+i).className='smartHide';
		}
		g('tbc_0'+n).className='smartShow';
		g('tb_'+n).className='hovertab';
	}
	
	function HoverLi2(n){
		for(var i=1;i<=2;i++){
			g('tb2_'+i).className='normaltab2';
			g('tbc2_0'+i).className='smartHide';
		}
		g('tbc2_0'+n).className='smartShow';
		g('tb2_'+n).className='hovertab2';
	}
 	
	$(function(){
		
		//鼠标划过时产生的页签切换
		$(".pageFileT").find("li[cape*=blockPage]").each(function(){
			$(this).mouseover(function(){
				var cape = $(this).attr("cape");
				var symbol = cape.substr(cape.length-1, cape.length);
				$(".pageFileT").find("li[cape*=blockPage]:not(this)").removeClass("activeTab").addClass("hoverTab")
				$(this).removeClass("hoverTab").addClass("activeTab");
				$parent = $(this).parent().parent().parent();
				$parent.find("#sheetT div[cape*=content]:not(div[cape=content"+symbol+"])").addClass("smartHide");
				$parent.find("#sheetT div[cape=content"+symbol+"]").removeClass("smartHide");
			})
		})
		
		$(".pageFileS").find("li[cape*=cubePage]").each(function(){
			$(this).mouseover(function(){
				var cape = $(this).attr("cape");
				var symbol = cape.substr(cape.length-1, cape.length);
				$(".pageFileS").find("li[cape*=cubePage]:not(this)").removeClass("activeTabS").addClass("hoverTabS")
				$(this).removeClass("hoverTabS").addClass("activeTabS");
				$parent = $(this).parent().parent().parent();
				$parent.find("#sheetS div[cape*=info]:not(div[cape=info"+symbol+"])").addClass("smartHide");
				$parent.find("#sheetS div[cape=info"+symbol+"]").removeClass("smartHide");
			})
		})
		
		$("#loginPage").find("input[class=inputText]").focus(function(){
			$("#loginPage").find("#validateCaption").html("");
		})
		
		$("#loginPage").find("#doLogin").click(function(){
			var loginName = $("#loginPage").find("#loginName").val();
			var accountPassword = $("#loginPage").find("#accountPassword").val();
			
			if(loginName == null || loginName == "") {
				//$("#loginPage").find("#validateCaption").html("请输入用户名");
				alert("请输入用户名！");
				return false;
			}
			if(accountPassword == null || accountPassword == "") {
				//$("#loginPage").find("#validateCaption").html("请输入密码");
				alert("请输入密码！");
				return false;
			}
			var subsystemName = $("#subsystemName").val();
			
			$.post("user-info/sys-login-info!login.do",
				{"cmUser.loginName":loginName,"cmUser.accountPassword":accountPassword,"subsystemName":subsystemName},
				function(data){
					if(data.success){
						//window.location.href = "auto-lesson!list.do";
						window.location.href = "standard.do";
						//-------------
						
						 //window.location.href = "auto-course!list.do";
					// window.location.href = "auto-lesson!list.do";
						$("#loginPage").find("#loginForm").submit();  
						return true;
					} else {
						alert(data.msg);
						//$("#loginPage").find("#validateCaption").html(data.msg);
						return false;
					}
				}
			);
		});
		
	})
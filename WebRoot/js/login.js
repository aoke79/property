if (document.addEventListener){//如果是Firefox    
	     //document.addEventListener("onkeypress", fireFoxHandler, true);  
		 document.addEventListener("keydown", fireFoxHandler, true);    
 	 } else{    
	     //document.attachEvent("onkeypress", ieHandler);  
		 document.attachEvent("onkeydown", ieHandler);   
     }    
 	function fireFoxHandler(evt){    
	     //alert("firefox");    
	     if (evt.keyCode == 13){    
	         //defaultlogin();
	           $("#login").click();
		}    
 	}    
 	function ieHandler(evt){    
	     //alert("IE");    
	     if (evt.keyCode == 13)   
	     {    
	         //defaultlogin();
		       $("#login").click();
	     }    
 	};
 
	$(function(){
		
		$("#loginName").focus(function(){			
			$("#validateImageInfo").html("");
		});
		$("#accountPassword").focus(function(){			
			$("#validateImageInfo").html("");
		});
		$("#validateCode").focus(function(){			
			$("#validateImageInfo").html("");
		});
		
		$("#validateImage").click(function () {
			$(this).attr("src", "user-info/validate-img.do?dy=" + new Date().getTime()).css("cursor", "pointer");
		});
		$("#changeImage").click(function () {
			$("#validateImage").click();
			return false;
		});
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
			var subsystemName = $("#subsystemName").val();
//			$.post("user-info/sys-login-info!login.do",
//				{"cmUser.loginName":loginName,"cmUser.accountPassword":accountPassword,"validateCode":$("#validateCode").val(),"subsystemName":subsystemName},
//				function(data){
//					if(data.success){
//						$("#login").hide();
//						$("#loginForm").submit();
//					}else{
//						$("#validateImageInfo").html(data.msg);
//					}
//				})
		});
		
	})
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery.contextmenu.r2.packed.js"></script>
<script>

   $(function(){ 
      var flag = '${flag}';
	  // alert($("#"+$("#organizationId").val()).val());
	  var orgFunId=$("#${organizationId }").val();
	  var orgFunIds=orgFunId.split(",");
	  
	  $("#orgTree").find("a[flag=sinoframe]").each(function(i,n){
	    for(var i=0; i < orgFunIds.length; i++){
	    	if($(this).attr("tid")==orgFunIds[i]){
	    		$(this).attr("checked","true");
	    	}
	    }
	   		
   	    })
   
	   // 单击清空功能的时候
	   $("#clear").click(function(){
	    
	   		// 如果为多选的时候，则把多选都取消
		   	if($("#chooseCheck").val()=="0"){
		   		$("#${organizationName }").val("");				
				$("#${organizationId }").val("");
		   	}else{
		   	// 如果为<a>标签的时候，则把后面的text框内的内容清空
		   		$("input[name=ids]").each(function(){
		   			if($(this).attr("checked")==true){
			   			$(this).trigger("click");
		   			}
		   		})
		   	}
	   })
	   // 如果为单选的时候取值方法使用此方法
	   $("a[flag=sinoframe]").each(function(){
	   		if($("#chooseCheck").val()=="0"){
	   			$(this).click(function(){
	   				$("#${organizationName }").val($(this).attr("tvalue"));				
					$("#${organizationId }").val($(this).attr("tid"));
					
					if(flag == "eventResearch"){
						$("#erorgNames").text($("#${organizationName }").val());
					}
					
					var tagOrg = "${organizationName }";
					if(flag == "researchReport"){
						var tagIndex = tagOrg.split("_")
						$("span[cape=org_"+tagIndex[1]+"]").text($(this).attr("tvalue"));
					}
					
					$("#getOrganizationListJsp").find("button.close").click();	
	   			})
	   		}
	   })
	  // 当单击取消按钮的时候
	   $("#cancel").click(function(){
	   		$("#getOrganizationListJsp").find("button.close").click();	
	   })
	   // 当点击确定按钮的时候
	   $("button[name=subUser]").click(function(){
	   		var ids="";
	   		var names="";
	   		$("#getOrganizationListJsp").find("input[name=ids]").each(function(){
	   		  	if($(this).attr("checked")==true){
	   		  		ids+=$(this).attr("id")+",";
	   		  		names+=$(this).attr("value")+",";
	   		  	}
	   		})
	   		ids=ids.substring(0,ids.length-1);
	   		names=names.substring(0,names.length-1);
	   		$("#${organizationName }").val(names);				
			$("#${organizationId }").val(ids);
			if(flag == "eventReport"){
				$("#orgNames").text($("#respOrg").val());
			} else if(flag == "eventReportC"){
				$("#ccNames").text($("#ccOrg").val());
			} else if(flag == "inspect"){
				addColFromSecCheckNoticeAdd(ids,names);
			} else if(flag == "inspectCheckNotice"){
				//添加安全检查通知书中的受检单位方法
				addCheckeddeptCol(ids,names);
			} else if(flag == "transpond"){
				submitTranspond();
			}
			
			$("#getOrganizationListJsp").find("button.close").click();	
	   })
		  
    })
</script>
<style>
li{
	text-align: left;
}
</style>
<div class="page" id="getOrganizationListJsp">
	<form method="post" id="formUser" action="" class="pageForm required-validate">
	<div class="pageContent" layoutH="56">
		<!-- 判断出来的树是多选还是单选 -->
		<input type="hidden" id="chooseCheck" value="${chooseCheck }">
        ${orgTree}
		<button style="display:none" class="close"></button>
	</div>	 
	<div class="formBar">
		<ul>
			<!-- 如果为复选框的时候则会有确定按钮   -->
			<s:if test="chooseCheck==1">
			
			<li>					
				<div class="buttonActive"><div class="buttonContent">
					<button type="button" name="subUser" ><s:property value="getText('OK')"/></button>
				</div></div>					
			</li>
			
			
			<li>
				<div class="button"><div class="buttonContent">
					<button type="Button" id="cancel"><s:property value="getText('cancel')"/></button>
				</div></div>
			</li>
			
			</s:if><!--
			
			<li>
				<div class="button"><div class="buttonContent">
					<button id="clear" type="reset"><s:property value="getText('clear')"/></button>
				</div></div>
			</li>
			
		--></ul>
	</div>
	  </form>
</div>

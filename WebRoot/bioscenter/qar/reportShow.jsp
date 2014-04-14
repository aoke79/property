<!-- 
	报表展示页面
	autor chenleilei
 -->
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="page" id="reportShowJsp">
   <div class="pageContent">
	   <form class="pageForm required-validate">
	   	<div class="pageFormContent" layoutH="56">
	   	<table class="pagePanelTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
	   	<table id="aa" class="blockPanelTable" cellpadding="0" cellspacing="0"><tr>
	   
	   	<s:iterator value="queryNameKey" var="key" status="keyIndex">
	   		<s:set name="queryKey" value="key"></s:set>
	   		<s:set name="queryValue" value="queryName.get(#key)"></s:set>
	   		<td class="cTitle">
	   			<s:if test='#queryKey == "a" '>开始时间</s:if>
	   			<s:if test='#queryKey == "b" '>结束时间</s:if>
	   			<s:if test='#queryKey == "c" '>机队</s:if>
	   			<s:if test='#queryKey == "d" '>机型</s:if>
	   			<s:if test='#queryKey == "e" '>飞机号</s:if>
	   			<s:if test='#queryKey == "f" '>飞行阶段</s:if>
	   			<s:if test='#queryKey == "g" '>是否夜航</s:if>
	   			<s:if test='#queryKey == "h" '>超限项目</s:if>
	   			<s:if test='#queryKey == "i" '>机场</s:if>
	   			<s:if test='#queryKey == "j" '>天气</s:if>
	   			<s:if test='#queryKey == "k" '>道面</s:if>
	   			<s:if test='#queryKey == "l" '>进近方式</s:if>
	   			<s:if test='#queryKey == "m" '>主操作技术标准</s:if>
	   			<s:if test='#queryKey == "n" '>超限程度</s:if>	   			
	   			<s:if test='#queryKey == "o" '>起飞机场</s:if>
	   			<s:if test='#queryKey == "p" '>落地机场</s:if>
	   		</td>
	   		<td class="cBody"><s:property value="queryValue"/></td>
	   		<s:if test="#keyIndex.index == queryName.keySet().size()-1 && #keyIndex.index%2 ==0">
	   			<td class="cTitle"><span>&nbsp;</span></td><td class="cBody"><span>&nbsp;</span></td>
	   		</s:if>
	   		<s:if test="#keyIndex.index%2==1">
	   		</tr><tr></s:if>	
	   	</s:iterator>
	  </tr></table>
	 </td></tr></table>
	   	<s:if test="btnFlag == 'crftoneuseid' && orgNameList.size() != 0">
	    <div class="panelBar">	
			<table class="toolBar" >
				<tr>
					<s:iterator value="orgNameList" var="orgName">
						<td><a class="add" name="orgBtn" orgName="<s:property value="orgName"/>" url="secQarFlyReport/qar_fly_da_jidui_org.brt" ><span><s:property value="orgName"/></span></a></td>
					</s:iterator>
				</tr>
			</table>		
		</div>
		</s:if>
		<s:if test="titleFlag.equals('overrunSingle') || titleFlag.equals('monthNum') || titleFlag.equals('monthNumSign') || titleFlag.equals('overrun')">
			<!-- 查看年份 -->
			<div style="text-align: right; width:15%;float:right;">
			
				<input type="hidden" id="url" value="<s:property value="url.substring(0,url.indexOf('yearNum'))"/>">
				<input type="hidden" id="url2" value="<s:property value="url2.substring(0,url2.indexOf('yearNum'))"/>">
				<span style="float: left;line-height: 25px">选择年份</span>
				<s:select list="#{'0':'1年','1':'2年','2':'3年','3':'4年','4':'5年' }" value='yearNum' id="yearNum"></s:select>
			</div>
		</s:if>
	    <s:if test="url != null && url.length() != 0">
	    	<div style="width:95%; height:100%" >		
				<iframe src="<s:property value='url'/>" id="rptIframe" frameborder="0" style='width:100%;height:100%'></iframe>
			</div>
		</s:if>	
		<s:if test="url2 != null && url2.length() != 0">
	     	<div style="width:95%; height:100%" >
				<iframe src="<s:property value="url2"/>" onload="remove_loading();" id="rptIframe2" frameborder="0" style='width:100%;height:100%' ></iframe>
			</div>
		 </s:if>
			
			<div style="width:95%; height:100%" >
				<iframe src="" id="frm" frameborder="0" style='width:100%;height:100%'></iframe>
			</div>
		 </div>
		 <div class="formBar"></div>
	  </form>
	  
	  	<div id="loader_container">
		<div id="loader">
			<div align="center">报表加载中...</div>
			<div id="loader_bg"><div id="progress"></div></div>
		</div>
	</div>
   </div>
</div>
<script type="text/javascript">
<!--
	$(function(){
		
		$("#reportShowJsp").find("a[name=orgBtn]").click(function(){			
			var query = $("#<s:property value='pageFlag'/>").find("input[name=param]").val();
			var url = $(this).attr("url");
			//var orgName = encodeURI(encodeURI($(this).attr("orgName")));
			var orgName = $(this).attr("orgName");
			alert(orgName);
			$.pdialog.open("qar/qar-report!disposeUrl.do", 
					"reportShow2", 
					"报表显示", 
					{width:1000,height:570,mask:true},"&url="+url+query+"&pageFlag=reportShowJsp&orgName="
					 +orgName+"&titleFlag=qiancilv&btnFlag=usedeptid");
		});
		
		
		$("#reportShowJsp").find("#yearNum").change(function(){
			alert("<s:property value="titleFlag"/>");
			var t_id = setInterval(animate,20);
			$.post("qar/qar-report!disposeYearUrl.do",
					{
					 "url":$("#reportShowJsp").find("#url").val(),
					 "url2":$("#reportShowJsp").find("#url2").val(),
					 "yearNum":$(this).val(),
					 "titleFlag":"<s:property value="titleFlag"/>"},
					function(data){
						$("#reportShowJsp").find("#rptIframe").attr("src",data.url);
						$("#reportShowJsp").find("#rptIframe2").attr("src",data.url2);
					}
			);
		});
	});
//-->
</script>


<style type="text/css">
<!--
#loader_container {
        text-align: center;
        position: absolute;
        top: 40%;
        width: 100%;
        left: 0;
}
#loader {
        font-family: Tahoma, Helvetica, sans;
        font-size: 11.5px;
        color: #000000;
        background-color: #FFFFFF;
        padding: 10px 0 16px 0;
        margin: 0 auto;
        display: block;
        width: 130px;
        border: 1px solid #5a667b;
        text-align: left;
        z-index: 2;
}
#progress {
        height: 5px;
        font-size: 1px;
        width: 1px;
        position: relative;
        top: 1px;
        left: 0px;
        background-color: #8894a8
}
#loader_bg {
        background-color: #e4e7eb;
        position: relative;
        top: 8px;
        left: 8px;
        height: 7px;
        width: 113px;
        font-size: 1px
}
-->
</style>
<script language="javascript">
var t_id = setInterval(animate,20);
var pos = 0;
var dir = 2;
var len = 0;
function animate() {
      var elem = document.getElementById('progress');
      if(elem != null) {
              if (pos == 0) len += dir;
              if (len > 32 || pos > 79) pos += dir;
              if (pos > 79) len -= dir;
              if (pos > 79 && len == 0) pos = 0;
              elem.style.left = pos;
              elem.style.width = len;
      }
}
function remove_loading() {
      this.clearInterval(t_id);
			
            
      var targelem = document.getElementById('loader_container');
      targelem.style.display = 'none';
      targelem.style.visibility = 'hidden';
}

</script>

<!-- 
	报表展示页面
	autor chenleilei
 -->
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="page" id="fxzdReportShowJsp">
   <div class="pageContent">
	   <form class="pageForm required-validate">
	   
	     <div class="pageFormContent" layoutH="56">
	   
	    	<div style="width:95%; height:100%" >
				<iframe src="<s:property value="url"/>"  id="rptIframe" frameborder="0" style='width:100%;height:100%'></iframe>
			</div>
			
			<s:if test="url2 != null && url2.length() != 0 && titleFlag == 'qiancilv'">
	     	<div style="width:95%; height:100%" >
				<iframe src="<s:property value="url2"/>" onload="remove_loading2();" id="rptIframe2" frameborder="0" style='width:100%;height:100%' ></iframe>
			</div>
		 </s:if>
		
			<div style="width:95%; height:100%" >
				<iframe src="" id="frmorg"  frameborder="0" style='width:100%;height:100%'></iframe>
			</div>
		 </div>
		 <div class="formBar"></div>
	  </form>
	  <div id="loader_container2">
		<div id="loader2">
			<div align="center">报表加载中...</div>
			<div id="loader_bg2"><div id="progress2"></div></div>
		</div>
	</div>
   </div>
</div>
<style type="text/css">
<!--
#loader_container2 {
        text-align: center;
        position: absolute;
        top: 40%;
        width: 100%;
        left: 0;
}
#loader2 {
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
#progress2 {
        height: 5px;
        font-size: 1px;
        width: 1px;
        position: relative;
        top: 1px;
        left: 0px;
        background-color: #8894a8
}
#loader_bg2 {
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
var t_id2 = setInterval(animate2,20);
var pos = 0;
var dir = 2;
var len = 0;
function animate2() {
      var elem = document.getElementById('progress2');
      if(elem != null) {
              if (pos == 0) len += dir;
              if (len > 32 || pos > 79) pos += dir;
              if (pos > 79) len -= dir;
              if (pos > 79 && len == 0) pos = 0;
              elem.style.left = pos;
              elem.style.width = len;
      }
}
function remove_loading2() {
      this.clearInterval(t_id2);
			
            
      var targelem = document.getElementById('loader_container2');
      targelem.style.display = 'none';
      targelem.style.visibility = 'hidden';
}

</script>


<!-- QAR统计分析 	 
机型 AC_TYPE
事件程度 ALERTCHAR
机号ARN
着陆机场ARR
航班日期 DDATE
起飞机场  DEP
事件号   EVENT_NO
单位代码ORGID
-->
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="bioscenter/js/bios.js"></script>
<script type="text/javascript">
<!--
	
	
	
	
//-->
</script>

<div class="page" id="secQarFlyReportJsp">
	<div class="pageHeader">
	<div class="pageContent" layoutH="0" >
	
	<div class="searchBar">
		<a id="chooseCaseBtn" style="display:none" mask="true" width="500" height="500"
		   target="dialog"  rel="chooseCaseJsp" ><span>选择条件</span></a>
		   
		<a id="reportBtn" style="display:none" mask="true" width="1000" height="570"
		   target="dialog"  rel="reportJsp" ><span>报表显示</span></a>
		<input type="hidden" name="param" value=""/>
		
			<table class="searchContent" style="width:100%">		
				<tr>					
					<td>航班运行日</td>
					<td><input name="query.dtgteq_ddate" case="case" value="<s:property value="query.dtgteq_ddate"/>" 
					class="date" style="float:left"  onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					<td>到</td>
					<td><input name="query.dtlteq_ddate" case="case" value="<s:property value="query.dtlteq_ddate"/>" 
					class="date" style="float:left" onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					
					<td>机队</td>
					<td>
						<input name="query.in_crftoneuseid" type="hidden" case="case" />
						<input name="queryName.c" case="name" flag="crftoneuseid" style="float:left" onkeydown="return false;">
						<button flag="crftoneuseid" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
				</tr>
				<tr>
					<td>机型</td>
					<td>
						<input name="query.in_acType" type="hidden" case="case" />
						<input name="queryName.d" flag="acType" case="name" style="float:left" onkeydown="return false;">
						<button flag="acType" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
							
					</td>
				
					<td>飞机号</td>
					<td>
						<input name="query.in_arn" type="hidden" case="case" />
						<input name="queryName.e" flag="arn" case="name" style="float:left" onkeydown="return false;">
						<button flag="arn"onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
					<td>飞行阶段</td>
					<td>
						<input name="query.in_phase" type="hidden" case="case" />
						<input name="queryName.f" flag="phase" case="name" style="float:left" onkeydown="return false;">
						<button flag="phase" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')"  
						href="javascript:void(0)">...</button>					
					</td>
				</tr>
				
				<tr>					
					<td>超限项目</td>
					<td>
						<input name="query.in_eventNo" type="hidden" case="case" />
						<input name="queryName.h" flag="eventNo" case="name" style="float:left" onkeydown="return false;">
						<button flag="eventNo" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>	
					<td>起飞机场</td>
					<td>
						<input name="query.in_dep" type="hidden" case="case" />
						<input name="queryName.o" flag="dep" case="name" style="float:left" onkeydown="return false;">
						<button flag="dep" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>		
					
					<td>落地机场</td>
					<td>
						<input name="query.in_arr" type="hidden" case="case" />
						<input name="queryName.p" flag="arr" case="name" style="float:left" onkeydown="return false;">
						<button flag="arr" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>				
				</tr>
				<tr>
					<td>超限程度</td>
					<td colspan="5">
						<s:select list="#{'':'','L':'轻度超限','H':'严重超限' }" case='case' name="query.eq_alertchar" value='<s:property value="query.eq_alertchar"/>'/>
					</td>				
				</tr>
			</table>			
		</div>
		
		<div class="panelBar" style="height:50px">	
		<table class="toolBar" >
			<tr>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_dep_jichang.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','dep','secQarFlyReportJsp')"><span>起飞机场</span></a></td>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_arr_jichang.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','arr','secQarFlyReportJsp')"><span>落地机场</span></a></td>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_shijian.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','event_no','secQarFlyReportJsp')"><span>事件</span></a></td>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_jidui.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','crftoneuseid','secQarFlyReportJsp')"><span>机队（飞机所在单位）</span></a></td>
			</tr>
			<tr>
			<td><a class="add" url="secQarFlyReport/qar_fly_da_jixing.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','ac_type','secQarFlyReportJsp')"><span>机型</span></a></td>
				<td><a class="add" url="" onclick="returnUrl($(this).attr('url'),'overrun','','secQarFlyReportJsp')"><span>超限月趋势图</span></a></td>
				<td colspan="2"><a class="add" url="" onclick="returnUrl($(this).attr('url'),'overrunSingle','','secQarFlyReportJsp')"><span>单个事件超限月趋势图</span></a></td>
				
			</tr>
		</table>		
	</div>
	</div>
	
		
	</div>
</div>


<!-- ����Ա -->
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="bioscenter/js/bios.js"></script>
<script type="text/javascript">
<!--

$("#secQarPilotReportJsp").find("button[name=reset]").mousedown(function(){
	$("#secQarPilotReportJsp").find(":input:not(button)").val("");
});

	// ѡ���ѯ���� 
	function chooseCmPeople(){
		$("#secQarPilotReportJsp").find("#chooseCaseBtn").attr("width","700");
		$("#secQarPilotReportJsp").find("#chooseCaseBtn").attr("href","qar/qar-report!chooseCmPeople.do?&pageFlag=secQarPilotReportJsp");
		
		$("#secQarPilotReportJsp").find("#chooseCaseBtn").click();
	}
	
	$("#secQarPilotReportJsp").find("#subBtn").click(function(){
		var gtdate = $("#secQarPilotReportJsp").find("input[name=query.dtgteq_ddate]").val();
		var ltdate = $("#secQarPilotReportJsp").find("input[name=query.dtlteq_ddate]").val();
		if(gtdate == ""){
			alertMsg.warn("����д�������п�ʼ����");
			return false;
		}
		if(ltdate == ""){
			alertMsg.warn("����д�������н�������");
			return false;
		}
		if(ltdate < gtdate){
			alertMsg.warn("�������н������ڲ���С�ڿ�ʼ����");
			return false;
		}
	});
	
//-->
</script>

<div class="page" id="secQarPilotReportJsp">
	<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="qar/qar-report!disposePilotUrl.do" method="post">
	<input type="hidden" name="pageFlag" value="secQarPilotReportJsp" >
	<div class="searchBar">
		<a id="chooseCaseBtn" style="display:none" mask="true" height="500"
		   target="dialog"  rel="chooseCaseJsp" ><span>ѡ������</span></a>
			<table class="searchContent" style="width:100%">		
				<tr>					
					<td>����������</td>
					<td><input name="query.dtgteq_ddate" case="case" value="<s:property value="query.dtgteq_ddate"/>" 
					class="date" style="float:left"  onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					<td>��</td>
					<td><input name="query.dtlteq_ddate" case="case" value="<s:property value="query.dtlteq_ddate"/>" 
					class="date" style="float:left" onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					
					<td>����</td>
					<td>
						<input name="query.in_crftoneuseid" type="hidden" case="case" value="<s:property value="query.in_crftoneuseid"/>"/>
						<input name="queryName.crftoneuseid" flag="crftoneuseid" style="float:left" onkeydown="return false;" value="<s:property value="queryName.crftoneuseid"/>">
						<button flag="crftoneuseid" onclick="chooseCase($(this).attr('flag'),'secQarPilotReportJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
				</tr>
				<tr>
					<td>����</td>
					<td>
						<input name="query.in_acType" type="hidden" case="case" value="<s:property value="query.in_arn"/>"/>
						<input name="queryName.acType"  flag="acType"  style="float:left" onkeydown="return false;" value="<s:property value="queryName.acType"/>">
						<button flag="acType" onclick="chooseCase($(this).attr('flag'),'secQarPilotReportJsp')" 
						 href="javascript:void(0)">...</button>
							
					</td>
				
					<td>�ɻ���</td>
					<td>
						<input name="query.in_arn" type="hidden" case="case" value="<s:property value="query.in_arn"/>"/>
						<input name="queryName.arn" flag="arn" style="float:left" onkeydown="return false;" value="<s:property value="queryName.arn"/>">
						<button flag="arn"  onclick="chooseCase($(this).attr('flag'),'secQarPilotReportJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
					<td>���н׶�</td>
					<td>
						<input name="query.in_phase" type="hidden" case="case" value="<s:property value="query.in_phase"/>"/>
						<input name="queryName.phase" flag="phase" style="float:left" onkeydown="return false;" value="<s:property value="queryName.phase"/>">
						<button flag="phase" onclick="chooseCase($(this).attr('flag'),'secQarPilotReportJsp')"  
						href="javascript:void(0)">...</button>					
					</td>
				</tr>
				
				
				<tr>
					<td>��ɻ���</td>
					<td>
						<input name="query.in_dep" type="hidden" case="case" value="<s:property value="query.in_dep"/>"/>
						<input name="queryName.dep" flag="dep" style="float:left" onkeydown="return false;" value="<s:property value="queryName.dep"/>">
						<button flag="dep" onclick="chooseCase($(this).attr('flag'),'secQarPilotReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>		
					
					<td>��ػ���</td>
					<td>
						<input name="query.in_arr" type="hidden" case="case" value="<s:property value="query.in_arr"/>"/>
						<input name="queryName.arr" flag="arr" style="float:left" onkeydown="return false;" value="<s:property value="queryName.arr"/>">
						<button flag="arr" onclick="chooseCase($(this).attr('flag'),'secQarPilotReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>	
					<td>������Ŀ</td>
					<td>
						<input name="query.in_eventNo" type="hidden" case="case" value="<s:property value="query.in_eventNo"/>"/>
						<input name="queryName.eventNo" flag="eventNo" style="float:left" onkeydown="return false;" value="<s:property value="queryName.eventNo"/>">
						<button flag="eventNo" onclick="chooseCase($(this).attr('flag'),'secQarPilotReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>	
									
				</tr>
				<tr>
					<td>���޳̶�</td>
					<td>
						<s:select list="#{'':'','L':'��ȳ���','H':'���س���' }" case='case' name="query.eq_alertchar" id='<s:property value="query.eq_alertchar"/>'/>
					</td>
					<td>ѡ����Ա</td>
					<td colspan="4">
						<input type="hidden" name="query.in_hrpilotid" value="<s:property value="query.in_hrpilotid"/>"/>
						<input name="queryName.pilotName" flag="pilotName" value="<s:property value="queryName.pilotName"/>"/>
						<button flag="pilotName" onclick="chooseCmPeople()" href="javascript:void(0)">...</button>
					</td>				
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" id="subBtn">�鿴����</button>
							</div>
						</div>
					</li>
					
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" name="reset">����</button>
							</div>
						</div>
					</li>
				</ul>
			</div>		
		
		</div>
		</form>	
		<div class="panelBar" style="height:5px"></div>
	
	<div class="pageContent" layoutH="93" >
		<table style="width:98%;height:80%">
			<tr>
				<td style="width:1300px;height:900px">
					<div>
						<iframe src="<s:property value="url"/>" id="rptIframe" frameborder="0" style='width:100%;height:100%'></iframe>
					</div>
				</td>
			</tr>
		</table>
		</div>
		
	</div>
</div>


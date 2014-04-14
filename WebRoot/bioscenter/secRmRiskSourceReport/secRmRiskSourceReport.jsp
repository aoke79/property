<!-- 风险统计分析 	 
-->
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="bioscenter/js/bios.js"></script>
<script type="text/javascript">
<!--

$("#secRmRiskSourceReportJsp").find("button[name=reset]").mousedown(function(){
	$("#secRmRiskSourceReportJsp").find(":input:not(button)").val("");
});


	$(function(){
		$("#secRmRiskSourceReportJsp").find("#chooseBaosid").change(function(){
			var id = $(this).val();	
			commonChange($(this),"risk-manage/sec-rm-definition!toFindBaoRms.do", {"secBaopesystem.id":id},$("#secRmRiskSourceReportJsp").find("#toChooseRmsid" ), "${query.eq_rmsid}");
		});
	});
	
	$(function(){
		$("#secRmRiskSourceReportJsp").find("#toChooseRmsid" ).change(function(){
			 var id = $(this).val();
			commonChange($(this),"risk-manage/sec-rm-definition!toFindRmsRmc.do", {"secRmsystem.rmsid":id},$("#secRmRiskSourceReportJsp").find("#toChooseFirstCircuit" ), "${query.eq_firstRmcid}");
		});
	});
	
	$(function(){
		$("#secRmRiskSourceReportJsp").find("#toChooseFirstCircuit" ).change(function(){
			var id = $(this).val();
			commonChange($(this),"risk-manage/sec-rm-definition!toFindSecondRmsRmc.do", {"secRmcircuit.rmcid":id},$("#secRmRiskSourceReportJsp").find("#toChooseSecondCircuit" ), "${query.eq_secondRmcid}");
		});
	});
	
	function commonChange($this, url, data, $dest, value){
		$.post(
			url,
			data,
			function(data){
				if(data.ok){
					var html = "<option value=''>请选择</option>";
					if(data.ids != ""){
						var rmsIds = data.ids.split(",");
						var rmsNames = data.names.split(",");
						for(var i=0; i<rmsIds.length; i++){
								html += "<option value="+rmsIds[i]+">"+rmsNames[i]+"</option>";	
						}
					}
					
					$dest.html(html);
					$dest.val(value);
					$dest.change();
				}
			}
		);
	}
	
	$(function(){
		$("#secRmRiskSourceReportJsp").find("#chooseBaosid").change();
	});
	
//-->
</script>

<div class="page" id="secRmRiskSourceReportJsp">
	<div class="pageHeader">
	<div class="pageContent" layoutH="0" >
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="ecRmRiskSourceReport/qar-report!disposePilotUrl.do" method="post">
	
	<div class="searchBar">
			<table class="searchContent">		
				<tr>					
					<td><label>航班运行日</label>
					<input name="query.dtgteq_appraldate" case="case" value="<s:property value="query.dtgteq_appraldate"/>" 
					class="date" style="float:left" /><a class="inputDateButton" href="javascript:void(0)"></a></td>
					<td><label>到</label>
					<input name="query.dtlteq_appraldate" case="case" value="<s:property value="query.dtlteq_appraldate"/>" 
					class="date" style="float:left" /><a class="inputDateButton" href="javascript:void(0)"></a></td>
				</tr>
				<tr>
					<td>
						<label>系统名称：</label>
						<s:select list="secBaopesystemList" case="case" listKey="id" listValue="asname" headerKey="" headerValue="请选择" cssStyle="width:250px;" id="chooseBaosid" name="query.eq_systemid"></s:select>
					</td>
					<td>
						<label>子系统名称：</label>
						<s:select list='#{"":"请选择"}' case="case" name="query.eq_rmsid" id="toChooseRmsid" cssStyle="width:250px;"></s:select>
					</td>
					</tr>
					<tr>
						<td>
							<label>一级流程名称：</label>
							<s:select list='#{"":"请选择"}' case="case" name="query.eq_rmcupid" id="toChooseFirstCircuit" cssStyle="width:250px;"></s:select>
						</td>
						<td>
							<label>二级流程名称：</label>
							<s:select list='#{"":"请选择"}' case="case" name="query.eq_rmcid" id="toChooseSecondCircuit" cssStyle="width:250px;"></s:select>
						</td>
					</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" id="subBtn">查看报表</button>
							</div>
						</div>
					</li>					
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" name="reset">重置</button>
							</div>
						</div>
					</li>
				</ul>
			</div>		
		</div>
		</form>	
		
	
	
	<div class="panelBar" style="height:5px"></div>
		
		</div>
		
	</div>
</div>



<script type="text/javascript">
<!--


$("#secRmRiskSourceReportJsp").find("#subBtn").click(function(){
	var gtdate = $("#secRmRiskSourceReportJsp").find("input[name=query.dtgteq_ddate]").val();
	var ltdate = $("#secRmRiskSourceReportJsp").find("input[name=query.dtlteq_ddate]").val();
	if(gtdate == ""){
		alertMsg.warn("请填写航班运行开始日期");
		return false;
	}
	if(ltdate == ""){
		alertMsg.warn("请填写航班运行结束日期");
		return false;
	}
	if(ltdate < gtdate){
		alertMsg.warn("航班运行结束日期不能小于开始日期");
		return false;
	}
	
	var param = "";
	var query = "";
	$("#secRmRiskSourceReportJsp").find(":input[case=case]").each(function(){
		query += $(this).attr("name") + "=" +$(this).val() + "&";
		
	});
	
	$.pdialog.open("risk-manage/sec-rm-risk-source-report!disposeUrl.do?"+query, 
			"reportShow", 
			"报表显示", 
			{width:1000,height:570,mask:true},param);
	
});


//-->
</script>


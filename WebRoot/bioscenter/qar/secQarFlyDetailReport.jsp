<!-- QAR�����¼�ͳ�Ʒ���  	 -->


<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="bioscenter/js/bios.js"></script>


<div class="page" id="secQarFlyDetailReportJsp">
	<div class="pageHeader">
	<div class="pageContent" layoutH="0" >
	<div class="searchBar">
		<a id="chooseCaseBtn" style="display:none" mask="true" width="500" height="500"
		   target="dialog"  rel="chooseCaseJsp" ><span>ѡ������</span></a>
		   
		<a id="reportBtn" style="display:none" mask="true" width="1000" height="570"
		   target="dialog"  rel="reportJsp" ><span>������ʾ</span></a>
			<table class="searchContent" style="width:100%">		
				<tr>					
					<td>����������</td>
					<td><input name="query.dtgteq_ddate" case="case" value="<s:property value="query.dtgteq_ddate"/>" 
					class="date" style="float:left" onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					<td>��</td>
					<td><input name="query.dtlteq_ddate" case="case" value="<s:property value="query.dtlteq_ddate"/>" 
					class="date" style="float:left" onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					
					<td>����</td>
					<td>
						<input name="query.in_qwDutyDept" type="hidden" case="case" />
						<input name="queryName.c" flag="qwDutyDept" case="name" style="float:left" onkeydown="return false;">
						<button flag="qwDutyDept" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')"
						 href="javascript:void(0)">...</button>						
					</td>
				</tr>
				<tr>
					<td>����</td>
					<td>
						<input name="query.in_qwEatrId" type="hidden" case="case" />
						<input name="queryName.d" flag="qwEatrId" case="name" style="float:left" onkeydown="return false;">
						<button flag="qwEatrId" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')" 
						 href="javascript:void(0)">...</button>							
					</td>
				
					<td>�ɻ���</td>
					<td>
						<input name="query.in_crftid" type="hidden" case="case" />
						<input name="queryName.e" flag="crftid" case="name" style="float:left" onkeydown="return false;">
						<button flag="crftid"  onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')"
						 href="javascript:void(0)">...</button>						
					</td>
					<td>���н׶�</td>
					<td>
						<input name="query.in_qwEpath" type="hidden" case="case" />
						<input name="queryName.f"  flag="qwEpath" case="name" style="float:left" onkeydown="return false;">
						<button flag="qwEpath" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')"  
						href="javascript:void(0)">...</button>					
					</td>
				</tr>
				<tr>
					<td>�Ƿ�ҹ��</td>
					<td>
						��<input type="radio" name="query.eq_qwEnight" value="1">
						��<input type="radio" name="query.eq_qwEnight" value="0">
					</td>	
					<td>������Ŀ</td>
					<td>
						<input name="query.in_qarid" type="hidden" case="case" />
						<input name="queryName.h" flag="qarid" case="name" style="float:left" onkeydown="return false;">
						<button flag="qarid" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>			
					
					<td>����</td>
					<td>
						<input name="query.in_threecord" type="hidden" case="case" />
						<input name="queryName.i" flag="threecord" case="name" style="float:left" onkeydown="return false;">
						<button flag="threecord" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>				
				</tr>
				<tr>
					<td>����</td>
					<td>
						<input name="query.in_qwEweather" type="hidden" case="case" />
						<input name="queryName.j"  flag="qwEweather" case="name" style="float:left" onkeydown="return false;">
						<button flag="qwEweather" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>
					
				
					<td>����</td>
					<td>
						<input name="query.in_qwEfroad" type="hidden" case="case" />
						<input name="queryName.k" flag="qwEfroad" case="name" style="float:left" onkeydown="return false;">
						<button flag="qwEfroad" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')" 
						 href="javascript:void(0)">...</button>						
					</td>
					<td>������ʽ</td>
					<td>
						<input name="query.in_qwEway" type="hidden" case="case" />
						<input name="queryName.l" flag="qwEway" case="name" style="float:left" onkeydown="return false;">
						<button flag="qwEway" onclick="chooseCase($(this).attr('flag'),'secQarFlyDetailReportJsp')" 
						 href="javascript:void(0)">...</button>					
					</td>
				</tr>
				<tr>
					<td>������������׼</td>
					<td colspan="5">
					<s:iterator value="tfQualPilotTechgradeList" var="tfQualPilotTechgrade" >
						<input type="checkbox" name="query.in_qwEqfTestd"  value="<s:property value="ptGradeId"/>"><s:property value="ptGradeDesc"/>
					</s:iterator>					
					</td>					
				</tr>
				
			</table>
		</div>
		
		<div class="panelBar" style="height:50px">	
		<table class="toolBar" >
			<tr>
				<td><a class="add" url="secQarFlyDetailReport/qar_fly_da_detail_jichang.brt" onclick="returnUrl($(this).attr('url'),'qiancilvDetail','threecord','secQarFlyDetailReportJsp')"><span>����</span></a></td>
				<td><a class="add" url="secQarFlyDetailReport/qar_fly_da_detail_shijian.brt" onclick="returnUrl($(this).attr('url'),'qiancilvDetail','qarid','secQarFlyDetailReportJsp')"><span>�¼�</span></a></td>
				<td><a class="add" url="secQarFlyDetailReport/qar_fly_da_detail_jidui.brt" onclick="returnUrl($(this).attr('url'),'qiancilvDetail','qwDutyDept','secQarFlyDetailReportJsp')"><span>���ӣ��ɻ����ڵ�λ��</span></a></td>
				<td><a class="add" url="secQarFlyDetailReport/qar_fly_da_detail_jixing.brt" onclick="returnUrl($(this).attr('url'),'qiancilvDetail','qwEatrId','secQarFlyDetailReportJsp')"><span>����</span></a></td>
			</tr>
			<tr>			
				<td><a class="add" url="" onclick="returnUrl($(this).attr('url'),'monthNum','','secQarFlyDetailReportJsp')"><span>�³�����Ŀ�ֲ�</span></a></td>			
				<td colspan="3"><a class="add" url="" onclick="returnUrl($(this).attr('url'),'monthNumSign','','secQarFlyDetailReportJsp')"><span>�����¼��³�����Ŀ�ֲ�</span></a></td>
			</tr>
		</table>		
	</div>
	</div>	
	</div>
</div>


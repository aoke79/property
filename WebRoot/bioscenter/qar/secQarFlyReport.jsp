<!-- QARͳ�Ʒ��� 	 
���� AC_TYPE
�¼��̶� ALERTCHAR
����ARN
��½����ARR
�������� DDATE
��ɻ���  DEP
�¼���   EVENT_NO
��λ����ORGID
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
		   target="dialog"  rel="chooseCaseJsp" ><span>ѡ������</span></a>
		   
		<a id="reportBtn" style="display:none" mask="true" width="1000" height="570"
		   target="dialog"  rel="reportJsp" ><span>������ʾ</span></a>
		<input type="hidden" name="param" value=""/>
		
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
						<input name="query.in_crftoneuseid" type="hidden" case="case" />
						<input name="queryName.c" case="name" flag="crftoneuseid" style="float:left" onkeydown="return false;">
						<button flag="crftoneuseid" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
				</tr>
				<tr>
					<td>����</td>
					<td>
						<input name="query.in_acType" type="hidden" case="case" />
						<input name="queryName.d" flag="acType" case="name" style="float:left" onkeydown="return false;">
						<button flag="acType" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
							
					</td>
				
					<td>�ɻ���</td>
					<td>
						<input name="query.in_arn" type="hidden" case="case" />
						<input name="queryName.e" flag="arn" case="name" style="float:left" onkeydown="return false;">
						<button flag="arn"onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
					<td>���н׶�</td>
					<td>
						<input name="query.in_phase" type="hidden" case="case" />
						<input name="queryName.f" flag="phase" case="name" style="float:left" onkeydown="return false;">
						<button flag="phase" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')"  
						href="javascript:void(0)">...</button>					
					</td>
				</tr>
				
				<tr>					
					<td>������Ŀ</td>
					<td>
						<input name="query.in_eventNo" type="hidden" case="case" />
						<input name="queryName.h" flag="eventNo" case="name" style="float:left" onkeydown="return false;">
						<button flag="eventNo" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>	
					<td>��ɻ���</td>
					<td>
						<input name="query.in_dep" type="hidden" case="case" />
						<input name="queryName.o" flag="dep" case="name" style="float:left" onkeydown="return false;">
						<button flag="dep" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>		
					
					<td>��ػ���</td>
					<td>
						<input name="query.in_arr" type="hidden" case="case" />
						<input name="queryName.p" flag="arr" case="name" style="float:left" onkeydown="return false;">
						<button flag="arr" onclick="chooseCase($(this).attr('flag'),'secQarFlyReportJsp')" 
						 href="javascript:void(0)">...</button>
					</td>				
				</tr>
				<tr>
					<td>���޳̶�</td>
					<td colspan="5">
						<s:select list="#{'':'','L':'��ȳ���','H':'���س���' }" case='case' name="query.eq_alertchar" value='<s:property value="query.eq_alertchar"/>'/>
					</td>				
				</tr>
			</table>			
		</div>
		
		<div class="panelBar" style="height:50px">	
		<table class="toolBar" >
			<tr>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_dep_jichang.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','dep','secQarFlyReportJsp')"><span>��ɻ���</span></a></td>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_arr_jichang.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','arr','secQarFlyReportJsp')"><span>��ػ���</span></a></td>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_shijian.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','event_no','secQarFlyReportJsp')"><span>�¼�</span></a></td>
				<td><a class="add" url="secQarFlyReport/qar_fly_da_jidui.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','crftoneuseid','secQarFlyReportJsp')"><span>���ӣ��ɻ����ڵ�λ��</span></a></td>
			</tr>
			<tr>
			<td><a class="add" url="secQarFlyReport/qar_fly_da_jixing.brt" onclick="returnUrl($(this).attr('url'),'qiancilv','ac_type','secQarFlyReportJsp')"><span>����</span></a></td>
				<td><a class="add" url="" onclick="returnUrl($(this).attr('url'),'overrun','','secQarFlyReportJsp')"><span>����������ͼ</span></a></td>
				<td colspan="2"><a class="add" url="" onclick="returnUrl($(this).attr('url'),'overrunSingle','','secQarFlyReportJsp')"><span>�����¼�����������ͼ</span></a></td>
				
			</tr>
		</table>		
	</div>
	</div>
	
		
	</div>
</div>


<!-- QAR�����¼�ͳ�Ʒ���  	 -->


<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="bioscenter/js/bios.js"></script>
<script type="text/javascript">
<!--
	
//-->
</script>

<div class="page" id="earlyWarnlingJsp">
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
					class="date" style="float:left"  onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					<td>��</td>
					<td><input name="query.dtlteq_ddate" case="case" value="<s:property value="query.dtlteq_ddate"/>" 
					class="date" style="float:left" onkeydown="return false;"/><a class="inputDateButton" href="javascript:void(0)"></a></td>
					
					<td>����</td>
					<td>
						<input name="query.in_crftoneuseid" type="hidden" case="case" />
						<input name="crftoneuseid" flag="crftoneuseid" style="float:left" onkeydown="return false;">
						<button flag="crftoneuseid" onclick="chooseCase($(this).attr('flag'),'earlyWarnlingJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
				</tr>
				<tr>
					<td>����</td>
					<td>
						<input name="query.in_acType" type="hidden" case="case" />
						<input name="acType" flag="acType" style="float:left" onkeydown="return false;">
						<button flag="acType" onclick="chooseCase($(this).attr('flag'),'earlyWarnlingJsp')" 
						 href="javascript:void(0)">...</button>
							
					</td>
				
					<td>�ɻ���</td>
					<td>
						<input name="query.in_arn" type="hidden" case="case" />
						<input name="arn" flag="arn" style="float:left" onkeydown="return false;">
						<button flag="arn"  onclick="chooseCase($(this).attr('flag'),'earlyWarnlingJsp')"
						 href="javascript:void(0)">...</button>
						
					</td>
					<td>���н׶�</td>
					<td>
						<input name="query.in_phase" type="hidden" case="case" />
						<input name="phase" flag="phase" style="float:left" onkeydown="return false;">
						<button flag="phase" onclick="chooseCase($(this).attr('flag'),'earlyWarnlingJsp')"  
						href="javascript:void(0)">...</button>					
					</td>
				</tr>				
				<tr>
					<td>��ɻ���</td>
					<td>
						<input name="query.in_dep" type="hidden" case="case" />
						<input name="dep" flag="dep" style="float:left" onkeydown="return false;">
						<button flag="dep" onclick="chooseCase($(this).attr('flag'),'earlyWarnlingJsp')" 
						 href="javascript:void(0)">...</button>
					</td>		
					
					<td>��ػ���</td>
					<td>
						<input name="query.in_arr" type="hidden" case="case" />
						<input name="arr" flag="arr" style="float:left" onkeydown="return false;">
						<button flag="arr" onclick="chooseCase($(this).attr('flag'),'earlyWarnlingJsp')" 
						 href="javascript:void(0)">...</button>
					</td>	
					<td>������Ŀ</td>
					<td>
						<input name="query.in_eventNo" type="hidden" case="case" />
						<input name="eventNo" flag="eventNo" style="float:left" onkeydown="return false;">
						<button flag="eventNo" onclick="chooseCase($(this).attr('flag'),'earlyWarnlingJsp')" 
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
		
		<div class="panelBar">	
		<table class="toolBar" >
			<tr>
				<td colspan="4"><a class="add" url="earlyWarnling/early_warnling.brt" onclick="returnUrl($(this).attr('url'),'','','earlyWarnlingJsp')" ><span>�¼�</span></a></td>
			</tr>
		</table>		
	</div>
	</div>	
	</div>
</div>


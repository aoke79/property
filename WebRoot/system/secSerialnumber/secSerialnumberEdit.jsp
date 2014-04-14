<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年5月
 	 * 最后更新时间：
 	 * 程序用途：修改序列号
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="page">
   <div class="pageContent">
      <form method="post" action="sec-serialnumber/sec-serialnumber!editSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
         <input type="hidden" name="secSerialnumber.snid" value="${secSerialnumber.snid}">
	     <div class="pageFormContent" layoutH="56">
         <p>
		 	<label>单位标识:</label>
		 	<input name="secSerialnumber.sdeptid" type="text" size="30" value="${secSerialnumber.sdeptid }"/>
	     </p>
	     <p>
		 	<label>SESPECIDLID:</label>
		 	<input name="secSerialnumber.sespecidlid" type="text" size="30" value="${secSerialnumber.sespecidlid }"/>
	     </p>
	     <p>
		 	<label>SDATEFORMAT:</label>
		 	<input name="secSerialnumber.sdateformat" type="text" size="30" value="${secSerialnumber.sdateformat }"/>
	     </p>
	     <p>
		 	<label>序号:</label>
		 	<input name="secSerialnumber.serial" type="text" size="30" value="${secSerialnumber.serial }"/>
	     </p>
	     <p>
		 	<label>序号的位数:</label>
		 	<input name="secSerialnumber.serialnum" type="text" size="30" value="${secSerialnumber.serialnum }"/>
	     </p>
	     <p>
		 	<label>SERSOURCE:</label>
		 	<input name="secSerialnumber.sersource" type="text" size="30" value="${secSerialnumber.sersource}"/>
	     </p>
		 </div>
		 <div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('save')"/></button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="navTab.closeCurrentTab()"><s:property value="getText('cancel')"/></button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset"><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>

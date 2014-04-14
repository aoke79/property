<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="page">
   <div class="pageContent">
      <form method="post" action="sec-bphase/sec-bphase!addSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	     <div class="pageFormContent" layoutH="56">
         <p>
		    <label><s:property value="getText('description')"/>:</label>
			<input align="left" name="secBphase.phdesc" type="text" size="30" value="" />
		 </p>
		 </div>
		 <div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:property value="getText('save')"/></button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent">
							<button type="Button" onclick='alertMsg.confirm("<s:property value='getText("addLeaveTips")' />",{okCall:function(){navTab.closeCurrentTab();}})'>
									<s:property value="getText('cancel')"/>
							</button>
						</div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button type="reset"><s:property value="getText('reset')"/></button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>
</div>

<% 
	/**
 	 * 作者：胡星
 	 * 创建时间：2011年3月
 	 * 最后更新时间：
 	 * 程序用途：增加操作
 	 * 更新记录：
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		pageSize = "${numPerPage}";
		orderBy = "${orderBlock}";
		orderCol = "${orderMethod}";
	});
	

		$("#knowchooseSystemClick").click(function(){
		
				$("#knowchooseSystem").attr("href",
											"sys-operate/sys-operate!menutSystemTreeList.do");
				$("#knowchooseSystem").click();
		
			
		})
		
		
				$("#knowchooseSystemClick2").click(function(){
		
				$("#knowchooseSystem2").attr("href",
											"sys-operate/sys-operate!palSystemTreeList.do");
				$("#knowchooseSystem2").click();
		
			
		})


</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="sys-operate/sys-operate!addSave.do"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 
				<p>
					<label>
						系统编号:
					</label>
					<input name="sysOperate.systemId" type="hidden" size="30"value="" />
				</p>
				-->
				<p>
					<label>
						<s:property value="getText('operName')"/>
						<!--  操作名称:-->
					</label>
					<input align="left" name="sysOperate.operateName" class="required" type="text" size="30" value="" alt="<s:property value="getText('')"/>" />
				</p>
				<p>
					<label>
						<s:property value="getText('urlAction')"/>
						<!--  触发的URL串:-->
					</label>
					<input name="sysOperate.operateAction" type="text" size="30" value="" />
				</p>
				<p>
					<label>
						<s:property value="getText('navigationInfo')"/>
						<!--  导航信息:-->
					</label>
					<input name="sysOperate.menuInfo" type="text" size="30" value="" />
				</p>
				<p>
					<label>
						<s:property value="getText('ifBaseMenu')"/>
						<!-- 是否为底层菜单: -->
					</label>
					<!-- <input name="sysOperate.ifFinallyMenu" type="hidden" size="30" 
						value="1" class="required"/>-->
					<INPUT TYPE="RADIO" NAME="sysOperate.ifFinallyMenu" VALUE="1"><!-- 是 --><s:property value="getText('yes')"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="sysOperate.ifFinallyMenu" value="0" checked="checked"/><!-- 否 --><s:property value="getText('no')"/>
				</p><%-- 
				<p>
					<label>
						<s:property value="getText('parentOperate')"/>
						 操作父编号: 
					</label>
					 <input name="sysOperate.parentId" type="hidden" size="30" value="-1" class="required"/> 
					<select id="bbb" name="sysOperate.parentOperate.id" class="required" style="width:183px">
						<option value="-1" selected="selected">
							<s:property value="getText('operateParentCode')"/>
							 请选择操作父编号 
						</option>
						<c:forEach items="${operateList}" var="parentOperate">
							<option value="<c:out value="${parentOperate.id}" />">
								<c:out value="${parentOperate.operateName}" />
							</option>
						</c:forEach>
					</select>
				</p>
				
								--%>

			
				
								<p>
					<label> 操作父编号: </label>
					<input name="sysOperate.parentOperate.id" type="hidden" size="30" value="-1" class="required"/> 
					<input name="systemType2"  readonly="readonly" />
					<a class="btnLook" id="knowchooseSystemClick2" 
					 title=" 请选择操作父编号"></a>
					<a id="knowchooseSystem2" 
					rel="knowchooseSystemDialog" target="dialog" style="display:none"
					width="500" height="600" title="选择操作父编号"></a>
				</p>
<p>
					<label> 菜单名称：</label>
					<input name="sysOperate.sysMenu.id" type="hidden" />
					<input name="systemType"  readonly="readonly" class="required"/>
					<a class="btnLook" id="knowchooseSystemClick" 
					 title="请选择菜单"></a>
					<a id="knowchooseSystem" 
					rel="knowchooseSystemDialog" target="dialog" style="display:none"
					width="500" height="600" title="选择菜单"></a>
				</p>
				<!--
				<p>
					<label>
						<s:property value="getText('menuName')"/>
						 菜单名称: 
					</label>
					<select id="bbb" name="sysOperate.sysMenu.id" class="required" style="width:183px">
						<option>
							<s:property value="getText('selectMenu')"/>
							  请选择菜单
						</option>
						<c:forEach items="${menuList}" var="sysMenu">
							<option value="<c:out value="${sysMenu.id}" />">
								<c:out value="${sysMenu.name}" />
							</option>
						</c:forEach>
					</select>
				</p>
				--><!-- 
				<p>
					<label>
						等级:
					</label>
					<input name="sysOperate.opeLevel" type="hidden" size="30" value="" />
				</p>
				-->
				<p>
					<label>
						<s:property value="getText('validity')"/>
						<!--  有效性:-->
					</label>
					<!-- 
					<input name="sysOperate.flag" type="text" size="30" value="" />
					 -->	
						<INPUT TYPE="RADIO" NAME="sysOperate.flag" VALUE="1" checked='checked'><!--  有效--><s:property value="getText('valid')"/>
						<INPUT TYPE="RADIO" NAME="sysOperate.flag" VALUE="-1"><!-- 无效 --><s:property value="getText('invalid')"/>
				</p>

				<p>
					<label>
						<s:property value="getText('description')"/>
						<!--  描述:-->
					</label>
					<textarea name="sysOperate.description"  cols="23" rows="3"></textarea>
				</p>
           <p>
         <label><s:property value="getText('subsystem')"/></label>
  <s:select list="#attr.sysSystemList" name="sysOperate.subsystemId" listKey="id" listValue="name"/>
         </p>
			</div>

			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									<s:property value="getText('save')"/>
									<!--  保存-->
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" onclick="navTab.closeCurrentTab()">
									<s:property value="getText('cancel')"/>
									<!-- 取消 -->
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="reset">
									<s:property value="getText('reset')"/>
									<!-- 重置 -->
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery.contextmenu.r2.packed.js"></script>
<script type="text/javascript">
<!--
	var treeName=$("#chooseSystemTreeJsp").find("#treeJsp${chooseName}").val();
	$("#chooseSystemTreeJsp").find("a[signIds=systemId]").each(function(){
		$(this).click(function(){
			$("input[name=systemType2]").val($(this).html()).prev("input").val($(this).attr("value"));
		//	$("#"+treeName).find("input[name=knowledge.simulatorSystem.systemId]").val($(this).attr("value"));
			$("#chooseSystemTreeJsp").find("#closeBtn${chooseName}").click();
		})
	})
//-->
</script>
<div class="page" id="chooseSystemTreeJsp">
	<div layoutH="100" class="pageHeader">
<input id="treeJsp${chooseName}" type="hidden" value="${chooseName}"/>
<ul class="tree treeFolder collapse expand" id="orgTree">
	<s:iterator value='systemList' var="first">
		<li><a signIds="systemId" value="-1">${name}(-1)</a>		
			<s:iterator value="operateList.{?#this.subsystemId == #first.id && #this.parentOperate.id=='-1'} " var="second">			
		<ul>
			<li><a signIds="systemId" value="${id}">${operateName}</a>
				<s:iterator value="operateList.{?#this.parentOperate != null && #this.parentOperate.id  == #second.id} " var="third">
					<ul>
						<li><a signIds="systemId" value="${id}">${operateName}</a>
							<s:iterator value="operateList.{?#this.parentOperate != null && #this.parentOperate.id  == #third.id} " var="fourth">
								<ul>
									<li><a signIds="systemId" value="${id}">${operateName}</a>
										<s:iterator value="operateList.{?#this.parentOperate != null && #this.parentOperate.id  == #fourth.id} " var="five">
										<ul><li><a signIds="systemId" value="${id}">${operateName}</a></ul>
										</s:iterator>
									</li>
								</ul>
							</s:iterator>
						</li>
					</ul>
				</s:iterator>
			</li>
		</ul>
		</s:iterator>
	</li>
	</s:iterator>
</ul>
<div style="display:none"><button class="close" id="closeBtn${chooseName}"  value="关闭"></button></div>
</div></div>


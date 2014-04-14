<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery.contextmenu.r2.packed.js"></script>
<script type="text/javascript">
$(function(){ 
	  var RROIDs=$("#hiddenRROIDs").val();
	  //初始化树结构，把属于当前角色的操作权限选中
   	    $("a[flag=sinoframe]").filter(function(index) {
         return RROIDs.indexOf($(this).attr("tid"))!=-1;
        }).attr("checked","true");
	   // 单击清空功能的时候
	   $("#clear").click(function(){
		   		$("input[name=ids]").each(function(){
		  			if($(this).attr("checked")==true){
		   				$(this).trigger("click");
		  			}
		   		})
	   })
	  // 当单击取消按钮的时候
	   $("#cancel").click(function(){
		   $.pdialog.closeCurrent();
	   })
	   // 当点击确定按钮的时候
	   // 遍历选中的操作，返回后台处理
	   $("button[name=subUser]").click(function(){
	   		var ids="";
	   		$("input[name=ids]").each(function(){
	   		  	if($(this).attr("checked")==true){
	   		  		ids+=$(this).attr("id")+",";
	   		  	}
	   		})
	   		ids=ids.substring(0,ids.length-1);
	   		// 把 ids 放入表单下得input标签里
	   		$("#TempRROIDs").val(ids);
	   		$("#dialogPagerForm").attr("action","sys-relation-role-operate/sys-relation-role-operate!doFp.do?roleId=" + $("input[name=roleId]").val());
	   		validateCallback($("#dialogPagerForm"), navTabAjaxDone);
	   		$.pdialog.closeCurrent();
	   })
    })
</script>
<style>
li{
	text-align: left;
}
</style>
<div class="page">
<form id="dialogPagerForm" class="pageForm required-validate" name="RROForm" onsubmit="validateCallback(this,navTabAjaxDone);" action="" method="post">
   <div class="pageContent" layoutH="56">
	<input type="hidden" id="TempRROIDs" name="RROIDs" />
	<input type="hidden" name="sysOrganizationId" value="${sysOrganization.id }"/>
	
	<%--<s:iterator value="baseOperate" var="operate">--%>

   <s:if test="#attr.userInfo!='超级管理员'">
	<s:iterator value="#application.initBaseOperate" var="operate">
		<ul class="tree treeFolder treeCheck collapse expand" id="orgTree">
                            <%--{?#this.subsystemId==#attr.userName} by lujie--%><%--
            <s:if test="#attr.userInfo=='超级管理员'">
            <s:iterator value="#operate.subOperate" var="subOperat">
            </s:if>
			--%><s:iterator value="#operate.subOperate.{?#this.subsystemId==#attr.userName}" var="subOperat">
				<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOperat.operateName}" tid="${subOperat.id}">${subOperat.operateName}</a>
					<s:iterator value="#subOperat.subOperate" var="subOpera">

						<ul>	
							<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOpera.operateName}" tid="${subOpera.id}">${subOpera.operateName}</a>
								<s:iterator value="#subOpera.subOperate" var="subOper">
									<ul>
										<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOper.operateName}" tid="${subOper.id}">${subOper.operateName}</a>
											<s:iterator value="#subOper.subOperate" var="subOpe">
												<ul>	
													<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOpe.operateName}" tid="${subOpe.id}">${subOpe.operateName}</a>
														<s:iterator value="#subOpe.subOperate" var="subOp">
															<ul>
																<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOp.operateName}" tid="${subOp.id}">${subOp.operateName}</a>
																	<s:iterator value="#subOp.subOperate" var="subO">
																		<ul>
																			<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subO.operateName}" tid="${subO.id}">${subO.operateName}</a>
																				
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
									</ul>
								</s:iterator>
							</li>
						</ul>
					</s:iterator>
				</li>
			</s:iterator>
		</ul> 
	</s:iterator>
</s:if><!-- end  -->



<!-- start -->
   <s:if test="#attr.userInfo=='超级管理员'">
	<s:iterator value="#application.initBaseOperate" var="operate">
		<ul class="tree treeFolder treeCheck collapse expand" id="orgTree">
                            <%--{?#this.subsystemId==#attr.userName} by lujie--%><%--
            <s:if test="#attr.userInfo=='超级管理员'">
            <s:iterator value="#operate.subOperate" var="subOperat">
            </s:if>
			--%><s:iterator value="#operate.subOperate" var="subOperat">
				<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOperat.operateName}" tid="${subOperat.id}">${subOperat.operateName}</a>
					<s:iterator value="#subOperat.subOperate" var="subOpera">
						<ul>	
							<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOpera.operateName}" tid="${subOpera.id}">${subOpera.operateName}</a>
								<s:iterator value="#subOpera.subOperate" var="subOper">
									<ul>
										<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOper.operateName}" tid="${subOper.id}">${subOper.operateName}</a>
											<s:iterator value="#subOper.subOperate" var="subOpe">
												<ul>	
													<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOpe.operateName}" tid="${subOpe.id}">${subOpe.operateName}</a>
														<s:iterator value="#subOpe.subOperate" var="subOp">
															<ul>
																<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subOp.operateName}" tid="${subOp.id}">${subOp.operateName}</a>
																	<s:iterator value="#subOp.subOperate" var="subO">
																		<ul>
																			<li><a flag="sinoframe" ttype="${orgType }" tname="ids" tvalue="${subO.operateName}" tid="${subO.id}">${subO.operateName}</a>
																				
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
									</ul>
								</s:iterator>
							</li>
						</ul>
					</s:iterator>
				</li>
			</s:iterator>
		</ul> 
	</s:iterator>
</s:if><!-- end  -->
</div>	 

			<div class="formBar">
				<ul>
					<li>					
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" name="subUser" ><s:property value="getText('OK')"/></button>
						</div>
					</div>					
					</li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" id="cancel"><s:property value="getText('cancel')"/></button></div></div>
					</li>
					<li><div class="button"><div class="buttonContent"><button id="clear" type="reset"><s:property value="getText('clear')"/></button></div></div></li>
				</ul>
			</div>
	  </form>
   </div>